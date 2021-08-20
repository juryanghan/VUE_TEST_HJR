package com.jti.event.admin.model.menu;


import com.jti.event.common.model.Base;
import lombok.Data;

@Data
public class Menu extends Base {
	private Integer MenuNo;
	private String MenuCode;
	private String MenuTitle;
	private String MenuDesc;
	private String Url;
	private Integer SortNo;
}