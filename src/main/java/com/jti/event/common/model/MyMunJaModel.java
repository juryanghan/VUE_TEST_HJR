package com.jti.event.common.model;

import lombok.Data;

@Data
public class MyMunJaModel {
    private String remote_id;		   //마이문자 회원아이디
    private String remote_pass; 	   //마이문자 회원 패스워드
    private String remote_returnurl;   //전송후 전송결과값을 리턴받을 주소 (http://는 생략)
    private String remote_num;		   // 문자메시지 전송할 개수
    private String remote_reserve;     //문자메시지 예약전송 체크 (0 : 즉시전송, 1: 예약전송)
    private String remote_reservetime; //문자메시지 예약시간 (형식 : 2005-05-18 13:30 ) 년-월-일 시:분
    private String remote_phone;       //문자메시지 수신번호 (0101003000 형식, 다수에게 전송시에는 쉼표로 구분 0101003000,0101003000,0101003000 형식) 예)0101234567 or 010-123-4567
    private String remote_name;        //문자메시지 수신번호에 해당되는 이름을 입력합니다. (홍길동 형식, 선택 다수에게 전송시에는 쉼표로 구분 홍길동,이순신,강감찬 형식)
    private String remote_callback;    //문자메시지 발신번호 (숫자만 입력)
    private String remote_subject;     //Lms 전송시 제목입력 20글자이내로 작성, 특수문자제외 (“, ‘, <, >, [, ], @,# 등)
    private String remote_msg;         // 문자메시지 내용 (sms 는 90바이트 이내로 작성,lms 는 2000바이트 이내로) URL 인코딩 필수적용 해야 함.( 메시지 내용에 & 기호가 들어가는 경우 내용이 짤릴 수 있음.)
    private String remote_etc1;        //사용자정의 (발송후 리턴페이지로 값그대로 리턴됨)
    private String remote_etc2;        //사용자정의 (발송후 리턴페이지로 값그대로 리턴됨)
}
