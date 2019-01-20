package com.sm.jeesns.weibo.service;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.model.Member;
import com.sm.jeesns.weibo.model.Weibo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zchuanzhao on 2016/11/25.
 */
public interface IWeiboService {

    Weibo findById(int id, int memberId);

    ResultModel save(HttpServletRequest request, Member loginMember, String content, String pictures);

    ResultModel<Weibo> listByPage(Page page, int memberId, int loginMemberId, String key);

    ResultModel delete(HttpServletRequest request, Member loginMember, int id);

    ResultModel userDelete(HttpServletRequest request, Member loginMember, int id);

    List<Weibo> hotList(int loginMemberId);

    ResultModel favor(Member loginMember, int weiboId);

    List<Weibo> listByCustom(int loginMemberId, String sort, int num, int day);
}
