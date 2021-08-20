/*<![CDATA[*/
var UpLoadrJS = {
	images : function(uploadSet) {
		return new plupload.Uploader({
			runtimes : 'html5,flash',
			drop_element : uploadSet.dropElement,
			browse_button : uploadSet.Button,
			multi_selection : false,
			unique_names : true,
			chunk_size : "1mb",
			flash_swf_url : '/admin/js/lib/plupload/plupload.flash.swf',
			filters : [{ title: '이미지 파일', extensions: 'jpg,jpeg,png,gif,bmp'}],
			url : "/adm/file/images",
			preinit : function(up) {
				up.bind('FilesAdded', function(up, files) {
					var i = 0, filext = '', exist = false, filt = $.map(
							up.settings.filters, function(obj, index) {
								return obj.extensions;
							}).join(',');
					for (i = 0; i < files.length; i++) {
						filext = files[i].name.substring(files[i].name
								.lastIndexOf(".") + 1);
						exist = new RegExp(',*' + filext + ',*', 'i')
								.test(filt);
						if (!exist) {
							alert("업로드가 불가능한 확장자 입니다. 가능 확장자(" + filt + ")");
						}
					}
				});
				up.bind('QueueChanged', function(up, files) {
					up.start();
				});

				up.bind('FileUploaded', function(up, file, res) {
					var data = JSON.parse(res.response);
					uploadSet.imgPlaceholderEl.attr('src', data.fileUrl);
					uploadSet.imgNameEl.val(data.fileUrl);
				});
			}
		});
	},
	html : function (uploadSet) {
	 return new plupload.Uploader({
         runtimes: 'html5,flash',
         drop_element : uploadSet.dropElement,
         browse_button : uploadSet.Button,
         chunk_size: "1mb",
         flash_swf_url : '/admin/js/lib/plupload/plupload.flash.swf',
         filters: [{ title: '이미지 파일, HTML 파일', extensions: 'jpg,jpeg,png,gif,bmp,htm,html' }],
         url: "/adm/file/content",
         preinit: function (up) {
             up.bind('FilesAdded', function (up, files) {
             	var filext = files[0].name.substring(files[0].name.lastIndexOf(".") + 1);
             	if(filext != "html" && filext != "htm"){
	                	if(uploadSet.CotentValEl.val() == "")
	                	{
	                		alert("HTML 파일 부터 등록해주세요.");
	                		return false;
	                	}
             	}
             	
             	var hdnUploadList =  uploadSet.TargetEl.find("#hdnUploadList").val();
             	var checkList =  hdnUploadList.split(";");
             	
                 var i = 0,
                     exist = false,
                     filt = $.map(up.settings.filters, function (obj, index) { return obj.extensions; }).join(',');

                 for (i = 0; i < files.length; i++) {
                 	for(var t = 0; t< checkList.length ; t++){
                 		if(files[i].name == checkList[t]){
                 			alert("이미 등록된 파일 입니다.");
                 			return false;
                 		}
	                	}
                 	
                 	
                     filext = files[i].name.substring(files[i].name.lastIndexOf(".") + 1);
                     exist = new RegExp(',*' + filext + ',*', 'i').test(filt);
                     if (!exist) {
                     	alert("업로드가 불가능한 확장자 입니다. 가능 확장자(" + filt + ")");
                     }
                 }
             });

             up.bind('QueueChanged', function (up, files) {
             	uploadSet.TargetEl.find("#spnUploadCount").text('1 / ' + up.files.length);
             	uploadSet.TargetEl.find("#divUploadProgress").find('.bar').css('width', '0%').end().show();
                 index = 0;
                 up.start();
             });

             up.bind('UploadComplete', function () {
                 setTimeout(function () {
                 	uploadSet.TargetEl.find("#divUploadProgress").fadeOut(200, function () { $(this).hide().css('opacity', '1'); });
                 }, 500);

             });
             up.bind("UploadProgress", function (up, file) {
             	uploadSet.TargetEl.find(".bar").css('width', up.total.percent + '%'); 
             });

             up.bind('FileUploaded', function (up, file, res) {
             	
                 var data = JSON.parse(res.response),
                     fileExt = Common.GetExtension(data.oriFilename);

                 uploadSet.TargetEl.find("#spnUploadCount").text(++index + ' / ' + up.files.length);

                 if (fileExt !== 'html' && fileExt !== 'htm'){
                	 
                 	var hdnContents = uploadSet.CotentValEl.val();
                 	hdnContents = hdnContents.replace("src=\"images/"+data.oriFilename,"src=\""+data.fileUrl);
                 	uploadSet.CotentValEl.val(hdnContents);
                 }
                 	

                 if (fileExt === 'html' || fileExt === 'htm'){
                 	uploadSet.CotentValEl.val(data.htmlContent);
                 }
                 	
             	uploadSet.TargetEl.find("#hdnUploadList").val(uploadSet.TargetEl.find("#hdnUploadList").val() + ';' + data.oriFilename);
                 uploadSet.TargetEl.find("#ulUploadList").append('<li class="text-warning">' + data.oriFilename + '</li>');
             });
         }
     });
	}
}
/* ]]> */