package com.jti.event.front.control;

import com.jti.event.common.model.BaseResult;
import com.jti.event.exception.ServiceException;
import com.jti.event.util.AES256Util;
import com.jti.event.util.CouponCreate;
import com.jti.event.util.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {
	
	private final Log log = LogFactory.getLog(MainController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest req, HttpServletResponse res) throws Exception {
		log.debug("/frt");
		ModelAndView mav = new ModelAndView("front/view/main/index");
		return mav;
	}
}