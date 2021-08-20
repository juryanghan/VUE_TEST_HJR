package com.jti.event.admin.model.admin;

import com.jti.event.admin.model.menu.Menu;
import com.jti.event.common.model.Base;
import lombok.Data;

import java.util.List;

@Data
public class Admin extends Base {
	private Integer adminNo;
	private String adminId;
	private String adminPw;
	private String adminName;
	private String adminTryPass;
	private Integer adminGroup;
	private String adminState;
	private String adminMenus;
	private List<Menu> menuList;
}