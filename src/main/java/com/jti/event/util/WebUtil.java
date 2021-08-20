package com.jti.event.util;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 프로그램명  : WebUtil.java
 * 작 성 일    : Oct 1, 2009
 * 작 성 자    : tslee
 * 프로그램 설명 : www 공통유틸
 */

public class WebUtil {
	public static final String DEFAULT_CHARSET = "UTF-8";
	public static final String EMPTY_STRING = "";
	public static final int EMPTY_INT = 0;
	public static final long EMPTY_LONG = 0;
	public static final float EMPTY_FLOAT = (float)0.0;
	public static final double EMPTY_DOUBLE = 0.0;
	public static final String SITE_DOMAIN_NAME = "";
	public static final String DEFAULT_ALERT_TITLE = "alert";
	private static String ACTION_HTML_BEGIN = "<!DOCTYPE html>\n<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"ko\">\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><head><title>{0}</title></head>\n<body>\n<script type=\"text/javascript\">\n";
	private static String ACTION_HTML_END = "\n</script>\n</body>\n</html>";
	
	/**
	 * 입력받은 문자열이 null 이면 빈 문자열을 리턴한다.
	 * null 이 아니라면 입력받은 문자열을 다시 리턴한다.
	 */
	public static String checkNull(String str) {
		return checkNull(str, EMPTY_STRING);
	}

	/**
	 * 입력받은 문자열이 null 이면 파라미터로 넘긴 기본값을 리턴한다.
	 * null 이 아니라면 입력받은 문자열을 다시 리턴한다.
	 */
	public static String checkNull(String str, String defaultString) {
		if (str == null) {
			return defaultString;
		}
		return str;
	}

	public static int checkInt(String str) {
		return checkInt(str, EMPTY_INT);
	}

	public static int checkInt(String str, int defaultInt) {
		if (str == null) {
			return defaultInt;
		}
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return defaultInt;
		}
	}

	public static long checkLong(String str) {
		return checkLong(str, EMPTY_LONG);
	}

	public static long checkLong(String str, long defaultLong) {
		if (str == null) {
			return defaultLong;
		}
		try {
			return Long.parseLong(str);
		} catch (NumberFormatException nfe) {
			return defaultLong;
		}
	}

	public static float checkFloat(String str) {
		return checkFloat(str, EMPTY_FLOAT);
	}

	public static float checkFloat(String str, float defaultFloat) {
		if (str == null) {
			return defaultFloat;
		}
		try {
			return Float.parseFloat(str);
		} catch (NumberFormatException nfe) {
			return defaultFloat;
		}
	}

	public static double checkDouble(String str) {
		return checkDouble(str, EMPTY_DOUBLE);
	}

	public static double checkDouble(String str, double defaultDouble) {
		if (str == null) {
			return defaultDouble;
		}
		try {
			return Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return defaultDouble;
		}
	}

	/**
	 * 문자열의 byte size를 구한다.
	 */
	public static int getByteSize(String str) {
		if (str == null) {
			return 0;
		}
		int size = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > 127) {
				size = size + 2;
			} else {
				size++;
			}
		}
		return size;
	}

	/**
	 * 문자열의 byte size를 구한다.(UTF-8 인코딩 기준으로)
	 */
	public static int getByteSizeUtf8(String str) {
		if (str == null) {
			return 0;
		}
		int size = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > 127) {
				size = size + 3;
			} else {
				size++;
			}
		}
		return size;
	}

	/**
	 * String을 읽어 지정한 문자가 있는지 check
	 *
	 * @param s source String
	 */
	public static boolean isContain(String s, String checkString) {
		if (s == null || s.length() == 0 || checkString == null) {
			return false;
		}
		int i = s.length();
		for (int j = 0; j < i; j++) {
			char c = s.charAt(j);
			if (checkString.indexOf(c) == -1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * String[]에 지정한 String이 있는지 check
	 *
	 * @param s source String
	 */
	public static boolean isContain(String[] s, String checkString) {
		if (s == null || s.length == 0) {
			return false;
		}
		int i = s.length;
		for (int j = 0; j < i; j++) {
			String t = s[j];
			if (checkString.equals(t)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * String을 읽어 영문, 숫자만 있는지 check
	 *
	 * @param s source String
	 */
	public static boolean isAlphaNum(String s) {
		return isRexMatch("^[a-zA-Z0-9]+$", s);
	}

	public static boolean PassMatchCheck(String Password, String Password_re) {
		if (!Password.equals(Password_re)) {
			return false;
		}
		return true;
	}

	/**
	 * String을 읽어 영문과 숫자만 있는지 check
	 * 영문, 숫자 외에 validString 파라메터문자열의 내용도 허용한다.
	 *
	 * @param s
	 * @param checkString
	 * @return
	 */
	public static boolean isAlphaNum(String s, String checkString) {
		String validStr = checkString
				+ "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return isContain(s, validStr);
	}

	/**
	 * String을 읽어 영문만 있는지 check
	 *
	 * @param s source String
	 */
	public static boolean isAlpha(String s) {
		return isRexMatch("^[a-zA-Z]+$", s);
	}

	/**
	 * String을 읽어 영문과 숫자만 있는지 check
	 * 영문, 숫자 외에 validString 파라메터문자열의 내용도 허용한다.
	 *
	 * @param s
	 * @param checkString
	 * @return
	 */
	public static boolean isAlphaSmallNum(String s, String checkString) {
		String validStr = checkString
				+ "0123456789abcdefghijklmnopqrstuvwxyz";
		return isContain(s, validStr);
	}

	/**
	 * String을 읽어 영문소문자숫자만 있는지 check
	 *
	 * @param s source String
	 */
	public static boolean isAlphaSmallNum(String s) {
		return isRexMatch("^[a-z0-9]+$", s);
	}

	/**
	 * String을 읽어 영문소문자만 있는지 check
	 *
	 * @param s source String
	 */
	public static boolean isAlphaSmall(String s) {
		return isRexMatch("^[a-z]+$", s);
	}

	/**
	 * String을 읽어 영문만 있는지 check
	 * 영문 외에 validString 파라메터문자열의 내용도 허용한다.
	 *
	 * @param s
	 * @param checkString
	 * @return
	 */
	public static boolean isAlpha(String s, String checkString) {
		String validStr = checkString
				+ "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return isContain(s, validStr);
	}

	/**
	 * String을 읽어 숫자만 있는지 check
	 *
	 * @param s source String
	 */
	public static boolean isNum(String s) {
		return isRexMatch("^[0-9]+$", s);
	}
	
	
	/**
	 * String을 읽어 숫자만 있는지 check
	 * 숫자 외에 validString 파라메터문자열의 내용도 허용한다.
	 *
	 * @param s
	 * @param checkString
	 * @return
	 */
	public static boolean isNum(String s, String checkString) {
		String validStr = checkString + "01234567890";
		return isContain(s, validStr);
	}

	/**
	 * 유효한 이메일 주소인지 체크한다.
	 * null이면 false
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
//		return GenericValidator.isEmail(email);

		if(email == null) return false;

		Pattern ps = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+",Pattern.CASE_INSENSITIVE);

		Matcher m = ps.matcher(email);

		return m.matches();
	}

	/**
	 * 유효한 pattern인지 체크한다.
	 * null이면 false
	 * @param email
	 * @return
	 */
	public static boolean isRexMatchCase(String rex, String cmpstr) {
		if(cmpstr == null) return false;

		Pattern ps = Pattern.compile(rex,Pattern.CASE_INSENSITIVE);

		Matcher m = ps.matcher(cmpstr);

		return m.matches();
	}

	/**
	 * 유효한 pattern인지 체크한다.
	 * null이면 false
	 * @param email
	 * @return
	 */
	public static boolean isRexMatch(String rex, String cmpstr) {
		if(cmpstr == null) return false;

		Pattern ps = Pattern.compile(rex);

		Matcher m = ps.matcher(cmpstr);

		return m.matches();
	}

	/**
	 * Convert a String to a boolean <p>대소문자 상관없이
	 * "true","yes","ok","okay","on","1"인 경우 true를 return한다.
	 *
	 * @param data the thing to convert
	 * @return the converted data
	 */
	public static boolean isTrue(String data) {
		if (data == null)
			return false;
		if (data.equalsIgnoreCase("true"))
			return true;
		if (data.equalsIgnoreCase("yes"))
			return true;
		if (data.equalsIgnoreCase("ok"))
			return true;
		if (data.equalsIgnoreCase("okay"))
			return true;
		if (data.equalsIgnoreCase("on"))
			return true;
		if (data.equalsIgnoreCase("1"))
			return true;
		if (data.equalsIgnoreCase("Y"))
			return true;

		return false;
	}

	/**
	 * Convert a String to a boolean <p>대소문자 상관없이
	 * "true","yes","ok","okay","on","1"인 경우 true를 return한다.
	 *
	 * @param data the thing to convert
	 * @return the converted data
	 */
	public static boolean isYesNo(String data) {
		if (data == null)
			return false;
		if (data.equalsIgnoreCase("Y"))
			return true;
		if (data.equalsIgnoreCase("N"))
			return true;
		return false;
	}
	/**
	 * URL encoding.
	 *
	 * @param s
	 * @return String
	 */
	public static String encodeURL(String s) {
		try {
			return URLEncoder.encode(s, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}

	/**
	 * 반각 문자만 있는지 check
	 * @param s
	 * @return
	 */
	public static boolean isHalfWord(String s) {
		int len = s.length();
		for (int i = 0; i < len; i++) {
			if (s.charAt(i) > 127) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 문자열의 사이즈를 byte로 check
	 *
	 * @param s
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isRangeByte(String s, int min, int max) {
		int size = getByteSize(s);
		if (size < min || size > max) {
			return false;
		}
		return true;
	}

	/**
	 * 문자열의 사이즈를 byte로 check
	 *
	 * @param s
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isRangeDb(String s, int min, int max) {
		int size = getByteSizeUtf8(s);
		if (size < min || size > max) {
			return false;
		}
		return true;
	}

	/**
	 * 문자열의 사이즈를 문자수로 check
	 *
	 * @param s
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isRangeLength(String s, int min, int max) {
		int size = s.length();
		if (size < min || size > max) {
			return false;
		}
		return true;
	}

	/**
	 * URL decoding.
	 *
	 * @param s
	 * @return String
	 */
	public static String decodeURL(String s) {
		try {
			return URLDecoder.decode(s, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			return s;
		}
	}

	/**
	 * 파일 Full Path에서 파일명만 가져온다.
	 * @param fullpath
	 * @return
	 */
	public static String getFileName(String fullpath) {
		String fileName;

		// check for Unix-style path
		int pos = fullpath.lastIndexOf("/");
		if (pos == -1) {
			// check for Windows-style path
			pos = fullpath.lastIndexOf("\\");
		}
		if (pos != -1) {
			// any sort of path separator found
			fileName = fullpath.substring(pos + 1);
		} else {
			// plain name
			fileName = fullpath;
		}

		return fileName;
	}

	public static String getFileExt(String filename) {
		String ext;
		int pos = filename.lastIndexOf('.');
		if (pos != -1) {
			ext = filename.substring(pos + 1);
		} else {
			ext = "";
		}
		return ext;
	}

	// 게시판 내용 filter
	// <script 테그 변환
	// <a 테그에 target 지정
	public static String filterContent(String content)
	{
		content = content.replaceAll("(?i)<script", "&ltscript");
		content = content.replaceAll("(?i)</script", "&lt/script");
		content = content.replaceAll("(?i)<a", "<a target='_blank'");
		return content;
	}
	
	
	/**
	 * parent forward
	 */
	public static void parentForward(String retUrl, PrintWriter w) {
		w.write(MessageFormat.format(ACTION_HTML_BEGIN, ""));
		w.write("parent.location.href='" + retUrl + "';");
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}

	/**
	 * parent reload
	 */
	public static void parentReload(PrintWriter w) {
		w.write(MessageFormat.format(ACTION_HTML_BEGIN, ""));
		w.write("parent.location.reload();");
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}

	/**
	 * parent reload
	 * @param message
	 */
	public static void alertAndParentReload(String message, HttpServletResponse res) throws IOException {
		res.setContentType("text/html; utf-8");
		alertAndParentReload(message, res.getWriter(), DEFAULT_ALERT_TITLE);
	}
	
	public static void alertAndParentReload(String message, PrintWriter w, String title) {
		message = message.replaceAll("\"", "'").replaceAll("\\\\n", "<br>");
		
		w.write(MessageFormat.format(ACTION_HTML_BEGIN, title));
		if (!message.equals("")) {
			w.write("alert(\"" + message + "\");");
		}
		w.write("parent.location.reload();");
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}

	/**
	 * alert() 후 parent forward
	 * @param message
	 */
	public static void alertAndParentForward(String message, String retUrl, HttpServletResponse res) throws IOException {
		res.setContentType("text/html; utf-8");
		alertAndParentForward(message, retUrl, res.getWriter(), DEFAULT_ALERT_TITLE);
	}

	public static void alertAndParentForwardAlert(String message, String retUrl, PrintWriter w) throws IOException {
		message = message.replaceAll("\"", "'").replaceAll("\\\\n", "<br>");
		
		w.write(MessageFormat.format(ACTION_HTML_BEGIN, ""));
		if (!message.equals("")) {
			w.write("alert(\"" + message + "\");");
		}
		if (retUrl != null)
			w.write("parent.location.href='" + retUrl + "';");
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}

	public static void alertAndParentForward(String message, String retUrl, PrintWriter w, String title) {
		message = message.replaceAll("\"", "'").replaceAll("\\\\n", "<br>");
		
		
		w.write(MessageFormat.format(ACTION_HTML_BEGIN, title));
		if (!message.equals("")) {
			w.write("alert(\"" + message + "\");");
		}
		if (retUrl != null)
			w.write("parent.location.href='" + retUrl + "';");
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}

	public static void alert(String message, HttpServletResponse res) throws IOException {
		res.setContentType("text/html; utf-8");
		alert(message, res.getWriter(),  DEFAULT_ALERT_TITLE);
	}

	public static void alert(String message, PrintWriter w, String title) {
		message = message.replaceAll("\"", "'").replaceAll("\\\\n", "<br>");
		
		w.write(MessageFormat.format(ACTION_HTML_BEGIN, title));
		if (!message.equals("")) {
			w.write("alert(\"" + message + "\");");
		}
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}

	/**
	 * alert() 후 이전 페이지로 이동
	 * @param message
	 * @throws IOException 
	 */

	public static ModelAndView alertAndBack(String message, HttpServletResponse res) throws IOException {
		res.setContentType("text/html;charset=UTF-8");
		alertAndBack(message, res.getWriter(), DEFAULT_ALERT_TITLE);
		return new ModelAndView("error/error");
	}

	public static void alertAndBack(String message, PrintWriter w, String title) {
		message = message.replaceAll("\"", "'").replaceAll("\\\\n", "<br>");
		
		w.write(MessageFormat.format(ACTION_HTML_BEGIN, title));
		if (!message.equals("")) {
			w.write("alert(\"" + message + "\");");
		}
		w.write("history.go(-1);");
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}

	/**
	 * alert() 후 해당 url로 이동
	 * @param message
	 */
	public static ModelAndView alertAndForward(String message, String retUrl, HttpServletResponse res) throws IOException {
		res.setContentType("text/html;charset=UTF-8");
		alertAndForward(message, retUrl, res.getWriter(), DEFAULT_ALERT_TITLE);
		return new ModelAndView("error/error");
	}
	/**
	 * alert() 후 해당 url로 이동
	 * @param message
	 */
	public static void alertAndForward(String message, String retUrl, PrintWriter w, String title) {
		message = message.replaceAll("\"", "'").replaceAll("\\\\n", "<br>");
		
		w.write(MessageFormat.format(ACTION_HTML_BEGIN, title));
		if (!message.equals("")) {
			w.write("alert(\"" + message + "\");");
		}
		w.write("location.href = '" + retUrl + "';");
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}


	/**
	 * alert() 후 창닫기, 부모창 refresh (popup용)
	 * @param message
	 */
	public static void alertAndClose(String message, boolean openerRefresh, HttpServletResponse res) throws IOException {
		res.setContentType("text/html; utf-8");
		alertAndClose(message, openerRefresh, res.getWriter(), DEFAULT_ALERT_TITLE);
	}

	public static void alertAndClose(String message, boolean openerRefresh, PrintWriter w, String title) {
		message = message.replaceAll("\"", "'").replaceAll("\\\\n", "<br>");
		
		w.write(MessageFormat.format(ACTION_HTML_BEGIN, title));
		if (!message.equals("")) {
			w.write("alert(\"" + message + "\");");
		}

		if (openerRefresh) {
			w.write("top.opener.history.go(0);");
		}
		w.write("top.window.close();");
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}
	/**
	 * alert() 후 창닫기, 부모창 이동 (popup용)
	 * @param message
	 */
	public static void alertAndClose(String message, String returl, HttpServletResponse res) throws IOException {
		res.setContentType("text/html; utf-8");
		alertAndClose(message, returl, res.getWriter(), DEFAULT_ALERT_TITLE);
	}
	public static void alertAndClose(String message, String returl, PrintWriter w, String title) {
		message = message.replaceAll("\"", "'").replaceAll("\\\\n", "<br>");

		w.write(MessageFormat.format(ACTION_HTML_BEGIN, title));
		if (!message.equals("")) {
			w.write("alert(\"" + message + "\");");
		}

		w.write("top.opener.location.href = '" + returl + "';");
		w.write("top.window.close();");
		w.write(ACTION_HTML_END);
		w.flush();
		w.close();
	}
	
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

}