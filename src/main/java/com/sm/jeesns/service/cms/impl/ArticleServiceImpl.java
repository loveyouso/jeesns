package com.sm.jeesns.service.cms.impl;

import com.sm.jeesns.dao.cms.IArticleDao;
import com.sm.jeesns.model.cms.Article;
import com.sm.jeesns.service.cms.IArticleCommentService;
import com.sm.jeesns.service.cms.IArticleService;
import com.sm.jeesns.model.common.Archive;
import com.sm.jeesns.service.common.IArchiveService;
import com.sm.jeesns.common.utils.ActionLogType;
import com.sm.jeesns.common.utils.ActionUtil;
import com.sm.jeesns.common.utils.ConfigUtil;
import com.sm.jeesns.common.utils.ScoreRuleConsts;
import com.sm.jeesns.core.conts.AppTag;
import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.enums.MessageType;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.core.utils.StringUtils;
import com.sm.jeesns.model.member.Member;
import com.sm.jeesns.service.member.IMemberService;
import com.sm.jeesns.service.member.IMessageService;
import com.sm.jeesns.service.member.IScoreDetailService;
import com.sm.jeesns.service.system.IActionLogService;
import com.sm.jeesns.service.system.IConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zchuanzhao on 2016/10/14.
 */
@Service("articleService")
public class ArticleServiceImpl implements IArticleService {
    @Resource
    private IArticleDao articleDao;
    @Resource
    private IArchiveService archiveService;
    @Resource
    private IConfigService configService;
    @Resource
    private IArticleCommentService articleCommentService;
    @Resource
    private IActionLogService actionLogService;
    @Resource
    private IScoreDetailService scoreDetailService;
    @Resource
    private IMessageService messageService;
    @Resource
    private IMemberService memberService;

    @Override
    public Article findById(int id) {
        return this.findById(id,null);
    }

    @Override
    public Article findById(int id, Member loginMember) {
        int loginMemberId = loginMember == null ? 0 : loginMember.getId();
        Article article = articleDao.findById(id,loginMemberId);
        this.atFormat(article);
        return article;
    }

    @Override
    @Transactional
    public ResultModel save(Member member, Article article) {
        Map<String,String> config = configService.getConfigToMap();
        if(member.getIsAdmin() == 0 && "0".equals(config.get(ConfigUtil.CMS_POST))){
            return new ResultModel(-1,"投稿功能已关闭");
        }
        if(article.getCateId() == null || article.getCateId() == 0){
            return new ResultModel(-1,"栏目不能为空");
        }
        article.setMemberId(member.getId());
        Archive archive = new Archive();
        try {
            //复制属性值
            archive = archive.copy(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        archive.setPostType(1);
        if(member.getIsAdmin() == 0 && "0".equals(config.get(ConfigUtil.CMS_POST_REVIEW))){
            article.setStatus(0);
        }else {
            article.setStatus(1);
        }
        //保存文档
        if(archiveService.save(member,archive)){
            //保存文章
            article.setArchiveId(archive.getArchiveId());
            int result = articleDao.save(article);
            if(result == 1){
                //@会员处理并发送系统消息
                messageService.atDeal(member.getId(),article.getContent(), AppTag.CMS, MessageType.CMS_ARTICLE_REFER,article.getId());
                if(article.getStatus() == 1){
                    //投稿审核通过奖励
                    scoreDetailService.scoreBonus(article.getMemberId(), ScoreRuleConsts.ARTICLE_SUBMISSIONS,article.getId());
                }
                actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.POST_ARTICLE,"", ActionLogType.ARTICLE.getValue(),article.getId());
                if (article.getStatus() == 0){
                    return new ResultModel(0,"文章发布成功，请等待审核");
                }
                return new ResultModel(0,"文章发布成功");
            }
        }
        return new ResultModel(-1,"文章发布失败");
    }

    @Override
    public ResultModel listByPage(Page page, String key, int cateid, int status, int memberId) {
        if (StringUtils.isNotBlank(key)){
            key = "%"+key+"%";
        }
        List<Article> list = articleDao.listByPage(page, key,cateid,status,memberId);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void updateViewCount(int id) {
        articleDao.updateViewCount(id);
    }

    @Override
    public ResultModel audit(int id) {
        if(articleDao.audit(id) == 1){
            Article article = this.findById(id);
            if(article != null){
                //说明此次操作是审核通过
                if(article.getStatus() == 1){
                    //投稿审核通过奖励
                    scoreDetailService.scoreBonus(article.getMemberId(), ScoreRuleConsts.ARTICLE_SUBMISSIONS,article.getId());
                }
            }
            return new ResultModel(1,"操作成功");
        }else {
            return new ResultModel(-1,"操作时候");
        }
    }

    @Override
    public ResultModel favor(Member loginMember, int articleId) {
        Article article = this.findById(articleId);
        if(article != null){
            ResultModel resultModel = archiveService.favor(loginMember,article.getArchiveId());
            if(resultModel.getCode() == 0){
                //文章收到喜欢
                scoreDetailService.scoreBonus(loginMember.getId(), ScoreRuleConsts.ARTICLE_RECEIVED_LIKE, articleId);
                //点赞之后发送系统信息
                messageService.diggDeal(loginMember.getId(),article.getMemberId(), AppTag.CMS, MessageType.CMS_ARTICLE_LIKE,article.getId());
            }else if(resultModel.getCode() == 1){
                //取消喜欢，扣除积分
                scoreDetailService.scoreCancelBonus(loginMember.getId(),ScoreRuleConsts.ARTICLE_RECEIVED_LIKE, articleId);
            }
            return resultModel;
        }
        return new ResultModel(-1,"文章不存在");
    }

    @Override
    public List<Article> listByCustom(int cid, String sort, int num, int day,int thumbnail) {
        return articleDao.listByCustom(cid,sort,num,day,thumbnail);
    }

    @Override
    @Transactional
    public ResultModel update(Member member, Article article) {
        Article findArticle = this.findById(article.getId(),member);
        if(findArticle == null){
            return new ResultModel(-2);
        }
        article.setArchiveId(findArticle.getArchiveId());
        Archive archive = new Archive();
        try {
            //复制属性值
            archive = archive.copy(article);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(archiveService.update(member,archive)){
            Map<String,String> config = configService.getConfigToMap();
            if(member.getIsAdmin() == 0 && "0".equals(config.get(ConfigUtil.CMS_POST_REVIEW))){
                findArticle.setStatus(0);
            }else {
                findArticle.setStatus(1);
            }
            //更新栏目
            findArticle.setCateId(article.getCateId());
            articleDao.update(findArticle);
            return new ResultModel(0,"更新成功");
        }
        return new ResultModel(-1,"更新失败");
    }

    @Override
    @Transactional
    public ResultModel delete(Member member, int id) {
        Article article = this.findById(id);
        if (article == null){
            return new ResultModel(-1,"文章不存在");
        }
        int result = articleDao.delete(id);
        if(result == 1){
            //扣除积分
            scoreDetailService.scoreCancelBonus(member.getId(),ScoreRuleConsts.ARTICLE_SUBMISSIONS,id);
            archiveService.delete(article.getArchiveId());
            articleCommentService.deleteByArticle(id);
            actionLogService.save(member.getCurrLoginIp(),member.getId(), ActionUtil.DELETE_ARTICLE,"ID："+article.getId()+"，标题："+article.getTitle());
            return new ResultModel(1,"删除成功");
        }
        return new ResultModel(-1,"删除失败");
    }

    public Article atFormat(Article article){
        article.setContent(memberService.atFormat(article.getContent()));
        return article;
    }
}
