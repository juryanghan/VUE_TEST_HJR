/*<![CDATA[*/
var EventBoardJS = {
    init : function() {

       //이미지 클릭시 해당 이미지 모달
    	$(".imgC").click(function(){
    		$(".modal").show();

    		var imgSrc = $('.imgC').attr('src');
    		var imgAlt = $('imgC').attr('alt');
    		$(".modalBox img").attr("src", imgSrc);
    		$(".modalBox img").attr("alt", imgAlt);

       // 해당 이미지에 alt값을 가져와 제목으로
    		//$(".modalBox p").text(imgAlt);
    	});

    	//.modal닫기
    	$(".modal button").click(function(){
    		$(".modal").hide();
    	});

    	//.modal밖에 클릭시 닫힘
    	$(".modal").click(function (e) {
            if (e.target.className != "modal") {
              return false;
            } else {
              $(".modal").hide();
            }
        });
    },
    Like : function(eventNo,like) {
//    var star = $("input:radio[name=rating]:checked").val();
//                    $("#myform").find("#like").val(star);
//
//             TckJS.Ajax.postAjaxLoding("/adm/admin/like", {eventNo:eventNo}, function (res) {
//                 location.reload();
//             });
            $("#Searchfrm").find("#like").val(like);
             TckJS.Ajax.postAjaxLoding("/adm/admin/ajax/like", {eventNo:eventNo , like:like}, function (res) {
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
    selectImg: function(){
		if(!$(".selectImg").is(":checked")) {
			TckJS.Util.Alert("선택된 항목이 없습니다.");
			return;
		}

		/*ExcelUtil.Down($("#Searchfrm"),"/adm/admin/download" ,"이미지다운", "이미지다운");*/
			$(".imgC").val("dlalwl.png");
        	$("#Searchfrm").attr("action", "/adm/admin/download").submit();
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
    imgModal: function(){

//      //이미지 클릭시 해당 이미지 모달
//    	$(".imgC").click(function(){
//    		$(".modal").show();
//    		// 해당 이미지 가져오기
//    		var imgSrc = $('.imgC').attr('src');
//    		var imgAlt = $('imgC').attr('alt');
//    		$(".modalBox img").attr("src", imgSrc);
//    		$(".modalBox img").attr("alt", imgAlt);
//
//    <!--		// 해당 이미지 텍스트 가져오기-->
//    <!--		var imgTit =  $(this).children("p").text();-->
//    <!--		$(".modalBox p").text(imgTit);-->
//
//       // 해당 이미지에 alt값을 가져와 제목으로
//    		//$(".modalBox p").text(imgAlt);
//    	});
//
//    	//.modal안에 button을 클릭하면 .modal닫기
//    	$(".modal button").click(function(){
//    		$(".modal").hide();
//    	});
//
//    	//.modal밖에 클릭시 닫힘
//    	$(".modal").click(function (e) {
//            if (e.target.className != "modal") {
//              return false;
//            } else {
//              $(".modal").hide();
//            }
//        });


    }
}
/*]]>*/
