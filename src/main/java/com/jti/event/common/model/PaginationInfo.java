package com.jti.event.common.model;
import lombok.Data;

@Data
public class PaginationInfo {
	  private int currentPageNo;
	  private int recordCountPerPage;
	  private int pageSize;
	  private int totalRecordCount;
	  private int totalPageCount;
	  private int firstPageNoOnPageList;
	  private int lastPageNoOnPageList;
	  private int firstRecordIndex;
	  private int lastRecordIndex;
	  private int firstPageNo;
	  private int lastPageNo;
}
