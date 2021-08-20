package com.jti.event.exception.message;
import org.springframework.context.MessageSource;

public class MessageSourceHelper {
    
    @SuppressWarnings("static-access")
	public void setMessageSource(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }

    private MessageSourceHelper()
    {
    }

    public static MessageSource getMessageSource()
    {
        return messageSource;
    }

    private static MessageSource messageSource;
}
