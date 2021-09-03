/*<![CDATA[*/
var StoreJS = {
 init : function() {

	$("#InsertBtn").click(function() {
		  var ValidateEl = $("#frm [data-required]");
            TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {

                var requestData = {}
                var frm_data = $("#frm").serializeArray();
                for (var index in frm_data) {
                		requestData[frm_data[index].name] = frm_data[index].value;

                }
                TckJS.Ajax.postAjaxLoding("/adm/admin/ajax/store", requestData, function (res) {
                    alert("등록 되었습니다.");
                    location.reload();
                })
            });
		});
	},
	SetDetail :function (type){

		if(type == "I"){
			var InsertBtn = $("#InsertBtn");
            $('#divInsertStore').modal('show');
		}


	}
}
/*]]>*/
