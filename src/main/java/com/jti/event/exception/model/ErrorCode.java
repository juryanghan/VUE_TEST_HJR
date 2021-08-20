/**
 * Copyright 2000-2012 tslee Co., All Rights Reserved.
 *   http://www.trans-cosmos.co.kr
 *
 * This source code is digitised property of tslee Company Limited ("tslee") and
 * protected by copyright under the law of Republic of Korea and other foreign laws.
 * Reproduction and/or redistribution of the source code without written permission of
 * tslee is not allowed. Also, it is severely prohibited to register whole or specific
 * part of the source code to any sort of information retrieval system.
 */
package com.jti.event.exception.model;

/**
 * Error interface
 * 
 * @author tslee
 * @since 2013. 3. 21
 *
 */
public interface ErrorCode {
    public abstract int getErrorCode();
    public abstract String getDescription();
}
