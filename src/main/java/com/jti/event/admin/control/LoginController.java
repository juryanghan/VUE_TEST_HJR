package com.jti.event.admin.control;

import com.jti.event.admin.model.admin.Admin;
import com.jti.event.admin.model.admin.param.AdminParam;
import com.jti.event.admin.model.menu.Menu;
import com.jti.event.admin.service.admin.AdminService;
import com.jti.event.admin.service.auth.AuthService;
import com.jti.event.authentication.Authentication;
import com.jti.event.authentication.AuthenticationHelper;
import com.jti.event.common.model.BaseResult;
import com.jti.event.exception.ServiceException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("adm")
public class LoginController {

	private final Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthService authService;
	
	/**
	 * index 화면
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "")
	public ModelAndView index(HttpServletRequest req, HttpServletResponse res) throws ServiceException {
		log.debug("/");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/adm/logIn");
		return mav;
	}

	/**
	 * 로그인 화면
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/logIn")
	public ModelAndView loginView(HttpServletRequest req, HttpServletResponse res) throws ServiceException {
		log.debug("/adm/logIn");

		ModelAndView mav = new ModelAndView("adm/logIn");
		return mav;
	}
	
	/**
	 * 로그인 화면
	 * 
	 * @param req
	 * @param res
	 * @return BaseResult
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/ajax/logIn")
	public BaseResult ajaxlogin(HttpServletRequest req, HttpServletResponse res) throws ServiceException {
		log.debug("/adm/ajax/logIn");
		BaseResult Result = new BaseResult();
		Result.setResultCode("LOGIN");
		Result.setResultMsg("로그인이 필요합니다.");
		return Result;
	}

	
	
	/**
	 * 로그인 요청
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 */
	/**
	 * @param req
	 * @param res
	 * @param adminParam
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public ModelAndView signIn(HttpServletRequest req, HttpServletResponse res, @ModelAttribute AdminParam adminParam)
			throws ServiceException {
		log.debug("/adm/signIn");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("adm/logIn");



		Admin admin = adminService.getAdminLogin(adminParam);

		if (null == admin) {
			mav.addObject("errorNo", "01"); // 존재하지 않는 아이디
			return mav;
		} else {
			if (!admin.getAdminPw().equals(admin.getAdminTryPass())) {
				mav.addObject("errorNo", "02"); // 비밀번호 상이
				return mav;
			}
			else if (admin.getAdminState().equals("0")) {
				mav.addObject("errorNo", "03"); // 정지된 관리자
				return mav;
			} 
			else {
				
				List<Menu> Authlist = authService.listAuth(admin.getAdminNo());
				if(Authlist == null || Authlist.size() == 0) {
					mav.addObject("errorNo", "04"); // 접근 권한 없음
					return mav;
				}
				
				admin.setMenuList(Authlist);
				Authentication authentication = new Authentication(admin);
				AuthenticationHelper.setAuthentication(req, authentication);
				mav.setViewName("redirect:"+Authlist.get(0).getUrl());
			}
		}
		return mav;
	}

	/**
	 * 로그아웃
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("/logOut")
	public ModelAndView showLogOut(HttpServletRequest req, HttpServletResponse res) throws ServiceException {
		log.debug("/adm/logOut");

		Authentication authentication = AuthenticationHelper.getAuthentication(req);
		if (authentication.getIsAuthentication())
			AuthenticationHelper.removeAuthentication(req);

		ModelAndView mav = new ModelAndView("redirect:/adm/logIn");

		return mav;
	}
}