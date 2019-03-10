package com.sm.jeesns.group.service.impl;

import com.sm.jeesns.common.model.Archive;
import com.sm.jeesns.common.service.IArchiveService;
import com.sm.jeesns.common.utils.ActionLogType;
import com.sm.jeesns.common.utils.ActionUtil;
import com.sm.jeesns.common.utils.ScoreRuleConsts;
import com.sm.jeesns.core.conts.AppTag;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.enums.MessageType;
import com.sm.jeesns.core.exception.NotLoginException;
import com.sm.jeesns.core.exception.ParamException;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.core.utils.Const;
import com.sm.jeesns.core.utils.StringUtils;
import com.sm.jeesns.dao.group.IGroupTopicDao;
import com.sm.jeesns.group.model.Group;
import com.sm.jeesns.group.model.GroupTopic;
import com.sm.jeesns.group.service.IGroupFansService;
import com.sm.jeesns.group.service.IGroupService;
import com.sm.jeesns.group.service.IGroupTopicCommentService;
import com.sm.jeesns.group.service.IGroupTopicService;
import com.sm.jeesns.member.model.Member;
import com.sm.jeesns.member.service.IMemberService;
import com.sm.jeesns.member.service.IMessageService;
import com.sm.jeesns.member.service.IScoreDetailService;
import com.sm.jeesns.system.service.IActionLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/12/26.
 */
@Service("groupTopicService")
public class GroupTopicServiceImpl implements IGroupTopicService {
    @Resource
    private IGroupTopicDao groupTopicDao;
    @Resource
    private IGroupService groupService;
    @Resource
    private IGroupTopicCommentService groupTopicCommentService;
    @Resource
    private IGroupFansService groupFansService;
    @Resource
    private IArchiveService archiveService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public GroupTopic findById(int id) {
        return this.findById(id,null);
    }

    @Override
    public GroupTopic findById(int id,Member loginMember) {
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        return this.atFormat(groupTopicDao.findById(id,loginMemberId));
    }

    @Override
    @Transactional
    public ResultModel save(Member member, GroupTopic groupTopic) {
        if(groupTopic.getGroupId() == null || groupTopic.getGroupId() == 0){
            return new ResultModel(-1,"群组不能为空");
        }
        Group group = groupService.findById(groupTopic.getGroupId());
        if(group == null){
            return new ResultModel(-1,"群组不存在");
        }
        if(groupFansService.findByMemberAndGroup(group.getId(),member.getId()) == null){
            return new ResultModel(-1,"必须关注该群组后才能发帖");
        }
        if(group.getCanPost() == 0){
            return new ResultModel(-1,"群组已关闭发帖功能");
        }
        groupTopic.setMemberId(member.getId());
        Archive archive = new Archive();
        try {
            //复制属性值
            archive = archive.copy(groupTopic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        archive.setPostType(2);
        //保存文档
        if(archiveService.save(member,archive)){
            //保存文章
            groupTopic.setStatus(group.getTopicReview()==0?1:0);
            groupTopic.setArchiveId(archive.getArchiveId());
            int result = groupTopicDao.save(groupTopic);
            if(result == 1){
                //@会员处理并发送系统消息
                messageService.atDeal(member.getId(),groupTopic.getContent(), AppTag.GROUP, MessageType.GROUP_TOPIC_REFER,groupTopic.getId());
                //群组发帖奖励
                scoreDetailService.scoreBonus(member.getId(), ScoreRuleConsts.GROUP_POST, groupTopic.getId());
                actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.POST_GROUP_TOPIC,"", ActionLogType.GROUP_TOPIC.getValue(),groupTopic.getId());
                if (groupTopic.getStatus() == 0){
                    return new ResultModel(2,"帖子发布成功，请等待管理员审核通过","../detail/"+groupTopic.getGroupId());
                }

                return new ResultModel(2,"帖子发布成功","../topic/"+groupTopic.getId());
            }
        }
        return new ResultModel(-1,"帖子发布失败");
    }

    @Override
    public ResultModel listByPage(Page page, String key, int groupId, int status, int memberId, int typeId) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<GroupTopic> list = groupTopicDao.listByPage(page, key,groupId,status,memberId,typeId);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    @Transactional
    public ResultModel update(Member member, GroupTopic groupTopic) {
        GroupTopic findGroupTopic = this.findById(groupTopic.getId(),member);
        if(findGroupTopic == null){
            return new ResultModel(-2);
        }
        if(member.getId().intValue() != findGroupTopic.getMember().getId().intValue()){
            return new ResultModel(-1,"没有权限");
        }
        groupTopic.setArchiveId(findGroupTopic.getArchiveId());
        Archive archive = new Archive();
        try {
            //复制属性值
            archive = archive.copy(groupTopic);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (groupTopic.getTypeId() != null && !groupTopic.getTypeId().equals(findGroupTopic.getTypeId())){
            groupTopicDao.updateType(groupTopic.getId(),groupTopic.getTypeId());
        }
        if(archiveService.update(member,archive)){
            return new ResultModel(0,"更新成功");
        }
        return new ResultModel(-1,"更新失败");
    }

    @Override
    @Transactional
    public ResultModel delete(Member loginMember, int id) {
        GroupTopic groupTopic = this.findById(id);
        if(groupTopic == null){
            return new ResultModel(-1,"帖子不存在");
        }
        int result = groupTopicDao.delete(id);
        if(result == 1){
            //扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_POST,id);
            archiveService.delete(groupTopic.getArchiveId());
            groupTopicCommentService.deleteByTopic(id);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_GROUP_TOPIC,"ID："+groupTopic.getId()+"，标题："+groupTopic.getTitle());
            return new ResultModel(1,"删除成功");
        }
        return new ResultModel(-1,"删除失败");
    }


    @Override
    @Transactional
    public ResultModel indexDelete(HttpServletRequest request, Member loginMember, int id) {
        if(loginMember == null){
            throw new NotLoginException();
        }
        GroupTopic groupTopic = this.findById(id,loginMember);
        if (groupTopic == null){
            return new ResultModel(-1,"帖子不存在");
        }
        Group group = groupService.findById(groupTopic.getGroup().getId());
        if(group == null){
            throw new ParamException();
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isManager = false;
        for (String manager : groupManagerArr){
            if(loginMember.getId().intValue() == Integer.parseInt(manager)){
                isManager = true;
            }
        }
        if(loginMember.getId().intValue() == groupTopic.getMember().getId().intValue() || loginMember.getIsAdmin() > 0 ||
                isManager || loginMember.getId().intValue() == group.getCreator().intValue()){
            ResultModel resultModel = this.delete(loginMember,id);
            if(resultModel.getCode() > 0){
                resultModel.setCode(2);
                resultModel.setUrl(Const.GROUP_PATH + "/detail/"+group.getId());
            }
            return resultModel;
        }
        return new ResultModel(-1,"权限不足");
    }

    @Override
    public ResultModel audit(Member member, int id) {
        if(member == null){
            throw new NotLoginException();
        }
        GroupTopic groupTopic = this.findById(id,member);
        if (groupTopic == null){
            return new ResultModel(-1,"帖子不存在");
        }
        Group group = groupService.findById(groupTopic.getGroup().getId());
        if(group == null){
            throw new ParamException();
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isManager = false;
        for (String manager : groupManagerArr){
            if(member.getId() == Integer.parseInt(manager)){
                isManager = true;
            }
        }
        if(member.getId().intValue() == groupTopic.getMember().getId().intValue() || member.getIsAdmin() > 0 ||
                isManager || member.getId().intValue() == group.getCreator().intValue()){
            if(groupTopicDao.audit(id) == 1){
                return new ResultModel(1,"审核成功");
            }else {
                return new ResultModel(-1,"审核失败");
            }
        }
        return new ResultModel(-1,"权限不足");
    }

    @Override
    public ResultModel top(Member member, int id, int top) {
        if(member == null){
            throw new NotLoginException();
        }
        GroupTopic groupTopic = this.findById(id,member);
        if (groupTopic == null){
            return new ResultModel(-1,"帖子不存在");
        }
        Group group = groupService.findById(groupTopic.getGroup().getId());
        if(group == null){
            throw new ParamException();
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isManager = false;
        for (String manager : groupManagerArr){
            if(member.getId() == Integer.parseInt(manager)){
                isManager = true;
            }
        }
        if(member.getId().intValue() == groupTopic.getMember().getId().intValue() || member.getIsAdmin() > 0 ||
                isManager || member.getId().intValue() == group.getCreator().intValue()){
            if(groupTopicDao.top(id,top) == 1){
                return new ResultModel(1,"操作成功");
            }else {
                return new ResultModel(-1,"操作失败");
            }
        }
        return new ResultModel(-1,"权限不足");
    }

    /**
     * 将帖子设置精华
     * @param member
     * @param id
     * @param essence
     * @return
     */
    @Override
    public ResultModel essence(Member member, int id, int essence) {
        if(member == null){
            throw new NotLoginException();
        }
        GroupTopic groupTopic = this.findById(id,member);
        if (groupTopic == null){
            return new ResultModel(-1,"帖子不存在");
        }
        Group group = groupService.findById(groupTopic.getGroup().getId());
        if(group == null){
            throw new ParamException();
        }
        String groupManagers = group.getManagers();
        String[] groupManagerArr = groupManagers.split(",");
        boolean isManager = false;
        for (String manager : groupManagerArr){
            if(member.getId() == Integer.parseInt(manager)){
                isManager = true;
            }
        }
        if(member.getId().intValue() == groupTopic.getMember().getId().intValue() || member.getIsAdmin() > 0 ||
                isManager || member.getId().intValue() == group.getCreator().intValue()){
            if(groupTopicDao.essence(id,essence) == 1){
                return new ResultModel(1,"操作成功");
            }else {
                return new ResultModel(-1,"操作失败");
            }
        }
        return new ResultModel(-1,"权限不足");
    }


    @Override
    public ResultModel favor(Member loginMember, int id) {
        GroupTopic groupTopic = this.findById(id);
        if(groupTopic != null){
            ResultModel resultModel = archiveService.favor(loginMember,groupTopic.getArchiveId());
            if(resultModel.getCode() == 0){
                //帖子收到喜欢
                scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.GROUP_TOPIC_RECEIVED_LIKE, id);
                //点赞之后发送系统信息
                messageService.diggDeal(loginMember.getId(),groupTopic.getMemberId(),AppTag.GROUP,MessageType.GROUP_TOPIC_LIKE,groupTopic.getId());
            }else if(resultModel.getCode() == 1){
                //帖子取消喜欢
                //扣除积分
                scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.GROUP_TOPIC_RECEIVED_LIKE, id);
            }
            return resultModel;
        }
        return new ResultModel(-1,"帖子不存在");
    }

    @Override
    public List<GroupTopic> listByCustom(int gid, String sort, int num, int day,int thumbnail) {
        return groupTopicDao.listByCustom(gid,sort,num,day,thumbnail);
    }

    public GroupTopic atFormat(GroupTopic groupTopic){
        groupTopic.setContent(memberService.atFormat(groupTopic.getContent()));
        return groupTopic;
    }
}
