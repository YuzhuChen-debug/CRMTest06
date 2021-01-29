package com.huanaco.crm.workbench.Services.Impl;

import com.huanaco.crm.utils.SqlSessionUtil;
import com.huanaco.crm.workbench.Services.ActivityService;
import com.huanaco.crm.workbench.dao.ActivityDao;
import com.huanaco.crm.workbench.domain.Activity;


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
}
