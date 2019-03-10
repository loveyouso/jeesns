package com.sm.jeesns.service.system;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.model.system.ScoreRule;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/14.
 */
public interface IScoreRuleService {

    List<ScoreRule> list();

    ScoreRule findById(Integer id);

    ResultModel update(ScoreRule scoreRule);

    ResultModel enabled(int id);

}
