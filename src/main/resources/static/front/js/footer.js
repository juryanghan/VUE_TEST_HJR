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
function gd_popup_bizInfo(businessNo) {
	var url = 'http://www.ftc.go.kr/info/bizinfo/communicationViewPopup.jsp?wrkr_no='
			+ businessNo;
	var win = gd_popup({
		url : url,
		target : 'communicationViewPopup',
		width : 750,
		height : 700,
		resizable : 'no',
		scrollbars : 'no'
	});
	win.focus();
	return win;
}