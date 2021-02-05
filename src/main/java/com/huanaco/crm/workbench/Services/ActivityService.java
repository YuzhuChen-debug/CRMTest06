package com.huanaco.crm.workbench.Services;

import com.huanaco.crm.Exceptions.DeleteActivityAndActivityRemarkErrorException;
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
}
