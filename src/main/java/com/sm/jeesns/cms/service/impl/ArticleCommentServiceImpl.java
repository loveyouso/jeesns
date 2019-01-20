package com.sm.jeesns.cms.service.impl;

import com.sm.jeesns.cms.dao.IArticleCommentDao;
import com.sm.jeesns.cms.model.Article;
import com.sm.jeesns.cms.model.ArticleComment;
import com.sm.jeesns.cms.service.IArticleCommentService;
import com.sm.jeesns.cms.service.IArticleService;
import com.sm.jeesns.common.utils.ActionUtil;
import com.sm.jeesns.common.utils.ScoreRuleConsts;
import com.sm.jeesns.core.conts.AppTag;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.enums.MessageType;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.core.utils.StringUtils;
import com.sm.jeesns.member.model.Member;
import com.sm.jeesns.member.service.IMemberService;
import com.sm.jeesns.member.service.IMessageService;
import com.sm.jeesns.member.service.IScoreDetailService;
import com.sm.jeesns.system.service.IActionLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/10/14.
 */
@Service("articleCommentService")
public class ArticleCommentServiceImpl implements IArticleCommentService {
    @Resource
    private IArticleCommentDao articleCommentDao;
    @Resource
    private IArticleService articleService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public ArticleComment findById(int id) {
        return this.atFormat(articleCommentDao.findById(id));
    }

    @Override
    public ResultModel save(Member loginMember, String content, Integer articleId) {
        Article article = articleService.findById(articleId);
        if(article == null){
            return new ResultModel(-1,"文章不存在");
        }
        if(StringUtils.isEmpty(content)){
            return new ResultModel(-1,"内容不能为空");
        }
        ArticleComment articleComment = new ArticleComment();
        articleComment.setMemberId(loginMember.getId());
        articleComment.setArticleId(articleId);
        articleComment.setContent(content);
        int result = articleCommentDao.save(articleComment);
        if(result == 1){
            //@会员处理并发送系统消息
            messageService.atDeal(loginMember.getId(),content, AppTag.CMS, MessageType.CMS_ARTICLE_COMMENT_REFER,articleComment.getId());
            messageService.diggDeal(loginMember.getId(),article.getMemberId(),content,AppTag.CMS,MessageType.CMS_ARTICLR_REPLY,article.getId());
            //文章评论奖励
            scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.ARTICLE_REVIEWS,articleComment.getId());
            return new ResultModel(1,"评论成功");
        }else {
            return new ResultModel(-1,"评论失败");
        }
    }

    @Override
    public ResultModel listByArticle(Page page, int articleId) {
        List<ArticleComment> list = articleCommentDao.listByArticle(page, articleId);
        this.atFormat(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByArticle(Integer articleId) {
        articleCommentDao.deleteByArticle(articleId);
    }

    @Override
    @Transactional
    public ResultModel delete(Member loginMember, int id) {
        ArticleComment articleComment = this.findById(id);
        if(articleComment == null){
            return new ResultModel(-1,"评论不存在");
        }
        int result = articleCommentDao.delete(id);
        if(result == 1){
            //扣除积分
            scoreDetailService.scoreCancelBonus(loginMember.getId(), ScoreRuleConsts.ARTICLE_REVIEWS,id);
            actionLogService.save(loginMember.getCurrLoginIp(),loginMember.getId(), ActionUtil.DELETE_ARTICLE_COMMENT,"ID："+articleComment.getId()+"，内容："+articleComment.getContent());
            return new ResultModel(1,"删除成功");
        }
        return new ResultModel(-1,"删除失败");
    }

    public ArticleComment atFormat(ArticleComment articleComment){
        articleComment.setContent(memberService.atFormat(articleComment.getContent()));
        return articleComment;
    }

    public List<ArticleComment> atFormat(List<ArticleComment> articleCommentList){
        for (ArticleComment articleComment : articleCommentList){
            atFormat(articleComment);
        }
        return articleCommentList;
    }
}
