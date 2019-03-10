package com.sm.jeesns.service.member;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.model.member.MemberFans;


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
