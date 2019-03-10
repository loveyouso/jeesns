package com.sm.jeesns.dao.system;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.system.model.Action;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionDao extends IBaseDao<Action> {
    int isenable(@Param("id") Integer id);
}
