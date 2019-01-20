package com.sm.jeesns.core.exception;


import com.sm.jeesns.core.enums.Messages;

/**
 * @author zchuanzhao
 */
public class OpeErrorException extends JeeException {

    public OpeErrorException(){
        super(Messages.ERROR);
    }

    public OpeErrorException(Messages message){
        super(message);
    }

    public OpeErrorException(String message){
        super(message);
    }
}
