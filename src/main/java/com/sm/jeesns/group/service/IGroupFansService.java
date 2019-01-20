package com.sm.jeesns.group.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.group.model.GroupFans;
import com.sm.jeesns.member.model.Member;
import org.apache.ibatis.annotations.Param;


/**
 * Created by zchuanzhao on 16/12/26.
 */
public interface IGroupFansService {

    ResultModel save(Member loginMember, Integer groupId);

    ResultModel delete(Member loginMember, Integer groupId);

    ResultModel listByPage(Page page, Integer groupId);

    GroupFans findByMemberAndGroup(@Param("groupId") Integer groupId, @Param("memberId") Integer memberId);

    /**
     * 获取用户关注的群组列表
     * @param page
     * @param memberId
     * @return
     */
    ResultModel listByMember(Page page, Integer memberId);
}
