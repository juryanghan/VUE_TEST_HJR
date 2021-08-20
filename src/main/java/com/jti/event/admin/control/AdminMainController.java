package com.jti.event.admin.control;

import com.jti.event.admin.model.admin.param.AdminParam;
import com.jti.event.admin.service.admin.AdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("adm/main")
public class AdminMainController {

	private final Log log = LogFactory.getLog(AdminMainController.class);

	@Autowired
	private AdminService adminService;

	/**
	 * 메인 화면
	 * 
	 * @param req
	 * @param res
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public ModelAndView AdminList(HttpServletRequest req, HttpServletResponse res, AdminParam adminParam)  {
		log.debug("/adm/main/index");

		ModelAndView mav = new ModelAndView("adm/view/main/index");
		return mav;
	}

	

}