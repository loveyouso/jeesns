package com.sm.jeesns.group.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.group.model.Group;
import com.sm.jeesns.member.model.Member;

import java.util.List;


/**
 * Created by zchuanzhao on 16/12/23.
 */
public interface IGroupService {

    Group findById(int id);

    ResultModel save(Member loginMember, Group group);

    ResultModel update(Member loginMember, Group group);

    ResultModel delete(Member loginMember, int id);

    List<Group> list(int status, String key);

    ResultModel follow(Member loginMember, Integer groupId, int type);

    ResultModel changeStatus(int id);

    List<Group> listByCustom(int status, int num, String sort);
}
