package com.jti.event.interceptors;

import com.jti.event.admin.model.menu.Menu;
import com.jti.event.authentication.Authentication;
import com.jti.event.authentication.AuthenticationHelper;
import groovy.util.logging.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 인터셉터
 * 
 * @author tslee
 * @since 2014-07-15
 * 
 */

@Slf4j
@Component
public class AdminInterceptor implements HandlerInterceptor {

	private Log log = LogFactory.getLog(AdminInterceptor.class);

	/**
	 * Controller가 실행 되기 전
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if (passLoginCheckUrl(uri))
			return true;

		Authentication authentication = AuthenticationHelper.getAuthentication(request);

		if (null == authentication || authentication.getAdminNo() == 0) {
			if (uri.indexOf("/adm") > -1) {
				if (uri.indexOf("/ajax") > -1) {
					response.sendRedirect("/adm/ajax/logIn");
					return false;
				} else {
					response.sendRedirect("/adm/logIn");
					return false;
				}
			}
		} else {
			Boolean Login = true;
			List<Menu> authMenu = authentication.getMenus();
			if (authMenu.size() != 0) {
				for (Menu menu : authMenu) {
					if(menu.getMenuCode().equals("Coupon")){
						if(uri.indexOf("adm/coupon") > -1) {
							Login = false;
							break;
						}
					}
					else if(menu.getMenuCode().equals("Product")){
						if(uri.indexOf("adm/product") > -1) {
							Login = false;
							break;
						}
					}
					else if(menu.getMenuCode().equals("Order")){
						if(uri.indexOf("adm/order") > -1) {
							Login = false;
							break;
						}
					}
					else if(menu.getMenuCode().equals("Delivy")){
						if(uri.indexOf("adm/delivery") > -1) {
							Login = false;
							break;
						}
					}
					else if(menu.getMenuCode().equals("Admin")){
						if(uri.indexOf("adm/admin") > -1) {
							Login = false;
							break;
						}
					}
				}
			}

			if (Login) {
				if (uri.indexOf("/adm") > -1) {
					if (uri.indexOf("/ajax") > -1) {
						response.sendRedirect("/adm/ajax/logIn");
						return false;
					} else {
						response.sendRedirect("/adm/logIn");
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean passLoginCheckUrl(String val) {
		String url = val.toLowerCase(); 
		if (
				url.indexOf("/adm/login") > -1
				|| url.indexOf("/adm/file") > -1
				|| url.indexOf("/adm/ajax/login") > -1 
				|| url.indexOf("/adm/signin") > -1
				|| url.indexOf("/adm/logout") > -1 
				|| url.indexOf("/error") > -1 
				|| url.equalsIgnoreCase("/")) {
			return true;
		}
		return false;
	}

	/**
	 * View 화면까지 완료 후 Client에 응답하기 전
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// 이곳에서 FPS 로직을 수행한다.
		// 아직 미정
		System.out.println("afterCompletion");
	}

	/**
	 * Controller가 실행이 완료 되고 View를 호출 하기 전
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("postHandle");
	}

}
