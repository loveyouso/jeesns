package com.sm.jeesns.service.weibo.impl;

import com.sm.jeesns.common.utils.ActionLogType;
import com.sm.jeesns.common.utils.ActionUtil;
import com.sm.jeesns.common.utils.ConfigUtil;
import com.sm.jeesns.common.utils.ScoreRuleConsts;
import com.sm.jeesns.core.conts.AppTag;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.enums.MessageType;
import com.sm.jeesns.core.exception.NotLoginException;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.core.utils.StringUtils;
import com.sm.jeesns.core.utils.WeiboTopicUtil;
import com.sm.jeesns.model.member.Member;
import com.sm.jeesns.service.member.IMemberService;
import com.sm.jeesns.service.member.IMessageService;
import com.sm.jeesns.service.member.IScoreDetailService;
import com.sm.jeesns.service.picture.IPictureService;
import com.sm.jeesns.service.system.IActionLogService;
import com.sm.jeesns.dao.weibo.IWeiboDao;
import com.sm.jeesns.model.weibo.Weibo;
import com.sm.jeesns.model.weibo.WeiboTopic;
import com.sm.jeesns.service.weibo.IWeiboFavorService;
import com.sm.jeesns.service.weibo.IWeiboService;
import com.sm.jeesns.service.weibo.IWeiboTopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
@Service("weiboService")
public class WeiboServiceImpl implements IWeiboService {
    @Resource
    private IWeiboDao weiboDao;
    @Resource
    private IWeiboFavorService weiboFavorService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IPictureService pictureService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;
    @Resource
    private IWeiboTopicService weiboTopicService;

    @Override
    public Weibo findById(int id, int memberId) {
        Weibo weibo = weiboDao.findById(id,memberId);
        return weibo;
    }

    @Override
    @Transactional
    public ResultModel save(HttpServletRequest request, Member loginMember, String content, String pictures) {
        if (null == loginMember){
            throw new NotLoginException();
        }
        if("0".equals(request.getServletContext().getAttribute(ConfigUtil.WEIBO_POST.toUpperCase()))){
            return new ResultModel(-1,"微博已关闭");
        }
        if(StringUtils.isEmpty(content)){
            return new ResultModel(-1,"内容不能为空");
        }
        if(content.length() > Integer.parseInt((String) request.getServletContext().getAttribute(ConfigUtil.WEIBO_POST_MAXCONTENT.toUpperCase()))){
            return new ResultModel(-1,"内容不能超过"+request.getServletContext().getAttribute(ConfigUtil.WEIBO_POST_MAXCONTENT.toUpperCase())+"字");
        }
        Weibo weibo = new Weibo();
        weibo.setMemberId(loginMember.getId());
        weibo.setContent(content);
        weibo.setStatus(1);
        if(StringUtils.isEmpty(pictures)){
            //普通文本
            weibo.setType(0);
        }else {
            //图片
            weibo.setType(1);
        }
        if(weiboDao.save(weibo) == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.WEIBO, MessageType.WEIBO_REFER,weibo.getId());
            pictureService.update(weibo.getId(),pictures, content);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.POST_WEIBO,"", ActionLogType.WEIBO.getValue(),weibo.getId());
            //发布微博奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.RELEASE_WEIBO, weibo.getId());
            return new ResultModel(1,"发布成功");
        }
        return new ResultModel(-1,"发布失败");
    }

    @Override
    public ResultModel<Weibo> listByPage(Page page, int memberId, int loginMemberId, String key) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key.trim()+"%";
        }
        List<Weibo> list = weiboDao.listByPage(page, memberId,loginMemberId,key);
        list = this.atFormat(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Transactional
    @Override
    public ResultModel delete(HttpServletRequest request, Member loginMember, int id) {
        Weibo weibo = this.findById(id,loginMember.getId());
        if(weibo == null){
            return new ResultModel(-1,"微博不存在");
        }
        weiboDao.delete(id);
        //扣除积分
        scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.RELEASE_WEIBO,id);
        pictureService.deleteByForeignId(request, id);
        actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_WEIBO, "ID："+weibo.getId()+"，内容："+weibo.getContent());
        return new ResultModel(1,"操作成功");
    }

    @Transactional
    @Override
    public ResultModel userDelete(HttpServletRequest request, Member loginMember, int id) {
        if(loginMember == null){
            throw new NotLoginException();
        }
        Weibo weibo = this.findById(id,loginMember.getId());
        if(weibo == null){
            return new ResultModel(-1,"微博不存在");
        }
        if(loginMember.getIsAdmin() == 0 && (loginMember.getId().intValue() != weibo.getMember().getId().intValue())){
            return new ResultModel(-1,"没有权限");
        }
        return this.delete(request, loginMember,id);
    }

    @Override
    public List<Weibo> hotList(int loginMemberId) {
        List<Weibo> hotList = weiboDao.hotList(loginMemberId);
        return hotList;
    }

    @Transactional
    @Override
    public ResultModel favor(Member loginMember, int weiboId) {
        String message;
        ResultModel<Integer> resultModel;
        Weibo weibo = this.findById(weiboId,loginMember.getId());
        if(weiboFavorService.find(weiboId,loginMember.getId()) == null){
            //增加
            weiboDao.favor(weiboId,1);
            weibo.setFavor(weibo.getFavor() + 1);
            weiboFavorService.save(weiboId,loginMember.getId());
            message = "点赞成功";
            resultModel = new ResultModel(0,message);
            //发布微博奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.WEIBO_RECEIVED_THUMBUP, weiboId);
            //点赞之后发送系统信息
            messageService.diggDeal(loginMember.getId(),weibo.getMemberId(),AppTag.WEIBO,MessageType.WEIBO_ZAN,weibo.getId());
        }else {
            //减少
            weiboDao.favor(weiboId,-1);
            weibo.setFavor(weibo.getFavor() - 1);
            weiboFavorService.delete(weiboId,loginMember.getId());
            message = "取消赞成功";
            //扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.WEIBO_RECEIVED_THUMBUP,weiboId);
            resultModel = new ResultModel(1,message);
        }
        resultModel.setData(weibo.getFavor());
        return resultModel;
    }

    @Override
    public List<Weibo> listByCustom(int loginMemberId, String sort, int num, int day) {
        return weiboDao.listByCustom(loginMemberId,sort,num,day);
    }

    @Override
    public ResultModel<Weibo> listByTopic(Page page, int loginMemberId, String topicName) {
        WeiboTopic weiboTopic = weiboTopicService.findByName(topicName);
        List<Weibo> list;
        if (weiboTopic == null){
            weiboTopic = new WeiboTopic();
            weiboTopic.setName(topicName);
            weiboTopicService.save(weiboTopic);
            list = new ArrayList<>();
        }else {
            list = weiboDao.listByTopic(page, loginMemberId, weiboTopic.getId());
            list = this.formatWeibo(list);
        }
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    public Weibo atFormat(Weibo weibo){
        weibo.setContent(memberService.atFormat(weibo.getContent()));
        return weibo;
    }

    public List<Weibo> atFormat(List<Weibo> weiboList){
        for (Weibo weibo : weiboList){
            atFormat(weibo);
        }
        return weiboList;
    }

    public Weibo formatWeibo(Weibo weibo){
        weibo.setContent(memberService.atFormat(weibo.getContent()));
        weibo.setContent(WeiboTopicUtil.formatTopic(weibo.getContent()));
        return weibo;
    }

    public List<Weibo> formatWeibo(List<Weibo> weiboList){
        for (Weibo weibo : weiboList){
            formatWeibo(weibo);
        }
        return weiboList;
    }
}
