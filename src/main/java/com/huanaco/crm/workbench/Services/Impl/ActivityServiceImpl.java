package com.huanaco.crm.workbench.Services.Impl;

import com.huanaco.crm.Exceptions.DeleteActivityAndActivityRemarkErrorException;
import com.huanaco.crm.Exceptions.PageListErrorException;
import com.huanaco.crm.VO.CountAndActivityVO;
import com.huanaco.crm.utils.SqlSessionUtil;
import com.huanaco.crm.workbench.Services.ActivityService;
import com.huanaco.crm.workbench.dao.ActivityDao;
import com.huanaco.crm.workbench.dao.ActivityRemarkDao;
import com.huanaco.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;


public class ActivityServiceImpl implements ActivityService {
    ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);
    ActivityRemarkDao activityRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ActivityRemarkDao.class);
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

    @Override
    public boolean delete(String[] ids) throws DeleteActivityAndActivityRemarkErrorException {
        //System.out.println("进入到删除市场列表服务曾");
        //System.out.println(ids.length);
        boolean success = true;
        //删除市场活动列表之前,我们需要看一下市场活动列表有没有跟市场活动相关联的备注,并提前删除市场活动备注
        //查询需要删除的市场活动备注条数
        int count1=activityRemarkDao.getCountByActivityId(ids);
        if(count1==-1){
            throw new DeleteActivityAndActivityRemarkErrorException("查询市场活动备注条数失败");
        }
        //删除市场活动备注
        int count2 = activityRemarkDao.deleteByActivityId(ids);
        if(count2==-1){
            throw new DeleteActivityAndActivityRemarkErrorException("删除市场活动备注条数失败");
        }
        if(count1!=count2){
            throw new DeleteActivityAndActivityRemarkErrorException("查询市场活动备注条数和删除市场活动备注条数不一致");
        }
        //删除市场活动列表
        int count3 = activityDao.deleteById(ids);
        if(count3==-1){
            throw new DeleteActivityAndActivityRemarkErrorException("删除市场活动条数失败");
        }
        if(count3!=ids.length){
            throw new DeleteActivityAndActivityRemarkErrorException("删除市场活动条数跟需要删除条数不一致");
        }
        //返回结果
        return success;
    }
}
