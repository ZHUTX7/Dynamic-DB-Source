package com.ztx.dynamicdbsource.service;



import com.ztx.dynamicdbsource.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    // 写入数据
    void saveData (UserInfo userInfo) ;
    // ID 查询
    UserInfo selectById (Integer id) ;
    // 查询全部
    List<UserInfo> selectList () ;


}
