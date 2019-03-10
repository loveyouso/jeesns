package com.sm.jeesns.dao;


import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.weibo.model.WeiboTopic;
import org.apache.ibatis.annotations.Param;


public interface IWeiboTopicDao extends IBaseDao<WeiboTopic> {

    WeiboTopic findByName(@Param("name") String name);

}