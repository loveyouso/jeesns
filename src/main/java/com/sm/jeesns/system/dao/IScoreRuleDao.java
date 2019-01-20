package com.sm.jeesns.system.dao;

import com.sm.jeesns.common.dao.IBaseDao;
import com.sm.jeesns.system.model.ScoreRule;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
public interface IScoreRuleDao extends IBaseDao<ScoreRule> {

    int enabled(@Param("id") int id);
}
