package com.sm.jeesns.member.service.impl;

import com.sm.jeesns.member.dao.IValidateCodeDao;
import com.sm.jeesns.member.model.ValidateCode;
import com.sm.jeesns.member.service.IValidateCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/1/20.
 */
@Service("validateCodeService")
public class ValidateCodeServiceImpl implements IValidateCodeService {
    @Resource
    private IValidateCodeDao validateCodeDao;

    @Override
    public boolean save(ValidateCode validateCode) {
        return validateCodeDao.save(validateCode) == 1;
    }

    @Override
    public ValidateCode valid(String email, String code, int type) {
        return validateCodeDao.valid(email,code,type);
    }

    @Override
    public boolean used(Integer id) {
        return validateCodeDao.used(id) == 1;
    }
}
