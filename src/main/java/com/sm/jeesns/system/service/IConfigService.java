package com.sm.jeesns.system.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.system.model.Config;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 16/9/29.
 */
public interface IConfigService {
    List<Config> allList();

    Map<String,String> getConfigToMap();

    String getValue(String key);

    ResultModel update(Map<String, String> params, HttpServletRequest request);
}
