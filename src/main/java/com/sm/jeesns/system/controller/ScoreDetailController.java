package com.sm.jeesns.system.controller;


import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.common.utils.MemberUtil;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.interceptor.UserLoginInterceptor;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.Member;
import com.sm.jeesns.member.model.ScoreDetail;
import com.sm.jeesns.member.service.IScoreDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/4/7.
 */
@Controller("scoreDetailFrontController")
@RequestMapping("/member/scoreDetail")
@Before(UserLoginInterceptor.class)
public class ScoreDetailController extends BaseController {
    private static final String INDEX_FTL_PATH = "/member/scoreDetail/";
    @Resource
    private IScoreDetailService scoreDetailService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        Member loginMember = MemberUtil.getLoginMember(request);
        Page page = new Page(request);
        ResultModel<ScoreDetail> resultModel = scoreDetailService.list(page,loginMember.getId());
        model.addAttribute("model", resultModel);
        return INDEX_FTL_PATH + "list";
    }
}
