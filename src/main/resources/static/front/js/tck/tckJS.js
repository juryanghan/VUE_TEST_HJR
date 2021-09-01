var CURRENT_VALID_OBJID;
var SITE_DOMAIN_NAME = "127.0.0.1";

var TckJS = {};


TckJS.Util = TckJS.prototype = {
	AlertMode : "default"   // default - 기본 , layer - 레이아웃
	, Confirm : function(msg,callbackOk,callbackCancle) {
		if ( this.AlertMode == "layer" ) 
		{
			this.ConfirmCallBackOk = callbackOk;
			this.ConfirmCallBackCancle = callbackCancle;
			var tempHtml = "";
			
			tempHtml += "<div class=\"tck-layer\" style=\"display:none;\">";
			tempHtml += "	<div id=\"blind\" style=\"display:block;\">레이어팝업시 뒤에 깔리는 검은 배경</div>";
			
			tempHtml += "	<div class=\"layer_pop\" style=\"display:block;\">";
			// tempHtml += " <p class=\"layer_close\"
			// onclick=\"TckJS.Util.ConfirmCancle();\"><img
			// src=\"../images/layer_close.png\" alt=\"닫기\" /></p>";
			tempHtml += "		<p class=\"layer_text\">" + msg + "</p>";
			tempHtml += "		<div class=\"layer_btn\">";
			tempHtml += "			<a href=\"javascript:;\" onclick=\"TckJS.Util.ConfirmOk();\" id=\"tckjs_btn_yes\" class=\"btn_yes\">예</a>";
			tempHtml += "			<a href=\"javascript:;\" onclick=\"TckJS.Util.ConfirmCancle();\" id=\"tckjs_btn_no\" class=\"btn_yes\">아니오</a>";
			tempHtml += "		</div>";
			tempHtml += "	</div>";
			tempHtml += "</div>";
			
			
			if($('.tck-layer').length != 0) $('.tck-layer').remove();
			$("body").append(tempHtml);
			$('.tck-layer').show();
			$('#tckjs_btn_yes').focus();
			
			// center
			if ($('.tck-layer-content').outerHeight() < $(document).height() ) $('.tck-layer-content').css('margin-top', '-'+$('.tck-layer-content').outerHeight()/2+'px');
			else $('.tck-layer-content').css('top', '0px');
			if ($('.tck-layer-content').outerWidth() < $(document).width() ) $('.tck-layer-content').css('margin-left', '-'+$('.tck-layer-content').outerWidth()/2+'px');
			else $('.tck-layer-content').css('left', '0px');
		}else {
			if(confirm(msg)){
				callbackOk();
			}
		}
	}
	, ConfirmOk : function(){
		$(".tck-layer").remove();
		
		if ( this.ConfirmCallBackOk )
		{
			this.ConfirmCallBackOk();
		}
	}
	, ConfirmCancle : function(){
		$(".tck-layer").remove();
		
		if ( this.ConfirmCallBackCancle )
		{
			this.ConfirmCallBackCancle();
		}
	}
	, ConfirmCallBackOk : null
	, ConfirmCallBackCancle : null
	, Alert : function(msg,callbackOk){
		if ( this.AlertMode == "layer" ) 
		{
			this.AlertCallBack = callbackOk;
			var tempHtml = "";
			
			tempHtml += "<div class=\"tck-layer\" style=\"display:none;\">";
			tempHtml += "	<div id=\"blind\" style=\"display:block;\">레이어팝업시 뒤에 깔리는 검은 배경</div>";
			
			tempHtml += "	<div class=\"layer_pop\" style=\"display:block;\">";
			// tempHtml += " <p class=\"layer_close\"
			// onclick=\"TckJS.Util.ConfirmCancle();\"><img
			// src=\"../images/layer_close.png\" alt=\"닫기\" /></p>";
			tempHtml += "		<p class=\"layer_text\">" + msg + "</p>";
			tempHtml += "		<div class=\"layer_btn\">";
			tempHtml += "			<a href=\"javascript:;\" onclick=\"TckJS.Util.AlertClose();\" class=\"btn_yes\">확인</a>";
			tempHtml += "		</div>";
			tempHtml += "	</div>";
			tempHtml += "</div>";
			
			$("body").append(tempHtml);
			$('.tck-layer').show();
			$('.btn_yes').focus(-1);
			
			// center
			if ($('.tck-layer-content').outerHeight() < $(document).height() ) $('.tck-layer-content').css('margin-top', '-'+$('.tck-layer-content').outerHeight()/2+'px');
			else $('.tck-layer-content').css('top', '0px');
			if ($('.tck-layer-content').outerWidth() < $(document).width() ) $('.tck-layer-content').css('margin-left', '-'+$('.tck-layer-content').outerWidth()/2+'px');
			else $('.tck-layer-content').css('left', '0px');
		}else {
			callbackOk();
		}
	}
	, AlertClose : function(){
		$(".tck-layer").remove();
		$(CURRENT_VALID_OBJID).focus();
		
		if (this.AlertCallBack)
		{
			this.AlertCallBack();
		}
	}
	, AlertCallBack : null
	// 사용자 layer
	, SendAjax : function(URL,Frm){
		$.ajax({
	        type: "GET",
	        url: URL,
	        data: $(Frm).serialize().replace(/%/g,'%25'),
	        dataType:"html",
	        success: function(layerHtml){
	        	var tempHtml = "";
				
				tempHtml += "<div class=\"tck-layer\" style=\"display:none;\">";
				tempHtml += "	<div id=\"blind\" style=\"display:block;\">레이어팝업시 뒤에 깔리는 검은 배경</div>";
				
				tempHtml += "	<div class=\"layer_pop\" style=\"display:block;\">";
				tempHtml += "		<p class=\"layer_close\" onclick=\"TckJS.Util.ConfirmCancle();\"><img src=\"../images/layer_close.png\" alt=\"닫기\" /></p>";
				tempHtml += "		<p class=\"layer_text\">" + msg + "</p>";
				tempHtml += "	</div>";
				tempHtml += "</div>";
	    		
	        	
	        	$("body").append(tempHtml);
	        	$('.tck-layer').show();
	        	
				// 화면의 중앙에 레이어를 띄운다.
				if ($('.tck-layer-content').outerHeight() < $(document).height() ) $('.tck-layer-content').css('margin-top', '-'+$('.tck-layer-content').outerHeight()/2+'px');
				else $('.tck-layer-content').css('top', '0px');
				if ($('.tck-layer-content').outerWidth() < $(document).width() ) $('.tck-layer-content').css('margin-left', '-'+$('.tck-layer-content').outerWidth()/2+'px');
				else $('.tck-layer-content').css('left', '0px');

	        },
			beforeSend:function(){
	       		/* 통신 전 process (ex. 로딩바 표시) */
	        },
	        error: function(err){
                   /* 통신 에러 발생시 process */
                   alert(err.status);
   			}
		});
		
	}
	, Loding: function () {

        if ($('.loading-mask').length != 0) $(".loading-mask").remove();

        var tempHtml = "";
        tempHtml += "<div class=\"loading-mask\" style=\"display:none;\">";
        tempHtml += "	<div class=\"loader\">";
        tempHtml += "	<img src=\"/admin/img/loader.gif\" alt=\"로딩중…\" />";
        tempHtml += "	</div>";
        tempHtml += "</div>";

        $("body").append(tempHtml);
        $('.loading-mask').show();

    }
    , LayoutClose: function () {
        $(".loading-mask").remove();
    }
};

/*
 * ajax 공통 모듈 성공시 필터링 추가, 실패시 처리, 로딩중 처리 등 커스터마이즈
 */
TckJS.Ajax = TckJS.prototype = {
	 postAjax: function (strURL, objData, resultHandler) {
		        $.ajax({
		            type: "POST",
		            url: strURL,
		            data: objData,
		            dataType: "json",
		            success: function (res) {

		                if (res == null || res == undefined) {
		                    alert("오류가 발생하였습니다.");
		                    return;
		                }
		                
		                if (res.resultCode == "LOGIN") {
		                	alert("잘못된 접근입니다.");
		                	location.href="/frt";
		                    return;
		                }
		                
		                if (res.resultCode != "0000") {
		                    if (res.resultMsg != undefined) {
		                        alert(res.resultMsg);
		                        console.log(res);
		                    }
		                    else {
		                        alert("오류가 발생하였습니다.");
		                    }
		                    return;
		                }
		                resultHandler(res);
		            },
		            beforeSend: function () {
		                /* 통신 전 process (ex. 로딩바 표시) */
		            },
		            error: function (err) {
		                /* 통신 에러 발생시 process */
		                // alert(err.status);
		                // alert(err.statusText);
		                alert("오류가 발생하였습니다.");
		                console.log(err);
		            }
		        });
	    },
	    postAjaxLoding: function (strURL, objData, resultHandler) {
		        TckJS.Util.Loding();

		        $.ajax({
		            type: "POST",
		            url: strURL,
		            data: objData,
		            dataType: "json",
		            success: function (res) {
		                TckJS.Util.LayoutClose();
		                if (res == null || res == undefined) {
		                    alert("오류가 발생하였습니다.");
		                    return;
		                }
		                
		                if (res.resultCode == "LOGIN") {
		                	alert("잘못된 접근입니다.");
		                	location.href="/frt";
		                    return;
		                }
		                
		                if (res.resultCode != "0000") {
		                    if (res.resultMsg != undefined) {
		                        alert(res.resultMsg);
		                        console.log(res);
		                    }
		                    else {
		                        alert("오류가 발생하였습니다.");
		                    }
		                    return;
		                }
		                resultHandler(res);
		            },
		            beforeSend: function () {
		                /* 통신 전 process (ex. 로딩바 표시) */
		            },
		            error: function (err) {
		                /* 통신 에러 발생시 process */
		                // alert(err.status);
		                // alert(err.statusText);
		                TckJS.Util.LayoutClose();
		                alert("오류가 발생하였습니다.");
		                console.log(err);
		            }
		        });
	}, 
	postAddrLoding: function (strURL, objData, resultHandler) {
	        TckJS.Util.Loding();

	        $.ajax({
	            type: "POST",
	            url: strURL,
	            data: objData,
	            dataType:"jsonp",
	            crossDomain:true,
	            success: function (res) {
	                TckJS.Util.LayoutClose();
	                if (res == null || res == undefined) {
	                    alert("오류가 발생하였습니다.");
	                    return;
	                }
	                
	                if (res.resultCode == "LOGIN") {
	                	alert("잘못된 접근입니다.");
	                	location.href="/frt";
	                    return;
	                }
	               
	                resultHandler(res);
	            },
	            beforeSend: function () {
	                /* 통신 전 process (ex. 로딩바 표시) */
	            },
	            error: function (err) {
	                /* 통신 에러 발생시 process */
	                TckJS.Util.LayoutClose();
	                alert("오류가 발생하였습니다.");
	                console.log(err);
	            }
	        });
		}
	, getAjax : function(strURL,objData,resultHandler) {
		$.ajax({
	        type: "GET",
	        url: strURL,
	        data: objData,
	        dataType:"json",
	        success: function(response){
	        	resultHandler(response);
	        },
			beforeSend:function(){
	       		/* 통신 전 process (ex. 로딩바 표시) */
	        },
	        error: function(err){
                   /* 통신 에러 발생시 process */
                   alert(err.status);
   			}
		});
	}
	, formAjax : function(strElement,resultHandler) {
		$(eval(strElement)).ajaxForm({
			beforeSubmit: function(a,f,o) {
				/* 통신 전 process (ex. 로딩바 표시) */
			},
			uploadProgress: function (event, position, total, percentComplete) {
				/* 업로드중 */
			},
			success: function(response) {
				resultHandler(response);
			},
			error: function(err){
                   /* 통신 에러 발생시 process */
                   alert(err.status);
   			}
		});
	},
	postAjaxFormLoding: function (strURL, objData, resultHandler) {
            TckJS.Util.Loding();

            $.ajax({
                type: 'POST',
                url: strURL,
                data: objData,
                processData: false,
                contentType: false,
                success: function (res) {
                    TckJS.Util.LayoutClose();
                    if (res == null || res == undefined) {
                        alert("오류가 발생하였습니다.");
                        return;
                    }

                    if (res.resultCode == "LOGIN") {
                        alert("로그인이 필요합니다.");
                        location.href = "/adm/logIn";
                        return;
                    }

                    if (res.resultCode != "0000") {
                        if (res.resultMsg != undefined) {
                            alert(res.resultMsg);

                            console.log(res);
                        } else {
                            alert("오류가 발생하였습니다.");
                        }
                        return;
                    }
                    resultHandler(res);
                },
                beforeSend: function () {
                    /* 통신 전 process (ex. 로딩바 표시) */
                },
                error: function (err) {
                    /* 통신 에러 발생시 process */
                    // alert(err.status);
                    // alert(err.statusText);
                    TckJS.Util.LayoutClose();
                    alert("오류가 발생하였습니다.");
                    console.log(err);
                }
            });
        },
};

TckJS.Cookie = TckJS.prototype = {

	isCookie : function(name) {
		var acookie = document.cookie;
		var op = 0;

		if (acookie.length > 0) {
			var cc = acookie.split(";");
			for(var i=0;i<cc.length;i++){
				if(cc[i].match(name+"=done"))op = op + 1;
			}
		}
		if (op == 0) {
			return false;// 쿠키가 존재하지 않는다.
		} else {
			return true;// 쿠키가 존재한다.
		}
	}
	, getCookie : function(name) {
		var nameOfCookie = name + "=";
		var x = 0;

		while ( x <= document.cookie.length ) {

			var y = (x+nameOfCookie.length);

			if ( document.cookie.substring( x, y ) == nameOfCookie ) {
				if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
					endOfCookie = document.cookie.length;
				return unescape( document.cookie.substring( y, endOfCookie ) );
			}

			x = document.cookie.indexOf( " ", x ) + 1;
			if ( x == 0 )
				break;
		}

		return "";
	}
	, setCookie : function(name,value,expiredays)	{
		var todayDate = new Date();
		todayDate.setDate( todayDate.getDate() + expiredays );
		document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	}
	, deleteCookie : function(name) {
		if (getCookie(name)) {
			document.cookie = name + "=; path=/; domain=" + SITE_DOMAIN_NAME + "; expires=Thu, 01-Jan-1970 00:00:01 GMT";
		}
	}
};


TckJS.PageUtil = TckJS.prototype = {
	goView : function(keySeletor,keyVal,frmSeletor,frmaction) {
			if(keySeletor && keyVal) $(frmSeletor).find(keySeletor).val(keyVal);
			$(frmSeletor).attr("method","post");
			$(frmSeletor).attr("action",frmaction);
			$(frmSeletor).submit();
	},
	goPage : function(Page) {
		$("#Searchfrm").find("#currentPageNo").val(Page);
		$("#Searchfrm").submit();
	},
	init : function(setData,callback) {
		$(".datepicker[type=text]").datepicker(
				{
					dateFormat : 'yy-mm-dd',
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					weekHeader : 'Wk',
					changeMonth : true, // 월변경가능
					changeYear : true, // 년변경가능
					yearRange : '2000:+5', // 연도 셀렉트 박스 범위(현재와 같으면 1988~현재년)
					showMonthAfterYear : true, // 년 뒤에 월 표시
					buttonImageOnly : true, // 이미지표시
					buttonText : '날짜를 선택하세요',
					autoSize : true, // 오토리사이즈(body등 상위태그의 설정에 따른다)
					buttonImage : '/images/calendar_icon.png', // 이미지주소
					showOn : "both" // 엘리먼트와 이미지 동시 사용
		});
		
		if($('#searchKeywordFrom[type=text]').length == 1){
			$('#searchKeywordFrom').datepicker();
			$('#searchKeywordFrom').datepicker("option", "maxDate", $("#searchKeywordTo").val());
			$('#searchKeywordFrom').datepicker("option","onClose",function(selectedDate) {
				$("#searchKeywordTo").datepicker("option", "minDate",selectedDate);
			});
		}
		
		if($('#searchKeywordTo[type=text]').length == 1){
			$('#searchKeywordTo').datepicker();
			$('#searchKeywordTo').datepicker("option", "minDate",$("#searchKeywordFrom").val());
			$('#searchKeywordTo').datepicker("option","onClose",function(selectedDate) {
				$("#searchKeywordFrom").datepicker("option", "maxDate",selectedDate);
			});
		}
		
		var SearchShow = false;
		if(setData) {
			for(var i = 0 ; i < setData.length; i++) {
				if($("#" + setData[i].formID).find("#" + setData[i].targatID).length == 1){
					
					var el = $("#" + setData[i].formID).find("#" + setData[i].targatID);
					var NodeName = el[0].nodeName; 
					
					if(el.attr("id") == "currentPageNo"){
						if(setData[i].val == "") setData[i].val = "1";
						el.val(setData[i].val);
						continue;
					}
					
					if(!setData[i].val) continue;
					
					if(NodeName == "INPUT") {
						var InputType = el[0].type.toUpperCase();
						if(InputType == "TEXT") {
							el.val(setData[i].val);
							SearchShow = true;
						}
						else if(InputType == "RADIO") {
							$("#" + setData[i].formID).find("[name="+setData[i].targatID+"][value="+setData[i].val+"]").prop("checked",true);
							SearchShow = true;
						}	
					}
					else if(NodeName == "SELECT") {
						el.val(setData[i].val);
						SearchShow = true;
					}else {
						el.val(setData[i].val);
					}
				}
			}
		}
		
		if(SearchShow && $('#SearchRow').length == 1) $('#SearchRow').show();
		
		if($( ".select_box").length != 0) $( ".select_box").selectmenu();
		if(callback) callback();
	},
	goSearch : function () {
		this.goPage(1);
	},
	ReSetSearch : function () {
		$('#Searchfrm')[0].reset();
		if($( ".select_box").length != 0) $( ".select_box").selectmenu("refresh");
	},
	ShowSearch : function () {
		$('#SearchRow').slideDown()
	},
	HideSearch : function () {
		$('#SearchRow').slideUp()
	},
	frmDataSubmit : function (el) {		
		var addUrl = $(el).data("addurl");
		var frmID = $(el).data("frmid");
		
		$("#"+frmID).attr("method", "post");
		$("#"+frmID).attr("action", addUrl );
		$("#"+frmID).submit();
	},
	frmSubmit: function (frmSelector,action){
		$(frmSelector).attr("action",action);
		$(frmSelector).submit();
	},
	EnterSubmit: function (Selector,frmSelector){
		$(Selector).keyup(function(){
			if(event.keyCode == 13) $(frmSelector).submit();
		})
	},
	CloneSearchData: function (Searchfrm,Targetfrm){
		/* 검색 조건 input을 가져와서 현재 frm 에 추가 */
		var Searchfrm_inputs = $(Searchfrm).find("input").clone();
		$(Targetfrm).append(Searchfrm_inputs);
		/* 검색 조건 input을 가져와서 현재 frm 에 추가 끝 */
	},
	
	
};


TckJS.ValidateUtil = TckJS.prototype = {
    ValidateCheck: function (el, submitHandler) {
        if (el.length == 0) return null;
        var is_submit = true

        el.each(function (index, el) {
            if (el.value == "" || el.value == undefined) {
                alert("필수 입력 사항이 입력되지 않았습니다.(" + $(el).data("required") + ")");
                el.focus();
                is_submit = false;
                return false;
            } else {
            	is_submit = TckJS.ValidateUtil.CheckSearchedWord(el);
            	if(!is_submit) return false;;
            }
            
            if (el.hasAttribute("data-number")) {
                if (!(/[0-9]/.test(el.value))) {
                    alert("숫자만 입력이 가능합니다.");
                    el.focus();
                    is_submit = false;
                    return false;
                }
            }

            if (el.hasAttribute("maxlength")) {
                if (el.value.trim().length > parseInt(el.getAttribute("maxlength"))) {
                    alert(el.getAttribute("maxlength").int() + "글자 이하로 입력하세요.");
                    el.focus();
                    is_submit = false;
                    return false;
                }
            }

            if (el.hasAttribute("data-minlength")) {
                if (el.value.trim().length < parseInt(el.getAttribute("data-minlength"))) {
                    alert(el.getAttribute("data-minlength").int() + "글자 이상으로 입력하세요.");
                    el.focus();
                    is_submit = false;
                    return false;
                }
            }
            
            
            
        });
        if (is_submit) submitHandler();
    },
    initValidate: function (frmSelector, submitHandler) {
        if ($(frmSelector).find("[data-required]").length == 0) return null;

        var vaildate_rules = {};
        var vaildate_messages = {};

        $(frmSelector).find("[data-required]").each(function (index, el) {
            var el_rules = {}
            el_rules["required"] = true;
            el_rules["Injection"] = true;
            if (el.hasAttribute("data-number")) el_rules["number"] = true;
            if (el.hasAttribute("data-equalTo")) el_rules["equalTo"] = el.getAttribute("data-equalTo");
            if (el.hasAttribute("maxlength")) el_rules["maxlength"] = el.getAttribute("maxlength").int();
            if (el.hasAttribute("data-minlength")) el_rules["minlength"] = el.getAttribute("data-minlength").int();
            if (el.hasAttribute("data-email")) el_rules["emailExt"] = true;


            var el_messages = {}
            el_messages["required"] = el.getAttribute("data-required") + "은(는) 필수 입력 값입니다.";;
            if (el.hasAttribute("data-number")) el_messages["number"] = "숫자만 입력해 주세요.";
            if (el.hasAttribute("data-equalTo")) el_messages["equalTo"] = el.getAttribute("data-required") + " 일치하지 않습니다.";
            if (el.hasAttribute("maxlength")) el_messages["maxlength"] = el.getAttribute("maxlength").int() + "글자 이하로 입력하세요.";
            if (el.hasAttribute("data-minlength")) el_messages["minlength"] = el.getAttribute("data-minlength").int() + "글자 이상으로 입력하세요.";

            vaildate_rules[el.name] = el_rules;
            vaildate_messages[el.name] = el_messages;

        })

        return $(frmSelector).validate({
            rules: vaildate_rules,
            messages: vaildate_messages,
            invalidHandler: function (form, validator) {
                alert(validator.errorList[0].message);
            },
            submitHandler: function (form) {
                submitHandler(form);
            }
        });
    },
    CheckSearchedWord: function (obj) {
        if (obj.value.length > 0) {
            // 특수문자 제거
            var expText = /[%=><]/;
            if (expText.test(obj.value) == true) {
                alert("특수문자를 입력 할수 없습니다.");
                obj.value = obj.value.split(expText).join("");
                return false;
            }

            // 특정문자열(sql예약어의 앞뒤공백포함) 제거
            var sqlArray = new Array(
                // sql 예약어
                "OR", "SELECT", "INSERT", "DELETE", "UPDATE", "CREATE", "DROP", "EXEC",
                "UNION", "FETCH", "DECLARE", "TRUNCATE"
            );

            var regex;
            for (var i = 0; i < sqlArray.length; i++) {
                regex = new RegExp(sqlArray[i], "gi");

                if (regex.test(obj.value)) {
                    alert("\"" + sqlArray[i] + "\"와(과) 같은 특정문자로 검색할 수 없습니다.");
                    obj.value = obj.value.replace(regex, "");
                    return false;
                }
            }
        }
        return true;
    }
};


var Common = {
    MakeFolderName: function (name, boardNo, digits) {
        var intFolderName = parseInt(boardNo / digits);
        var zero = '';
        intFolderName = intFolderName.toString();

        if (intFolderName.length < digits) {
            for (var i = 0; i < 3 - intFolderName.length; i++)
                zero += '0';
        }
        zero + intFolderName
        var folderPath = "/" + name + "/" + zero + intFolderName + "/" + boardNo;
        return folderPath;
    },
    correctPhoneNumber: function (el) {
        el.on("keyup", function (event) {
            $(this).val($(this).val().replace(/[^0-9\-]/g, ''));
        });

        el.on("change", function (event) {
            var el_value = $(this).val();
            el_value = el_value.replace(/[-]/g, '');

            var val_length = el_value.length;
            if (val_length == 10) {
                $(this).val(el_value.replace(/(\d{3})\-?(\d{1,3})-?(\d{1,4})/, '$1-$2-$3'));
            }
            else if (val_length == 11) {
                $(this).val(el_value.replace(/(\d{3})\-?(\d{1,4})-?(\d{1,4})/, '$1-$2-$3'));
            }
            else {
                el_value = el_value.substring(0, 11)
                $(this).val(el_value.replace(/(\d{3})\-?(\d{1,4})-?(\d{1,4})/, '$1-$2-$3'));
            }
        });
    },
    correctPhoneNumber_text: function (value) {
        var return_value = value;
        return_value = return_value.replace(/[-]/g, '');

        var val_length = return_value.length;
        if (val_length == 10) {

        }
        else if (val_length == 11) {
            return_value = return_value.replace(/(\d{3})\-?(\d{1,4})-?(\d{1,4})/, '$1-$2-$3')
        }
        else {
            return_value = return_value.replace(/(\d{3})\-?(\d{1,4})-?(\d{1,4})/, '$1-$2-$3')
        }
        return return_value;
    },
    onlyNumber: function (el) {
        el.on("keyup", function (event) {
            $(this).val($(this).val().replace(/[^0-9]/g, ''));
        });
    },
    GetIEVersion: function () {
        var word;
        var agent = navigator.userAgent.toLowerCase();

        // IE old version ( IE 10 or Lower )
        if (navigator.appName == "Microsoft Internet Explorer") word = "msie ";

        // IE 11
        else if (agent.search("trident") > -1) word = "trident/.*rv:";
        // Microsoft Edge
        else if (agent.search("edge/") > -1) word = "edge/";
        // 그외, IE가 아니라면 ( If it's not IE or Edge )
        else return -1;
        var reg = new RegExp(word + "([0-9]{1,})(\\.{0,}[0-9]{0,1})");
        if (reg.exec(agent) != null) return parseFloat(RegExp.$1 + RegExp.$2);

        return -1;
    },
    IECheck: function () {
        var agent = navigator.userAgent.toLowerCase();
        var Result = true;

        // IE old version ( IE 10 or Lower )
        if (navigator.appName == "Microsoft Internet Explorer") {
            var reg = new RegExp("msie ([0-9]{1,})(\\.{0,}[0-9]{0,1})");
            if (reg.exec(agent) != null) {
                var Version = parseFloat(RegExp.$1 + RegExp.$2);
                if (Version <= 9) Result = false;
            }
        }

        return Result;
    }, EnterEvent: function (TarEl, CallBack) {
        $(TarEl).keyup(function () {
            if (event.keyCode == 13) {
                if (CallBack != null && CallBack != undefined)
                    CallBack();
            }
        })
    }
    ,GetExtension: function (filePath) {  // 파일 확장자 반환
        return filePath.substr(filePath.lastIndexOf('.') + 1, filePath.length - (filePath.lastIndexOf('.') + 1));
    },
    isCellPhone :function (p) {
		p = p.split('-').join('');
		var regPhone = /^((01[1|6|7|8|9])[1-9]+[0-9]{6,7})|(010[1-9][0-9]{7})|(010[1-9][0-9]{6})$/;
		return regPhone.test(p);
	}
}

