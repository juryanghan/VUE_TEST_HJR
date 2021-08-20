package com.jti.event.admin.service.auth;

import com.jti.event.admin.mapper.auth.AuthMapper;
import com.jti.event.admin.model.auth.Auth;
import com.jti.event.admin.model.menu.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

	@Autowired
	private AuthMapper authMapper;
	
	/*
	 * 관리자 권한 등록
	 * 
	 * @param Auth
	 * 
	 * @return int
	 */
	public int insertAuth(Auth auth) {
		return authMapper.insertAuth(auth);
	}
	
	/*
	 * 관리자 권한 삭제
	 * 
	 * @param adminNo
	 * 
	 * @return int
	 */
	public int deleteAuth(int adminNo) {
		return authMapper.deleteAuth(adminNo);
	}
	
	/*
	 * 관리자 권한 리스트
	 * 
	 * @param int
	 * @return List<Menu>
	 */
	public List<Menu> listAuth(int adminNo) {
		return authMapper.listAuth(adminNo);
	}
}