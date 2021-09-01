/*<![CDATA[*/
var EventBoardJS = {
    init : function() {

        $(".datepicker[type=text]").datepicker(
            {
                dateFormat : 'yy-mm-dd',
                monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
                    '7월', '8월', '9월', '10월', '11월', '12월' ],
                dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
                weekHeader : 'Wk',
                changeMonth : true, // 월변경가능
                changeYear : true, // 년변경가능
                yearRange : '2000:+5', // 연도 셀렉트 박스 범위(현재와 같으면 1988~현재년)
                showMonthAfterYear : true, // 년 뒤에 월 표시
                buttonImageOnly : true, // 이미지표시
                buttonText : '날짜를 선택하세요',
                autoSize : true, // 오토리사이즈(body등 상위태그의 설정에 따른다)
                buttonImage : '/images/calendar_icon.png', // 이미지주소
                showOn : "both" // 엘리먼트와 이미지 동시 사용
            });

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
