package com.jti.event.front.model.event.param;

import com.jti.event.front.model.common.ImageModel;
import com.jti.event.util.AES256Util;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Data
public class EventParam {
    private String storeName;  // 편의점 본사이름
    private String name;    // 점주이름
    private String telNum1;  // 이벤트 참여자 번호
    private String telNum2;  // 이벤트 참여자 번호
    private String telNum3;  // 이벤트 참여자 번호
    private String convenienceStoreName;   // 점포명
    private String recommendedName; // 추천 직원 이름
    private String content; // 작품 설명
    private String imagePath;   // 이미지 경로
    private String imageUrl;    //이미지  url
    private String originName;  // 이미지 원본 이름
    private long imageSize;   // 이미지 사이즈
    private String telNum = "";  // 전화번호 합친것

    public String get_name(){
        if(this.name != null && !this.name.isEmpty()){
            try {
                return  new AES256Util().encrypt(this.name);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return  this.name;
    }

    public String get_recommendedName(){
        if(this.recommendedName != null && !this.recommendedName.isEmpty()){
            try {
                return  new AES256Util().encrypt(this.recommendedName);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return  this.recommendedName;
    }

    public String get_telNum(){
        if(this.telNum != null && !this.telNum.isEmpty()){
            try {
                return  new AES256Util().encrypt(this.telNum);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return  this.telNum;
    }

//    public String get_telNum2(){
//        if(this.telNum2 != null && !this.telNum2.isEmpty()){
//            try {
//                return  new AES256Util().encrypt(this.telNum2);
//            } catch (GeneralSecurityException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return  this.telNum2;
//    }
//
//    public String get_telNum3(){
//        if(this.telNum3 != null && !this.telNum3.isEmpty()){
//            try {
//                return  new AES256Util().encrypt(this.telNum3);
//            } catch (GeneralSecurityException e) {
//                e.printStackTrace();
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//        return  this.telNum3;
//    }


}
