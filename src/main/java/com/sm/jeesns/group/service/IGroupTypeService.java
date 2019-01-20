package com.sm.jeesns.group.service;

import com.sm.jeesns.group.model.GroupType;

import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/15 下午11:13
 */
public interface IGroupTypeService {

    GroupType findById(int id);

    List<GroupType> list();

    boolean delete(int id);

    boolean save(GroupType groupType);

    boolean update(GroupType groupType);
}
