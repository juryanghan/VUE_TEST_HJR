package com.jti.event.admin.control.event;

import com.jti.event.admin.model.event.EventBoard;
import com.jti.event.admin.model.event.EventBoardParam;
import com.jti.event.admin.model.event.Store;
import com.jti.event.admin.model.event.StoreParam;
import com.jti.event.admin.service.event.board.EventBoardService;
import com.jti.event.common.model.BaseResult;
import com.jti.event.common.model.PaginationInfo;
import com.jti.event.util.AES256Util;
import com.jti.event.util.WebUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("adm/admin")
class EventBoardController {

    private final Log log = LogFactory.getLog(EventBoardController.class);

    @Autowired
    private EventBoardService eventBoardService;

    /**
     * 이벤트 - 점포등록
     *
     * @param req
     * @param res
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/ajax/store")
    public BaseResult insertStore (HttpServletRequest req, HttpServletResponse res , Store store) {
        log.debug("/adm/admin/store");
        BaseResult Result = new BaseResult();
        Result.setResultCode("0000");
        Result.setResultMsg("정상");

        if (eventBoardService.insertStore(store) < 0) {
            Result.setResultCode("9998");
            Result.setResultMsg("에러가 발생하였습니다.");

            return Result;
        }
        return Result;
    }

    /**
     * 이벤트 - 점포리스트
     *
     * @param req
     * @param res
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/store")
    public ModelAndView storeList (HttpServletRequest req, HttpServletResponse res , StoreParam param)  throws  UnsupportedEncodingException, GeneralSecurityException {
        log.debug("/adm/admin/store");
        ModelAndView mav = new ModelAndView("adm/view/admin/event/board/store");

        PaginationInfo paginationInfo = new PaginationInfo();
        paginationInfo.setCurrentPageNo(param.getCurrentPageNo());
        paginationInfo.setRecordCountPerPage(param.getRecordCountPerPage());
        paginationInfo.setPageSize(param.getPageSize());
        paginationInfo.setTotalRecordCount(eventBoardService.countStore(param));

        // 리스트 가져오기
        if (paginationInfo.getTotalRecordCount() > 0)
            mav.addObject("store", eventBoardService.listStore(param));
        mav.addObject("storeParam", param);
        mav.addObject("paginationInfo", paginationInfo);

        return mav;
    }


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
        ModelAndView mav = new ModelAndView("adm/view/admin/event/board/list");

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
        mav.addObject("convenienceStoreParam", eventBoardService.selectConvenienceStore(param));


        return mav;
    }

    /**
     * 이벤트참여 사진 리스트 화면
     *
     * @param req
     * @param res
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping(value = "/event/imgList")
    public ModelAndView imgList(HttpServletRequest req, HttpServletResponse res, EventBoardParam param)  throws  UnsupportedEncodingException, GeneralSecurityException {
        log.debug("/adm/admin/event");
        ModelAndView mav = new ModelAndView("adm/view/admin/event/board/imgList");

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
        mav.addObject("convenienceStoreParam", eventBoardService.selectConvenienceStore(param));


        return mav;
    }

    /**
     *
     * 별점
     * */
    @ResponseBody
    @RequestMapping(value="/ajax/like", method = RequestMethod.POST)
    public BaseResult like(HttpServletRequest req, HttpServletResponse res , EventBoard param) throws ParseException {
        log.debug("/adm/admin/ajax/like");

        BaseResult result = new BaseResult();
        result.setResultCode("0000");
        result.setResultMsg("정상");


        if(param.getEventNo() == null  || param.getEventNo() == 0){
            result.setResultCode("9999");
            result.setResultMsg("잘못된 접근입니다.");
            return result;
        }

        if (eventBoardService.updateLike(param) != 1){
            result.setResultCode("9999");
            result.setResultMsg("수정 중 오류가 발생하였습니다.");
            return result;
        }

        return result;
    }


//    /**
//     *
//     * 이미지 다운로드
//     * */
//    @RequestMapping(value="/download")
//    public ModelAndView download(HttpServletRequest req, HttpServletResponse res ,EventBoardParam eventBoardParam, String fileName, String sheetName) throws ParseException {
//        log.debug("/adm/admin/download");
//        ModelAndView mav = new ModelAndView("excelView");
//
//        List<String> listColumn = new ArrayList<String>();
//        List<List<Object>> listData = new ArrayList<List<Object>>();
//
//        listColumn.add("이미지");
//
//        if(eventBoardService.countEventBoard(eventBoardParam) != 0){
//            List<EventBoard> list = eventBoardService.selectImg(eventBoardParam);
//            if (null != list && list.size() != 0) {
//                for(EventBoard item : list){
//                List<Object> row = new ArrayList<Object>();
//                row.add(item.getImageUrl());
//                listData.add(row);
//            }
//        }
//    }
//
//        mav.addObject("fileName", fileName);
//        mav.addObject("sheetName", sheetName);
//        mav.addObject("listData", listData);
//        mav.addObject("listColumn", listColumn);
//        mav.addObject("event",eventBoardService.selectImg(eventBoardParam));
//
//        return mav;
//    }

    /**
     *
     * 엑셀 다운로드
     * */
    @RequestMapping(value="/excel_download")
    public ModelAndView excel_download(HttpServletRequest req, HttpServletResponse res ,EventBoardParam eventBoardParam, String fileName, String sheetName) throws ParseException {
        log.debug("/adm/admin/excel_download");
        ModelAndView mav = new ModelAndView("excelView");

        List<String> listColumn = new ArrayList<String>();
        List<List<Object>> listData = new ArrayList<List<Object>>();

        listColumn.add("참여일");
        listColumn.add("편의점 본사");
        listColumn.add("점포명");
        listColumn.add("이름");
        listColumn.add("전화번호");
        listColumn.add("추천인");
        listColumn.add("이미지 URL");
        listColumn.add("작품 설명");
        listColumn.add("평점");

        String URI = req.getRequestURL().toString().replace(req.getRequestURI().toString(),"");

        if(eventBoardService.countEventBoard(eventBoardParam) != 0){
            List<EventBoard> list = eventBoardService.listEventBoardExcel(eventBoardParam);
            if (null != list && list.size() != 0) {
                for(EventBoard item : list){
                    List<Object> row = new ArrayList<Object>();
                    row.add(item.getRegDt());
                    row.add(item.getStoreName());
                    row.add(item.getConvenienceStoreName());
                    row.add(item.getName());
                    row.add(item.getTelNum());
                    row.add(item.getRecommendedName());
                    row.add(URI +item.getImageUrl());
                    row.add(item.getContent());
                    row.add(item.getLike());
                    listData.add(row);
                }
            }
        }

        mav.addObject("fileName", fileName);
        mav.addObject("sheetName", sheetName);
        mav.addObject("listData", listData);
        mav.addObject("listColumn", listColumn);

        return mav;
    }

    /**
     *
     * 엑셀 다운로드
     * */
    @RequestMapping(value="/excel_select_download")
    public ModelAndView excel_select_download(HttpServletRequest req, HttpServletResponse res ,EventBoardParam eventBoardParam, String fileName, String sheetName) throws ParseException {
        log.debug("/adm/admin/excel_download");
        ModelAndView mav = new ModelAndView("excelView");

        if(eventBoardParam.getEventNos() == null || eventBoardParam.getEventNos().isEmpty() ){
            try {
                return WebUtil.alertAndBack("잘못된 접근입니다.", res);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        List<String> listColumn = new ArrayList<String>();
        List<List<Object>> listData = new ArrayList<List<Object>>();

        listColumn.add("참여일");
        listColumn.add("편의점 본사");
        listColumn.add("점포명");
        listColumn.add("이름");
        listColumn.add("전화번호");
        listColumn.add("추천인");
        listColumn.add("이미지 URL");
        listColumn.add("작품 설명");
        listColumn.add("평점");

        String URI = req.getRequestURL().toString().replace(req.getRequestURI().toString(),"");

        if(eventBoardService.countEventBoard(eventBoardParam) != 0){
            List<EventBoard> list = eventBoardService.listEventBoardSelectExcel(eventBoardParam);
            if (null != list && list.size() != 0) {
                for(EventBoard item : list){
                    List<Object> row = new ArrayList<Object>();
                    row.add(item.getRegDt());
                    row.add(item.getStoreName());
                    row.add(item.getConvenienceStoreName());
                    row.add(item.getName());
                    row.add(item.getTelNum());
                    row.add(item.getRecommendedName());
                    row.add(URI +item.getImageUrl());
                    row.add(item.getContent());
                    row.add(item.getLike());
                    listData.add(row);
                }
            }
        }

        mav.addObject("fileName", fileName);
        mav.addObject("sheetName", sheetName);
        mav.addObject("listData", listData);
        mav.addObject("listColumn", listColumn);

        return mav;
    }

    /**
     *
     * 이미지 다운로드
     * */
    @RequestMapping(value="/image_download")
    public ModelAndView download(HttpServletRequest req, HttpServletResponse res, EventBoardParam param) {
        log.debug("/adm/admin/image_download");
        ModelAndView mav = new ModelAndView("DownloadView");

        if(param.getEventNo() == null || param.getEventNo() == 0){
            try {
                return WebUtil.alertAndBack("잘못된 접근입니다.", res);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        EventBoard event =  eventBoardService.selectImg(param);

        File file = new File(event.getImagePath());
        String fileName = event.getOriginName();

        if(file == null || !file.exists()){
            try {
                return WebUtil.alertAndBack("파일이 존재하지 않습니다.", res);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mav.setViewName("DownloadView");
        mav.addObject("downloadFile", file);
        mav.addObject("downloadFileName", fileName);
        return mav;
    }


    /**
     * 이벤트 참여 처리
     *
     * @return
     * */
    @ResponseBody
    @RequestMapping(value = "/delete")
    public BaseResult deleteList(HttpServletRequest req, HttpServletResponse res, EventBoard param) {
        log.debug("/adm/admin/delete");
        BaseResult result = new BaseResult();
        result.setResultCode("0000");
        result.setResultMsg("정상");


        if(param.getEventNo() == null  || param.getEventNo() == 0){
            result.setResultCode("9999");
            result.setResultMsg("잘못된 접근입니다.");
            return result;
        }

        if (eventBoardService.deleteList(param) != 1){
            result.setResultCode("9999");
            result.setResultMsg("수정 중 오류가 발생하였습니다.");
            return result;
        }

        return result;
    }

}
