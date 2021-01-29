package com.huanaco.crm.settings.web.controller;

import com.huanaco.crm.settings.Services.Imp.UserServiceImpl;
import com.huanaco.crm.settings.Services.UserService;
import com.huanaco.crm.settings.domain.User;
import com.huanaco.crm.utils.MD5Util;
import com.huanaco.crm.utils.PrintJson;
import com.huanaco.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            //获取地址
            String path = request.getServletPath();
            if("/settings/User/login.do".equals(path)){
                login(request,response);
            }else if("/settings/User/xxx.do".equals(path)){
                //xxx(request,response);
            }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到用户登录控制器");
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        loginPwd = MD5Util.getMD5(loginPwd);
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        try{
            User user = us.login(loginAct,loginPwd,ip);
            request.getSession().setAttribute("user",user);
            PrintJson.printJsonFlag(response,true);
            System.out.println(1);
        }catch(Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}
