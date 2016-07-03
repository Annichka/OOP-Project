$(function() {
	$("#navigation li").click(function(event) {
		$("#navigation li a").removeClass("active");
		$(this).find("a").addClass("active");
		event.preventDefault();
		var target = "/Quiz/" + $(this).attr("data-target");
		console.log(target);
		$.get(target, function(data, status) {
			$("#content").html(data);
		});
	});
	
	$("#content").on("click", ".user", function(event) {
		var user = this.id;
		var target = "/Quiz/AdminUser?user_id=" + user;
		$.get(target, function(data, status) {
			$("#content").html(data);
		});
	});
	
	$("#content").on("click", ".submit_button", function(event) {
		event.preventDefault();
		var form_data = $(this).parents("form").serialize();
		form_data += "&action=" + $(this).val();
		$.post("/Quiz/AdminUser", form_data, function(data, status) {
				$("#content").html(data);
		});
	});
});