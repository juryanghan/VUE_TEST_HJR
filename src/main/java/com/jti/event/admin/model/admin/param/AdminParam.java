package com.jti.event.admin.model.admin.param;

import com.jti.event.common.model.ComDefaultVO;
import lombok.Data;


@Data
public class AdminParam extends ComDefaultVO {
	private Integer adminNo;
	private String adminId;
	private String adminPw;
	private String searchAdminLevel = "";
	private String searchAdminState = "";
	private String searchAdminGroup = "";
	private Integer adminNos[];
}