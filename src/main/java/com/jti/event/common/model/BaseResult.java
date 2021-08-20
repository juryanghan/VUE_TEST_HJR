package com.jti.event.common.model;
import lombok.Data;

@Data
public class BaseResult {
	private String ResultCode = "0000";
	private String ResultMsg = "정상";
	private Object ResultData;
}
