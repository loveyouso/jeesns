package com.sm.jeesns.controller.manage;


import com.sm.jeesns.controller.common.BaseController;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.interceptor.AdminLoginInterceptor;
import com.sm.jeesns.model.system.ScoreRule;
import com.sm.jeesns.service.system.IScoreRuleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/3/24.
 */
@Controller
@RequestMapping("/${managePath}/system/scoreRule/")
@Before(AdminLoginInterceptor.class)
public class ScoreRuleController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/system/scoreRule/";
    @Resource
    private IScoreRuleService scoreRuleService;

    @RequestMapping("list")
    public String actionList(Model model){
        List<ScoreRule> list = scoreRuleService.list();
        model.addAttribute("list",list);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping("edit/{id}")
    public String find(@PathVariable("id") Integer id, Model model){
        ScoreRule scoreRule = scoreRuleService.findById(id);
        model.addAttribute("scoreRule",scoreRule);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    @ResponseBody
    public Object update(ScoreRule scoreRule){
        ResultModel resultModel = scoreRuleService.update(scoreRule);
        if(resultModel.getCode() == 0){
            resultModel.setCode(3);
        }
        return resultModel;
    }

    @RequestMapping(value = "enabled/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Object enabled(@PathVariable("id") Integer id){
        ResultModel resultModel = scoreRuleService.enabled(id);
        if(resultModel.getCode() == 0){
            resultModel.setCode(1);
        }
        return resultModel;
    }

}
