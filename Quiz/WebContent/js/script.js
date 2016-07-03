$(function() {
	$("#navigation li").click(function(event) {
		$("#navigation li a").removeClass("active");
		$(this).find("a").addClass("active");
		event.preventDefault();
		var target = "/Quiz/" + $(this).attr("data-target");
		console.log(target);
		$.get(target, function(data, status) {
			console.log(data);
			$("#content").html(data);
		});
	});
	
	$("#content").on("click", ".user", function(event) {
		var user = this.id;
		var target = "/Quiz/AdminUser?user_id=" + user;
		$.post(target, function(data, status) {
			$("#content").html(data);
		});
	});
});