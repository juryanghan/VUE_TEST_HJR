/*<![CDATA[*/
var OrderJS = {
	init : function() {
		$("#Searchfrm").find("#searchReceiverPhone").keyup(function(){
			if(this.value != "") $("#Searchfrm").find("#searchReceiverPhone2").val(Common.correctPhoneNumber_text(this.value));
		})		
		
		if($('#searchDeliveryStartDtFrom[type=text]').length == 1){
			$('#searchDeliveryStartDtFrom').datepicker();
			$('#searchDeliveryStartDtFrom').datepicker("option", "maxDate", $("#searchDeliveryStartDtTo").val());
			$('#searchDeliveryStartDtFrom').datepicker("option","onClose",function(selectedDate) {
				$("#searchDeliveryStartDtTo").datepicker("option", "minDate",selectedDate);
			});
		}
		
		if($('#searchDeliveryStartDtTo[type=text]').length == 1){
			$('#searchDeliveryStartDtTo').datepicker();
			$('#searchDeliveryStartDtTo').datepicker("option", "minDate",$("#searchDeliveryStartDtFrom").val());
			$('#searchDeliveryStartDtTo').datepicker("option","onClose",function(selectedDate) {
			});
		}
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
	}
}
/* ]]> */