package com.sm.jeesns.cms.service;

import com.sm.jeesns.cms.model.ArticleComment;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.Member;

import java.util.List;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArticleCommentService {

    ArticleComment findById(int id);

    ResultModel save(Member loginMember, String content, Integer articleId);

    ResultModel delete(Member loginMember, int id);

    ResultModel listByArticle(Page page, int articleId);

    List<ArticleComment> listByPage(Page page, int articleId, String key);

    void deleteByArticle(Integer articleId);
}
