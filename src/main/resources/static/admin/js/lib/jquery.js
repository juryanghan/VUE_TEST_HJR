$(function() {
	
	$(".gnb").mouseenter(function(){
	 	$(".nav_depth").slideDown(220);
		$(".nav_bg").slideDown(190);	
	}).mouseleave(function(){
		$('.nav_depth').stop();
	  	$(".nav_depth").slideUp(200);
	  	$('.nav_bg').stop();
		$(".nav_bg").slideUp(220);
	});

});




$(document).ready(function (){
	addgnb();
	
});

/* header */
function addgnb(){
	
	$(".gnb>ul>li").click(function(e){
		var menu = e.currentTarget;
		var index = Number( $(menu).attr("index") );
		var width = $(menu).eq(0).find("a").width();
		setBar(index, width);
		setBg(index);

		
	}).mouseenter(function(e){
		var menu = e.currentTarget;
		var index = Number( $(menu).attr("index") );
		ov("ov");
		var width = $(menu).eq(0).find("a").width();
		setBar(index, width);
		setBg(index);
		_gnb_overIndex = index;
	}).mouseleave(function(e){
		var menu = e.currentTarget;
		var index = Number( $(menu).attr("index") );
		
	});

};

function ov($str){
	if($str == "ov"){
		_gnb_ov = true;
	}else{
		_gnb_ov = false;
		setTimeout(function (){
			if(_gnb_ov == false){
				setBar(_gnb_overIndex, 0);
				_active = false;

			}
		}, 500)
	}
}


function setBar($index, $width){
	var barindex = $index;
	var barPos = [0, 24, 190 ,357, 523];
	//var barWidth = [0, 64, 43, 50, 69, 52, 97];
	

	$(".gnb_anibar").stop().animate({left:barPos[barindex], width:$width},300);
}


function setBg($index){
	var bgindex = $index;
	var Class = ['bg','bg1','bg2','bg3','bg4'];
	
	$(".sub_gnb_img").addClass(Class[bgindex]).removeClass(Class[bgindex-1]).removeClass(Class[bgindex+1]);
}

