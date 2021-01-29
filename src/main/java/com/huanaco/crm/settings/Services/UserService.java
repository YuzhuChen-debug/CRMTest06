package com.huanaco.crm.settings.Services;

import com.huanaco.crm.Exceptions.LoginErrorException;
import com.huanaco.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User login(String loginAct, String loginPwd, String ip) throws LoginErrorException;

    List<User> getUserList();
}
