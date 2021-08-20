package com.jti.event.common.control;

import com.jti.event.common.model.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {
 
    private static final String ERROR_PATH = "/error";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int Code = Integer.valueOf(status.toString());
        if(Code == 405 || Code == 404) {
        	model.addAttribute("msg", "페이지를 찾을 수 없습니다");
        }else {
        	model.addAttribute("msg", "오류가 발생하였습니다.");
        }
       
        if(request.getAttribute("javax.servlet.forward.request_uri") != null) {
        	String request_uri =  request.getAttribute("javax.servlet.forward.request_uri").toString();
        	if(request_uri.indexOf("/adm")  > -1) model.addAttribute("url", "/adm/main/index");
        	else model.addAttribute("url", "/"); 
        }else {
        	model.addAttribute("url", "/");
        }
        
        return "error/error";
    }
    
    @ResponseBody
    @RequestMapping("/ajax/sqlinject")
    public BaseResult SqlInject(HttpServletRequest request, Model model) {
		BaseResult Result = new BaseResult();
		HttpSession session = request.getSession();
		String txtSqlInjection = session.getAttribute("txtSqlInjection") == null?  ""  : session.getAttribute("txtSqlInjection") .toString();
		
		Result.setResultCode("SQLINJECT");
		Result.setResultMsg("[SQL INJECTION] 사용 불가능한 문구가 있습니다. - ("+txtSqlInjection+")");
		return Result;
    }
    
    @ResponseBody
    @RequestMapping("/ajax/commandinject")
    public BaseResult CommandInject(HttpServletRequest request, Model model) {
		BaseResult Result = new BaseResult();
		HttpSession session = request.getSession();
		String txtCommand = session.getAttribute("txtCommand") == null?  ""  : session.getAttribute("txtCommand") .toString();
		
		Result.setResultCode("COMMANDINJECT");
		Result.setResultMsg("[COMMAND INJECTION] 사용 불가능한 문구가 있습니다. - ("+txtCommand+")");
		return Result;
    }
    
    
}