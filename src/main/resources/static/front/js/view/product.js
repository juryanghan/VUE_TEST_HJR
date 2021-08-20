/*<![CDATA[*/
var ProductJS = {
	init : function() {

		$('#productOptionNo').niceSelect();

		$('#productOptionNo').change(function() {
			if (this.value == "") {
				$("#OrderBtn").removeClass("active");
			} else {
				$("#OrderBtn").addClass("active");
			}
		})

		$(".imgbox03").slick({
			slidesToShow : 1,
			slidesToScroll : 1,
			arrows : false,
			dots : true
		});

		// image change
		$(".imgbox01 img").click(function() {
			$("#imgL").attr("src", this.src);
		});

		$("#OrderBtn").click(function() {
			if ($("#productOptionNo").val() == "") {
				alert("색상을 선택해주세요.");
				return false;
			}
			alert("주문 완료 후 색상 교환은 불가합니다.<br/>신중하게 색상 선택 부탁드립니다");
			$(".pop02").find(".btn-close").click(function(){
				ProductJS.submit();
			})
		});
	},
	submit : function() {
		$("#frm").attr("action", "/frt/order/index");
		$("#frm").submit();
	}
}
/* ]]> */