package com.sm.jeesns.system.controller;


import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.common.model.Link;
import com.sm.jeesns.common.service.ILinkService;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.interceptor.AdminLoginInterceptor;
import com.sm.jeesns.core.model.Page;
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
@RequestMapping("/${managePath}/link")
@Before(AdminLoginInterceptor.class)
public class LinkController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/link/";
    @Resource
    private ILinkService linkService;

    @RequestMapping("/list")
    public String list(Model model){
        Page page = new Page(request);
        ResultModel resultModel = linkService.listByPage(page);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping("/add")
    public String add(){
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public ResultModel save(Link link){
        return new ResultModel(linkService.save(link));
    }


    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id){
        Link link = linkService.findById(id);
        model.addAttribute("link",link);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResultModel update(Link link){
        return new ResultModel(linkService.update(link));
    }

    @RequestMapping("/delete/{id}")
    @ResponseBody
    public ResultModel delete(@PathVariable("id") Integer id){
        return new ResultModel(linkService.delete(id));
    }

    @RequestMapping("/enable/{id}")
    @ResponseBody
    public ResultModel enable(@PathVariable("id") Integer id){
        return new ResultModel(linkService.enable(id));
    }


}