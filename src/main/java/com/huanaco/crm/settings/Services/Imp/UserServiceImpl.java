package com.huanaco.crm.settings.Services.Imp;

import com.huanaco.crm.Exceptions.LoginErrorException;
import com.huanaco.crm.settings.Services.UserService;
import com.huanaco.crm.settings.dao.UserDao;
import com.huanaco.crm.settings.domain.User;
import com.huanaco.crm.utils.DateTimeUtil;
import com.huanaco.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserServiceImpl implements UserService {
    UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginErrorException {
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userDao.login(map);
        String nowTime = DateTimeUtil.getSysTime();
        System.out.println(ip);
        if(user==null){
            throw new LoginErrorException("账号和密码错误");
        }
        if(!user.getAllowIps().contains(ip)){
            throw new LoginErrorException("ip地址受限");
        }
        if(user.getExpireTime().compareTo(nowTime)<0){
            throw new LoginErrorException("登陆期限超时");
        }
        if(user.getLockState()=="0"){
            throw new LoginErrorException("账号状态已锁定");
        }
        System.out.println(111);
        return user;
    }

    @Override
    public List<User> getUserList() {
        System.out.println("进入到获取用户列表服务器");
        List<User> userList = userDao.getUserList();
        return userList;
    }
}
