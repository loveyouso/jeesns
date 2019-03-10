package com.sm.jeesns.member.controller;


import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.common.utils.MemberUtil;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.interceptor.UserLoginInterceptor;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.core.utils.JeesnsConfig;
import com.sm.jeesns.member.model.Checkin;
import com.sm.jeesns.member.model.Member;
import com.sm.jeesns.member.service.ICheckinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 签到
 * Created by zchuanzhao on 2018/8/20.
 */
@Controller
@RequestMapping("/checkin/")
public class CheckinController extends BaseController {
    @Resource
    private ICheckinService checkinService;
    @Resource
    private JeesnsConfig jeesnsConfig;

    @RequestMapping({"","index"})
    public String index(Model model){
        Page page = new Page<Checkin>(request);
        List<Checkin> list = checkinService.todayList(page);
        ResultModel resultModel = new ResultModel(0, page);
        resultModel.setData(list);
        model.addAttribute("model",resultModel);
        model.addAttribute("todayContinueList",checkinService.todayContinueList());
        return jeesnsConfig.getFrontTemplate() + "/checkin/index";
    }

    @RequestMapping("save")
    @ResponseBody
    @Before(UserLoginInterceptor.class)
    public ResultModel save(){
        Member member = MemberUtil.getLoginMember(request);
        return new ResultModel<>(checkinService.save(member.getId()));
    }
}
