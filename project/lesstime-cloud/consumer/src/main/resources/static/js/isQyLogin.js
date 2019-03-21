$(function() {
	var qyid = localStorage.getItem("sjbh");
	if(!qyid) {
		window.location.href="/login";
	}
})