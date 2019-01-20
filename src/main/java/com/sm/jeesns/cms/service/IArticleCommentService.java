package com.sm.jeesns.cms.service;

import com.sm.jeesns.cms.model.ArticleComment;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.Member;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArticleCommentService {

    ArticleComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer articleId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByArticle(Page page, int articleId);

    void deleteByArticle(Integer articleId);
}
