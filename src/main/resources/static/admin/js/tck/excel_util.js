/*<![CDATA[*/ 
var ExcelUtil = {
	Down : function (targetForm,action , fileName , sheetName) {
		if($("#exceldown").length != 0) $("#exceldown").remove();
			var exceldown_form =  document.createElement("form");
			exceldown_form.id = "exceldown";
			exceldown_form.name = "exceldown";
			exceldown_form.action = action;
			$(exceldown_form).css("display","none");
			
			
			var params = targetForm.serializeArray();
			for(var i = 0 ; i < params.length; i++){
				var param_input = document.createElement("input");
				param_input.id = params[i].name;
				param_input.name = params[i].name;
				param_input.value = params[i].value;
				exceldown_form.appendChild(param_input);
			}
			
			
			var fileName_input = document.createElement("input");
			fileName_input.id = "fileName";
			fileName_input.name = "fileName";
			fileName_input.value = encodeURI(fileName);
			exceldown_form.appendChild(fileName_input);
			
			var sheetName_input = document.createElement("input");
			sheetName_input.id = "sheetName";
			sheetName_input.name = "sheetName";
			sheetName_input.value = sheetName;
			exceldown_form.appendChild(sheetName_input);

			$("body").append(exceldown_form);
			$("#exceldown").submit();
		}
	}
/* ]]> */