package com.huanaco.crm.workbench.web;

import com.huanaco.crm.settings.Services.Imp.UserServiceImpl;
import com.huanaco.crm.settings.Services.UserService;
import com.huanaco.crm.settings.domain.User;
import com.huanaco.crm.utils.*;
import com.huanaco.crm.workbench.Services.ActivityService;
import com.huanaco.crm.workbench.Services.Impl.ActivityServiceImpl;
import com.huanaco.crm.workbench.domain.Activity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if("/workbench/Activity/userList.do".equals(path)){
            getUserList(request,response);
        }else if("/workbench/Activity/save.do".equals(path)){
            save(request,response);
        }
    }

    private void save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到保存市场活动信息控制器");
        String  id = UUIDUtil.getUUID();
        String  owner = request.getParameter("owner");
        String  name = request.getParameter("name");
        String  startDate = request.getParameter("startDate");
        String  endDate = request.getParameter("endDate");
        String  cost = request.getParameter("cost");
        String  description = request.getParameter("description");
        String  createTime = DateTimeUtil.getSysTime();
        String  createBy = ((User)request.getSession().getAttribute("user")).getName();
        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setCreateBy(createBy);
        a.setCreateTime(createTime);
        a.setCost(cost);
        a.setDescription(description);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean success = as.save(a);
        PrintJson.printJsonFlag(response,success);

    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到获取用户列表控制器");
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> userList = us.getUserList();
        PrintJson.printJsonObj(response,userList);
    }
}
