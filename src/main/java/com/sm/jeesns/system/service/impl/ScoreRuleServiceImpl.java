package com.sm.jeesns.system.service.impl;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.system.dao.IScoreRuleDao;
import com.sm.jeesns.system.model.ScoreRule;
import com.sm.jeesns.system.service.IScoreRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Service
public class ScoreRuleServiceImpl implements IScoreRuleService {
    @Resource
    private IScoreRuleDao scoreRuleDao;


    @Override
    public List<ScoreRule> list() {
        return scoreRuleDao.allList();
    }

    @Override
    public ScoreRule findById(Integer id) {
        return scoreRuleDao.findById(id);
    }

    @Override
    public ResultModel update(ScoreRule scoreRule) {
        if(scoreRuleDao.update(scoreRule) == 1){
            return new ResultModel(0, "操作成功");
        }
        return new ResultModel(-1, "操作失败");
    }

    @Override
    public ResultModel enabled(int id) {
        if(scoreRuleDao.enabled(id) == 1){
            return new ResultModel(0, "操作成功");
        }
        return new ResultModel(-1, "操作失败");
    }

}