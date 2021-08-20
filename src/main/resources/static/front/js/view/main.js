/*<![CDATA[*/
var MainJS = {
 init : function() {
	$("#SaveBtn").click(function() {
		  var ValidateEl = $("#couponSerial");
            TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {
                TckJS.Ajax.postAjax("/frt/ajax/check", {couponSerial:$("#couponSerial").val()}, function (res) {
                   location.href="/frt/main/index";
                })
            });
		});
	}
}
/*]]>*/