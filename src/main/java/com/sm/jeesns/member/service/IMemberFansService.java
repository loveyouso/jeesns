package com.sm.jeesns.member.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.MemberFans;


/**
 * Created by zchuanzhao on 17/2/21.
 */
public interface IMemberFansService {

    ResultModel save(Integer whoFollowId, Integer followWhoId);

    ResultModel delete(Integer whoFollowId, Integer followWhoId);

    ResultModel followsList(Page page, Integer whoFollowId);

    ResultModel fansList(Page page, Integer followWhoId);

    MemberFans find(Integer whoFollowId, Integer followWhoId);
}
