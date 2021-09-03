package com.jti.event.admin.model.event;

import com.jti.event.common.model.ComDefaultVO;
import com.jti.event.util.AES256Util;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

@Data
public class EventBoardParam extends ComDefaultVO {
    private Integer eventNo;
    private String searchConvenienceStoreName;
    private String searchStore;
    private String searchCustName;
    private String searchCustName_Encrypt;
    private String searchTelNum;
    private String SearchTelNum_Encrypt;
    private String searchEmail;
    private String searchOpenType;
    private String searchStartDt;
    private String searchEndDt;

    public String getSearchCustName_Encrypt(){
        if(this.searchCustName != null && !this.searchCustName.isEmpty()){
            try {
                return  new AES256Util().encrypt(this.searchCustName);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return  this.searchCustName;
    }
    public String getSearchEmail_Encrypt(){
        if(this.searchEmail != null && !this.searchEmail.isEmpty()){
            try {
                return  new AES256Util().encrypt(this.searchEmail);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return  this.searchEmail;
    }

    public String getSearchTelNum_Encrypt(){
        if(this.searchTelNum != null && !this.searchTelNum.isEmpty()){
            try {
                return  new AES256Util().encrypt(this.searchTelNum);
            } catch (GeneralSecurityException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return  this.searchTelNum;
    }



}
