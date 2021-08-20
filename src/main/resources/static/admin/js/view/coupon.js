/*<![CDATA[*/
var CouponJS = {
	init : function() {

	},
	Unused : function(el) {
		if(!confirm("쿠폰을 미사용하시겠습니까?")) return false;

		TckJS.Ajax.postAjaxLoding("/adm/coupon/ajax/unused", {couponSerial:$(el).data("coupon")}, function (res) {
			alert("쿠폰이 미사용 처리되었습니다.");
			location.reload();
		});
	},
	Expiration : function(el) {
		if(!confirm("쿠폰을 만료 처리하시겠습니까?")) return false;

		TckJS.Ajax.postAjaxLoding("/adm/coupon/ajax/expiration", {couponSerial:$(el).data("coupon")}, function (res) {
			alert("쿠폰이 만료 처리되었습니다.");
			location.reload();
		});
	},
	ExcelDivShow : function () {
		$("#frm_upload").find("#result_msg").html("");
		$('#divCouponExpiration').modal('show');
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

		TckJS.Ajax.postformAjax("/adm/coupon/excel_upload", formData, function (res) {
			$("#frm_upload").find("#result_msg").html(res.resultMsg);
			//alert("처리 되었습니다.");
			//location.reload();
		});
	},
}
/* ]]> */