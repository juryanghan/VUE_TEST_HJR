package com.jti.event.util;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class CommonUtil {
	
	/**
	 * 클라이언트 실제 IP수집
	 * @param request
	 * @return
	 */
	public static String getClientIP(HttpServletRequest request) {

	     String ip = request.getHeader("X-FORWARDED-FOR"); 
	     System.out.println("X-FORWARDED-FOR : " + ip);
	     
	     if (ip == null || ip.length() == 0) {
	         ip = request.getHeader("Proxy-Client-IP");
	         System.out.println("Proxy-Client-IP : " + ip);
	     }
	     
	     if (ip == null || ip.length() == 0) {
	         ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
	         System.out.println("WL-Proxy-Client-IP : " + ip);
	     }
	     
	     if (ip == null || ip.length() == 0) {
	         ip = request.getRemoteAddr() ;
	         System.out.println("getRemoteAddr : " + ip);
	     }
	     
	     return ip;

	 }
	
	public static String convert(String str, String encoding) throws IOException {
		ByteArrayOutputStream requestOutputStream = new ByteArrayOutputStream();
		requestOutputStream.write(str.getBytes(encoding));
		return requestOutputStream.toString(encoding);
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", ""); 
	}

}
