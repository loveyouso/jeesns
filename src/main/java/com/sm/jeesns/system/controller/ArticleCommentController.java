package com.sm.jeesns.system.controller;


import com.sm.jeesns.cms.model.ArticleComment;
import com.sm.jeesns.cms.service.IArticleCommentService;
import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.common.utils.MemberUtil;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.interceptor.AdminLoginInterceptor;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 18/8/15.
 */
@Controller("manageArticleCommentController")
@RequestMapping("${managePath}/cms/comment")
@Before(AdminLoginInterceptor.class)
public class ArticleCommentController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/cms/comment/";
    @Resource
    private IArticleCommentService articleCommentService;

    @RequestMapping("/list")
    public String list(String key, @RequestParam(value = "articleId",defaultValue = "0",required = false) Integer articleId, Model model) {
        Page page = new Page(request);
        List<ArticleComment> list = articleCommentService.listByPage(page, articleId, key);
        ResultModel resultModel = new ResultModel(0, page);
        resultModel.setData(list);
        model.addAttribute("model", resultModel);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "list";
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new ResultModel(articleCommentService.delete(loginMember,id));
    }

}
