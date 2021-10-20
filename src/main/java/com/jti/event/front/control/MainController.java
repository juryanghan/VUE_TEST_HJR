package com.jti.event.front.control;

import com.jti.event.common.model.BaseResult;
import com.jti.event.exception.ServiceException;
import com.jti.event.front.model.event.EventMain;
import com.jti.event.front.model.event.param.EventParam;
import com.jti.event.front.service.event.EventService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
	
	private final Log log = LogFactory.getLog(MainController.class);

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest req, HttpServletResponse res) throws Exception {
		log.debug("/");
		ModelAndView mav = new ModelAndView("front/view/main/index");
		mav.setViewName("redirect:/winner");

		/*
		List<EventMain> em = new ArrayList<EventMain>();
		em = eventService.getConvenienceStore();
		mav.addObject("convenienceStore", em);
		 */
		return mav;
	}

	@RequestMapping(value = "/winner", method = RequestMethod.GET)
	public ModelAndView winner(HttpServletRequest req, HttpServletResponse res) throws Exception {
		log.debug("/winner");
		ModelAndView mav = new ModelAndView("front/view/main/winner");
		return mav;
	}

}