package com.sm.jeesns.core.exception;

import com.sm.jeesns.core.enums.Messages;

/**
 * 未登录异常
 * Created by zchuanzhao on 2017/11/15.
 */
public class NotLoginException extends JeeException {

    public NotLoginException(){
        super(Messages.UN_LOGIN);
    }
}
