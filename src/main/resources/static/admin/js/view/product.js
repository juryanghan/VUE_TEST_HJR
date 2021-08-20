/*<![CDATA[*/

var goodsUploader1,goodsUploader2,goodsUploader3,goodsUploader4,goodsUploader5,contentUpload,moContentUpload;

var ProductJS = {
 init : function() {
	$(".product-item").mouseover(function(){$(this).find("#item-action").show();});
	$(".product-item").mouseleave(function(){$(this).find("#item-action").hide();});
	
	
	$(".ProductImagUpload img").click(function () {
		ImagePopJS.Show(this);
	})
	
	 
	$("#InsertBtn").click(function() {
		  var ValidateEl = $("#frm [data-required]");
            TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {
                var requestData = {}
                var ProductOptionArr = new Array();
                var frm_data = $("#frm [data-required]").serializeArray();
                
                for (var index in frm_data) {
                	if(frm_data[index].name =="productOptionName" || frm_data[index].name =="quantity" ){
                		continue;
                	}
                	if(frm_data[index].name == "OptionVal"){
                		ProductOptionArr.push(frm_data[index].value);
                	}
                	
                	else {
                		requestData[frm_data[index].name] = frm_data[index].value;
                	}
                }
                
                requestData["productOption"]=  ProductOptionArr.join(",");
                TckJS.Ajax.postAjaxLoding("/adm/product/ajax/insert", requestData, function (res) {
                    alert("등록 되었습니다.");
                    location.reload();
                })
            });
		});
	
		$("#UpdateBtn").click(function() {
			  if(!confirm("수정하시겠습니까?")) return false;
			  var ValidateEl = $("#frm #productName");
	            TckJS.ValidateUtil.ValidateCheck(ValidateEl, function () {
	                var requestData = {}
	                var frm_data = $("#frm").serializeArray();
	                
	                for (var index in frm_data) {
	                	if(frm_data[index].name =="productOptionName" || frm_data[index].name =="quantity" || frm_data[index].name == "OptionVal" ){
	                		continue;
	                	}
	                	else {
	                		requestData[frm_data[index].name] = frm_data[index].value;
	                	}
	                }
	                TckJS.Ajax.postAjaxLoding("/adm/product/ajax/update", requestData, function (res) {
		                  alert("수정 되었습니다.");
		                  location.reload();
	                });
	            });
		});
          
          
      	$("#OptionAddBtn").click(function() {
      		var OptionDiv =  $("#frm").find("#OptionDiv").clone();
      		OptionDiv.css("display","block");
      		var del_btn = '<button type="button" class="btn btn-sm btn-danger" onclick="ProductJS.goOptionTrDel(this)" style="margin-left: 5px;">삭제</button>';
      		OptionDiv.append(del_btn);
      		OptionDiv.find("#productOptionName").val("");
      		OptionDiv.find("#quantity").val("");
      		$("#frm").find("#OptionTr").append(OptionDiv);
      		Common.onlyNumber($("[data-number]"));
      	});
      	
      	Common.onlyNumber($("[data-number]"));
	},
	SetDetail :function (type){
		var InsertBtn = $("#InsertBtn");
		var UpdateBtn = $("#UpdateBtn");
		var insertTR = $(".insertTR");
		var updateTR = $(".updateTR");
		 
		if(type == "I"){
			InsertBtn.show();
			insertTR.show();
			UpdateBtn.hide();
			updateTR.hide();
			$("#frm")[0].reset()
			ProductJS.setUploadr();
		}
		else if(type == "U"){
			InsertBtn.hide();
			insertTR.hide();
			UpdateBtn.show();
			updateTR.show();
		}
		
		$('#divProductDetail').modal('show');
	},
	GetData : function (productNo){
		TckJS.Ajax.postAjaxLoding("/adm/product/ajax/getProduct", {productNo:productNo}, function (res) {
			ProductJS.DataSet(res);
        });
	},
	DataSet :function (data){
		ProductJS.setUploadr();
		$("#frm").find("#productNo").val(data.productNo);
		$("#frm").find("#productName").val(data.productName);
		$("#frm").find("#ProductImagUpload1").find("#imgPlaceholder").attr("src",data.productImage1);
		$("#frm").find("#ProductImagUpload2").find("#imgPlaceholder").attr("src",data.productImage2);
		$("#frm").find("#ProductImagUpload3").find("#imgPlaceholder").attr("src",data.productImage3);
		$("#frm").find("#ProductImagUpload4").find("#imgPlaceholder").attr("src",data.productImage4);
		$("#frm").find("#ProductImagUpload5").find("#imgPlaceholder").attr("src",data.productImage5);
		 
		var productOptions = data.productOptionTxt.split(',');
		for(var i =0; i < productOptions.length ; i++ ){
			var productOptionArr = productOptions[i].split('/');
			if(productOptionArr.length == 3){
				var _html = '<div id="OptionDiv" style="display:block">';
				_html += '<input type="text" class="form-control" disabled value="'+productOptionArr[1]+'">/';
				_html += '<input type="text" class="form-control" disabled value="'+productOptionArr[2]+'"><br/>';
				_html += '</div>';
				$("#frm").find("#OptionView").append(_html);
			}
		}
		
		ProductJS.SetDetail("U");
	},
	goDel : function(productNo) {			
		TckJS.Util.Confirm("선택된 상품을 삭제하시겠습니까?", function(){
			TckJS.Ajax.postAjaxLoding("/adm/product/ajax/delete", {productNo:productNo}, function (res) {
				alert("삭제되었습니다.");
				location.reload();
	        });
		}, TckJS.Util.ConfirmCancle());
	},
	goOptionTrDel : function (el) {
		if($(el).parent().attr("id") == "OptionDiv"){
			$(el).parent().remove();
		}
	},setUploadr : function () {
		if(goodsUploader1) goodsUploader1.destroy();
		if(goodsUploader2) goodsUploader2.destroy();
		if(goodsUploader3) goodsUploader3.destroy();
		if(goodsUploader4) goodsUploader4.destroy();
		if(goodsUploader5) goodsUploader5.destroy();
		if(contentUpload) contentUpload.destroy();
		if(moContentUpload) moContentUpload.destroy();
		
		$(".img-polaroid").attr("src","/admin/img/placeholder.png");
		$(".ulUploadList ").text("");
		$("[data-uploader]").val("");
		
		
		goodsUploader1 = UpLoadrJS.images({
			dropElement : "ProductImagUpload1",
			Button : "btnUploader1",
			imgPlaceholderEl : $("#ProductImagUpload1").find("#imgPlaceholder"),
			imgNameEl : $("#ProductImagUpload1").find("#productImage")
		})
		
		goodsUploader2 = UpLoadrJS.images({
			dropElement : "ProductImagUpload2",
			Button : "btnUploader2",
			imgPlaceholderEl : $("#ProductImagUpload2").find("#imgPlaceholder"),
			imgNameEl : $("#ProductImagUpload2").find("#productImage")
		})
		
		goodsUploader3 = UpLoadrJS.images({
			dropElement : "ProductImagUpload3",
			Button : "btnUploader3",
			imgPlaceholderEl : $("#ProductImagUpload3").find("#imgPlaceholder"),
			imgNameEl : $("#ProductImagUpload3").find("#productImage")
		})
		
		goodsUploader4 = UpLoadrJS.images({
			dropElement : "ProductImagUpload4",
			Button : "btnUploader4",
			imgPlaceholderEl : $("#ProductImagUpload4").find("#imgPlaceholder"),
			imgNameEl : $("#ProductImagUpload4").find("#productImage")
		})
		
		goodsUploader5 = UpLoadrJS.images({
			dropElement : "ProductImagUpload5",
			Button : "btnUploader5",
			imgPlaceholderEl : $("#ProductImagUpload5").find("#imgPlaceholder"),
			imgNameEl : $("#ProductImagUpload5").find("#productImage")
		})
        
        contentUpload = UpLoadrJS.html({
			dropElement : "ProductContentTR",
			Button : "btnUploadHtmlDlg",
			TargetEl : $("#ProductContentTR"),
			CotentValEl : $("#productContents")
		})
		
		moContentUpload = UpLoadrJS.html({
			dropElement : "productContentsMoTR",
			Button : "btnUploadHtmlDlg2",
			TargetEl : $("#productContentsMoTR"),
			CotentValEl : $("#productContentsMo")
		})
        
		
		goodsUploader1.init();
		goodsUploader2.init();
		goodsUploader3.init();
		goodsUploader4.init();
		goodsUploader5.init();
        contentUpload.init();
        moContentUpload.init();
        
		
	},
	SetOptionVal : function(el){
		if($(el).parent().attr("id") == "OptionDiv"){
			var OptionDiv = $(el).parent();
			var OptionVal = OptionDiv.find("#productOptionName").val() +"/"+ OptionDiv.find("#quantity").val();
			OptionDiv.find("#OptionVal").val(OptionVal);
		}
	}
}
/*]]>*/