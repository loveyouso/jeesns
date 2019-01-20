package com.sm.jeesns.group.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.group.model.GroupTopicType;
import com.sm.jeesns.member.model.Member;

import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/8 下午11:13
 */
public interface IGroupTopicTypeService {

    GroupTopicType findById(int id);

    List<GroupTopicType> list(int groupId);

    ResultModel delete(Member member, int id);

    ResultModel save(Member member, GroupTopicType groupTopicType);

    ResultModel update(Member member, GroupTopicType groupTopicType);
}
