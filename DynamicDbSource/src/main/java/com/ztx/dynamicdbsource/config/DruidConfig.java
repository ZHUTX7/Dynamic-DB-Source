package com.ztx.dynamicdbsource.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @Resource
    private PgConfig pgConfig ;
    @Resource
    private ChConfig chConfig ;

    @Bean
    public DataSource chSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(chConfig.getUrl());
        datasource.setDriverClassName(chConfig.getDriverClassName());
        datasource.setUsername(chConfig.getUsername());
        datasource.setPassword(chConfig.getPassword());
        return datasource;
    }

    @Bean
    public DataSource pgSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(pgConfig.getUrl());
        datasource.setDriverClassName(pgConfig.getDriverClassName());
        datasource.setUsername(pgConfig.getUsername());
        datasource.setPassword(pgConfig.getPassword());
        return datasource;
    }

    @Bean
    public DynamicDataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 设置默认数据源
        dynamicDataSource.setDefaultTargetDataSource(chSource());

        // 设置全部的数据源
        Map map=new HashMap();
        map.put(DynamicDataSource.CLICKHOUSE,chSource());
        map.put(DynamicDataSource.POSTGRE,pgSource());
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }


    @Bean
    public SqlSessionTemplate sqlSessionTemplate(){
        SqlSessionTemplate sqlSessionTemplate=null;
        try {
            sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactoryBean().getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  sqlSessionTemplate;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        // 设置mapper.xml扫描
        // (一定不能在application.yml中配置，因为我们现在是用的自己的mybatis配置，application.yml中的配置是失效状态，要配置属性，只能在这个配置类中配置)
        sqlSessionFactoryBean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        // 设置返回值pojo的别名包
        sqlSessionFactoryBean.setTypeAliasesPackage("com.ztx.dynamicdbsource.entity");
        System.out.printf("创建新的sqlSession");
        return sqlSessionFactoryBean;
    }

}
