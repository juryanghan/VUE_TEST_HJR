package com.jti.event.admin.mapper.auth;

import com.jti.event.admin.model.auth.Auth;
import com.jti.event.admin.model.menu.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthMapper {
	/*
	 * 관리자 권한 등록
	 * 
	 * @param Auth
	 * @return int
	 */
	public int insertAuth(Auth auth);
	
	/*
	 * 관리자 권한 삭제
	 * 
	 * @param adminNo
	 * @return int
	 */
	public int deleteAuth(int adminNo);
	
	/*
	 * 관리자 권한 리스트
	 * 
	 * @param int
	 * @return List<Menu>
	 */
	public List<Menu> listAuth(int adminNo);
	
}