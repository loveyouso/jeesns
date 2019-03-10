package com.sm.jeesns.system.controller;


import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.common.utils.ValidUtill;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.interceptor.AdminLoginInterceptor;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.system.model.Tag;
import com.sm.jeesns.system.service.ITagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/10/13.
 */
@Controller
@RequestMapping("/${managePath}/tag")
@Before(AdminLoginInterceptor.class)
public class TagController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/tag/";
    @Resource
    private ITagService tagService;

    @RequestMapping("/list/{funcType}")
    public String list(Model model, @PathVariable("funcType") Integer funcType){
        Page page = new Page(request);
        ResultModel resultModel = tagService.listByPage(page,funcType);
        model.addAttribute("funcType",funcType);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping("/add/{funcType}")
    public String add(Model model, @PathVariable("funcType") Integer funcType){
        model.addAttribute("funcType",funcType);
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultModel save(Tag tag){
        return new ResultModel(tagService.save(tag));
    }


    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){
        Tag tag = tagService.findById(id);
        model.addAttribute("tag",tag);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultModel update(Tag tag){
        ValidUtill.checkIsNull(tag);
        return new ResultModel(tagService.update(tag));
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id){
        return new ResultModel(tagService.delete(id));
    }

}
