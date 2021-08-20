/*<![CDATA[*/
var ImagePopJS = {
	Show: function(el){
	    if ($('.imagesPop').length != 0) $(".imagesPop").remove();

        var tempHtml = "";
        tempHtml += "<div class='imagesPop'>";
        tempHtml += "	<div class='overlay'></div>";
        tempHtml += "	<div class='img-show'>";
        tempHtml += "		<span>X</span>";
        tempHtml += "		<img src='"+$(el).attr("src")+"'>";
        tempHtml += "	</div>";
        tempHtml += "</div>";
        
        $("body").append(tempHtml);
        $(".imagesPop").fadeIn();
		
		$(".imagesPop .overlay , .imagesPop span").click(function () {
	        $(".imagesPop").fadeOut(function () {
	        	$(".imagesPop").remove();
	        });
	    });
	}
}
/*]]>*/
