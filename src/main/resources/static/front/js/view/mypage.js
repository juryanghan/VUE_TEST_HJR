/*<![CDATA[*/
var MyPageJS = {
	init : function() {

	},
	Check : function() {
		var ValidateEl = $("#phoneCheck");
        TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {
            TckJS.Ajax.postAjax("/frt/mypage/ajax/check", {phoneCheck:$("#phoneCheck").val()}, function (res) {
               location.href="/frt/mypage/index";
            })
        });
	}
}
/* ]]> */