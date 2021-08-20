package com.jti.event.admin.service.admin;

import com.jti.event.admin.mapper.admin.AdminMapper;
import com.jti.event.admin.model.admin.Admin;
import com.jti.event.admin.model.admin.param.AdminParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

	@Autowired
	private AdminMapper adminMapper;

	/**
	 * 관리자 아이디 중복 체크
	 * 
	 * @param String
	 * @return int
	 */
	public int overlapCheck(String adminId) {
		return adminMapper.overlapCheck(adminId);
	}

	/**
	 * 관리자 로그인
	 * 
	 * @param CounselParam
	 * @return Admin
	 */
	public Admin getAdminLogin(AdminParam adminParam) {
		return adminMapper.getAdminLogin(adminParam);
	}
	
	
	/**
	 * 관리자 정보 
	 * 
	 * @param CounselParam
	 * @return Admin
	 */
	public Admin getAdmin(AdminParam adminParam){
		return adminMapper.getAdmin(adminParam);
	}

	/*
	 * 관리자 Count
	 * 
	 * @param AdminParam
	 * 
	 * @return int
	 */
	public int countAdmin(AdminParam adminParam) {
		return adminMapper.countAdmin(adminParam);
	}

	/*
	 * 관리자 정보 목록
	 * 
	 * @param AdminParam
	 * 
	 * @return int
	 */
	public List<Admin> listAdmin(AdminParam adminParam) {
		return adminMapper.listAdmin(adminParam);
	}

	/*
	 * 관리자 등록
	 * 
	 * @param Admin
	 * 
	 * @return int
	 */
	public int insertAdmin(Admin admin) {
		return adminMapper.insertAdmin(admin);
	}
	
	
	/*
	 * 관리자 수정
	 * 
	 * @param Admin
	 * 
	 * @return int
	 */
	public int updateAmdin(Admin admin){
		return adminMapper.updateAdmin(admin);
	}
	
	/*
	 * 관리자 삭제
	 * 
	 * @param AdminParam
	 * 
	 * @return int
	 */
	public int deleteAdminAll(AdminParam adminParam){
		return adminMapper.deleteAdminAll(adminParam);
	}

}