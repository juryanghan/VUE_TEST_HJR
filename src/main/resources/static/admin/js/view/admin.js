/*<![CDATA[*/
var AdminJS = {
 init : function() {
	$("#adminId").change(function() {
		$("#frm").find("#overlap").val("0");
	})


	$("#InsertBtn").click(function() {
		  var ValidateEl = $("#frm [data-required]");
            TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {
            	if ($("#frm").find("#overlap").val() == "0") {
					alert("아이디 중복 검사를 해주세요.");
					return false;
				}
            	
                var requestData = {}
                var adminMenus = new Array();
                
                var frm_data = $("#frm").serializeArray();

                for (var index in frm_data) {
                	if(frm_data[index].name =="adminMenus"){
                		adminMenus.push(frm_data[index].value);
                	}else {
                		requestData[frm_data[index].name] = frm_data[index].value;
                	}
                }
                requestData["adminMenus"]=  adminMenus.join(",");
                TckJS.Ajax.postAjaxLoding("/adm/admin/ajax/insert", requestData, function (res) {
                    alert("등록 되었습니다.");
                    location.reload();
                })
            });
		});
	
	$("#UpdateBtn").click(function() {
		  if(!confirm("수정하시겠습니까?")) return false;
		  var ValidateEl = $("#frm [data-required]").not("#adminPw");
          TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {
              var requestData = {}
              var adminMenus = new Array();
              var frm_data = $("#frm").serializeArray();

              for (var index in frm_data) {
              	if(frm_data[index].name =="adminMenus"){
              		adminMenus.push(frm_data[index].value);
              	}else {
              		requestData[frm_data[index].name] = frm_data[index].value;
              	}
              }
              requestData["adminMenus"]=  adminMenus.join(",");
              TckJS.Ajax.postAjaxLoding("/adm/admin/ajax/update", requestData, function (res) {
                  alert("수정 되었습니다.");
                  location.reload();
              })
          });
	});
	},
	DataSet :function (data){
		$("#frm").find("#adminNo").val(data.adminNo);
		$("#frm").find("#adminGroup").val(data.adminGroup);
		$("#frm").find("#adminName").val(data.adminName);
		$("#frm").find("#adminId").val(data.adminId);
		$("#frm").find("[name=adminState][value="+data.adminState+"]").attr("checked",true);
		var adminMenus = data.adminMenus.split(',');
		for(var i =0; i < adminMenus.length ; i++ ){
			if(adminMenus[i] != ""){
				$("#frm").find("[name=adminMenus][value="+adminMenus[i]+"]").attr("checked",true);
			}
		}
		
		AdminJS.SetDetail("U");
	},
	SetDetail :function (type){
		var adminId = $("#frm").find("#adminId");
		var adminPw = $("#frm").find("#adminPw");
		var InsertBtn = $("#InsertBtn");
		var UpdateBtn = $("#UpdateBtn");
		
		if(type == "I"){
			adminId.removeAttr("disabled");
			adminPw.attr("placeholder","비밀번호를 입력하세요.");
			InsertBtn.show();
			UpdateBtn.hide();
			$("#frm")[0].reset()
		}
		else if(type == "U"){
			adminId.attr("disabled", true);
			adminPw.attr("placeholder","변경 시 입력");
			InsertBtn.hide();
			UpdateBtn.show();
		}
		
		$('#divAccountDetail').modal('show');
	},
	GetData : function (adminNo){
		TckJS.Ajax.postAjaxLoding("/adm/admin/ajax/getAdmin", {adminNo:adminNo}, function (res) {
			AdminJS.DataSet(res);
        });
	},
	goDel : function() {			
		if(!$(".c_adminNos").is(":checked")) {
			TckJS.Util.Alert("선택된 항목이 없습니다.");
			return;
		}
		TckJS.Util.Confirm("선택된 관리자를 삭제하시겠습니까?", function(){
			TckJS.Ajax.postAjaxLoding("/adm/admin/ajax/delete", $(".c_adminNos:checked").serializeArray(), function (res) {
				alert("삭제되었습니다.");
				location.reload();
	        });
		}, TckJS.Util.ConfirmCancle());
	},Overlap : function () {
		
		if($("#adminId").val().trim() == ""){
			alert("아이디를 입력해 주세요.");
			$("#adminId").focus();
			return false;
		}
		
		if ($("#frm").find("#overlap").val() == "0") {
			TckJS.Ajax.postAjax("/adm/admin/ajax/overlap", { adminId : $("#adminId").val().trim() }, function (res) {
				 $("#frm").find("#overlap").val("1");
				 alert("사용 가능한 아이디 입니다.");
            })
		}
	}
}
/*]]>*/
