package com.sm.jeesns.system.controller;

import com.sm.jeesns.cms.model.ArticleCate;
import com.sm.jeesns.cms.service.IArticleCateService;
import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.enums.Messages;
import com.sm.jeesns.core.exception.ParamException;
import com.sm.jeesns.core.interceptor.AdminLoginInterceptor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 16/9/29.
 */
@Controller
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class ArticleCateController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/cms/articleCate";
    @Resource
    private IArticleCateService articleCateService;

    @RequestMapping("${managePath}/cms/articleCate/list")
    public String list(Model model){
        List<ArticleCate> list = articleCateService.list();
        model.addAttribute("list",list);
        return MANAGE_FTL_PATH + "/list";
    }

    @RequestMapping("${managePath}/cms/articleCate/add")
    public String add(Model model){
        return MANAGE_FTL_PATH + "/add";
    }

    @RequestMapping("${managePath}/cms/articleCate/save")
    @ResponseBody
    public ResultModel save(ArticleCate articleCate){
        if(articleCate == null){
            throw new ParamException();
        }
        if(StringUtils.isEmpty(articleCate.getName())){
            throw new ParamException(Messages.NAME_NOT_EMPTY);
        }
        return new ResultModel(articleCateService.save(articleCate));
    }

    @RequestMapping("${managePath}/cms/articleCate/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        ArticleCate articleCate = articleCateService.findById(id);
        model.addAttribute("articleCate",articleCate);
        return MANAGE_FTL_PATH + "/edit";
    }

    @RequestMapping("${managePath}/cms/articleCate/update")
    @ResponseBody
    public ResultModel update(ArticleCate articleCate){
        if(articleCate == null){
            throw new ParamException();
        }
        if(StringUtils.isEmpty(articleCate.getName())){
            throw new ParamException(Messages.NAME_NOT_EMPTY);
        }
        return new ResultModel(articleCateService.update(articleCate));
    }


    @RequestMapping("${managePath}/cms/articleCate/delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") int id){
        return new ResultModel(articleCateService.delete(id));
    }
}
