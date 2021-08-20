package com.jti.event.dialect;

import com.jti.event.constant.Constant;
import com.jti.event.util.AES256Util;
import com.jti.event.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * CommonUtil
 * 
 * @author tslee
 *
 */
public class CommonUtil {
	private Log log = LogFactory.getLog(CommonUtil.class);
	
	
	/**
	 * 관리자 수준
	 *
	 * @param val1:문자열1
	 * @return String
	 */
	public static String getAdminLevel(int val1) {
		if ( 0 < val1) {
			if(val1 == Constant.Admin.AdminLevel.SUPER_MASTER)
				return "전체관리자";
			else if(val1 == Constant.Admin.AdminLevel.MASTER)
				return "관리자";
			else
				return "운영자";
			
		}
		return Constant.Admin.NOT_AUTH;
	}

	/**
	 * 관리자 상태
	 *
	 * @param val1:문자열1
	 * @return String
	 */
	public static String getAdminState(String val1) {
		if(val1.equals(Constant.Admin.AdminState.NORMAL))
			return "해제";
		else if(val1.equals(Constant.Admin.AdminState.STOP))
			return "잠금";
		
		return "-";
	}
	
	/**
	 * 관리자 부서
	 *
	 * @param val1:문자열1
	 * @return String
	 */
	public static String getAdminGroup(int val1) {
		if(val1 == 100)
			return "Michelin";
		else if(val1 == 110)
			return "TCK-센터";
		else if(val1 == 120)
			return "TCK-개발";
		return "-";
	}
	
	
	
	/**
	 * 코드 데이터일때 경우에 따라 데이터 표현
	 *
	 * @param Y, N, 기타 등등: 데이터
	 * @param 데이터와 비교1: ""
	 * @param 비교값 표현명1: ""
	 * @param 데이터와 비교2: ""
	 * @param 비교값 표현명2: ""  
	 * @return String
	 */
	public static String getCompareToStrings(String val1, String val2, String val3, String val4, String val5) {
		if(null == val1) return "";
		
        if ( val1.equalsIgnoreCase(val2) ) return val3;
        else if ( val1.equalsIgnoreCase(val4) ) return val5;
        
        return val1;
    }
	
	/**
	 * 두개의 값을 비교하여 일치하면 selected 문자열을 리턴한다.
	 *
	 * @param val1:문자열1
	 * @param val2:문자열2
	 * @return String
	 */
	public static String setSeleted(String val1, String val2) {
        if ( val1 != null && val2 != null && !val1.equals("") && !val2.equals("")) {
            if(val1.equalsIgnoreCase(val2)) return "selected";
        }
        return "";
    }
	
	/**
	 * 두개의 값을 비교하여 일치하면 selected 문자열을 리턴한다.
	 *
	 * @param val1:문자열1
	 * @param val2:문자열2
	 * @return String
	 */
	public static String setSeleted(Integer val1, Integer val2) {
        if ( val1 != null && val2 != null && !val1.equals("") && !val2.equals("")) {
            if(val1 == val2) return "selected";
        }
        return "";
    }
	
	/**
	 * 두개의 값을 비교하여 일치하면 selected 문자열을 리턴한다.
	 *
	 * @param val1:문자열1
	 * @param val2:문자열2
	 * @return String
	 */
	public static String setSeleted(Boolean val1, Boolean val2) {
        if ( val1 == val2) {
            return "selected";
        }
        return "";
    }
	
	

	/**
	 * 두개의 값을 비교하여 일치하면 checked 문자열을 리턴한다.
	 *
	 * @param val1:문자열1
	 * @param val2:문자열2
	 * @return String
	 */
	public static String setChecked(String val1, String val2) {
        if ( val1 != null && val2 != null && !val1.equals("") && !val2.equals("")) {
            if(val1.equalsIgnoreCase(val2)) return "checked";
        }
        return "";
    }
	
		
	/**
	 * 배열과 비교하여 문자열이 일치하면 selected 문자열을 리턴한다.
	 *
	 * @param valArray:문자열 배열
	 * @param val:문자열
	 * @return String
	 */
	public static String setCheckedWithArray(Integer[] valArray, int val) 
	{
        if ( valArray != null && valArray.length>0) 
        {
        	int arrayLength = valArray.length;
    		
        	for(int i = 0; i < arrayLength; i++)
    		{
    			if(valArray[i] == val) 
    			{
    				return "checked";	
    			}
    		}
        }
        
        return "";
    }
	
	/**
	 * 배열과 비교하여 문자열이 일치하면 checked 문자열을 리턴한다.
	 *
	 * @param valArray:문자열 배열
	 * @param val:문자열
	 * @return String
	 */
	public static String setCheckedWithStringArray(String[] valArray, String val) 
	{
        if ( valArray != null && valArray.length>0) 
        {
        	int arrayLength = valArray.length;
    		
        	for(int i = 0; i < arrayLength; i++)
    		{
    			if(valArray[i].equals(val)) 
    			{
    				return "checked";	
    			}
    		}
        }
        
        return "";
    }
	
	
	/**
	 * 전화번호에 - 문자열을 추가하여 리턴한다.
	 *
	 * @param val:전화번호
	 * @return String
	 */
	public static String maskPhone(String val) {
		if("".equals(val) || val.length() < 9)
			return val;
		
		String n = val.replaceAll("-", "");
		int len = n.length(); 
		String number = n;
		int seoul = 0;
		
		if ( !number.substring(0, 1).equals("0") ) {
			if (len > 4) {
				if (len < 8)
					number = n.substring(0, 3) + "-" + n.substring(3);
				else
					number = n.substring(0, 4) + "-" + n.substring(4);
			}
		}
		else {
			if ( number.substring(0, 2).equals("02") )
				seoul = 1;
			
			if (len > 3-seoul) {
				number = n.substring(0, 3-seoul) + "-";
				if (len < 7-seoul)
					number += n.substring(3-seoul);
				else if (len < 11-seoul)
					number += n.substring(3-seoul, 6-seoul) + "-" + n.substring(6-seoul);
				else 
					number += n.substring(3-seoul, 7-seoul) + "-" + n.substring(7-seoul);
			}				
		}
		
		return number;
	}
	
	/**
	 * 전화번호에 앞중간뒤 정보 리턴
	 *
	 * @param val:전화번호
	 * @return String
	 */
	public static String getPhoneIndexNum(String val1, int val2) {
		String[] arrPhoneNum = maskPhone(val1).split("-");
		if(arrPhoneNum.length < val2 + 1) return "";
		else return arrPhoneNum[val2];
	}
	
	/**
	 * 이메일 @ 기준으로 앞 뒤 정보 리턴
	 *
	 * @param val:전화번호
	 * @return String
	 */
	public static String getEmailIndexValue(String val1, int val2) {
		if ( val1 == null || val1.equals(""))
			return "";
		
		String[] arrEmail = val1.split("@");
		if(arrEmail.length < val2 + 1) return "";
		else return arrEmail[val2];
	}
	
	
	/**
	 * maskMoney 
	 * 
	 * money format
	 * 
	 * @param money
	 * @return
	 */
	public static String maskMoney(String money) {
		if(null == money || "".equals(money))
			return "";
		
		java.text.DecimalFormat decFormat = new java.text.DecimalFormat("###,###,###,###");
		String val = decFormat.format(Integer.parseInt(money));
		return val;
	}
	

	/**
	 * String글자 자르기 리턴.
	 * 
	 * @param String
	 *            yyyyMMdd
	 * @return
	 */
	public static String getSubstringValue(String val1, int val2, int val3){
		if(null == val1 || "".equals(val1))
			return "";

		return val1.substring(val2, val3);
	}
	
	/**
	 * maskHtml 
	 * 
	 * html
	 * 
	 * @param String
	 * @return
	 */
	public static String getMaskHtml(String strHtml) {
		if(null == strHtml || "".equals(strHtml))
			return "";
		
		return StringUtil.encodeTextToHtml(strHtml);
	}
	
	/**
	 * isEmpty 
	 * 
	 * boolean
	 * 
	 * @param String
	 * @return
	 */
	public static boolean isEmpty(Object obj) { 
	    if (obj == null) return true; 

	    if (obj instanceof String) { 
	        return ((String) obj).equals(""); 
	    } else if (obj instanceof List) { 
	        return ((List) obj).isEmpty(); 
	    } else if (obj instanceof Map) { 
	        return ((Map) obj).isEmpty(); 
	    } else if (obj instanceof Object[]) { 
	        return Array.getLength(obj) == 0; 
	    } else { 
	        return obj == null; 
	    } 
	}
	
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", ""); 
	}
	
	
	public static String getVersion(String Path) {
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String versionName = transFormat.format(from);
		Path += "?version="+versionName;
		return Path;
	}

	public static String getOrderStateName(int orderState) {
		if(orderState == 0)
			return "접수";
		else if(orderState == 1)
			return "배송준비중";
		else if(orderState == 2)
			return "배송중";
		else if(orderState == 3)
			return "배송완료";
		else if(orderState == 4)
			return "취소완료";
		return "-";
	}
	
	
	public static String getAdminOrderStateName(int orderState) {
		if(orderState == 0)
			return "접수";
		else if(orderState == 1)
			return "배송준비중";
		else if(orderState == 2)
			return "배송중";
		else if(orderState == 3)
			return "<span style='color:red;'>배송완료</span>";
		else if(orderState == 4)
			return "<span style='color:red;'>취소완료</span>";
		return "-";
	}
	
	public static String AES256Decrypt(String str) {
		try {
			return new AES256Util().decrypt(str);	
		}catch(Exception ex) {
			return "";
		}
		
	}

}
