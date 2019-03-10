package com.sm.jeesns.dao.system;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.system.model.ActionLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionLogDao extends IBaseDao<ActionLog> {

    List<ActionLog> listByPage(@Param("page") Page page, @Param("memberId") Integer memberId);

    List<ActionLog> memberActionLog(@Param("page") Page page, @Param("memberId") Integer memberId);
}
