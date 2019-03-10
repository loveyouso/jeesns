package com.sm.jeesns.system.controller;


import com.sm.jeesns.cms.service.IArticleService;
import com.sm.jeesns.common.controller.BaseController;
import com.sm.jeesns.common.service.IArchiveService;
import com.sm.jeesns.common.service.ILinkService;
import com.sm.jeesns.common.utils.EmojiUtil;
import com.sm.jeesns.common.utils.MemberUtil;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.core.utils.Const;
import com.sm.jeesns.core.utils.ErrorUtil;
import com.sm.jeesns.core.utils.JeesnsConfig;
import com.sm.jeesns.core.utils.StringUtils;
import com.sm.jeesns.group.service.IGroupFansService;
import com.sm.jeesns.group.service.IGroupService;
import com.sm.jeesns.group.service.IGroupTopicService;
import com.sm.jeesns.member.model.Member;
import com.sm.jeesns.member.service.IMemberFansService;
import com.sm.jeesns.member.service.IMemberService;
import com.sm.jeesns.system.model.ActionLog;
import com.sm.jeesns.system.service.IActionLogService;
import com.sm.jeesns.weibo.service.IWeiboService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
@Controller("indexController")
@RequestMapping("/")
public class IndexController extends BaseController {
    @Resource
    private IArticleService articleService;
    @Resource
    private IGroupTopicService groupTopicService;
    @Resource
    private IGroupService groupService;
    @Resource
    private IWeiboService weiboService;
    @Resource
    private IMemberService memberService;
    @Resource
    private IArchiveService archiveService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private JeesnsConfig jeesnsConfig;
    @Resource
    private IGroupFansService groupFansService;
    @Resource
    private IMemberFansService memberFansService;
    @Resource
    private ILinkService linkService;

    @RequestMapping(value={"/", "index"},method = RequestMethod.GET)
    public String index(@RequestParam(value = "key",required = false,defaultValue = "") String key, Integer cateid, Model model) {
        Page page = new Page(request);
        if(cateid == null){
            cateid = 0;
        }
        Member loginMember = MemberUtil.getLoginMember(request);
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        ResultModel linkModel = linkService.recommentList();
        page.setPageSize(50);
        model.addAttribute("linkModel",linkModel);

        return jeesnsConfig.getFrontTemplate() + "/index";
    }

    @RequestMapping(value = "u/{id}",method = RequestMethod.GET)
    public String u(@PathVariable("id") Integer id, Model model){
        Page page = new Page(request);
        Member member = memberService.findById(id);
        if(member == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1005, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("member",member);
        Member loginMember = MemberUtil.getLoginMember(request);
        model.addAttribute("loginMember", loginMember);
        ResultModel<ActionLog> list = actionLogService.memberActionLog(page,id);
        model.addAttribute("actionLogModel",list);
        return jeesnsConfig.getFrontTemplate() + "/u";
    }

    @RequestMapping(value = "u/{id}/home/{type}",method = RequestMethod.GET)
    public String home(@PathVariable("id") Integer id, @PathVariable("type") String type, Model model){
        Page page = new Page(request);
        Member member = memberService.findById(id);
        if(member == null){
            return jeesnsConfig.getFrontTemplate() + ErrorUtil.error(model,-1005, Const.INDEX_ERROR_FTL_PATH);
        }
        model.addAttribute("member",member);

        Member loginMember = MemberUtil.getLoginMember(request);
        model.addAttribute("loginMember", loginMember);
        int loginMemberId = 0;
        if(loginMember != null){
            loginMemberId = loginMember.getId().intValue();
        }
        if("article".equals(type)){
            model.addAttribute("model", articleService.listByPage(page,"",0,1, id));
        } else if("groupTopic".equals(type)){
            model.addAttribute("model", groupTopicService.listByPage(page,"",0,1, id,0));
        } else if("group".equals(type)){
            model.addAttribute("model", groupFansService.listByMember(page, id));
        } else if("weibo".equals(type)){
            model.addAttribute("model", weiboService.listByPage(page,id,loginMemberId,""));
        } else if("follows".equals(type)){
            model.addAttribute("model", memberFansService.followsList(page,id));
        } else if("fans".equals(type)){
            model.addAttribute("model", memberFansService.fansList(page,id));
        }
        model.addAttribute("type",type);
        return jeesnsConfig.getFrontTemplate() + "/home";
    }


    @RequestMapping(value = "newVersion",method = RequestMethod.GET)
    public void newVersion(@RequestParam("callback") String callback){
        response.setCharacterEncoding("utf-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("LAST_SYSTEM_VERSION",Const.LAST_SYSTEM_VERSION);
        jsonObject.put("LAST_SYSTEM_UPDATE_TIME", Const.LAST_SYSTEM_UPDATE_TIME);
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(callback + "(" + jsonObject.toString() + ")");
        out.flush();
        out.close();
    }
    /**
     * 获取Emoji数据
     * @return
     */
    @RequestMapping(value="/emoji/emojiJsonData.json",method = RequestMethod.GET)
    @ResponseBody
    public Object emojiJsonData(){
        return EmojiUtil.emojiJson();
    }

    @RequestMapping(value="/404",method = RequestMethod.GET)
    public String jeesns404(){
        return jeesnsConfig.getFrontTemplate() + "/common/404";
    }

    @RequestMapping(value="/error",method = RequestMethod.GET)
    public String error(String msg){
        if (StringUtils.isNotBlank(msg)){
            request.setAttribute("msg", msg);
        }
        return jeesnsConfig.getFrontTemplate() + "/common/error";
    }

    /**
     * 友情链接
     * @param model
     * @return
     */
    @RequestMapping(value={"/link"},method = RequestMethod.GET)
    public String link(Model model) {
        ResultModel linkModel = linkService.allList();
        model.addAttribute("linkModel",linkModel);
        return jeesnsConfig.getFrontTemplate() + "/link";
    }
}
