package com.sm.jeesns.weibo.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.Member;
import com.sm.jeesns.weibo.model.WeiboComment;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IWeiboCommentService {

    WeiboComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer weiboId, Integer weiboCommentId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByWeibo(Page page, int weiboId);

    void deleteByWeibo(Integer weiboId);
}
