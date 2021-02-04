package com.huanaco.crm.workbench.Services.Impl;

import com.huanaco.crm.Exceptions.PageListErrorException;
import com.huanaco.crm.VO.CountAndActivityVO;
import com.huanaco.crm.utils.SqlSessionUtil;
import com.huanaco.crm.workbench.Services.ActivityService;
import com.huanaco.crm.workbench.dao.ActivityDao;
import com.huanaco.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;


public class ActivityServiceImpl implements ActivityService {
    ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    @Override
    public boolean save(Activity activity) {
        boolean flag = false;
        int count = activityDao.save(activity);
        if(count==1){
            flag = true;
        }
        return flag;
    }

    @Override
    public CountAndActivityVO pageList(Map<String, Object> map) throws PageListErrorException {
        System.out.println("进入到查询列表服务层");
        CountAndActivityVO caav = new CountAndActivityVO();
        int count = activityDao.getCount(map);
        if(count==-1){
            throw new PageListErrorException("获取总条数失败");
        }
        List<Activity> aList = activityDao.getActivityList(map);
        if(aList==null){
            throw new PageListErrorException("获取市场活动列表失败");
        }
        caav.setaList(aList);
        caav.setTotal(count);
        return caav;
    }
}
