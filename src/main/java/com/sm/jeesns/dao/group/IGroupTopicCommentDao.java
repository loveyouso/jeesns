package com.sm.jeesns.dao.group;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.group.model.GroupTopicComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 16/12/27.
 */
public interface IGroupTopicCommentDao extends IBaseDao<GroupTopicComment> {

    List<GroupTopicComment> listByGroupTopic(@Param("page") Page page, @Param("groupTopicId") Integer groupTopicId);

    int deleteByTopic(@Param("groupTopicId") Integer groupTopicId);
}