/**
 * 用户注册信息的验证
 */
function check() {
	var username = document.getElementById("username");
	var password = document.getElementById("password");
	var repassword = document.getElementById("repassword");
	if (username.value == "" || password.value == ""|| repassword.value == "") {
		alert("信息不能为空");
		return false;
	} else if (password.value.length < 6 || password.value.length > 20) {
		alert("密码长度不能小于6位或者大于20位");
		return false;
	} else if(password.value != repassword.value){
		alert("两次输入的密码不一致");
		return false;
	}
	return true;
}

//实时监听用户的输入是否正确
function inprove(attr) {
	var flag = document.getElementById(attr);

	if (attr == "password") {
		var regex = /^[0-9a-zA-Z]{6,20}$/;
		if (!(flag.value.match(regex))) {
			document.getElementById("checkPassword").innerText = "密码格式不对";
		} else {
			document.getElementById("checkPassword").innerText = "√";
		}
	} else if (attr == "username") {
		var mobileRegex = /^1[34578]\d{9}$/;
		if (!(flag.value.match(mobileRegex))) {
			document.getElementById("checkUsername").innerText = "手机格式不对";
		} else {
			document.getElementById("checkUsername").innerText = "√";
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





























