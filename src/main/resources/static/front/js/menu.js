if(location.pathname.indexOf("product") != -1 || location.pathname.indexOf("order") != -1){
	$(".navbar-nav").find("#product").addClass("active");
}
else if(location.pathname.indexOf("mypage") != -1){
	$(".navbar-nav").find("#mypage").addClass("active");
}
else if(location.pathname.indexOf("faq") != -1){
	$(".navbar-nav").find("#faq").addClass("active");
}
