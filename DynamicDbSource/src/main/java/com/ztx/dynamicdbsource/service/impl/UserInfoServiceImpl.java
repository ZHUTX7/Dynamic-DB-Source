package com.ztx.dynamicdbsource.service.impl;


import com.ztx.dynamicdbsource.entity.UserInfo;
import com.ztx.dynamicdbsource.mapper.UserInfoMapper;
import com.ztx.dynamicdbsource.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper ;

    @Override
    public void saveData(UserInfo userInfo) {
        userInfoMapper.saveData(userInfo);
    }

    @Override
    public UserInfo selectById(Integer id) {
        return userInfoMapper.selectById(id);
    }

    @Override
    public List<UserInfo> selectList() {
        return userInfoMapper.selectList();
    }
}
