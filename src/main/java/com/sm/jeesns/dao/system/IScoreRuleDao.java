package com.sm.jeesns.dao.system;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.model.system.ScoreRule;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
public interface IScoreRuleDao extends IBaseDao<ScoreRule> {

    int enabled(@Param("id") int id);
}
