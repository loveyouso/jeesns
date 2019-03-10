package com.sm.jeesns.service.member;

import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.model.member.Checkin;

import java.util.List;


/**
 * 签到
 * Created by zchuanzhao on 18/8/20.
 */
public interface ICheckinService {

    List<Checkin> list(Page page, Integer memberId);

    List<Checkin> todayList(Page page);

    List<Checkin> todayContinueList();

    Checkin todayCheckin(Integer memberId);

    Checkin yesterdayCheckin(Integer memberId);

    boolean save(Integer memberId);
}
