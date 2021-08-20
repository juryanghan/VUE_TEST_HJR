/*<![CDATA[*/
var OrderJS = {
	init : function() {
		
		Common.correctPhoneNumber($("#frm").find("#receiverPhone"));
		
		$("#zipCode").on("keyup", function (event) {
            $(this).val($(this).val().replace(/[^0-9\-]/g, ''));
        });
		
        $("#frm").find("[data-required]").change(function () {
        	ValCheck();
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
	            $("#AddrRow").slideDown();
	            $("#AddrRow_hide").slideUp();
	            $("#search_addr").val("");
	            $("#AddrRow_Text").text("주소를 검색하려면 클릭하세요.");
	            $("#receiverAddrDetail").focus();

	        });
		
		
		$("#OrderBtn").click(function() {
			var ValidateEl = $("#frm [data-required]");
			TckJS.ValidateUtil.ValidateCheck(ValidateEl, function() {
				
				if (!$("#inputCheck").is(":checked")) {
		             alert("개인정보 수집 및 이용 동의를 해주세요.");
		             $("#inputCheck").focus();
		             return false;
		         }
				
				if(!Common.isCellPhone($("#receiverPhone").val())){
					alert("핸드폰 번호가 올바르지 않습니다.");
					$("#receiverPhone").focus()
		            return false;
				}
				
				var requestData = {}
				var frm_data = $("#frm").serializeArray();
				for (var index in frm_data) {
                	requestData[frm_data[index].name] = frm_data[index].value;
                }
	                
				TckJS.Ajax.postAjaxLoding("/frt/order/ajax/insert", requestData , function(res) {
					location.href="/frt/order/complete";
				})
			});
		});
		
	     //개인정보 취급방침 layer popup
        $("#viewInfo").click(function() {
            $(".dimmed, .pop01").show();

            var w = $(".pop01").width();
            var h = $(".pop01").height();

            $(".pop01").css({
                'left': ($(window).width() - w) / 2
            });
            $(".pop01").css({
                'top': ($(window).height() - h) / 2
            });
        });
	}
}
/* ]]> */