package com.sm.jeesns.service.cms;

import com.sm.jeesns.model.cms.Article;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.model.member.Member;

import java.util.List;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArticleService {

    Article findById(int id);

    Article findById(int id, Member loginMember);

    ResultModel save(Member member, Article article);

    ResultModel update(Member member, Article article);

    ResultModel delete(Member member, int id);

    ResultModel listByPage(Page page, String key, int cateid, int status, int memberId);

    void updateViewCount(int id);

    ResultModel audit(int id);

    ResultModel favor(Member loginMember, int articleId);

    List<Article> listByCustom(int cid, String sort, int num, int day, int thumbnail);
}
