package com.sm.jeesns.group.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.group.model.GroupTopicComment;
import com.sm.jeesns.member.model.Member;


/**
 * Created by zchuanzhao on 2016/12/27.
 */
public interface IGroupTopicCommentService {

    GroupTopicComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer groupTopicId, Integer commentId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByGroupTopic(Page page, int groupTopicId);

    void deleteByTopic(int groupTopicId);
}
