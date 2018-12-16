/**
 * 上架食品信息判断
 */

function check() {
	var check = true;
	var cuisineName = document.getElementById("cuisineName").value;
	var price = document.getElementById("price").value;
	var regex1 = /^[1-9][0-9]*$/;
	var regex2 = /^[1-9]d*.d*|0.d*[1-9]d*$/;
	if (cuisineName == "" || price == "") {
		alert("必填信息不能为空！");
		check = false;
	} else if (!(regex1.test(marketPrice)) && !(regex2.test(marketPrice))) {
		alert("价格必须为正浮点数或正整数");
		check = false;
	}
	return check;
}
