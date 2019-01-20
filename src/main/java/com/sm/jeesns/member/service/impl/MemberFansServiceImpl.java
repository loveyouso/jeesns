package com.sm.jeesns.member.service.impl;

import com.sm.jeesns.core.dto.ResultModel;
import com.sm.jeesns.core.model.Page;
import com.sm.jeesns.member.dao.IMemberFansDao;
import com.sm.jeesns.member.model.MemberFans;
import com.sm.jeesns.member.service.IMemberFansService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zchuanzhao on 2017/2/21.
 */
@Service("memberFansServiceImpl")
public class MemberFansServiceImpl implements IMemberFansService {
    @Resource
    private IMemberFansDao memberFansDao;

    @Override
    public MemberFans find(Integer whoFollowId, Integer followWhoId) {
        return memberFansDao.find(whoFollowId,followWhoId);
    }

    /**
     * 关注
     */
    @Override
    public ResultModel save(Integer whoFollowId, Integer followWhoId) {
        if(memberFansDao.find(whoFollowId,followWhoId) == null){
            if(memberFansDao.save(whoFollowId,followWhoId) == 1){
                return new ResultModel(1,"关注成功");
            }
        }else {
            //已经关注了
            return new ResultModel(0,"关注成功");
        }
        return new ResultModel(-1,"关注失败");
    }

    /**
     * 取消关注
     */
    @Override
    public ResultModel delete(Integer whoFollowId, Integer followWhoId) {
        if(memberFansDao.delete(whoFollowId,followWhoId) > 0){
            return new ResultModel(1,"取消关注成功");
        }
        return new ResultModel(-1,"取消关注失败");
    }

    @Override
    public ResultModel followsList(Page page, Integer whoFollowId) {
        List<MemberFans> list = memberFansDao.followsList(page, whoFollowId);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public ResultModel fansList(Page page, Integer followWhoId) {
        List<MemberFans> list = memberFansDao.fansList(page, followWhoId);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }


}
