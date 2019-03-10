package com.sm.jeesns.dao.member;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.MemberFans;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/16.
 */
public interface IMemberFansDao extends IBaseDao<MemberFans> {

    List<MemberFans> followsList(@Param("page") Page page, @Param("whoFollowId") Integer whoFollowId);

    List<MemberFans> fansList(@Param("page") Page page, @Param("followWhoId") Integer followWhoId);

    MemberFans find(@Param("whoFollowId") Integer whoFollowId, @Param("followWhoId") Integer followWhoId);

    Integer save(@Param("whoFollowId") Integer whoFollowId, @Param("followWhoId") Integer followWhoId);

    Integer delete(@Param("whoFollowId") Integer whoFollowId, @Param("followWhoId") Integer followWhoId);
}