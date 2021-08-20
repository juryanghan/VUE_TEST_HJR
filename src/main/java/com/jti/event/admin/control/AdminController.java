package com.jti.event.admin.control;

import com.jti.event.admin.model.admin.Admin;
import com.jti.event.admin.model.admin.param.AdminParam;
import com.jti.event.admin.model.auth.Auth;
import com.jti.event.admin.service.admin.AdminService;
import com.jti.event.admin.service.auth.AuthService;
import com.jti.event.authentication.Authentication;
import com.jti.event.authentication.AuthenticationHelper;
import com.jti.event.common.model.BaseResult;
import com.jti.event.common.model.PaginationInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("adm/admin")
public class AdminController {

	private final Log log = LogFactory.getLog(AdminController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private AuthService authService;

	/**
	 * 트랜잭션관리 객체
	 */
	@Autowired
	private DataSourceTransactionManager transactionManager;

	/**
	 * 관리자 리스트 화면
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView List(HttpServletRequest req, HttpServletResponse res, AdminParam adminParam) {
		log.debug("/adm/admin/list");

		ModelAndView mav = new ModelAndView("adm/view/admin/list");

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(adminParam.getCurrentPageNo());
		paginationInfo.setRecordCountPerPage(adminParam.getRecordCountPerPage());
		paginationInfo.setPageSize(adminParam.getPageSize());
		paginationInfo.setTotalRecordCount(adminService.countAdmin(adminParam));

		// 리스트 가져오기
		if (paginationInfo.getTotalRecordCount() > 0)
			mav.addObject("listAdmin", adminService.listAdmin(adminParam));

		mav.addObject("adminParam", adminParam);
		mav.addObject("paginationInfo", paginationInfo);
		return mav;
	}

	/**
	 * 관리자 등록
	 * 
	 * @param req
	 * @param res
	 * @return BaseResult
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/insert", method = RequestMethod.POST)
	public BaseResult Insert(HttpServletRequest req, HttpServletResponse res, Admin admin)
			 {
		log.debug("/adm/admin/ajax/insert");

		BaseResult Result = new BaseResult();
		Authentication authentication = AuthenticationHelper.getAuthentication(req);
		admin.setRegId(authentication.getAdminId());

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("admin_insert-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);

		try {

			if (adminService.insertAdmin(admin) < 0) {
				Result.setResultCode("9998");
				Result.setResultMsg("에러가 발생하였습니다.");
				transactionManager.rollback(status);
				return Result;
			}

			String[] AdminMenus = admin.getAdminMenus().split(",");
			for (String AdminMenu : AdminMenus) {
				Auth auth = new Auth();
				auth.setAdminNo(admin.getAdminNo());
				auth.setMenuCode(AdminMenu);
				authService.insertAuth(auth);
			}
			
			transactionManager.commit(status);

		} catch (Exception e) {
			// 오류 발생시 롤백
			transactionManager.rollback(status);

			log.error(e.toString());
			Result.setResultCode("9999");
			Result.setResultMsg("에러가 발생하였습니다.");
			return Result;
		}

		return Result;
	}

	/**
	 * 관리자 아이디 중복 검사
	 * 
	 * @param req
	 * @param res
	 * @return Map<String, String>
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/overlap", method = RequestMethod.POST)
	public BaseResult Overlap(HttpServletRequest req, String adminId)  {
		log.debug("/adm/admin/ajax/overlap");
		BaseResult Result = new BaseResult();
		if (adminId == null || adminId.isEmpty()) {
			Result.setResultCode("9999");
			Result.setResultMsg("아이디가 입력되지 않았습니다.");
			return Result;
		}

		int Overlap = adminService.overlapCheck(adminId);
		if (Overlap != 0) {
			Result.setResultCode("9998");
			Result.setResultMsg("이미 사용 중인 아이디 입니다.");
		}
		return Result;
	}

	/**
	 * 관리자 정보
	 * 
	 * @param req
	 * @param res
	 * @return Admin
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/getAdmin", method = RequestMethod.POST)
	public Admin Detail(HttpServletRequest req, HttpServletResponse res, AdminParam adminParam)
			 {
		log.debug("/adm/admin/ajax/getAdmin");
		Admin admin = new Admin();

		if (adminParam == null || adminParam.getAdminNo() < 0) {
			admin.setResultCode("9999");
			admin.setResultMsg("잘못된 접근입니다.");
			return admin;
		}

		admin = adminService.getAdmin(adminParam);
		if (admin == null || admin.getAdminNo() < 0) {
			admin.setResultCode("9999");
			admin.setResultMsg("잘못된 접근입니다.");
			return admin;
		}

		return admin;
	}

	/**
	 * 관리자 수정
	 * 
	 * @param req
	 * @param res
	 * @return BaseResult
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/update", method = RequestMethod.POST)
	public BaseResult Update(HttpServletRequest req, HttpServletResponse res, Admin admin)
			 {
		log.debug("/adm/admin/ajax/update");
		BaseResult Result = new BaseResult();
		if (admin == null || admin.getAdminNo() < 0) {
			Result.setResultCode("9999");
			Result.setResultMsg("잘못된 접근입니다.");
			return Result;
		}

		Authentication authentication = AuthenticationHelper.getAuthentication(req);
		admin.setUptId(authentication.getAdminId());

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("admin_update-transaction");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		TransactionStatus status = transactionManager.getTransaction(def);

		try {

			if (adminService.updateAmdin(admin) < 0) {
				Result.setResultCode("9998");
				Result.setResultMsg("에러가 발생하였습니다.");
				transactionManager.rollback(status);
				return Result;
			}

			authService.deleteAuth(admin.getAdminNo());
			String[] AdminMenus = admin.getAdminMenus().split(",");
			for (String AdminMenu : AdminMenus) {
				Auth auth = new Auth();
				auth.setAdminNo(admin.getAdminNo());
				auth.setMenuCode(AdminMenu);
				authService.insertAuth(auth);
			}
			
			transactionManager.commit(status);

		} catch (Exception e) {
			// 오류 발생시 롤백
			transactionManager.rollback(status);

			log.error(e.toString());
			Result.setResultCode("9999");
			Result.setResultMsg("에러가 발생하였습니다.");
			return Result;
		}

		return Result;
	}

	/**
	 * 관리자 삭제
	 * 
	 * @param req
	 * @param res
	 * @return BaseResult
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/delete", method = RequestMethod.POST)
	public BaseResult Delete(HttpServletRequest req, HttpServletResponse res, AdminParam adminParam)
			 {
		log.debug("/adm/admin/ajax/delete");

		BaseResult Result = new BaseResult();

		if (adminParam.getAdminNos() == null || adminParam.getAdminNos().length == 0) {
			Result.setResultCode("9999");
			Result.setResultMsg("잘못된 접근입니다.");
			return Result;
		}
		Authentication authentication = AuthenticationHelper.getAuthentication(req);
		adminParam.setDelId(authentication.getAdminId());

		if (adminService.deleteAdminAll(adminParam) < 0) {
			Result.setResultCode("9998");
			Result.setResultMsg("에러가 발생하였습니다.");
		}

		return Result;
	}

}