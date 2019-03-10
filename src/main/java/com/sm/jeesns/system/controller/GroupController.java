package com.sm.jeesns.system.controller;


import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.common.utils.MemberUtil;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.interceptor.AdminLoginInterceptor;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.group.model.Group;
import com.sm.jeesns.group.service.IGroupService;
import com.sm.jeesns.member.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 16/12/23.
 */
@Controller("manageGroupController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class GroupController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/group/";
    @Resource
    private IGroupService groupService;

    @RequestMapping(value = "${managePath}/group/index")
    public String index(@RequestParam(value = "status",required = false,defaultValue = "-1") Integer status,
                        String key,
                        Model model) {
        Page page = new Page(request);
        List<Group> list = groupService.list(status,key);
        model.addAttribute("list",list);
        model.addAttribute("key",key);
        return MANAGE_FTL_PATH + "index";
    }

    @RequestMapping(value = "${managePath}/group/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel delete(@PathVariable("id") int id){
        Member loginMember = MemberUtil.getLoginMember(request);
        return new ResultModel(groupService.delete(loginMember,id));
    }

    @RequestMapping(value = "${managePath}/group/changeStatus/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel changeStatus(@PathVariable("id") int id){
        return new ResultModel(groupService.changeStatus(id));
    }



}
