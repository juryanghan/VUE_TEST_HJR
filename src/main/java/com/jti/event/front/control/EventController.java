package com.jti.event.front.control;

import com.jti.event.common.model.BaseResult;
import com.jti.event.front.model.common.ImageModel;
import com.jti.event.front.model.event.param.EventParam;
import com.jti.event.front.service.common.ImageService;
import com.jti.event.front.service.event.EventService;
import com.jti.event.util.AES256Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("frt/event")
public class EventController {
    private final Log log = LogFactory.getLog(MainController.class);

    @Autowired
    private EventService eventService;

    /**
     * Image 서비스 객체
     */
    @Autowired
    private ImageService imageService;

    @Autowired
    private AES256Util aes256Util;

    @ResponseBody
    @RequestMapping(value = "/ajax/add")
    public BaseResult add(HttpServletRequest req, HttpServletResponse res, EventParam eventParam) throws UnsupportedEncodingException {
        log.debug("frt/event/ajax/add");
        String fullTelNum = "";
        BaseResult result = new BaseResult();
        result.setResultCode("0000");
        result.setResultMsg("정상");


        MultipartHttpServletRequest part = (MultipartHttpServletRequest) req;
        MultipartFile mf = part.getFile("eventImageFile");
        if(null == mf || mf.getSize() == 0){
            result.setResultCode("9999");
            result.setResultMsg("이미지가 존재하지 않습니다. ");
            return  result;
        }

            // 이미지 저장
            List<ImageModel> listImageFile = imageService.doImageInsertProcess(req, res, "eventImageFile");

                eventParam.setImagePath(listImageFile.get(0).getImagePath());
                eventParam.setImageUrl(listImageFile.get(0).getImageUrl());
                eventParam.setOriginName(listImageFile.get(0).getOriginName());
                eventParam.setImageSize(listImageFile.get(0).getImageSize());
                eventParam.setName(eventParam.get_name());

            if(eventParam.getTelNum1() == null || eventParam.getTelNum1() == ""){
                result.setResultCode("9999");
                result.setResultMsg("전화번호가 입력되지 않았습니다.");
                return  result;
            }

            if(eventParam.getTelNum2() == null || eventParam.getTelNum2() == ""){
                result.setResultCode("9999");
                result.setResultMsg("전화번호가 입력되지 않았습니다.");
                return  result;
            }

            if(eventParam.getTelNum3() == null || eventParam.getTelNum3() == ""){
                result.setResultCode("9999");
                result.setResultMsg("전화번호가 입력되지 않았습니다.");
                return  result;
            }

            fullTelNum += eventParam.getTelNum1();
            fullTelNum += eventParam.getTelNum2();
            fullTelNum += eventParam.getTelNum3();
            eventParam.setTelNum(fullTelNum);
            eventParam.setTelNum(eventParam.get_telNum());

            if(eventParam.get_name() == null || eventParam.get_name() == ""){
                    result.setResultCode("9999");
                    result.setResultMsg("이름이 입력되지 않았습니다.");
                    return  result;
                }

            if(eventParam.getStoreName() == null || eventParam.getStoreName() == ""){
                result.setResultCode("9999");
                result.setResultMsg("편의점 본사가 입력되지 않았습니다.");
                return  result;
            }

            if(eventParam.getConvenienceStoreName() == null || eventParam.getConvenienceStoreName() == ""){
                result.setResultCode("9999");
                result.setResultMsg("점포명이 입력되지 않았습니다.");
                return  result;
            }

            if(eventParam.getContent() == null || eventParam.getContent() == ""){
                result.setResultCode("9999");
                result.setResultMsg("작품 설명이 입력되지 않았습니다.");
                return  result;
            }

            if(eventParam.getImagePath() == null || eventParam.getImagePath() == ""){
                result.setResultCode("9999");
                result.setResultMsg("이미지 파일이 선택되지 않았습니다.");
                return  result;
            }

            if(eventParam.getImageUrl() == null || eventParam.getImageUrl() == ""){
                result.setResultCode("9999");
                result.setResultMsg("이미지 파일이 선택되지 않았습니다.");
                return  result;
            }

            if(eventParam.getOriginName() == null || eventParam.getOriginName() == ""){
                result.setResultCode("9999");
                result.setResultMsg("이미지 파일이 선택되지 않았습니다.");
                return  result;
            }

            if(eventService.eventAdd(eventParam) != 1){
                result.setResultCode("9999");
                result.setResultMsg("이벤트 등록 중 오류가 발생하였습니다.");
                return  result;
            }
        return result;
    }

}
