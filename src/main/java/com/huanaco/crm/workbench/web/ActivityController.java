package com.huanaco.crm.workbench.web;

import com.huanaco.crm.VO.CountAndActivityVO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if("/workbench/Activity/userList.do".equals(path)){
            getUserList(request,response);
        }else if("/workbench/Activity/save.do".equals(path)){
            save(request,response);
        }else if("/workbench/Activity/pageList.do".equals(path)){
            pageList(request,response);
        }else if("/workbench/Activity/delete.do".equals(path)){
            delete(request,response);
        }else if("/workbench/Activity/getActivityAndUList.do".equals(path)){
            getActivityAndUList(request,response);
        }else if("/workbench/Activity/update.do".equals(path)){
            updateActivity(request,response);
        }else if("/workbench/activity/detail.do".equals(path)){
            getActivityDetail(request,response);
        }
    }

    private void getActivityDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到市场活动详情控制器");
        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        Activity activity = as.getActivityDetialById(id);
        request.setAttribute("a",activity);
        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request,response);
    }

    private void updateActivity(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到修改市场活动信息控制器");
        String  id = request.getParameter("id");
        String  owner = request.getParameter("owner");
        String  name = request.getParameter("name");
        String  startDate = request.getParameter("startDate");
        String  endDate = request.getParameter("endDate");
        String  cost = request.getParameter("cost");
        String  description = request.getParameter("description");
        String  editTime = DateTimeUtil.getSysTime();
        String  editBy = ((User)request.getSession().getAttribute("user")).getName();
        Activity a = new Activity();
        a.setId(id);
        a.setOwner(owner);
        a.setName(name);
        a.setStartDate(startDate);
        a.setEndDate(endDate);
        a.setEditBy(editBy);
        a.setEditTime(editTime);
        a.setCost(cost);
        a.setDescription(description);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        boolean success = as.updateActivity(a);
        PrintJson.printJsonFlag(response,success);


    }

    private void getActivityAndUList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到打开修改模块控制器");
        String id = request.getParameter("id");
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        try{
            Map<String,Object> map = as.getActivityAndUList(id);
            Map<String,Object> map1 = new HashMap<>();
            map1.put("success",true);
            map1.put("map",map);
            PrintJson.printJsonObj(response,map1);
        }catch (Exception e){
            String msg = e.getMessage();
            e.printStackTrace();
            Map<String,Object> map2 = new HashMap<>();
            map2.put("success",false);
            map2.put("msg",msg);
            PrintJson.printJsonObj(response,map2);

        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到删除市场活动列表控制器");
        String ids[] = request.getParameterValues("id");
        System.out.println(ids.length);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        try{
            boolean success = as.delete(ids);
            PrintJson.printJsonFlag(response,success);
        }catch(Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }

    private void pageList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到页面查询控制器 ");
        String owner = request.getParameter("owner");
        String name = request.getParameter("name");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.parseInt(pageNoStr);
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.parseInt(pageSizeStr);
        int pageCount = (pageNo-1)*pageSize;
        Map<String,Object> map = new HashMap<>();
        map.put("owner",owner);
        map.put("name",name);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("pageSize",pageSize);
        map.put("pageCount",pageCount);
        ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
        try{
            CountAndActivityVO caav = as.pageList(map);
            Map<String,Object> map1 = new HashMap<>();
            map1.put("success",true);
            map1.put("caav",caav);
            PrintJson.printJsonObj(response,map1);
        }catch(Exception e){
            e.printStackTrace();
            String msg = e.getMessage();
            Map<String,Object> map2 = new HashMap<>();
            map2.put("success",false);
            map2.put("msg",msg);
            PrintJson.printJsonObj(response,map2);
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
