$(".navbar-toggle").click(function(){
    $("#navbar").addClass("in");
    $(".navbar-toggle").hide();
    $("#closeMenu").show();
});

$("#closeMenu").click(function(){
   $("#navbar").removeClass("in");
    $(".navbar-toggle").show();
    $("#closeMenu").hide(); 
});

//반응형 사이즈 계산
        $(function() {
            $(window).on('load resize', function() {

                var w = $(window).width();
                var h = $(window).height();
                
                var contentH = h-100;
                if(w<768){
                    contentH = h-50;
                    $(".navbar-inverse .navbar-brand").css('display','none');
                    $(".navbar-inverse .navbar-toggle").css('display','inline-block');
                }else{
                     $(".navbar-inverse .navbar-brand").css('display','inline-block');
                     $(".navbar-inverse .navbar-toggle").css('display','none');
                }
                $(".content.login").css('height',contentH);
                
                
            });
        });