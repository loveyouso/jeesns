package com.sm.jeesns.service.group;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.model.group.GroupTopicComment;
import com.sm.jeesns.model.member.Member;


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
