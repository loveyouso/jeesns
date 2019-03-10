package com.sm.jeesns.cms.controller;


import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.core.utils.JeesnsConfig;
import com.sm.jeesns.system.model.ActionLog;
import com.sm.jeesns.system.service.IActionLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 动态
 * Created by zchuanzhao on 2017/3/8.
 */
@Controller("frontActionController")
@RequestMapping("/action/")
public class ActionController extends BaseController {
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private JeesnsConfig jeesnsConfig;

    @RequestMapping("list")
    public String list(Model model){
        Page page = new Page(request);
        ResultModel<ActionLog> actionList = actionLogService.memberActionLog(page,0);
        model.addAttribute("model", actionList);
        return jeesnsConfig.getFrontTemplate() + "/action/list";
    }


}
