package com.jti.event.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.http.HttpServletRequest;
import java.lang.Character.UnicodeBlock;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class StringUtil.
 */
public class StringUtil {

	private static final Log log = LogFactory.getLog(StringUtil.class);

	/**
	 * Checks if is empty.
	 *
	 * @param str
	 *            the str
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.length() == 0);
	}
	
	/**
	 * Object Null 체크
	 * @param s
	 * @return
	 */
	public static boolean isNull(Object obj){
		return obj == null;
	}

	/**
	 * 주어진 문장열이 공백(빈칸,개행문자,탭문자)로만 이루어져 있는지 확인한다.
	 *
	 * @param str
	 *            :검사할 문자열
	 * @return 공백으로만 이루어졌다면 tue 아니면 false
	 */
	public static boolean hasOnlySpace(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * if String is null or "".
	 *
	 * @param str
	 *            the str
	 * @return true/false
	 */
	public static boolean isBlank(String str) {

		return (str == null || "".equals(str));
	}

	/**
	 * return default value for integer if the object is null.
	 *
	 * @param i
	 *            the i
	 * @return the int
	 */
	public static int nvlInt(int i) {

		return ((Object) i == null ? 0 : i);
	}

	/**
	 * if String is "" , return Default Value.
	 *
	 * @param str1
	 *            the str1
	 * @param str2
	 *            the str2
	 * @return the string
	 */
	public static String defVal(String str1, String str2) {

		return ("".equals(nvl(str1)) ? str2 : str1);
	}

	/**
	 * Def val int.
	 *
	 * @param int1
	 *            the int1
	 * @param int2
	 *            the int2
	 * @return the int
	 */
	public static int defValInt(int int1, int int2) {

		return (nvlInt(int1) == 0 ? int2 : int1);
	}

	/**
	 * Def val double.
	 *
	 * @param str
	 *            the str
	 * @return the double
	 */
	public static double defValDouble(String str) {

		if ("".equals(nvl(str))) {

			return 0;

		} else {

			try {
				return new Double(str);
			} catch (NumberFormatException nfex) {
				return 0;
			}
		}
	}

	/**
	 * if request.getParameter is null , return ""
	 *
	 * @param request
	 *            the request
	 * @param pname
	 *            the pname
	 * @return "" or request.getParameter
	 */
	public static String getParam(HttpServletRequest request, String pname) {
		return request.getParameter(pname);
	}

	/**
	 * if request.getParameterValues is null , return []
	 *
	 * @param request
	 *            the request
	 * @param pname
	 *            the pname
	 * @return "" or request.getParameterValues
	 */
	public static String[] getParamValues(HttpServletRequest request, String pname) {
		return request.getParameterValues(pname);
	}

	public static String getParameter(HttpServletRequest request, String pname) {
		String tempValue = request.getParameter(pname);
		if (tempValue == null) {
			tempValue = "";
		}

		return tempValue;
	}

	/**
	 * if request.getParameter is null , return 0
	 *
	 * @param request
	 *            the request
	 * @param pname
	 *            the pname
	 * @return the param int
	 */
	public static int getParamInt(HttpServletRequest request, String pname) {

		if (request.getParameter(pname) == null) {

			return 0;

		} else {

			try {
				return Integer.parseInt(request.getParameter(pname));
			} catch (NumberFormatException nfex) {
				return 0;
			}
		}

	}

	/**
	 * Gets the params.
	 *
	 * @param request
	 *            the request
	 * @param pname
	 *            the pname
	 * @return the params
	 */
	public static String getParams(HttpServletRequest request, String pname) {

		String values = "";
		Enumeration<String> paramNames = request.getAttributeNames();

		while (paramNames.hasMoreElements()) {
			String name = (String) paramNames.nextElement();

			if (name.equals(pname)) {
				values = nvl((String) request.getAttribute(name));
				break;
			}
		}

		return values;
	}

	/** The Constant HEX_DIGITS. */
	private static final String HEX_DIGITS = "0123456789abcdef";

	/**
	 * 문자열이 null일때 ""를 리턴한다.
	 *
	 * @param obj
	 *            the obj
	 * @return String
	 */
	public static String nvl(Object obj) {
		return nvl(obj, "");
	}

	/**
	 * 문자열이 null일때 ""를 리턴한다.
	 *
	 * @param obj
	 *            the obj
	 * @param ifNull
	 *            the if null
	 * @return String
	 */
	public static String nvl(Object obj, String ifNull) {
		return (obj != null) ? obj.toString() : ifNull;
	}
	
	/**
	 * 문자열이 null일이거나 ""일때 두번째 인자값을 리턴한다.
	 *
	 * @param obj
	 *            the obj
	 * @param ifNull
	 *            the if null
	 * @return String
	 */
	public static String nvlAndEmpty(Object obj, String ifNull) {
		return (obj != null && !"".equals(obj)) ? obj.toString() : ifNull;
	}

	/**
	 * HTML 문자들을 HTML에서 사용되는 특수문자로 변환한다. 대상 HTML 문자 : <, >, “, ‘, &, ⓒ
	 *
	 * @param src
	 *            the src
	 * @return String
	 */
	public static String encodeHtmlToText(String src) {
		src = src.replaceAll("<", "&lt;");
		src = src.replaceAll(">", "&gt;");
		src = src.replaceAll("\"", "&quot;");
		src = src.replaceAll("'", "&#146;");
		src = src.replaceAll("&", "&#38;");
		src = src.replaceAll("ⓒ", "&#169;");
		return src;
	}

	/**
	 * HTML에서 사용되는 특수문자를 HTML 문자들로 변환한다. 대상 HTML 문자 : <, >, “, ‘, &, ⓒ
	 *
	 * @param src
	 *            the src
	 * @return String
	 */
	public static String encodeTextToHtml(String src) {
		src = src.replaceAll("&lt;", "<");
		src = src.replaceAll("&gt;", ">");
		src = src.replaceAll("&quot;", "\"");
		src = src.replaceAll("&#146;", "'");
		src = src.replaceAll("&#38;", "&");
		src = src.replaceAll("&#169;", "ⓒ");
		return src;
	}

	/**
	 * JavaScript 변수전달시 스크립트 풀림 방지. 대상 HTML 문자 : " ' test('ad's') ->
	 * test('ad&#146;s')
	 *
	 * @param src
	 *            the src
	 * @return String
	 */
	public static String escapeJavaScriptParam(String src) {
		src = src.replaceAll("\"", "&quot;");
		src = src.replaceAll("'", "&#146;");
		return src;
	}

	/**
	 * JavaScript 변수전달시 스크립트 풀림 방지 복원. 대상 HTML 문자 : " '
	 *
	 * @param src
	 *            the src
	 * @return String
	 */
	public static String unescapeJavaScriptParam(String src) {
		src = src.replaceAll("&quot;", "\"");
		src = src.replaceAll("&#146;", "'");
		return src;
	}

	/**
	 * 일반 텍스트에서 '\n' 문자를 HTML의 '<br>
	 * ' 로 바꿔준다.
	 *
	 * @param src
	 *            the src
	 * @return String
	 */
	public static String convertNewLineToBRTag(String src) {
		if (src != null) {
			return src.replaceAll("\n", "<br>");
		} else {
			return src;
		}
	}

	/**
	 * 주어진 문자열에서 html tag를 모두 제거한 값을 반환한다.
	 *
	 * @param str
	 *            the str
	 * @return String
	 */
	public static String removeAllHtmlTag(String str) {

		if (str == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();

		char[] c = str.toCharArray();
		int len = c.length;
		boolean inTag = false;
		for (int index = 0; index < len; index++) {
			if (!inTag) {
				if (c[index] == '<') {
					inTag = true;
				} else {
					buffer.append(c[index]);
				}
			} else {
				if (c[index] == '>') {
					inTag = false;
				}
			}
		}
		return buffer.toString();
	}

	/**
	 * 문자열을 지정한 size 공간내에서 가운데로 정렬시킨다.
	 *
	 * @param str
	 *            - 가운데 정렬시킬 문자열.
	 * @param size
	 *            - 공간.
	 * @return String - 가운데로 정렬된 문자열.
	 */
	public static String center(String str, int size) {
		return center(str, size, " ");
	}

	/**
	 * 문자열을 지정한 size 공간내에서 가운데로 정렬시킨다.
	 *
	 * @param str
	 *            - 가운데 정렬시킬 문자열.
	 * @param size
	 *            - 공간.
	 * @param delim
	 *            - 정렬시킨 문자열 양 옆에 채울 문자열.
	 * @return String - 가운데로 정렬된 문자열.
	 * @throws NullPointerException
	 *             - str 이나 delim 파라메터가 null일 경우.
	 * @throws ArithmeticException
	 *             - delim 파라메터가 비어있는(empty) 문자열일 경우.
	 */
	public static String center(String str, int size, String delim) {
		// int sz = str.length();
		// 한글 처리를 위해 다음의 코드로 바꿈.
		int sz = lengthHan(str);
		int p = size - sz;
		if (p < 1) {
			return str;
		}
		str = leftPad(str, sz + p / 2, delim);
		str = rightPad(str, size, delim);
		return str;
	}

	/**
	 * str 을 포함하는 size 크기의 문자열을 만든다.<br>
	 * str 의 length 가 size 보다 작다면 str 왼쪽 공간은 공백으로 채워진다.
	 *
	 * @param str
	 *            - 문자열.
	 * @param size
	 *            - 전체 문자열 크기.
	 * @return left padded String
	 * @throws NullPointerException
	 *             if str is null
	 */
	public static String leftPad(String str, int size) {
		return leftPad(str, size, " ");
	}

	/**
	 * str 을 포함하는 size 크기의 문자열을 만든다.<br>
	 * str 의 length 가 size 보다 작다면 str 왼쪽 공간은 delim으로 채워진다.
	 *
	 * @param str
	 *            - 문자열.
	 * @param size
	 *            - 전체 문자열 크기.
	 * @param delim
	 *            - 반복할 문자열.
	 * @return left padded String
	 * @throws NullPointerException
	 *             - if str or delim is null
	 * @throws ArithmeticException
	 *             - if delim is the empty string
	 */
	public static String leftPad(String str, int size, String delim) {
		// size = (size - str.length()) / delim.length();
		// 한글 처리를 위해 다음의 코드로 바꿈.
		size = (size - lengthHan(str)) / lengthHan(delim);
		if (size > 0) {
			str = repeat(delim, size) + str;
		}
		return str;
	}

	/**
	 * str 을 포함하는 size 크기의 문자열을 만든다. str 의 length 가 size 보다 작다면 str 오른쪽 공간은 공백으로
	 * 채워진다.
	 *
	 * @param str
	 *            - 문자열.
	 * @param size
	 *            - 전체 문자열 크기.
	 * @return right padded String
	 * @throws NullPointerException
	 *             if str is null.
	 */
	public static String rightPad(String str, int size) {
		return rightPad(str, size, " ");
	}

	/**
	 * Right pad a String with a specified string. Pad to a size of n.
	 * <p>
	 * str 을 포함하는 size 크기의 문자열을 만든다. str 의 length 가 size 보다 작다면 str 오른쪽 공간은
	 * delim 문자열로 반복해 채워진다.
	 *
	 * @param str
	 *            문자열.
	 * @param size
	 *            전체 문자열 크기.
	 * @param delim
	 *            반복할 문자열.
	 * @return right padded String
	 * @throws NullPointerException
	 *             if str or delim is null
	 * @throws ArithmeticException
	 *             if delim is the empty string
	 */
	public static String rightPad(String str, int size, String delim) {
		// size = (size - str.length()) / delim.length();
		// 한글 처리를 위해 다음의 코드로 바꿈.
		size = (size - lengthHan(str)) / lengthHan(delim);
		if (size > 0) {
			str += repeat(delim, size);
		}
		return str;
	}

	public static String rightPad2(String str, int size, String delim) {

		size = (size - str.length()) / lengthHan(delim);

		if (size > 0) {
			str += repeat(delim, size);
		}
		return str;
	}

	/**
	 * 지정한 문자열에서 왼쪽 len 크기 만큼 잘라낸다.
	 *
	 * @param str
	 *            - 원본 문자열.
	 * @param len
	 *            - 왼쪽에서 잘라낼 크기.
	 * @return String - 잘라내어진 문자열.
	 * @exception IllegalArgumentException
	 *                if len is less than zero
	 */
	public static String left(String str, int len) {
		if (len < 0) {
			throw new IllegalArgumentException("Requested String length " + len + " is less than zero");
		}
		if ((str == null) || (str.length() <= len)) {
			return str;
		} else {
			return str.substring(0, len);
		}
	}

	/**
	 * 지정한 문자열에서 오른쪽 len 크기 만큼 잘라낸다.
	 *
	 * @param str
	 *            - 원본 문자열.
	 * @param len
	 *            - 왼쪽에서 잘라낼 크기.
	 * @return String - 잘라내어진 문자열.
	 * @exception IllegalArgumentException
	 *                if len is less than zero
	 */
	public static String right(String str, int len) {
		if (len < 0) {
			throw new IllegalArgumentException("Requested String length " + len + " is less than zero");
		}
		if ((str == null) || (str.length() <= len)) {
			return str;
		} else {
			return str.substring(str.length() - len);
		}
	}

	/**
	 * 지정한 문자열에서 pos 위치부터 len 크기까지 잘라낸다.
	 *
	 * @param str
	 *            - 원본 문자열.
	 * @param pos
	 *            - 시작 위치.
	 * @param len
	 *            - 왼쪽에서 잘라낼 크기.
	 * @return String - 잘라내어진 문자열.
	 * @exception IndexOutOfBoundsException
	 *                if pos is out of bounds
	 * @exception IllegalArgumentException
	 *                if len is less than zero
	 */
	public static String mid(String str, int pos, int len) {
		if ((pos < 0) || (str != null && pos > str.length())) {
			throw new StringIndexOutOfBoundsException("String index " + pos + " is out of bounds");
		}
		if (len < 0) {
			throw new IllegalArgumentException("Requested String length " + len + " is less than zero");
		}
		if (str == null) {
			return null;
		}
		if (str.length() <= (pos + len)) {
			return str.substring(pos);
		} else {
			return str.substring(pos, pos + len);
		}
	}

	/**
	 * 문자열에서 시작으로"[@"와 끝으로 "]"의 패턴으로 감싸진 패턴의 안에 있는 문자열의 값을 순서대로 1개씩 치환한다.
	 *
	 * @param source
	 *            the source
	 * @param args
	 *            the args
	 * @return String
	 */
	public static String replaceMessage(String source, List<String> args) {
		StringBuffer buffer = null;

		if (isEmpty(source) || args == null || args.size() == 0 || source.indexOf("[@") == -1 || source.indexOf("]") == -1) {
			return source;
		}

		buffer = new StringBuffer();

		for (String arg : args) {
			int strIdx = source.indexOf("[@");
			if (strIdx != -1) {
				buffer.append(source.substring(0, strIdx));
				int endIdx = source.indexOf("]");

				if (endIdx != -1) {
					buffer.append(arg);
					source = source.substring(endIdx + 1);
				}
			}
		}

		if (source.length() != 0) {
			buffer.append(source);
		}

		return buffer.toString();
	}

	/**
	 * 문자열에서 시작으로"[@"와 끝으로 "]"의 패턴으로 감싸진 패턴의 안에 있는 문자열의 값을 치환한다.
	 *
	 * @param source
	 *            the source
	 * @param args
	 *            the args
	 * @return String
	 */
	public static String replaceMessage(String source, HashMap<String, String> args) {
		return replaceMessage(source, args, "[@", "]");
	}

	/**
	 * 문자열에서 특정한 시작패턴과 특정한 끝패턴으로 감싸진 패턴의 안에 있는 문자열의 값을 치환한다.
	 *
	 * @param source
	 *            the source
	 * @param args
	 *            the args
	 * @param startBracePattern
	 *            the start brace pattern
	 * @param endBracePattern
	 *            the end brace pattern
	 * @return String
	 */
	public static String replaceMessage(String source, HashMap<String, String> args, String startBracePattern, String endBracePattern) {

		StringBuffer buffer = new StringBuffer();
		int position = 0;
		int markEndPos = 0;

		String remainder;
		String argname;
		String value;

		while (source.length() > 0) {
			position = source.indexOf(startBracePattern);
			if (position == -1) {
				buffer.append(source);
				break;
			}
			if (position != 0) {
				buffer.append(source.substring(0, position));
			}

			if (source.length() == position + 2) {
				break;
			}
			remainder = source.substring(position + 2);

			markEndPos = remainder.indexOf(endBracePattern);
			if (markEndPos == -1) {
				break;
			}

			argname = remainder.substring(0, markEndPos).trim();
			value = (String) args.get(argname);
			if (value != null) {
				buffer.append(value);
			}

			if (remainder.length() == markEndPos + 1) {
				break;
			}
			source = remainder.substring(markEndPos + 1);
		}

		return buffer.toString();
	}

	/**
	 * repeat 만큼 반복된 문자열을 만든다.
	 * <p>
	 * e.g.)
	 *
	 * @param str
	 *            원본 문자열.
	 * @param repeat
	 *            반복 횟수.
	 * @return repeat 만큼 반복된 문자열.
	 */
	public static String repeat(String str, int repeat) {
		StringBuffer buffer = new StringBuffer(repeat * str.length());
		for (int index = 0; index < repeat; index++) {
			buffer.append(str);
		}
		return buffer.toString();
	}

	/**
	 * 문자열에서 지정한 문자를 기준으로 최대 max 만큼 분리한다.
	 *
	 * @param str
	 *            지정한 문자열.
	 * @param separator
	 *            기준문자.
	 * @param max
	 *            분리할 최대 수.
	 * @return separator 를 기준으로 max 개까지 분리된 String 배열.
	 */
	public static String[] split(String str, String separator, int max) {
		StringTokenizer tok = null;
		if (separator == null) {
			// Null separator means we're using StringTokenizer's default
			// delimiter, which comprises all whitespace characters.
			tok = new StringTokenizer(str);
		} else {
			tok = new StringTokenizer(str, separator);
		}
		int listSize = tok.countTokens();
		if (max > 0 && listSize > max) {
			listSize = max;
		}
		String[] list = new String[listSize];
		int i = 0;
		int lastTokenBegin = 0;
		int lastTokenEnd = 0;
		while (tok.hasMoreTokens()) {
			if (max > 0 && i == listSize - 1) {
				// In the situation where we hit the max yet have
				// tokens left over in our input, the last list
				// element gets all remaining text.
				String endToken = tok.nextToken();
				lastTokenBegin = str.indexOf(endToken, lastTokenEnd);
				list[i] = str.substring(lastTokenBegin);
				break;
			} else {
				list[i] = tok.nextToken();
				lastTokenBegin = str.indexOf(list[i], lastTokenEnd);
				lastTokenEnd = lastTokenBegin + list[i].length();
			}
			i++;
		}
		return list;
	}

	/**
	 * 해당 문자열이 모두 알파벳인지 확인.
	 *
	 * @param str
	 *            검사할 문자열.
	 * @return 숫자/공백문자/특수문자(\n\r...) 문자는 false.
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int index = 0; index < sz; index++) {
			if (Character.isLetter(str.charAt(index)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 해당 문자열이 모두 숫자인지 확인.
	 *
	 * @param str
	 *            검사할 문자열.
	 * @return 한/영문/공백문자/특수문자(\n\r...) 문자는 false.
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int index = 0; index < sz; index++) {
			if (Character.isDigit(str.charAt(index)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 해당 문자열이 모두 알파벳 이거나 숫자인지 확인. 단, _ 가능
	 *
	 * @param str
	 *            검사할 문자열.
	 * @return 한/공백문자/특수문자(\n\r...) 문자는 false.
	 */
	public static boolean isAlphaOrDigit(String str) {

		String regex = "[a-zA-Z0-9 *\\_\\.]+";

		if (str == null) {
			return false;
		}

		return str.matches(regex);
	}

	/**
	 * 해당 문자열이 모두 알파벳 이거나 숫자인지 확인.
	 *
	 * @param str
	 *            검사할 문자열.
	 * @return 한/공백문자/특수문자(\n\r...) 문자는 false.
	 */
	public static boolean isAlphaOrDigitOrSpecialLetter(String str) {

		String regex = "[a-zA-Z0-9 *\\_([#;*{}<>&]|\\.-:/)+]+";

		if (str == null) {
			return false;
		}

		/*
		 * str.matches(regex); if( str.matches(regex)){ return false; }
		 */

		/*
		 * int sz = str.length(); for ( int index = 0 ; index < sz ; index++ ) {
		 * if ( Character.isLetterOrDigit(str.charAt(index)) == false ) { return
		 * false; } }
		 */

		return str.matches(regex);
	}

	/**
	 * 문자열이 비어 있지 않은지 확인.
	 *
	 * @param str
	 *            원본 문자열.
	 * @return true if the String is non-null, and not length zero
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 문자열을 특정길이만큰 축약하여 ... 붙여주는 메소드
	 *
	 * @param str
	 *            the str
	 * @param maxNum
	 *            the max num
	 * @return String
	 */
	public static String contractString(String str, int maxNum) {
		int tLen = str.length();
		int count = 0;
		char c;
		int index = 0;
		for (index = 0; index < tLen; index++) {
			c = str.charAt(index);
			if (count >= maxNum) {
				break;
			}
			if (c > 127) {
				count += 2;
			} else {
				count++;
			}
		}
		return (tLen > index) ? str.substring(0, index) + "..." : str;
	}

	/**
	 * Byte 배열 문자열을 헥사코드로 변환.
	 *
	 * @param digest
	 *            the digest
	 * @return String
	 */
	public static String toHex(byte[] digest) {
		StringBuffer sb = new StringBuffer(digest.length * 2);
		for (int index = 0; index < digest.length; index++) {
			int b = digest[index] & 0xFF;
			sb.append(HEX_DIGITS.charAt(b >>> 4)).append(HEX_DIGITS.charAt(b & 0xF));
		}
		return sb.toString();
	}

	/**
	 * Base64Encoding 방식으로 바이트 배열을 아스키 문자열로 인코딩한다. In-Binany, Out-Ascii
	 *
	 * @param inputStr
	 *            인코딩할 스트링
	 * @return 인코딩된 아스키 문자열(String) </pre>
	 */
	public static String base64Encode(String inputStr) {
		return new String(Base64.encodeBase64(inputStr.getBytes()));
	}

	/**
	 * Base64Decoding 방식으로 아스키 문자열을 바이트 배열로 디코딩한다. In-Ascii, Out-Binany
	 *
	 * @param strDecode
	 *            디코딩할 아스키 문자열(String)
	 * @return String
	 */
	public static String base64Decode(String strDecode) {
		return new String(Base64.decodeBase64(strDecode.getBytes()));
	}

	/**
	 * 주어진 문자열을 StringTokenizer해서 List<String>형태로 반환.
	 *
	 * @param source
	 *            the source
	 * @param separater
	 *            the separater
	 * @return List<String>
	 */
	public static List<String> tokenize(String source, String separater) {
		StringTokenizer t = (separater != null) ? new StringTokenizer(source, separater) : new StringTokenizer(source);

		List<String> list = new ArrayList<String>();
		while (t.hasMoreElements()) {
			list.add((String) t.nextElement());
		}
		return list;
	}

	/**
	 * String의 순서를 뒤집어준다.
	 *
	 * @param str
	 *            the str
	 * @return String
	 */
	public static String reverse(String str) {
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		return sb.reverse().toString();
	}

	/**
	 * 입력 받은문자열의 제일 첫글자를 대문자로 만든다.
	 * <p>
	 * 영문일 경우에만 변환된다.
	 *
	 * @param str
	 *            문자열
	 * @return String 변환된 문자열.
	 */
	public static String capitalize(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() == 0) {
			return "";
		}
		return new StringBuffer(str.length()).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
	}

	/**
	 * 한/영문이 같이 들어 있는 문자열의 길이를 계산한다.
	 * <p>
	 * Java에서의 String.length() 메소드는 한글을 1 자리 로 처리하기 때문에 한글을 2 자리로 계산할 경우에 사용된다.
	 * <p>
	 *
	 * @param str
	 *            길이를 구할 문자열.
	 * @return int 문자열의 길이
	 */
	public static int lengthHan(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}
		int length = str.length();
		int strLength = 0;
		char c;
		for (int index = 0; index < length; index++) {
			c = str.charAt(index);
			if (c < 0xac00 || 0xd7a3 < c) {
				strLength++;
			} else {
				strLength += 2;
			}
		}
		return strLength;
	}

	/**
	 * 한/영 혼용 문자열의 길이를 check
	 *
	 * @param str
	 *            문자열
	 * @param maxLength
	 *            최대 길이
	 * @return boolean
	 */
	public static boolean checkLength(String str, int maxLength) {
		if (lengthHan(str) > maxLength) {
			return false;
		}

		return true;
	}

	/**
	 * 문자열내에 해당 pattern 이 들어있는지 check
	 *
	 * @param str
	 *            문자열
	 * @param pattern
	 *            특수문자
	 * @return boolean
	 */
	public static boolean checkSpecialChars(String str, String pattern) {
		if (str.indexOf(pattern) > 0) {
			return false;
		}

		return true;
	}

	/**
	 * 문자열내에 공백이 들어있는지 check
	 *
	 * @param str
	 *            문자열
	 * @return boolean
	 */
	public static boolean checkSpace(String str) {
		return checkSpecialChars(str, " ");
	}

	/**
	 * Gets the VARCHAR2 ( for UTF-8 Oracle DB ) length.
	 *
	 * @param str
	 *            the str
	 *
	 * @return the varchar2 length
	 */
	public static int varchar2Length(String str) {
		if (str == null || str.length() == 0) {
			return 0;
		}

		int length = 0;

		for (int i = 0; i < str.length(); ++i) {
			if (str.charAt(i) >= 0 && str.charAt(i) < 256) {
				length += 1;
			} else {
				length += 3;
			}
		}

		return length;
	}

	/**
	 * Checks if is not blank.
	 *
	 * @param str
	 *            the str
	 * @return true, if is not blank
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	/**
	 * 문자와 숫자가 섞인 문자열에서 숫자만 반환하는 메소드.
	 *
	 * @param str
	 *            the str
	 * @return String / 숫자만 남은 문자열
	 */
	public static String toNumeralString(String str) {
		Pattern pattern = Pattern.compile("\\D");
		Matcher matcher = pattern.matcher(str);
		str = matcher.replaceAll("");
		return str == null ? "" : str;
	}

	/**
	 * 문자열 Validation 검사
	 *
	 * <pre>
	 * 문자열이 빈 문자열인지 검사한다.
	 * </pre>
	 *
	 * @param String
	 *            str / Validation Check 문자열
	 * @return boolean / 적합한 문자열 : true, 잘못된 문자열 : false
	 */
	public static boolean CheckValidationText(String str) {
		return CheckValidationText(str, 0, 0);
	}

	/**
	 * 문자열 Validation 검사
	 *
	 * <pre>
	 * minSize == 0 : 최소 문자열 사이즈 무시
	 * maxSize == 0 : 최대 문자열 사이즈 무시
	 * </pre>
	 *
	 * @param String
	 *            str / Validation Check 문자열
	 * @param int minSize / 최소 문자열 길이 제한
	 * @param int maxSize / 최대 문자열 길이 제한
	 * @return boolean / 적합한 문자열 : true, 잘못된 문자열 : false
	 */
	public static boolean CheckValidationText(String str, int minSize, int maxSize) {
		boolean chkFlag = true;
		if (isNotBlank(str)) {
			int length = lengthHan(str);
			if ((minSize != 0 && length < minSize) || (maxSize != 0 && maxSize < length)) {
				chkFlag = false;
			}
		} else {
			chkFlag = false;
		}
		return chkFlag;
	}

	/**
	 * Text 개행문자를 &lt;BR/> TAG로 변경
	 *
	 * @param String
	 *            txt / 변환할 문자열
	 * @return String / &lt;BR/> 추가된 문자열
	 */
	public static String addBrTag(String txt) {
		if (txt != null && txt.length() > 0) {
			return txt.replaceAll("\\n", "<BR/> \n");
		} else {
			return "";
		}
	}

	/**
	 * 문자열 Validation 검사
	 *
	 * <pre>
	 * minSize == 0 : 최소 문자열 사이즈 무시
	 * maxSize == 0 : 최대 문자열 사이즈 무시
	 * </pre>
	 *
	 * @param String
	 *            str / Validation Check 문자열
	 * @param int minSize 최소 문자열 길이 제한
	 * @param int maxSize 최대 문자열 길이 제한
	 * @return boolean / 적합한 문자열 : true, 잘못된 문자열 : false
	 */
	public static boolean CheckValidationNumber(String str, int minSize, int maxSize) {
		return CheckValidationText(toNumeralString(str), minSize, maxSize);
	}

	/**
	 * E-mail 유효성 검사
	 *
	 * @param String
	 *            email / 유효성 검사 대상 E-mail 주소
	 * @return boolean / 적합한 E-mail : true, 잘못된 E-mail : false
	 */
	public static boolean isValidEmail(String email) {

		if (CheckValidationText(email, 5, 50)) {

			/** .html,*.gif,*.jpg,*.js,*.css,*.png,*.swf,*.flv,*.xml,*.jar */
			String[] noPermitPostfix = { ".html", ".gif", ".jpg", ".js", ".css", ".png", ".swf", ".flv", ".xml", ".jar" };
			if (email != null) {
				for (int i = 0; i < noPermitPostfix.length; i++) {
					if (email.toLowerCase().endsWith(noPermitPostfix[i])) {
						return false;
					}
				}
			}

			Pattern p = Pattern.compile("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$");
			Matcher m = p.matcher(email);
			return m.matches();
		} else {
			return false;
		}
	}

	/**
	 * Password 유효성 검사
	 *
	 * @param String
	 *            password / 유효성 검사 대상 Password
	 * @return boolean / 적합한 Password : true, 잘못된 Password : false
	 */
	public static boolean isVaildPassword(String password) {
		return isVaildPassword(password, null);
	}

	/**
	 *  Password 유효성 검사
	 *
	 * @param String
	 *            pw / 유효성 검사 대상 Password
	 * @param String
	 *            id / 비교 대상 ID
	 * @return boolean / 적합한 Password : true, 잘못된 Password : false
	 */
	public static boolean isVaildPassword(String pw, String id) {
		// 특수문자 및 길이
		//Pattern p = Pattern.compile("^[\\w+!+@+*+$+^+%+&]{8,15}$");
		Pattern p = Pattern.compile("^[\\w~`!@#[$]%\\^&[*]\\(\\)-=[+][|]\\\\\\{\\}\\[\\]'\"<,>.\\?]{8,15}$");
		Matcher m = p.matcher(pw);
		if ( !m.matches() ) {
			return false;
		}

		Pattern pnum = Pattern.compile("[\\d]+"); // 0-9
	    Pattern palpha = Pattern.compile("[a-zA-Z]+"); // a-zA-Z

	    if (pnum.matcher(pw).matches() || palpha.matcher(pw).matches() ) {
	    	return false;
        }

	    // 동일문자 3회연속 불가
	    if ( !isSameCharacters(pw) ) {
	    	return false;
	    }

	    // 순차증가 3자리 숫자 불가
	    if ( !isSequentialNumbers(pw) ) {
	    	return false;
	    }

	    // ID 포함여부
	    if (isNotEmpty(id) ) {
	    	if (pw.indexOf(id) > -1) {
				return false;
			}
	    }

		return true;
	}
	private static boolean isSameCharacters(String password) {

		int strSize = password.length();

		for (int i = 0; i < strSize; i++) {
			int sameCount = 0;
			char searchChar = password.charAt(i) ;

			int charPos = 0;
			while (charPos != -1) {
				charPos = password.indexOf(searchChar, charPos);
				if (charPos != -1) {
					charPos++;
					sameCount++;
					if (sameCount > 2) {
						return false;
					}
				}
			}

		}
		/*char[] chars = new char[password.length() ];
		for (int i = 0; i < password.length(); i++) {
			chars[i] = password.charAt(i);
		}
		for (char c : chars) {

			int count = 0;

			Pattern p = Pattern.compile(String.valueOf(c) );
			Matcher m = p.matcher(password);

			for (int i = 0; m.find(i); i = m.end() ) {
				count++;
			}

			if (count > 2) {
				return false;
			}
		}*/

		return true;
	}
	private static boolean isSequentialNumbers(String password) {
		for (int i = 0; i < password.length() - 2; i++) {
			String subPw = password.substring(i, i + 3);
			if (isNumeric(subPw) == false) {
				continue;
			}

			int a = Integer.parseInt(subPw.substring(0, 1) );
			int b = Integer.parseInt(subPw.substring(1, 2) );
			int c = Integer.parseInt(subPw.substring(2, 3) );

			if (b - a == 1 && c - b == 1) {
				return false;
			}

			else if (a - b == 1 && b - c == 1) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 입력된 숫자를 자리수에 맞는 문자열을 출력한다.
	 *
	 * <pre>
	 * 예: intToString(324, 5) = 00324
	 * </pre>
	 *
	 * @param int number / 입력 숫자
	 * @param int length / 자리수
	 * @return String
	 */
	public static String intToString(int number, int length) {
		String strNum = Integer.toString(number);
		return leftPad(strNum, length, "0");
	}

	/**
	 * 입력된 Double 을 지정된 Format 형식의 String 반환한다.
	 *
	 * <pre>
	 * 예: dblToString(64550.0, &quot;#############.00&quot;) = 00324
	 * </pre>
	 *
	 * @param number
	 *            double value
	 * @param format
	 *            the format "#############.00"
	 * @return the string
	 */
	public static String dblToString(double number, String format) {

		DecimalFormat df = new DecimalFormat(format);

		return df.format(number);
	}

	/**
	 * 숫자를 생월(MM), 생일(DD) 문자열로 변환한다.
	 *
	 * @param int number / 입력 숫자
	 * @return String / 변환된 문자열
	 */
	public static String toBirthText(int number) {
		return intToString(number, 2);
	}

	/**
	 * 구분자 사이에 empty space 를 넣어서 split 한다.
	 *
	 * @param String
	 *            dest / 대상 문자열
	 * @param String
	 *            regex / 구분자 문자열
	 * @return String / 구분된 문자열배열
	 */
	public static String[] split(String dest, String regex) {
		if (dest == null) {
			return null;
		} else {
			if (dest.indexOf(regex) != -1) {
				/* 공백을 앞뒤에 붙여 split 하고 나중에 trim() 한다. */
				String[] ar = dest.replaceAll(regex, " " + regex + " ").split(regex);
				int index = 0;
				for (String s : ar) {
					// System.out.println("################## ar.length="+ar.length+"/ar[index]="+ar[index]+"/index="+index);
					ar[index++] = s.trim();
				}
				return ar;
			} else {
				return new String[] { dest };
			}
		}
	}

	/**
	 * 메일 전송 메시지에서 메일 전송 API에서 금지하고 있는 특수문자를 HTML코드값으로 치환한다.
	 *
	 * @param msg
	 *            the msg / 메일 전송용 원본 메일 메시지 내용
	 * @return the string / 특수문자 치환이 완료된 메일 메시지 내용
	 */
	public static String replaceMailMessage(String msg) {
		/*
		 * 사용금지문자: "%", "--", "..", "'", "\\", script, </script, <SCRIPT,
		 * </SCRIPT
		 */
		msg = msg.replace("%", "&#37;");
		msg = msg.replace("--", "&#45;&#45;");
		msg = msg.replace("..", "&#46;&#46;");
		msg = msg.replace("'", "&#39;");
		// msg = msg.replace("\\\\", "&#92;&#92;"); /* "\\"는 현재 허용되고 있다.*/
		msg = msg.replace("script", "&#115;&#99;&#114;&#105;&#112;&#116;");
		msg = msg.replace("SCRIPT", "&#83;&#67;&#82;&#73;&#80;&#84;");

		return msg;
	}

	/**
	 * 입력된 문자로 시작되는 문자열인지 확인
	 *
	 * @param str
	 * @param prefix
	 * @return
	 */
	public static boolean startsWith(String str, String prefix) {
		return startsWith(str, prefix, false);
	}

	/**
	 * 입력된 문자로 시작되는 문자열인지 확인
	 *
	 * @param str
	 * @param prefix
	 * @return
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		return startsWith(str, prefix, true);
	}

	/**
	 * 입력된 문자로 시작되는 문자열인지 확인
	 *
	 * @param str
	 * @param prefix
	 * @param ignoreCase
	 * @return
	 */
	private static boolean startsWith(String str, String prefix, boolean ignoreCase) {
		if (str == null || prefix == null) {
			return str == null && prefix == null;
		}
		if (prefix.length() > str.length()) {
			return false;
		} else {
			return str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
		}
	}

	public static String trim(String s) {
		if (s == null) {
			return "";
		} else {
			return s.trim();
		}
	}

	/**
	 * 자바스크립트의 join 메소드와 동일한 기능
	 *
	 * @param 배열값
	 * @param split문자열
	 * @return join된문자열
	 */
	public static String join(String[] array, String splitString) {
		if (array == null) {
			return "";
		}
		String result = "";
		for (int i = 0; i < array.length; i++) {
			result += trim(array[i]);
			if (i < array.length - 1) {
				result += splitString;
			}
		}
		return result;
	}

	/**
	 * (역)허용되지 않은 특수문자를 가능한 문자로 치환하여 교체한다.
	 *
	 * @param replace
	 *            문자열
	 * @return 치환된 문자열
	 */
	public static String deReplace(String replaceStr) {
		if (replaceStr == null) {
			return "";
		}
		replaceStr = replaceStr.replaceAll("0xxx01", "#");
		replaceStr = replaceStr.replaceAll("0xxx02", ";");
		replaceStr = replaceStr.replaceAll("0xxx03", "\\\\");
		replaceStr = replaceStr.replaceAll("0xxx04", "*");
		replaceStr = replaceStr.replaceAll("0xxx05", "{");
		replaceStr = replaceStr.replaceAll("0xxx06", "}");
		replaceStr = replaceStr.replaceAll("0xxx07", "<");
		replaceStr = replaceStr.replaceAll("0xxx08", ">");
		replaceStr = replaceStr.replaceAll("0xxx09", "&");
		replaceStr = replaceStr.replaceAll("0xxx10", "\"");
		replaceStr = replaceStr.replaceAll("0xxx11", "'");
		replaceStr = replaceStr.replaceAll("0xxx12", "-");
		replaceStr = replaceStr.replaceAll("0xxx13", "/");
		replaceStr = replaceStr.replaceAll("0xxx14", "&nbsp;");
		return replaceStr;
	}

	/**
	 *
	 * 문자열 속의 특수알파벳들을 Escape Sequence Text로 변환
	 *
	 * <pre>
	 * 공지사항 조회시 특수알파벳에 대한 검색어를 Escape Sequence Text
	 * 변환하여 검색하기 위해 사용.
	 * </pre>
	 *
	 * @param src
	 *            문자열
	 * @return 치환된 문자열
	 */
	public static String toEscapeSequenceText(String src) {
		if (src != null) {
			src = src.replaceAll("À", "&Agrave;");
			src = src.replaceAll("Á", "&Aacute;");
			src = src.replaceAll("Â", "&Acirc;");
			src = src.replaceAll("Ã", "&Atilde;");
			src = src.replaceAll("Ä", "&Auml;");
			src = src.replaceAll("Å", "&Aring;");
			src = src.replaceAll("Æ", "&AElig;");
			src = src.replaceAll("Ç", "&Ccedil;");
			src = src.replaceAll("È", "&Egrave;");
			src = src.replaceAll("É", "&Eacute;");
			src = src.replaceAll("Ê", "&Ecirc;");
			src = src.replaceAll("Ë", "&Euml;");
			src = src.replaceAll("Ì", "&Igrave;");
			src = src.replaceAll("Í", "&Iacute;");
			src = src.replaceAll("Î", "&Icirc;");
			src = src.replaceAll("Ï", "&Iuml;");
			src = src.replaceAll("Ð", "&ETH;");
			src = src.replaceAll("Ñ", "&Ntilde;");
			src = src.replaceAll("Ò", "&Ograve;");
			src = src.replaceAll("Ó", "&Oacute;");
			src = src.replaceAll("Ô", "&Ocirc;");
			src = src.replaceAll("Õ", "&Otilde;");
			src = src.replaceAll("Ö", "&Ouml;");
			src = src.replaceAll("×", "&times;");
			src = src.replaceAll("Ø", "&Oslash;");
			src = src.replaceAll("Ù", "&Ugrave;");
			src = src.replaceAll("Ú", "&Uacute;");
			src = src.replaceAll("Û", "&Ucirc;");
			src = src.replaceAll("Ü", "&Uuml;");
			src = src.replaceAll("Ý", "&Yacute;");
			src = src.replaceAll("Þ", "&THORN;");
			src = src.replaceAll("ß", "&szlig;");
			src = src.replaceAll("à", "&agrave;");
			src = src.replaceAll("á", "&aacute;");
			src = src.replaceAll("â", "&acirc;");
			src = src.replaceAll("ã", "&atilde;");
			src = src.replaceAll("ä", "&auml;");
			src = src.replaceAll("å", "&aring;");
			src = src.replaceAll("æ", "&aelig;");
			src = src.replaceAll("ç", "&ccedil;");
			src = src.replaceAll("è", "&egrave;");
			src = src.replaceAll("é", "&eacute;");
			src = src.replaceAll("ê", "&ecirc;");
			src = src.replaceAll("ë", "&euml;");
			src = src.replaceAll("ì", "&igrave;");
			src = src.replaceAll("í", "&iacute;");
			src = src.replaceAll("î", "&icirc;");
			src = src.replaceAll("ï", "&iuml;");
			src = src.replaceAll("ð", "&eth;");
			src = src.replaceAll("ñ", "&ntilde;");
			src = src.replaceAll("ò", "&ograve;");
			src = src.replaceAll("ó", "&oacute;");
			src = src.replaceAll("ô", "&ocirc;");
			src = src.replaceAll("õ", "&otilde;");
			src = src.replaceAll("ö", "&ouml;");
			src = src.replaceAll("÷", "&divide;");
			src = src.replaceAll("ø", "&oslash;");
			src = src.replaceAll("ù", "&ugrave;");
			src = src.replaceAll("ú", "&uacute;");
			src = src.replaceAll("û", "&ucirc;");
			src = src.replaceAll("ü", "&uuml;");
			src = src.replaceAll("ý", "&yacute;");
			src = src.replaceAll("þ", "&thorn;");
		}
		return src;
	}

	public static boolean checkFacetField(String str) {

		boolean result = false;

		if (str.equals("category") || str.equals("contentType") || str.equals("serviceProvider")) {
			result = true;
		}

		return result;
	}

	/**
	 * 해당 문자열이 Field 값으로 유효한지 검사한다
	 *
	 * @param str
	 *            검사할 문자열.
	 * @return 유효하지 않을 경우 문자는 false.
	 */
	public static boolean checkField(String str) {

		boolean result = false;

		if (str.equals("title") || str.equals("actor") || str.equals("director") || str.equals("genre") || str.equals("serviceProvider") || str.equals("all") || str.equals("people")) {
			result = true;
		}

		return result;
	}

	/**
	 * 해당 문자열이 AutoCompleteField 값으로 유효한지 검사한다
	 *
	 * @param str
	 *            검사할 문자열.
	 * @return 유효하지 않을 경우 문자는 false.
	 */
	public static boolean checkAutoCompleteField(String str) {

		boolean result = false;


		if (str.equals("title") || str.equals("actor") || str.equals("director") || str.equals("all") || str.equals("people")) {
			result = true;
		}

		return result;
	}

	/**
	 * 해당 문자열이 MediaRating값으로 유효한지 검사한다
	 *
	 * @param str
	 *            검사할 문자열.
	 * @return 유효하지 않을 경우 문자는 false.
	 */
	public static boolean checkMediaRating(String str) {

		String regex = "\\[(-?[1-9]?[0-9]?|\\*) TO (-?[1-9]?[0-9]?|\\*)\\]";

		if (str == null) {
			return false;
		}

		return str.matches(regex);
	}

	/**
	 * 한글이 포함되어 있는지 확인.
	 * @param str
	 * @return
	 */
	public static boolean containsKoreanLanguage(String str) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			UnicodeBlock unicodeBlock = UnicodeBlock.of(ch);
			if (UnicodeBlock.HANGUL_SYLLABLES.equals(unicodeBlock)
					|| UnicodeBlock.HANGUL_COMPATIBILITY_JAMO.equals(unicodeBlock)
					|| UnicodeBlock.HANGUL_JAMO.equals(unicodeBlock)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 8, 10, 16진수 문자열을 바이트 배열로 변환한다.
	 * 8, 10진수인 경우는 문자열의 3자리가, 16진수인 경우는 2자리가, 하나의 byte로 바뀐다.
	 * 
	 * @param data 문자열
	 * @param idx 진수(8, 10, 16만 가능)
	 * @return
	 * @throws NumberFormatException
	 */
	public static byte[] toBytes(String data, int idx) throws IllegalArgumentException, NumberFormatException {
		if (data == null) {
			return null;
		}
		if (idx != 16 && idx != 10 && idx != 8) {
			throw new IllegalArgumentException("For input idx: \"" + idx + "\"");
		}
		int divLen = (idx == 16) ? 2 : 3;
    	int length = data.length();
    	if (length % divLen == 1) {
    		throw new IllegalArgumentException("For input string: \"" + data + "\"");
    	}
    	length = length / divLen;
    	byte[] bytes = new byte[length];
    	for (int i = 0; i < length; i++) {
    		int index = i * divLen;
    		bytes[i] = (byte)(Short.parseShort(data.substring(index, index+divLen), idx));
    	}
    	return bytes;
	}
	
	public static String toHexString(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		
		StringBuffer result = new StringBuffer();
		for (byte b : bytes) {
			result.append(Integer.toString((b & 0xF0) >> 4, 16));
			result.append(Integer.toString(b & 0x0F, 16));
		}
		return result.toString();
	}

	public static String getChosung(String val){
		if(null == val || "".equals(val)) return "";
		
		char b = val.charAt(0);
		String chosung = null;
		int first = (b - 44032 ) / ( 21 * 28 );
		switch(first){
			case 0:chosung="ㄱ";break;
			case 1:chosung="ㄲ";break;
			case 2:chosung="ㄴ";break;
			case 3:chosung="ㄷ";break;
			case 4:chosung="ㄸ";break;
			case 5:chosung="ㄹ";break;
			case 6:chosung="ㅁ";break;
			case 7:chosung="ㅂ";break;
			case 8:chosung="ㅃ";break;
			case 9:chosung="ㅅ";break;
			case 10:chosung="ㅆ";break;
			case 11:chosung="ㅇ";break;
			case 12:chosung="ㅈ";break;
			case 13:chosung="ㅉ";break;
			case 14:chosung="ㅊ";break;
			case 15:chosung="ㅋ";break;
			case 16:chosung="ㅌ";break;
			case 17:chosung="ㅍ";break;
			case 18:chosung="ㅎ";break;
			default: chosung=isAlpha(String.valueOf(b))?String.valueOf(b).toUpperCase():"";
		}     
		return chosung;
	}

}
