/**
 * 用户登录信息校对
 */
function check() {
	var check = 0;
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	var roles = document.getElementsByName("role");
	var mobileRegex = /^1[34578]\d{9}$/;
	if (username.value == "" || password.value  == "") {
		alert("信息不能为空");
		return false;
	} else if (password.value .length < 6 || password.value.length > 16) {
		alert("密码长度不能小于6位或者大于16位");
		return false;
	} else if (!(mobileRegex.test(username.value))) {
		alert("手机格式不对");
		return false;
	}  
	for( var i = 0; i < roles.length; i++) {
		 if (roles[i].checked == true) {
			  check =  1;
		 }		
	}
	if(check == 0){
		alert("请选着角色");
		return false;
	}
	return true;
}

function inprove(attr) {
	var flag = document.getElementById(attr);

	if (attr == "username") {
		var mobileRegex = /^1[34578]\d{9}$/;
		if (!(flag.value.match(mobileRegex))) {
			document.getElementById("checkUsername").innerText = "手机格式不对";
			document.getElementById("checkUsername").style.color = "red";
		} else {
			document.getElementById("checkUsername").innerText = "√";
			document.getElementById("checkUsername").style.color = "red";
		}
	} else {
		var regex = /^[0-9a-zA-Z]{6,16}$/g;
		if (!(flag.value.match(regex))) {
			document.getElementById("checkPassword").innerText = "密码格式不对";
			document.getElementById("checkPassword").style.color = "red";
		} else {
			document.getElementById("checkPassword").innerText = "√";
			document.getElementById("checkPassword").style.color = "red";
		}
	}
}
window.onload = function() {

	var username = document.getElementById("username");
	var password = document.getElementById("password");
	username.onkeyup = function() {
		inprove("username");
	}
	password.onkeyup = function() {
		inprove("password");
	}
}