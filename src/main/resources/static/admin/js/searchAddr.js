var TckJS_Addr = {
    juso_data: undefined,
    checkKeyword: undefined,
    SetSearchEl: function (el, targetEl, SetAddr) {
        $(el).change(function () {
            if ($(targetEl).find("div").length == 0) { 
                TckJS_Addr.SerachJuso(this, targetEl, SetAddr);
            }
        })
        $(el).keyup(function () {
            if (event.keyCode == 13) { 
                $(targetEl).find("div").remove();
                TckJS_Addr.SerachJuso(this, targetEl, SetAddr);
            }
        })

    }, Serach: function (el, targetEl, SetAddr) {
        var keyword = el.value.trim();
        if (keyword != "" && keyword.length > 1) {
            var requestData_Addr = {
                currentPage: 1,
                countPerPage: 50,
                keyword: keyword
            };

            if (!TckJS.ValidateUtil.CheckSearchedWord(el)) return;

            TckJS_Addr.checkKeyword = requestData_Addr.keyword;
            TckJS.Ajax.postAddrLoding("/common/addrSearch", requestData_Addr, function (res) {
                try {
                    if (res.results.common.errorCode != "0") {
                        alert(res.results.common.errorMessage);
                        return;
                    }
                    TckJS_Addr.juso_data = res.results.juso;

                    if (!TckJS_Addr.juso_data || TckJS_Addr.juso_data.length == 0) {
                        alert("해당 검색어의 결과가 존재하지 않습니다.");
                        return;
                    }

                    $(targetEl).find("div").remove();
                    var divs = new Array();

                    for (var i = 0; i < TckJS_Addr.juso_data.length; i++) {
                        var div = document.createElement("div");
                        div.setAttribute("data-id", i);


                        var Addr_text = "";
                        if (TckJS_Addr.juso_data[i].roadAddr.indexOf(TckJS_Addr.checkKeyword) != -1) {
                            Addr_text = TckJS_Addr.juso_data[i].roadAddr;
                        }
                        else if (TckJS_Addr.juso_data[i].jibunAddr.indexOf(TckJS_Addr.checkKeyword) != -1) {
                            Addr_text = TckJS_Addr.juso_data[i].jibunAddr;
                        }
                        else {
                            Addr_text = TckJS_Addr.juso_data[i].roadAddr;
                        }

                        div.innerHTML = TckJS_Addr.juso_data[i].zipNo + "    " + Addr_text;
                        div.className = "result-item";
                        div.onclick = function () { SetAddr(this); $(targetEl).find("div").remove(); }
                        divs.push(div);
                    }

                    $(targetEl).append(divs);
                    $(targetEl).show();

                } catch (e) {
                    alert("오류가 발생했습니다.");
                }
            });
        }
    }, SerachJuso: function (el, targetEl, SetAddr) {
        var keyword = el.value.trim();
        if (keyword != "" && keyword.length > 1) {
            var requestData_Addr = {
                currentPage: 1,
                countPerPage: 50,
                resultType : "json",
                confmKey : "U01TX0FVVEgyMDE5MTAzMDA5MzczNjEwOTE1MDY=",
                keyword: keyword
            };

            if (!TckJS.ValidateUtil.CheckSearchedWord(el)) return;

            TckJS_Addr.checkKeyword = requestData_Addr.keyword;
            TckJS.Ajax.postAddrLoding("https://www.juso.go.kr/addrlink/addrLinkApiJsonp.do", requestData_Addr, function (res) {
                try {
                    if (res.results.common.errorCode != "0") {
                        alert(res.results.common.errorMessage);
                        return;
                    }
                    TckJS_Addr.juso_data = res.results.juso;

                    if (!TckJS_Addr.juso_data || TckJS_Addr.juso_data.length == 0) {
                        alert("해당 검색어의 결과가 존재하지 않습니다.");
                        return;
                    }

                    $(targetEl).find("div").remove();
                    var divs = new Array();

                    for (var i = 0; i < TckJS_Addr.juso_data.length; i++) {
                        var div = document.createElement("div");
                        div.setAttribute("data-id", i);


                        var Addr_text = "";
                        if (TckJS_Addr.juso_data[i].roadAddr.indexOf(TckJS_Addr.checkKeyword) != -1) {
                            Addr_text = TckJS_Addr.juso_data[i].roadAddr;
                        }
                        else if (TckJS_Addr.juso_data[i].jibunAddr.indexOf(TckJS_Addr.checkKeyword) != -1) {
                            Addr_text = TckJS_Addr.juso_data[i].jibunAddr;
                        }
                        else {
                            Addr_text = TckJS_Addr.juso_data[i].roadAddr;
                        }

                        div.innerHTML = TckJS_Addr.juso_data[i].zipNo + "    " + Addr_text;
                        div.className = "result-item";
                        div.onclick = function () { SetAddr(this); $(targetEl).find("div").remove(); }
                        divs.push(div);
                    }

                    $(targetEl).append(divs);
                    $(targetEl).show();

                } catch (e) {
                    alert("오류가 발생했습니다.");
                }
            });
        }
    }
}


