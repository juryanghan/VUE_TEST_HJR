package com.jti.event.common.model;

import lombok.Data;

@Data
public class Base extends BaseModel {
	private String regId;
	private String regDt;
	private String uptId;
	private String uptDt;
	private String delId;
	private String delDt;
	
	private String ResultCode = "0000";
	private String ResultMsg = "정상";
}