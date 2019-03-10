package com.sm.jeesns.dao.weibo;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.weibo.model.WeiboFavor;
import org.apache.ibatis.annotations.Param;

/**
 * 微博点赞DAO接口
 * Created by zchuanzhao on 2017/2/8.
 */
public interface IWeiboFavorDao extends IBaseDao<WeiboFavor> {

    WeiboFavor find(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);

    Integer save(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);

    Integer delete(@Param("weiboId") Integer weiboId, @Param("memberId") Integer memberId);
}