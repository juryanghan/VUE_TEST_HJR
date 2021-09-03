package com.jti.event.admin.model.event;

import com.jti.event.common.model.Base;
import com.jti.event.util.AES256Util;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

@Data
public class EventBoard extends Base {
    private Integer eventNo;
    private String name; //점주이름
    private String telNum; //전화번호1
    private String storeName; //점포명
    private String  convenienceStoreName; //편의점 본사명
    private Integer like; //좋아요
    private String email; //이메일
    private String regDt; //등록일
    private String content; //내용
    private String imageUrl;//이미지경로
    private String originName; //이미지이름
    private String recommendedName; //추천인
    private String openYn;//공개여부


    public String getName(){
        if(this.name != null && !this.name.isEmpty()){
            try {
                return  new AES256Util().decrypt(this.name);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return this.name;
    }

    public String getTelNum(){
        if(this.telNum != null && !this.telNum.isEmpty()){
            try {
                return  new AES256Util().decrypt(this.telNum);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return this.telNum;
    }


    public String getEmail(){
        try {
            return  this.email =  new AES256Util().decrypt(this.email);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this.email;

    }



}
