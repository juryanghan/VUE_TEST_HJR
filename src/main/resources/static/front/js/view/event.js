/*<![CDATA[*/
var contentUpload;
var EventJS = {
    init : function() {

    },
    getFileExtension : function(filePath){
    console.log(filePath + "    filePath");
        var lastIndex = -1;
            lastIndex = filePath.lastIndexOf('.');
            var extension = "";

            if ( lastIndex != -1 ){
                extension = filePath.substring( lastIndex+1, filePath.len );
            } else {
                extension = "";
            }
            return extension.toLowerCase();
    },
    goFileUpload : function(obj){
      $("#" + obj).click();
    },
    uploadImageChange : function(event, obj, nameObj){
            var src = EventJS.getFileExtension(obj.value);

            if (src == "") {
                return;
            } else if ( !((src.toLowerCase() == "gif") || (src.toLowerCase() == "jpg") || (src.toLowerCase() == "jpeg") || (src.toLowerCase() == "png") || (src.toLowerCase() == "bmp")) ) {
                alert("gif/jpg/png/bmp 파일만 지원합니다.");
                return;
            }

            let file = obj.files[0];
            var size = file.size || file.fileSize;
            if( size > (1024 * 1024 * 10) )
            {
                TckJS.Util.Alert( '파일용량은 10MB 를 넘을수 없습니다.' );
                return;
            }

            var reader = new FileReader();
            reader.onload = function(event) {
                document.getElementById("defaultImg").setAttribute("src", event.target.result);
            };

            reader.readAsDataURL(event.target.files[0]);

            $("#" + nameObj).val(obj.value);
    },
    eventAdd : function(){
        var ValidateEl = $("#frm [data-required]");
                TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {
                    var frm_data = $("#frm").serializeArray();
                    var formData = new FormData();
                    for (var index in frm_data) {
                        if(frm_data[index].name == "imageNos" ){
                            continue;
                        }
                        else {
                            formData.append(frm_data[index].name, frm_data[index].value);
                        }
                    }

                    var eventImageFile = $("#eventImageFile")[0];
                    formData.append("eventImageFile", eventImageFile.files[0]);

                    TckJS.Ajax.postAjaxFormLoding("/frt/event/ajax/add", formData , function (res) {
                        location.reload();
                    });
                });
    }
}
/*]]>*/
