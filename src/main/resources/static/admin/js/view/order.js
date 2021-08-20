/*<![CDATA[*/
var OrderJS = {
	init : function() {
		$("#Searchfrm").find("#searchReceiverPhone").keyup(function(){
			if(this.value != "") $("#Searchfrm").find("#searchReceiverPhone2").val(Common.correctPhoneNumber_text(this.value));
		})		
	},
	GoDetail : function(el) {
		var orderNo = $(el).attr("data_orderno");
		$("#Searchfrm").find("#orderNo").val(orderNo);
		$("#Searchfrm").attr("action","detail");
		$("#Searchfrm").submit();
	},
	detail_init : function() {
		$("#UpdateBtn").click(function() {
			  if(!confirm("수정하시겠습니까?")) return false;
			  var ValidateEl = $("#frm [data-required]");
	          TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {
	              var requestData = {}
	              var frm_data = $("#frm").serializeArray();

	              for (var index in frm_data) {
	            	  requestData[frm_data[index].name] = frm_data[index].value;
	              }
	              
	              TckJS.Ajax.postAjaxLoding("/adm/order/ajax/addr_update", requestData, function (res) {
	                  alert("수정 되었습니다.");
	                  location.reload();
	              });
	          });
       });
		
		TckJS_Addr.SetSearchEl($("#search_addr")[0], $("#addr_list")[0], function (juso_detail) {
	            var data = TckJS_Addr.juso_data[$(juso_detail).data("id")];
	            
	            TckJS_Addr.juso_data = undefined;
	            TckJS_Addr.checkKeyword = undefined;
	            

	            $("#receiverAddr").val(data.jibunAddr);
	            $("#receiverRoadAddr").val(data.roadAddr);
	            $("#zipCode").val(data.zipNo);
	            
	            $("#addr_list").hide();
	            is_AddrRowShow = true;
	            $(".AddrRow").slideDown();
	            $(".AddrRow_hide").slideUp();
	            $("#search_addr").val("");
	            $("#AddrRow_Text").text("주소를 검색하려면 클릭하세요.");
	            $("#receiverAddrDetail").focus();

	    });
	},
	AddrView : function() {
	    $('#divOrderDetail').modal('show');
	},
	Cancel : function () {
		if(!confirm("주문을 취소하시겠습니까?")) return false;
	    TckJS.Ajax.postAjaxLoding("/adm/order/ajax/cancel", {orderNo:$("#orderNo").val()}, function (res) {
            alert("주문이 취소되었습니다.");
            location.reload();
        });
	},
	ExcelDivShow : function () {
		$('#divOrderExcelUpload').modal('show');
	},
	ExcelUpload : function () {
		if($("#excel_upload")[0].files[0] == undefined){
			alert("파일을 선택해주세요.");
			return false;
		}
		var file_name =  $("#excel_upload")[0].files[0].name;
		var file_name_arr = file_name.split(".");
		var file_extension = file_name_arr[file_name_arr.length -1];
		 
		if(file_extension != "xls" && file_extension != "xlsx" ) {
			alert("엑셀 파일을 선택해주세요.");
			return false;
		} 
		
		 var form = $('#frm_upload')[0];
         var formData = new FormData(form);
         formData.append("excel_upload", $("#excel_upload")[0].files[0]);
         
         TckJS.Ajax.postformAjax("/adm/order/excel_upload", formData, function (res) {
             alert("처리 되었습니다.");
             location.reload();
         });
	},
	ExcelDivShow2 : function () {
		$('#divOrderYnExcelUpload').modal('show');
	},
	ExcelUpload2 : function () {
		if($("#excel_upload2")[0].files[0] == undefined){
			alert("파일을 선택해주세요.");
			return false;
		}
		var file_name =  $("#excel_upload2")[0].files[0].name;
		var file_name_arr = file_name.split(".");
		var file_extension = file_name_arr[file_name_arr.length -1];
		 
		if(file_extension != "xls" && file_extension != "xlsx" ) {
			alert("엑셀 파일을 선택해주세요.");
			return false;
		} 
		
		 var form = $('#frm_upload_yn')[0];
         var formData = new FormData(form);
         formData.append("excel_upload", $("#excel_upload2")[0].files[0]);
         
         TckJS.Ajax.postformAjax("/adm/order/orderYn/excel_upload", formData, function (res) {
             alert("처리 되었습니다.");
             location.reload();
         });
	}
}
/* ]]> */