package com.sm.jeesns.service.cms;

import com.sm.jeesns.model.cms.ArticleComment;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.model.member.Member;

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
