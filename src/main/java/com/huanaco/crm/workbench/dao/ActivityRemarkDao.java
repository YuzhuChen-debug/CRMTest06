package com.huanaco.crm.workbench.dao;

public interface ActivityRemarkDao {
    int getCountByActivityId(String[] ids);

    int deleteByActivityId(String[] ids);
}
