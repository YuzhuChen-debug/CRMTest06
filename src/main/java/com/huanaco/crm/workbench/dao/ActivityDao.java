package com.huanaco.crm.workbench.dao;

import com.huanaco.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityDao {
    int save(Activity activity);

    int getCount(Map<String, Object> map);

    List<Activity> getActivityList(Map<String, Object> map);
}
