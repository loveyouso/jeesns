package com.sm.jeesns.member.service;


import com.sm.jeesns.member.model.MemberToken;

/**
 * Created by zchuanzhao on 2017/7/15.
 */
public interface IMemberTokenService {

    MemberToken getByToken(String token);

    void save(Integer memberId, String token);

}