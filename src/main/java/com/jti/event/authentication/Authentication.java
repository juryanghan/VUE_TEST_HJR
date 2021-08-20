/**
 * 
 */
package com.jti.event.authentication;

import com.jti.event.admin.model.admin.Admin;
import com.jti.event.admin.model.menu.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Authentication
 * 
 * @author tslee
 *
 */
public class Authentication {

	private int adminNo;
	private String adminId;
	private String adminName;
	private int adminGroup;
	private String authMenus;
	private List<Menu> Menus;
	
	private Map<String, String> extendedData = new HashMap<String, String>();
	
	private boolean isAuthentication;
	
	public Authentication() {
		this.adminNo = 0;
		
		this.adminId = "";
		this.authMenus = "";
		this.isAuthentication = false;
		
	}

	public Authentication(Admin admin) {
		this.adminNo = admin.getAdminNo();
		this.adminId = admin.getAdminId();
		this.adminGroup = admin.getAdminGroup();
		this.Menus = admin.getMenuList();
		this.isAuthentication = true;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	

	public int getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(int adminGroup) {
		this.adminGroup = adminGroup;
	}

	public String getAuthMenus() {
		return authMenus;
	}

	public void setAuthMenus(String authMenus) {
		this.authMenus = authMenus;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public List<Menu> getMenus() {
		return Menus;
	}

	public void setMenus(List<Menu> Menus) {
		this.Menus = Menus;
	}
	
	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	
	
	public boolean getIsAuthentication() {
		return isAuthentication;
	}

	public void setAuthentication(boolean isAuthentication) {
		this.isAuthentication = isAuthentication;
	}

	public Map<String, String> getExtendedData() {
		return extendedData;
	}

	public void setExtendedData(Map<String, String> extendedData) {
		this.extendedData = extendedData;
	}

}
