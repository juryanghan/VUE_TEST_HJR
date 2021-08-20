package com.jti.event.admin.mapper.admin;

import com.jti.event.admin.model.admin.Admin;
import com.jti.event.admin.model.admin.param.AdminParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
	/**
	 * 관리자 아이디 중복 체크
	 * 
	 * @param String
	 * @return int
	 */
	public int overlapCheck(String adminId);

	/**
	 * 관리자 로그인
	 * 
	 * @param AdminOrderParam
	 * @return Admin
	 */
	public Admin getAdminLogin(AdminParam adminParam);
	
	/**
	 * 관리자 정보 
	 * 
	 * @param AdminOrderParam
	 * @return Admin
	 */
	public Admin getAdmin(AdminParam adminParam);

	
	/*
	 * 관리자 Count
	 * 
	 * @param AdminParam
	 * 
	 * @return int
	 */
	public int countAdmin(AdminParam adminParam);

	/*
	 * 관리자 정보 목록
	 * 
	 * @param AdminParam
	 * 
	 * @return int
	 */
	public List<Admin> listAdmin(AdminParam adminParam);

	/*
	 * 관리자 등록
	 * 
	 * @param Admin
	 * 
	 * @return int
	 */
	public int insertAdmin(Admin admin);
	
	/*
	 * 관리자 수정
	 * 
	 * @param Admin
	 * 
	 * @return int
	 */
	public int updateAdmin(Admin admin);
	
	
	/*
	 * 관리자 권한 삭제
	 * 
	 * @param AdminParam
	 * 
	 * @return int
	 */
	public int deleteAdminAll(AdminParam adminParam);
	
}