package com.jti.event.admin.model.event;

import com.jti.event.common.model.Base;
import com.jti.event.util.AES256Util;
import lombok.Data;

import java.io.UnsupportedEncodingException;

@Data
public class Store extends Base {

    private Integer convenienceStoreNo;
    private String convenienceStoreName;


}
