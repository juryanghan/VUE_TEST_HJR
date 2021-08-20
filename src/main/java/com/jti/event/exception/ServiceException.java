package com.jti.event.exception;

import com.jti.event.exception.message.MessageSourceHelper;
import com.jti.event.exception.model.ErrorCode;

import java.util.Locale;

public class ServiceException extends RuntimeException {
    public ServiceException(ErrorCode errCode)
    {
    	super(errCode.getDescription());
    	this.errorCode = errCode.getErrorCode();
    }

    public ServiceException(ErrorCode errCode, Object[] args)
    {
    	super(errCode.getDescription());
    	this.errorCode = errCode.getErrorCode();
    	this.args = args;
    }

    public ServiceException(ErrorCode errCode, Throwable e)
    {
    	super(errCode.getDescription(), e);
    	this.errorCode = errCode.getErrorCode();
    }
    
    public ServiceException(ErrorCode errCode, Object[] args, Throwable e)
    {
    	super(errCode.getDescription(), e);
    	this.errorCode = errCode.getErrorCode();
    }
    
    public ServiceException(ErrorCode errCode, Throwable e, Object[] args) {
      super(errCode.getDescription(), e);
      this.errorCode = errCode.getErrorCode();
      this.args = args;
    }

    public ServiceException(ErrorCode errCode, String errMsg, Throwable e) {
     super(errMsg, e);
     this.errorCode = errCode.getErrorCode();
    }

    public int getErrorCode() {
      return this.errorCode;
    }

    public Object[] getArgs() {
      return this.args;
    }

    public String getMessage(Locale locale) {
        try {
            String additionalMessage = super.getMessage();
            String message = MessageSourceHelper.getMessageSource().getMessage("ex." + String.valueOf(this.errorCode), getArgs(), "", locale);

            if ("".equals(message)) {
            	message = additionalMessage;
            }
            
            return message; 
        } catch (Exception e) {
            // no
        }
        return super.getMessage();
    }

    private static final long serialVersionUID = 1L;
    final int errorCode;
    private Object[] args;
}
