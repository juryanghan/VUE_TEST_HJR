/*<![CDATA[*/
var EventBoardJS = {
    init : function() {

       //이미지 클릭시 해당 이미지 모달
    	$(".imgC").click(function(){
    		var imgSrc = $(this).attr('src');
    		var imgAlt = $(this).attr('alt');
    		$(".modalBox img").attr("src", imgSrc);
    		$(".modalBox img").attr("alt", imgAlt);

    		$("#filefrm").find("#eventNo").val($(this).data("event_no"));
    		$('#img_modal').modal('show');

    	});

    	//.modal닫기
    	$(".img_modal button").click(function(){
    		$('#img_modal').modal('hide');
    	});

       //작품설명 클릭시 해당 이미지 모달
//        $(".content_view").click(function(){
//        alert("Dddd")
//            var content = $(#content_view).val();
//            $(".modalContentBox p").text(content);
//            $(".filefrm").find("#event_No").val($(this).data("event_no"));
//
//            $('#content_modal').modal('show');
//
//            });
//
//       //.modal닫기
//        $(".content_modal button").click(function(){
//            $('#content_modal').modal('hide');
//        });
    },
    Like : function(eventNo,like) {
            $("#Searchfrm").find("#like").val(like);
             TckJS.Ajax.postAjaxLoding("/adm/admin/ajax/like", {eventNo:eventNo , like:like}, function (res) {
                 alert("처리되었습니다.");
                 location.reload();
             });
    },
    Process : function(eventNo,openYn) {
        if(openYn =="Y"){
            alert("이미 처리되어있습니다.");
            return false;
        }

        if(!confirm("처리하시겠습니까?")) return false;
            TckJS.Ajax.postAjaxLoding("/adm/admin/process", {eventNo:eventNo}, function (res) {
                alert("처리 되었습니다.");
                location.reload();
             });
    },
    ExcelDown: function(){
		if(!$(".selectImg").is(":checked")) {
			TckJS.Util.Alert("선택된 항목이 없습니다.");
			return;
		}

		var selectImg = $(".selectImg:checked");
		var selectKey = [];
		for(var i = 0; i < selectImg.length; i++){
 		    selectKey.push(selectImg[i].value);
		}

		var eventNos = selectKey.join(",")
		$("#Searchfrm").find("#eventNos").val(eventNos);
		ExcelUtil.Down($("#Searchfrm"),"/adm/admin/excel_select_download" ,"이벤트_참여_내역", "이벤트_참여_내역");


    },
    ExcelDownAll : function(){
        ExcelUtil.Down($("#Searchfrm"),"/adm/admin/excel_download" ,"이벤트_참여_내역(전체)", "이벤트_참여_내역(전체)");
     },
    deleteBtn : function(eventNo,openYn){
            if(openYn =="Y"){
                        alert("이미 처리되어있습니다.");
                        return false;
             }

            if(!confirm("삭제하시겠습니까?")) return false;
            TckJS.Ajax.postAjaxLoding("/adm/admin/delete",{eventNo:eventNo}, function (res) {
                alert("처리 되었습니다.");
                location.reload();
            });
    },
}
/*]]>*/
