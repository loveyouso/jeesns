package com.sm.jeesns.group.dao;

import com.sm.jeesns.common.dao.IBaseDao;
import com.sm.jeesns.group.model.GroupTopicType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/8 下午10:59
 */
public interface IGroupTopicTypeDao extends IBaseDao<GroupTopicType> {

    List<GroupTopicType> list(@Param("groupId") Integer groupId);

}