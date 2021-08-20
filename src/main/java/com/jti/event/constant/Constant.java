package com.jti.event.constant;

public class Constant {
	
		
	// Delimeter
	public static String DELIMITER1 = "__";												// 구분자
	public static String DELIMITER2 = "@@";												// 구분자
	public static String DELIMITER3 = ",,";												// 구분자
	public static String DELIMITER4 = "&&";												// 구분자
	public static String DELIMITER5 = "_";													// 구분자
	
	// Delimeter 
	public static int OVER_TIME_LIMIT = 3600;											// 인증 시간제한
	
	// MaxPage 
	public static int MAX_PAGE = 9999999;													
	
	// 정상 ResultCode
	public static int NORMAL_RESULT_CODE = 200;
	
	// 실패 ResultCode
	public static int FAIL_RESULT_CODE = 201;
	
	// upload max size
	public static int UPLOAD_MAX_SIZE = 10485760; //10MB

	
	// 도메인
	public static final String DOMAIN = "http://localhost:9080";
	
	// 저장 경로 구분자
	public static final String FILE_SPLIT = "\\.";
	public static final String FOLDER_SPLIT = "/";
	
	/**
	 * 관리자 관련
	 * @author tslee
	 *
	 */
	public static class Admin {
		
		/**
		 * 접근권한 미존재
		 */
		public static final String NOT_AUTH = "접근 권한이 없습니다.";
		
		/**
		 * 관리자 레벨 코드
		 */
		public static class AdminLevel {
			/**
			 * 전체관리자
			 */
			public static final int SUPER_MASTER = 1;
			
			/**
			 * 관리자
			 */
			public static final int MASTER = 2;
			
			/**
			 * 운영자
			 */ 
			public static final int OPRATOR = 3;			
		}
		
		/**
		 * 관리자 상태
		 */
		public static class AdminState {
			/**
			 * 정상
			 */
			public static final String NORMAL = "1";
			
			/**
			 * 정지
			 */
			public static final String STOP = "0";
		
		}
	}
	
	/*
	 * 문의
	 */
	public static class Counsel {
		
		/*
		 * 쿠폰 발급되는 이벤트 진입점
		 */
		public static class CounselState {
			/**
			 * 완료
			 */
			public static final String COMPLETE = "1";
			
			/**
			 * 미완료
			 */
			public static final String INCOMPLETE = "0";
			
		}
	
	}
}
