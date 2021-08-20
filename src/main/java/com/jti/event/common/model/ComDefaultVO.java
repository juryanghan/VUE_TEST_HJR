package com.jti.event.common.model;

import java.io.Serializable;

public class ComDefaultVO extends Base implements Serializable {
	
    /** 현재페이지 */
    private int currentPageNo = 1;
    
    /** 한 페이지당 게시되는 게시물 건 수 */
    private int recordCountPerPage = 10;
    
    /** 페이지 리스트에 게시되는 페이지 건수 */
    private int pageSize = 10;
    
    /** 전체 게시물 건 수 */
    private int totalRecordCount = 0;
    
    /** 페이지 개수 */
    private int totalPageCount = 0;
    
    /** 페이지 리스트의 첫 페이지 번호 */
    private int firstPageNoOnPageList = 0;
    
    /** 페이지 리스트의 마지막 페이지 번호 */
    private int lastPageNoOnPageList = 0;
    
    /** 페이징 SQL의 조건절에 사용되는 시작 rownum */
    private int firstRecordIndex = 1;

    /** 페이징 SQL의 조건절에 사용되는 마지막 rownum */
    private int lastRecordIndex = 1;
    
    /** 검색조건 */
    private String searchType = "";
    
    /** 검색Keyword */
    private String searchKeyword = "";
    
    /** 검색사용여부 */
    private String searchUseYn = "";
    
    /** 검색공개여부 */
    private String searchOpenYn = "";
    
    /** 검색KeywordFrom */
    private String searchKeywordFrom = "";    

	/** 검색KeywordTo */
    private String searchKeywordTo = "";
    
    /** 검색KeywordFrom */
    private String searchKeywordFrom2 = "";    
    
    /** 검색KeywordTo */
    private String searchKeywordTo2 = "";
    
    /** 사용 여부 */
    private String useYn;
    
    
    public int getTotalPageCount(){
    	return ((totalRecordCount-1) / recordCountPerPage) + 1;
    }
    
    public int getFirstPageNoOnPageList(){
    	return ((currentPageNo-1) / pageSize) * pageSize + 1;
    }
    
    public int getLastPageNoOnPageList(){
    	 int rtnVal = getFirstPageNoOnPageList() + pageSize - 1;
    	 
    	 if(lastPageNoOnPageList > totalRecordCount){
    		 rtnVal = totalPageCount;
    	 }
    	return rtnVal;
    }
    
    public int getFirstRecordIndex() {
    	return (currentPageNo - 1) * recordCountPerPage;
    }
    
    public int getLastRecordIndex() {
    	return recordCountPerPage;
    }

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

    public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchUseYn() {
        return searchUseYn;
    }

    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    public String getSearchOpenYn() {
		return searchOpenYn;
	}

	public void setSearchOpenYn(String searchOpenYn) {
		this.searchOpenYn = searchOpenYn;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    

    
    public String getSearchKeywordFrom2() {
		return searchKeywordFrom2;
	}

	public void setSearchKeywordFrom2(String searchKeywordFrom2) {
		this.searchKeywordFrom2 = searchKeywordFrom2;
	}

	public String getSearchKeywordTo2() {
		return searchKeywordTo2;
	}

	public void setSearchKeywordTo2(String searchKeywordTo2) {
		this.searchKeywordTo2 = searchKeywordTo2;
	}

	/**
	 * searchKeywordFrom attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordFrom() {
		return searchKeywordFrom;
	}

	/**
	 * searchKeywordFrom attribute 값을 설정한다.
	 * @param searchKeywordFrom String
	 */
	public void setSearchKeywordFrom(String searchKeywordFrom) {
		this.searchKeywordFrom = searchKeywordFrom;
	}

	/**
	 * searchKeywordTo attribute를 리턴한다.
	 * @return String
	 */
	public String getSearchKeywordTo() {
		return searchKeywordTo;
	}

	/**
	 * searchKeywordTo attribute 값을 설정한다.
	 * @param searchKeywordTo String
	 */
	public void setSearchKeywordTo(String searchKeywordTo) {
		this.searchKeywordTo = searchKeywordTo;
	}

	/**
	 * useYn attribute를 리턴한다.
	 * @return String
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * useYn attribute 값을 설정한다.
	 * @param useYn String
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
}
