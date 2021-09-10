/*<![CDATA[*/
var contentUpload;
var fileList = new Array();
var fileSizeList = new Array();
var totalFileSize = 0;
// 등록 가능한 파일 사이즈 MB
var uploadSize = 0.2;
// 등록 가능한 총 파일 사이즈 MB
//var maxUploadSize = 500;
var EventJS = {
    init : function() {

        $('.fileDrop')
          .on("dragover", dragOver)
          .on("dragleave", dragOver)
          .on("drop", uploadFiles);

        function dragOver(e){
          e.stopPropagation();
          e.preventDefault();
        }

        function uploadFiles(e) {
            e.stopPropagation();
            e.preventDefault();
            dragOver(e);

            e.dataTransfer = e.originalEvent.dataTransfer;
            var files = e.target.files || e.dataTransfer.files;

            if (files.length > 1) {
                alert('사진은 한장만 업로드 가능합니다.');
                return;
            }else if(!(files[0].type.match(/image.*/))){
                // 확장자 체크
                alert("이미지가 아닙니다.");
                $("#fileUploadBtn").val('');
                fileList = new Array();
                $('.file-Label').show();
                $('#file-label2').css({
                    "background-image" : "none"
                });
                return;
            }else{
                EventJS.selectFile(files);
            }
        }
    },
    check2 : function (obj){
        let file = obj.files;
        if (file.length > 1) {
            alert('사진은 한장만 업로드 가능합니다.');
            return;
        }else if(!(file[0].type.match(/image.*/))){
            // 확장자 체크
            alert("이미지가 아닙니다.");
            $("#fileUploadBtn").val('');
            fileList = new Array();
            $('.file-Label').show();
            $('#file-label2').css({
                "background-image" : "none"
            });
            return;
        }
        else{
            EventJS.selectFile(file);
        }
    },
    selectFile : function(files){
        if(files != null){
            // 파일 이름
            var fileName = files[0].name;
            var fileNameArr = fileName.split('.');
            // 확장자
            var ext = fileNameArr[fileNameArr.length - 1];
            // 파일 사이즈(단위 :MB)
            var fileSize = files[0].size / 1024 / 1024;
            var img = new Image();
            img.src = window.URL.createObjectURL(files[0]);
            var imgWidth;
            var imgHeight;

                if(fileSize < uploadSize){
                   // 파일 사이즈 체크
                   alert("업로드 가능 용량은 " + (uploadSize*1000) + " KB 이상 입니다.");
                   $("#fileUploadBtn").val('');
                   fileList = new Array();
                   $('.file-Label').show();
                   $('#file-label2').css({
                        "background-image" : "none"
                   });
                   return;
                }else if(files[0].type.match(/image.*/) && fileSize > uploadSize){
                   $('#file-label2').css({
                       "background-image": "url(" + window.URL.createObjectURL(files[0]) + ")",
                       "outline": "none",
                       "background-size": "100% 100%",
                       "width": "530px",
                       "height" : "183px"
                   });
                   $('.file-Label').hide();

                   // 전체 파일 사이즈
                   totalFileSize += fileSize;
                   // 파일 배열에 넣기
                   fileList[0] = files[0];
                   // 파일 사이즈 배열에 넣기
                   fileSizeList[0] = fileSize;

                }
            }else{
                alert("ERROR");
            }

    },
    eventAdd : function(){
            if(confirm("등록 하시겠습니까?")){
                var ValidateEl = $("#frm [data-required]");
                TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {
                    // 등록할 파일 리스트
                    var uploadFileList = Object.keys(fileList);
                    // 파일이 있는지 체크
                    if(uploadFileList.length == 0 &&  $("#fileUploadBtn")[0].files.length == 0){
                        // 파일등록 경고창
                        alert("파일이 없습니다.");
                        return;
                    }

                    if($("input:checkbox[id=privacyCheck_01]").is(":checked") == false){
                        alert('개인정보 처리에 관한 동의를 해주세요.');
                        return;
                    }

                    if($("input:checkbox[id=privacyCheck_02]").is(":checked") == false){
                        alert('유의사항 및 저작권 관련 사항에 대한 서약서에 대한 동의를 해주세요.');
                        return;
                    }



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

                    for(var i = 0; i < uploadFileList.length; i++){
                        formData.append('eventImageFile', fileList[uploadFileList[i]]);
                    }

                    TckJS.Ajax.postAjaxFormLoding("/frt/event/ajax/add", formData , function (res) {
                        alert('등록되었습니다.');
                        location.reload();
                    });
                });
            }
    }
}
/*]]>*/
