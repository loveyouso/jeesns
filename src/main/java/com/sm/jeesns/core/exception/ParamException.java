package com.sm.jeesns.core.exception;

import com.sm.jeesns.core.enums.Messages;

/**
 * 参数异常
 * Created by zchuanzhao on 2017/5/17.
 */
public class ParamException extends JeeException {

    public ParamException(String msg){
        super(msg);
    }

    public ParamException(Messages messages){
        super(messages);
    }

    public ParamException(){
        super(Messages.PARAM_ERROR);
    }
}
