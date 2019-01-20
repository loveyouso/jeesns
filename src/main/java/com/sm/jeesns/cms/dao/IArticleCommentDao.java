package com.sm.jeesns.cms.dao;


import com.sm.jeesns.cms.model.ArticleComment;
import com.sm.jeesns.common.dao.IBaseDao;
import com.sm.jeesns.core.model.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章评论DAO接口
 * Created by zchuanzhao on 2016/11/26.
 */
public interface IArticleCommentDao extends IBaseDao<ArticleComment> {

    List<ArticleComment> listByArticle(@Param("page") Page page, @Param("articleId") Integer articleId);

    int deleteByArticle(@Param("articleId") Integer articleId);
}