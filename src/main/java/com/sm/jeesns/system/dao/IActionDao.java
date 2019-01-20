package com.sm.jeesns.system.dao;

import com.sm.jeesns.common.dao.IBaseDao;
import com.sm.jeesns.system.model.Action;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IActionDao extends IBaseDao<Action> {
    int isenable(@Param("id") Integer id);
}
