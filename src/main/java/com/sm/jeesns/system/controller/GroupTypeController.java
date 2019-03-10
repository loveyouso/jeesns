package com.sm.jeesns.system.controller;

import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.interceptor.AdminLoginInterceptor;
import com.sm.jeesns.group.model.GroupType;
import com.sm.jeesns.group.service.IGroupTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午1:17
 */
@Controller("manageGroupTypeController")
@RequestMapping("/")
@Before(AdminLoginInterceptor.class)
public class GroupTypeController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/group/type/";
    @Resource
    private IGroupTypeService groupTypeService;

    @RequestMapping(value = "${managePath}/group/type/list")
    public String index(Model model) {
        List<GroupType> list = groupTypeService.list();
        model.addAttribute("list", list);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping(value = "${managePath}/group/type/add", method = RequestMethod.GET)
    public Object add() {
        return MANAGE_FTL_PATH + "add";
    }

    @RequestMapping(value = "${managePath}/group/type/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel save(GroupType groupType) {
        return new ResultModel(groupTypeService.save(groupType));
    }


    @RequestMapping(value = "${managePath}/group/type/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") int id, Model model) {
        GroupType groupType = groupTypeService.findById(id);
        model.addAttribute("groupType", groupType);
        return MANAGE_FTL_PATH + "edit";
    }

    @RequestMapping(value = "${managePath}/group/type/update", method = RequestMethod.POST)
    @ResponseBody
    public ResultModel update(GroupType groupType) {
        return new ResultModel(groupTypeService.update(groupType));
    }


    @RequestMapping(value = "${managePath}/group/type/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResultModel delete(@PathVariable("id") int id) {
        return new ResultModel(groupTypeService.delete(id));
    }


}
