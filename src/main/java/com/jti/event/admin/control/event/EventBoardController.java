package com.jti.event.admin.control.event;

import com.jti.event.admin.model.event.EventBoard;
import com.jti.event.admin.model.event.EventBoardParam;
import com.jti.event.admin.service.event.board.EventBoardService;
import com.jti.event.common.model.BaseResult;
import com.jti.event.common.model.PaginationInfo;
import com.jti.event.util.AES256Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("adm/admin")
class EventBoardController {

    private final Log log = LogFactory.getLog(EventBoardController.class);

    @Autowired
    private EventBoardService eventBoardService;

    /**
     * 이벤트참여 리스트 화면
     *
     * @param req
     * @param res
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/event")
    public ModelAndView list(HttpServletRequest req, HttpServletResponse res, EventBoardParam param)  throws  UnsupportedEncodingException, GeneralSecurityException {
        log.debug("/adm/admin/event");
        ModelAndView mav = new ModelAndView("adm/view/event/board/list");

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(param.getCurrentPageNo());
        paginationInfo.setRecordCountPerPage(param.getRecordCountPerPage());
        paginationInfo.setPageSize(param.getPageSize());
        paginationInfo.setTotalRecordCount(eventBoardService.countEventBoard(param));

        // 리스트 가져오기
        if (paginationInfo.getTotalRecordCount() > 0)
            mav.addObject("listData", eventBoardService.listEventBoard(param));
        mav.addObject("param", param);
        mav.addObject("paginationInfo", paginationInfo);


        return mav;
    }

    /**
     * 이벤트 참여 처리
     *
     * @return
     * */
    @ResponseBody
    @RequestMapping(value = "/process")
    public BaseResult process(HttpServletRequest req, HttpServletResponse res, EventBoard param) {
        log.debug("/adm/admin/process");
        BaseResult result = new BaseResult();
        result.setResultCode("0000");
        result.setResultMsg("정상");


        if(param.getEventNo() == null  || param.getEventNo() == 0){
            result.setResultCode("9999");
            result.setResultMsg("잘못된 접근입니다.");
            return result;
        }

        if (eventBoardService.updateEventProcess(param) != 1){
            result.setResultCode("9999");
            result.setResultMsg("수정 중 오류가 발생하였습니다.");
            return result;
        }

        return result;
    }

}
