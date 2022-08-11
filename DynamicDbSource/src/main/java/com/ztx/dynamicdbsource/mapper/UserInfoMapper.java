package com.ztx.dynamicdbsource.mapper;

import com.ztx.dynamicdbsource.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoMapper {
    // 写入数据
    void saveData (UserInfo userInfo) ;
    // ID 查询
    UserInfo selectById (@Param("id") Integer id) ;
    // 查询全部
    List<UserInfo> selectList () ;

    @Select("Select * from cs_user_info ")
    List<UserInfo> selectList2 () ;
}
