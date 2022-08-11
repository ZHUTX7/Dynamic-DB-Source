package com.ztx.dynamicdbsource.controller;


import com.ztx.dynamicdbsource.config.DynamicDataSource;
import com.ztx.dynamicdbsource.config.ThreadLocalDataSource;
import com.ztx.dynamicdbsource.entity.UserInfo;
import com.ztx.dynamicdbsource.service.UserInfoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Resource
    DynamicDataSource dynamicDataSource;
    @Resource
    private UserInfoService userInfoService ;
    @RequestMapping("/saveData")
    public String saveData (){
        UserInfo userInfo = new UserInfo () ;
        userInfo.setId(4);
        userInfo.setUserName("winter");
        userInfo.setPassWord("567");
        userInfo.setPhone("13977776789");
        userInfo.setEmail("winter");
        userInfo.setCreateDay("2020-02-20");
        userInfoService.saveData(userInfo);
        return "sus";
    }
    @RequestMapping("/selectById")
    public UserInfo selectById () {
        return userInfoService.selectById(1) ;
    }

    @RequestMapping(value = "/selectList/{type}",method = RequestMethod.GET)
    public List<UserInfo> selectList (@PathVariable int type) {


        if(type ==1){
            ThreadLocalDataSource.setDataSource(DynamicDataSource.CLICKHOUSE);
        }
        else {
            ThreadLocalDataSource.setDataSource(DynamicDataSource.POSTGRE);
        }
        dynamicDataSource.determineCurrentLookupKey();
        return userInfoService.selectList() ;
    }
}
