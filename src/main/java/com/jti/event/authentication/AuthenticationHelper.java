/**
 * 
 */
package com.jti.event.authentication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * AuthenticationHelper
 * 
 * @author tslee
 *
 */
public class AuthenticationHelper {
	private final static Log log = LogFactory.getLog(AuthenticationHelper.class);

    /**
     * getAuthentication()
     * 
     * @param request
     * @return Authentication
     */
	public static Authentication getAuthentication(HttpServletRequest request) {
		Authentication authentication = null;
		authentication = (Authentication) request.getAttribute("authentication");
		
		if(authentication == null) {
			HttpSession session = request.getSession();
			if(session != null) {
				authentication = (Authentication) session.getAttribute("authentication");
			}
			
			if(authentication == null) {
				authentication = new Authentication(); 
			}
			
			request.setAttribute("authentication", authentication);
		}

		return authentication;
	}
	
    /**
     * setAuthentication()
     * 
     * @param request
     * @param response
     * @param authentication
     * @return void
     */
	public static Authentication setAuthentication(HttpServletRequest request, Authentication authentication) {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval((60*60)*3); // 3시간
		session.setAttribute("authentication", authentication);
		request.setAttribute("authentication", authentication);
		return authentication;
	}

    /**
     * removeAuthentication()
     * 
     * @param request
     * @param response
     * @return void
     */
	public static void removeAuthentication(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("authentication");
		request.removeAttribute("authentication");
	}
    
}
