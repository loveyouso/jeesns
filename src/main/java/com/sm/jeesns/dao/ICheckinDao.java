package com.sm.jeesns.dao;

import com.sm.jeesns.dao.common.IBaseDao;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.Checkin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员签到DAO
 * Created by zchuanzhao on 18/8/20.
 */
public interface ICheckinDao extends IBaseDao<Checkin> {
    List<Checkin> listByPage(@Param("page") Page page, @Param("memberId") Integer memberId);

    List<Checkin> todayList(@Param("page") Page page);

    List<Checkin> todayContinueList();

    Checkin todayCheckin(@Param("memberId") Integer memberId);

    Checkin yesterdayCheckin(@Param("memberId") Integer memberId);

}