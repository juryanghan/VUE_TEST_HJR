package com.jti.event.dialect;

import com.jti.event.common.model.ComDefaultVO;
import com.jti.event.common.model.PaginationInfo;

import java.text.MessageFormat;


public class PaginationRenderer  {
 
	private String firstPageLabel;
	private String previousPageLabel;
	private String currentPageLabel;
	private String otherPageLabel;
	private String nextPageLabel;
	private String lastPageLabel;
	
	public PaginationRenderer() {
		firstPageLabel = "<li class=\"paginate_button page-item previous\"><a href\"#\" onclick=\"{0}({1}); return false;\" class=\"page-link\">←←</a></li>";
		previousPageLabel = "<li class=\"paginate_button page-item previous\"><a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"page-link\">←</a></li>";
		currentPageLabel = "<li class=\"paginate_button page-item active\"><a href=\"javascript:void(1);return false;\" class=\"page-link\">{0}</a></li>";
		otherPageLabel = "<li class=\"paginate_button page-item\"><a href=\"#\" onclick=\"{0}({1}); return false;\" class=\"page-link\">{2}</a></li>";
		nextPageLabel = "<li class=\"paginate_button page-item next\"><a href\"#\" onclick=\"{0}({1}); return false;\" class=\"page-link\">→</a></li>";
		lastPageLabel = "<li class=\"paginate_button page-item next\"><a href\"#\" onclick=\"{0}({1}); return false;\" class=\"page-link\">→→</a></li>";
	}
	
	
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {		
		StringBuffer strBuff = new StringBuffer();

		int firstPageNo = 1;
		int firstPageNoOnPageList = (int)Math.floor((double)(paginationInfo.getCurrentPageNo() - 1) / (double)10) * 10 + 1;
        int lastPageNoOnPageList = firstPageNoOnPageList + 9;
        int totalPageCount = (int) Math.ceil((double)paginationInfo.getTotalRecordCount() / (double)paginationInfo.getPageSize());
        int pageSize = paginationInfo.getPageSize();
        int currentPageNo = paginationInfo.getCurrentPageNo();
        int lastPageNo = totalPageCount;
        
        lastPageNoOnPageList = lastPageNoOnPageList > totalPageCount ? totalPageCount : lastPageNoOnPageList;
        
        
        
        // firstPageNo 첫 페이지 firstPageLabel 세팅 값
        // firstPageNoOnPageList  previousPageLabel 세팅 값
        // currentPageNo 현재 페이지
        // totalPageCount 총 페이지수
        // lastPageNo 마지막 페이지
        // pageSize 한번에노출하고자 하는 페이지수
        // lastPageNoOnPageList 현재 페이지에서 마지막 페이지
        
        // 첫 페이지 부분 이미지 표시


        if(totalPageCount > pageSize && currentPageNo > pageSize){
        	strBuff.append(MessageFormat.format(firstPageLabel, new Object[]{jsFunction,Integer.toString(firstPageNo)}));
        	strBuff.append(MessageFormat.format(previousPageLabel, new Object[]{jsFunction,Integer.toString(firstPageNoOnPageList-1)}));
        }
        		
        for(int i=firstPageNoOnPageList;i<=lastPageNoOnPageList;i++){
            if(i==currentPageNo){
                strBuff.append(MessageFormat.format(currentPageLabel,new Object[]{Integer.toString(i)}));
            }else{
                strBuff.append(MessageFormat.format(otherPageLabel,new Object[]{jsFunction,Integer.toString(i),Integer.toString(i)}));
            }
        }
        
        if(totalPageCount > lastPageNoOnPageList){
        	// 마지막 페이지 부분 이미지 표시        
            strBuff.append(MessageFormat.format(nextPageLabel,new Object[]{jsFunction,Integer.toString(firstPageNoOnPageList+pageSize)}));
            strBuff.append(MessageFormat.format(lastPageLabel,new Object[]{jsFunction,Integer.toString(lastPageNo)}));	
        }
        

        return strBuff.toString();
	}
	
	public int GetNumber(int index, int totalRecordCount , Object Data ) {
		ComDefaultVO pageData = (ComDefaultVO) Data;
		int Number = (totalRecordCount - index) - pageData.getCurrentPageNo() * pageData.getRecordCountPerPage() + pageData.getRecordCountPerPage() ;
		return Number;
	}
}