/**
 * 开店信息验证
 */
function check(){
	var storeName = document.getElementById("storeName");
	var phone = document.getElementById("phone");
	var address = document.getElementById("address");
	var shopkeeperName = document.getElementById("shopkeeperName");
	var storeDescription = document.getElementById("storeDescription");
	var mobileRegex = /^1[34578]\d{9}$/;
	
	if(storeName.value == "" || phone.value == "" || address.value == "" || shopkeeperName.value == ""){
		alert("必填信息为空");
		return false;
	} else if(!(mobileRegex.test(phone.value))){   //test是RegExp的方法，参数是字符串，返回值是boolean类型。
		alert("手机号码格式错误");
		return false;
	} 
	return true;
}

//实时监听用户的输入是否正确

function inprove(attr){
	var flag = document.getElementById(attr);
	if(attr == "phone"){
		var regex = /^1[34578]\d{9}$/;
		if(!(flag.value.match(regex))) {    //match是String的方法，参数是正则表达式，返回值是数组。
			document.getElementById("checkPhone").innerText = "手机格式不对";
			document.getElementById("checkPhone").style.color = "red";
		} else {
			document.getElementById("checkPhone").innerText = "√";
			document.getElementById("checkPhone").style.color = "red";
		}
	}
}

window.onload = function (){
	var phone = document.getElementById("phone");
	phone.onkeyup = function(){
		inprove("phone");
	}
}