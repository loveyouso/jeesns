package com.sm.jeesns.system.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.system.model.ScoreRule;

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
