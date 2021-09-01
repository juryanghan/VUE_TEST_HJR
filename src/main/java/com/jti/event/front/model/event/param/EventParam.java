package com.jti.event.front.model.event.param;

import com.jti.event.front.model.common.ImageModel;
import com.jti.event.util.AES256Util;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Data
public class EventParam {
    private String name;    // 이벤트 참여자 이름
    private String telNum;  // 이벤트 참여자 번호
    private String email;   // 이벤트 참여자 이메일
    private String imagePath;   // 이미지 경로
    private String imageUrl;    //이미지  url;
    private String originName;  // 이미지 원본 이름
    private long imageSize;   // 이미지 사이즈

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

    public String get_email(){
        if(this.email != null && !this.email.isEmpty()){
            try {
                return  new AES256Util().encrypt(this.email);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return  this.email;
    }
}
