package com.sm.jeesns.weibo.service;


import com.sm.jeesns.weibo.model.WeiboTopic;

/**
 * Created by zchuanzhao on 2018/11/14.
 */
public interface IWeiboTopicService {

    WeiboTopic findByName(String name);

    Integer save(WeiboTopic weiboTopic);

}
