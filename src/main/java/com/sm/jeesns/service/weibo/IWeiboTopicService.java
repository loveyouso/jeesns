package com.sm.jeesns.service.weibo;


import com.sm.jeesns.model.weibo.WeiboTopic;

/**
 * Created by zchuanzhao on 2018/11/14.
 */
public interface IWeiboTopicService {

    WeiboTopic findByName(String name);

    Integer save(WeiboTopic weiboTopic);

}
