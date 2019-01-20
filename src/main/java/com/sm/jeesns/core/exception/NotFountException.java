package com.sm.jeesns.core.exception;

import com.sm.jeesns.core.enums.Messages;

/**
 * 找不到异常
 * Created by zchuanzhao on 2017/11/15.
 */
public class NotFountException extends JeeException {

    public NotFountException(Messages messages){
        super(messages);
    }

    public NotFountException(String msg){
        super(msg);
    }
}
