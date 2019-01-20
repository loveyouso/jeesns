package com.sm.jeesns.system.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.system.model.ActionLog;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionLogService {

    ResultModel<ActionLog> listByPage(Page page, Integer memberId);

    ResultModel<ActionLog> memberActionLog(Page page, Integer memberId);

    ActionLog findById(Integer id);

    void save(String actionIp, Integer memberId, Integer actionId);

    void save(String actionIp, Integer memberId, Integer actionId, String remark);

    void save(String actionIp, Integer memberId, Integer actionId, String remark, Integer type, Integer foreignId);

}
