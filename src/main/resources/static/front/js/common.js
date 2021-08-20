$(".navbar-toggle").click(function(){
    $("#navbar").addClass("in");
    $(".navbar-inverse .navbar-brand").hide();
    $(".navbar-toggle").hide();
    $("#closeMenu").show();
});

$("#closeMenu").click(function(){
   $("#navbar").removeClass("in");
    $(".navbar-inverse .navbar-brand").show();
    $(".navbar-toggle").show();
    $("#closeMenu").hide(); 
});

//반응형 사이즈 계산
        $(function() {
            $(window).on('load resize', function() {

                var w = $(window).width();
                var h = $(window).height();
                
                var contentH = h-414;
                
                if(w<768){
                    contentH = h-335;
                   // $(".navbar-inverse .navbar-brand").css('display','none'); 
                    $(".navbar-inverse .navbar-toggle").css('display','inline-block');
                }else{
                    // $(".navbar-inverse .navbar-brand").css('display','inline-block');
                     $(".navbar-inverse .navbar-toggle").css('display','none');
                     
                }
                $(".content").css('min-height',contentH);
                
                
            });
        });