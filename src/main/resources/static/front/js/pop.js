//공통 layer popup
function alert(msg) {
	$("#msgPop").html(msg);

	$(".dimmed, .pop02").show();

	var w = $(".pop02").width();
	var h = $(".pop02").height();

	$(".pop02").css({
		'left' : ($(window).width() - w) / 2
	});
	$(".pop02").css({
		'top' : ($(window).height() - h) / 2
	});
};


function openPop(msg) {
	$("#msgPop").html(msg);

	$(".dimmed, .pop02").show();

	var w = $(".pop02").width();
	var h = $(".pop02").height();

	$(".pop02").css({
		'left' : ($(window).width() - w) / 2
	});
	$(".pop02").css({
		'top' : ($(window).height() - h) / 2
	});
};

function closePop() {
	$(".dimmed").hide();
	$(".layer-pop").hide();
};

function gd_popup(options) {
	if (!options.width)
		options.width = 500;
	if (!options.height)
		options.height = 415;
	var status = new Array();
	$.each(options, function(i, v) {
		if ($.inArray(i, [ 'url', 'target' ]) == '-1') {
			status.push(i + '=' + v);
		}
	});
	var status = status.join(',');
	var win = window.open(options.url, options.target, status);
	return win;
}

