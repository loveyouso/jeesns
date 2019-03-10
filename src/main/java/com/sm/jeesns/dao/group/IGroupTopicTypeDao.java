package com.sm.jeesns.dao.group;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.model.group.GroupTopicType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: zchuanzhao
 * @date: 2018/5/8 下午10:59
 */
public interface IGroupTopicTypeDao extends IBaseDao<GroupTopicType> {

    List<GroupTopicType> list(@Param("groupId") Integer groupId);

}