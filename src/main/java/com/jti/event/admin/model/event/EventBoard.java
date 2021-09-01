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
    private String name;
    private String telNum;
    private String email;
    private String regDt;
    private String title;
    private String content;
    private String imageUrl;
    private String originName;
    private String openYn;

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
