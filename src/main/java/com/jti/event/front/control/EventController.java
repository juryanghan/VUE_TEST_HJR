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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public BaseResult add(HttpServletRequest req, HttpServletResponse res, EventParam eventParam){
        log.debug("frt/event/ajax/add");
        BaseResult result = new BaseResult();
        result.setResultCode("0000");
        result.setResultMsg("정상");

            // 이미지 저장
            List<ImageModel> listImageFile = imageService.doImageInsertProcess(req, res, "eventImageFile");
            eventParam.setImagePath(listImageFile.get(0).getImagePath());
            eventParam.setImageUrl(listImageFile.get(0).getImageUrl());
            eventParam.setOriginName(listImageFile.get(0).getOriginName());
            eventParam.setImageSize(listImageFile.get(0).getImageSize());
            eventParam.setEmail(eventParam.get_email());
            eventParam.setName(eventParam.get_name());
            eventParam.setTelNum(eventParam.get_telNum());

        eventService.eventAdd(eventParam);
        return result;
    }

}
