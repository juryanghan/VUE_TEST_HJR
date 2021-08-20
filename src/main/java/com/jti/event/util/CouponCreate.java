package com.jti.event.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CouponCreate {

	/**
	 * BH로 시작하는 10자리 쿠폰 번호 생성  (예시:NT21106FF4B0)
	 * @return
	 */
	public String GetCoupon(){
		Random rnd = new Random();
		StringBuffer result = new StringBuffer();
		result.append("BH");

		while (result.length() <= 12){
			// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
			if(rnd.nextBoolean()){
				result.append((char)((int)(rnd.nextInt(26))+97));
			}else{
				result.append((rnd.nextInt(10)));
			}
		}
		return result.toString().toUpperCase();
	}


}