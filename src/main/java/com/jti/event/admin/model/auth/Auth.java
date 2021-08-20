package com.jti.event.admin.model.auth;

import com.jti.event.common.model.Base;
import lombok.Data;

@Data
public class Auth extends Base {
	private Integer AuthNo;
	private Integer AdminNo;
	private String MenuCode;
	private String MenuDesc;
}