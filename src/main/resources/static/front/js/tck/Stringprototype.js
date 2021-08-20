

//-----------------------------------------------------------------------------
// 문자의 좌 공백 제거	y
// @return : String
//-----------------------------------------------------------------------------
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/, "");
}

//-----------------------------------------------------------------------------
// 문자의 우 공백 제거	y
// @return : String
//-----------------------------------------------------------------------------
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/, "");    
}

//-----------------------------------------------------------------------------
// 문자열의 byte 길이 반환 y
// @return : int
//-----------------------------------------------------------------------------

String.prototype.byte = function() {
	var cnt = 0;
	for (var i = 0; i < this.length; i++) {
		if (this.charCodeAt(i) > 127)
			cnt += 2;
		else
			cnt++;
	}
	return cnt;
}

//-----------------------------------------------------------------------------
// 정수형으로 변환	y
// @return : String
//-----------------------------------------------------------------------------

String.prototype.int = function() {
	if(!isNaN(this)) {
		return parseInt(this);
	}
	else {
		return null;
	}
}

//-----------------------------------------------------------------------------
// 숫자만 가져 오기	y
// @return : String
//-----------------------------------------------------------------------------

String.prototype.num = function() {
	return (this.trim().replace(/[^0-9]/g, ""));
}

//-----------------------------------------------------------------------------
// 숫자에 3자리마다 , 를 찍어서 반환	y
// @return : String
//-----------------------------------------------------------------------------

String.prototype.money = function() {
	var num = this.trim();
	while((/(-?[0-9]+)([0-9]{3})/).test(num)) {
		num = num.replace((/(-?[0-9]+)([0-9]{3})/), "$1,$2");
	}
	return num;
}

//-----------------------------------------------------------------------------
// 숫자의 자리수(cnt)에 맞도록 반환	y
// @return : String
//-----------------------------------------------------------------------------

String.prototype.digits = function(cnt) {
	var digit = "";
	if (this.length < cnt) {
		for(var i = 0; i < cnt - this.length; i++) {
			digit += "0";
		}
	}
	return digit + this;
}

//-----------------------------------------------------------------------------
// " -> &#34; ' -> &#39;로 바꾸어서 반환		y
// @return : String
//-----------------------------------------------------------------------------

String.prototype.quota = function() {
	return this.replace(/"/g, "&#34;").replace(/'/g, "&#39;");
}

//-----------------------------------------------------------------------------
// 파일 확장자만 가져오기								y
// @return : String
//-----------------------------------------------------------------------------

String.prototype.ext = function() {
	return (this.indexOf(".") < 0) ? "" : this.substring(this.lastIndexOf(".") + 1, this.length);    
}

//-----------------------------------------------------------------------------
// URL에서 파라메터 제거한 순수한 url 얻기		y
// @return : String
//-----------------------------------------------------------------------------    

String.prototype.uri = function() {
	var arr = this.split("?");
	arr = arr[0].split("#");
	return arr[0];    
}
//-----------------------------------------------------------------------------
// Replaceall 		y
// @return : String
//-----------------------------------------------------------------------------    

String.prototype.Replaceall = function() {
	var str = arguments[0];
	var str2 = arguments[1] ? arguments[1] : null;

	var arr = this.split(str).join(str2);

	return arr;
}

/*---------------------------------------------------------------------------------*\
*  각종 체크 함수들
\*---------------------------------------------------------------------------------*/

//-----------------------------------------------------------------------------
// 정규식에 쓰이는 특수문자를 찾아서 이스케이프 한다.	 y
// @return : String
//-----------------------------------------------------------------------------

String.prototype.meta = function() {
	var str = this;
	var result = "";
	for(var i = 0; i < str.length; i++) {
		if((/([\$\(\)\*\+\.\[\]\?\\\^\{\}\|]{1})/).test(str.charAt(i))) {
			result += str.charAt(i).replace((/([\$\(\)\*\+\.\[\]\?\\\^\{\}\|]{1})/), "\\$1");
		}
		else {
			result += str.charAt(i);
		}
	}
	return result;
}

//-----------------------------------------------------------------------------
// 정규식에 쓰이는 특수문자를 찾아서 이스케이프 한다.	 y
// @return : String
//-----------------------------------------------------------------------------

String.prototype.remove = function(pattern) {
	return (pattern == null) ? this : eval("this.replace(/[" + pattern.meta() + "]/g, \"\")");
}

//-----------------------------------------------------------------------------
// 최소 최대 길이인지 검증
// str.isLength(min [,max])
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isLength = function() {
	var min = arguments[0];
	var max = arguments[1] ? arguments[1] : null;
	var success = true;
	if(this.length < min) {
		success = false;
	}
	if(max && this.length > max) {
		success = false;
	}
	return success;
}

//-----------------------------------------------------------------------------
// 최소 최대 바이트인지 검증
// str.isByteLength(min [,max])
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isByteLength = function() {
	var min = arguments[0];
	var max = arguments[1] ? arguments[1] : null;
	var success = true;
	if(this.byte() < min) {
		success = false;
	}
	if(max && this.byte() > max) {
		success = false;
	}
	return success;
}

//-----------------------------------------------------------------------------
// 공백이나 널인지 확인
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isBlank = function() {
	var str = this.trim();
	for(var i = 0; i < str.length; i++) {
		if ((str.charAt(i) != "\t") && (str.charAt(i) != "\n") && (str.charAt(i)!="\r")) {
			return false;
		}
	}
	return true;
}


String.prototype.RemoveTag = function() {
	return this.replace(/<[^>]+>/g, "");  
}
String.prototype.isTag = function() {
	return (/<[^>]+>/g).test(this.remove(arguments[0])) ? true : false;
}
//-----------------------------------------------------------------------------
// 숫자로 구성되어 있는지 학인
// arguments[0] : 허용할 문자셋
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isNum = function() {
	return (/^[0-9]+$/).test(this.remove(arguments[0])) ? true : false;
}

//-----------------------------------------------------------------------------
// 영어만 허용 - arguments[0] : 추가 허용할 문자들
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isEng = function() {
	return (/^[a-zA-Z]+$/).test(this.remove(arguments[0])) ? true : false;
}

//-----------------------------------------------------------------------------
// 숫자와 영어만 허용 - arguments[0] : 추가 허용할 문자들
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isEngNum = function() {
	return (/^[0-9a-zA-Z]+$/).test(this.remove(arguments[0])) ? true : false;
}

//-----------------------------------------------------------------------------
// 숫자와 영어만 허용 - arguments[0] : 추가 허용할 문자들
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isNumEng = function() {
	return this.isEngNum(arguments[0]);
}

//-----------------------------------------------------------------------------
// 아이디 체크 영어와 숫자만 체크 첫글자는 영어로 시작 - arguments[0] : 추가 허용할 문자들
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isUserid = function() {
	return (/^[a-zA-z]{1}[0-9a-zA-Z]+$/).test(this.remove(arguments[0])) ? true : false;
}

//-----------------------------------------------------------------------------
// 한글 체크 - arguments[0] : 추가 허용할 문자들
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isKor = function() {
	return (/^[가-힣]+$/).test(this.remove(arguments[0])) ? true : false;
}

//-----------------------------------------------------------------------------
// 주민번호 체크 - arguments[0] : 주민번호 구분자
// XXXXXX-XXXXXXX
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isJumin = function() {
	var arg = arguments[0] ? arguments[0] : "";
	var jumin = eval("this.match(/[0-9]{2}[01]{1}[0-9]{1}[0123]{1}[0-9]{1}" + arg + "[1234]{1}[0-9]{6}$/)");
	if(jumin == null) {
		return false;
	}
	else {
		jumin = jumin.toString().num().toString();
	}

	// 생년월일 체크
	var birthYY = (parseInt(jumin.charAt(6)) == (1 ||2)) ? "19" : "20";
	birthYY += jumin.substr(0, 2);
	var birthMM = jumin.substr(2, 2) - 1;
	var birthDD = jumin.substr(4, 2);
	var birthDay = new Date(birthYY, birthMM, birthDD);
	if(birthDay.getYear() % 100 != jumin.substr(0,2) || birthDay.getMonth() != birthMM || birthDay.getDate() != birthDD) {
		return false;
	}        
	var sum = 0;
	var num = [2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5]
	var last = parseInt(jumin.charAt(12));
	for(var i = 0; i < 12; i++) {
		sum += parseInt(jumin.charAt(i)) * num[i];
	}
	return ((11 - sum % 11) % 10 == last) ? true : false;

}

//-----------------------------------------------------------------------------
// 외국인 등록번호 체크 - arguments[0] : 등록번호 구분자
// XXXXXX-XXXXXXX
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isForeign = function() {
	var arg = arguments[0] ? arguments[0] : "";
	var jumin = eval("this.match(/[0-9]{2}[01]{1}[0-9]{1}[0123]{1}[0-9]{1}" + arg + "[5678]{1}[0-9]{1}[02468]{1}[0-9]{2}[6789]{1}[0-9]{1}$/)");
	if(jumin == null) {
		return false;
	}
	else {
		jumin = jumin.toString().num().toString();
	}
	// 생년월일 체크
	var birthYY = (parseInt(jumin.charAt(6)) == (5 || 6)) ? "19" : "20";
	birthYY += jumin.substr(0, 2);
	var birthMM = jumin.substr(2, 2) - 1;
	var birthDD = jumin.substr(4, 2);
	var birthDay = new Date(birthYY, birthMM, birthDD);
	if(birthDay.getYear() % 100 != jumin.substr(0,2) || birthDay.getMonth() != birthMM || birthDay.getDate() != birthDD) {
		return false;
	}
	if((parseInt(jumin.charAt(7)) * 10 + parseInt(jumin.charAt(8))) % 2 != 0) {
		return false;
	}
	var sum = 0;
	var num = [2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5]
	var last = parseInt(jumin.charAt(12));
	for(var i = 0; i < 12; i++) {
		sum += parseInt(jumin.charAt(i)) * num[i];
	}
	return (((11 - sum % 11) % 10) + 2 == last) ? true : false;
}    

//-----------------------------------------------------------------------------
// 사업자번호 체크 - arguments[0] : 등록번호 구분자
// XX-XXX-XXXXX
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isBiznum = function() {
	var arg = arguments[0] ? arguments[0] : "";
	var biznum = eval("this.match(/[0-9]{3}" + arg + "[0-9]{2}" + arg + "[0-9]{5}$/)");
	if(biznum == null) {
		return false;
	}
	else {
		biznum = biznum.toString().num().toString();
	}
	var sum = parseInt(biznum.charAt(0));
	var num = [0, 3, 7, 1, 3, 7, 1, 3];
	for(var i = 1; i < 8; i++) sum += (parseInt(biznum.charAt(i)) * num[i]) % 10;
	sum += Math.floor(parseInt(parseInt(biznum.charAt(8))) * 5 / 10);
	sum += (parseInt(biznum.charAt(8)) * 5) % 10 + parseInt(biznum.charAt(9));
	return (sum % 10 == 0) ? true : false;
}

//-----------------------------------------------------------------------------
// 법인 등록번호 체크 - arguments[0] : 등록번호 구분자
// XXXXXX-XXXXXXX
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isCorpnum = function() {
	var arg = arguments[0] ? arguments[0] : "";
	var corpnum = eval("this.match(/[0-9]{6}" + arg + "[0-9]{7}$/)");
	if(corpnum == null) {
		return false;
	}
	else {
		corpnum = corpnum.toString().num().toString();
	}
	var sum = 0;
	var num = [1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2];
	var last = parseInt(corpnum.charAt(12));
	for(var i = 0; i < 12; i++) {
		sum += parseInt(corpnum.charAt(i)) * num[i];
	}
	return ((10 - sum % 10) % 10 == last) ? true : false;
}

//-----------------------------------------------------------------------------
// 이메일의 유효성을 체크
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isEmail = function() {
	return (/\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/).test(this.trim());
}

//-----------------------------------------------------------------------------
// 전화번호 체크 - arguments[0] : 전화번호 구분자
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isPhone = function() {
	var arg = arguments[0] ? arguments[0] : "";
	return eval("(/(02|0[3-9]{1}[0-9]{1})" + arg + "[1-9]{1}[0-9]{2,3}" + arg + "[0-9]{4}$/).test(this)");
}

//-----------------------------------------------------------------------------
// 핸드폰번호 체크 - arguments[0] : 핸드폰 구분자
// @return : boolean
//-----------------------------------------------------------------------------

String.prototype.isMobile = function() {
	var arg = arguments[0] ? arguments[0] : "";
	return eval("(/01[016789]" + arg + "[1-9]{1}[0-9]{2,3}" + arg + "[0-9]{4}$/).test(this)");
}

//-----------------------------------------------------------------------------
// 특정길이 이상의 문자열은 삭제 (제한 글자수)
// @return : string
//-----------------------------------------------------------------------------
String.prototype.cut = function(limitLength) 
{
    var str = this;
    var l = 0;
    for (var i=0; i<str.length; i++) 
    {
        l += (str.charCodeAt(i) > 128) ? 2 : 1;
        if (l > limitLength) return str.substring(0,i);
    }
    return str;
}
//-->
