package com.sm.jeesns.common.service.impl;

import com.sm.jeesns.dao.common.ICommonDao;
import com.sm.jeesns.common.service.ICommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/2/6.
 */
@Service("commonService")
public class CommonServiceImpl implements ICommonService {
    @Resource
    private ICommonDao commonDao;

    @Override
    public String getMysqlVsesion() {
        return commonDao.getMysqlVsesion();
    }
}
