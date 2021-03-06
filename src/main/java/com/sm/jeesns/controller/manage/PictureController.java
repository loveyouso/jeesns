package com.sm.jeesns.controller.manage;


import com.sm.jeesns.controller.common.BaseController;
import com.sm.jeesns.core.annotation.Before;
import com.sm.jeesns.core.conts.AppTag;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.interceptor.AdminLoginInterceptor;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.service.picture.IPictureService;
import com.sm.jeesns.service.system.ITagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *
 * @author zchuanzhao
 * @date 2017/11/01
 */
@Controller
@RequestMapping("/${managePath}/picture")
@Before(AdminLoginInterceptor.class)
public class PictureController extends BaseController {
    private static final String MANAGE_FTL_PATH = "/manage/picture/";
    @Resource
    private IPictureService pictureService;
    @Resource
    private ITagService tagService;

    @RequestMapping("/tagList")
    public String tagList(Model model){
        Page page = new Page(request);
        ResultModel resultModel = tagService.listByPage(page, AppTag.PICTURE);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "tagList";
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        Page page = new Page(request);
        ResultModel resultModel = pictureService.listByPage(page,0);
        model.addAttribute("model", resultModel);
        return MANAGE_FTL_PATH + "list";
    }

    @RequestMapping(value = "/delete/{pictureId}",method = RequestMethod.GET)
    @ResponseBody
    public Object delete(@PathVariable("pictureId") Integer pictureId){
        return pictureService.delete(request,pictureId);
    }

}
