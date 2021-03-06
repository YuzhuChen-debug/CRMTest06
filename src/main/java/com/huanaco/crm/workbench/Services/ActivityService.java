package com.huanaco.crm.workbench.Services;

import com.huanaco.crm.Exceptions.DeleteActivityAndActivityRemarkErrorException;
import com.huanaco.crm.Exceptions.GetUListAndActivityErrorException;
import com.huanaco.crm.Exceptions.PageListErrorException;
import com.huanaco.crm.VO.CountAndActivityVO;
import com.huanaco.crm.settings.domain.User;
import com.huanaco.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    boolean save(Activity activity);

    CountAndActivityVO pageList(Map<String, Object> map) throws PageListErrorException;

    boolean delete(String[] ids) throws DeleteActivityAndActivityRemarkErrorException;

    Map<String, Object> getActivityAndUList(String id) throws GetUListAndActivityErrorException;

    boolean updateActivity(Activity a);

    Activity getActivityDetialById(String id);
}
