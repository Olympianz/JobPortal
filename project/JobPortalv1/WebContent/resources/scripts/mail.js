
$(function(){
	var checkMail = function(){
		$.ajax({
			type: "GET",
			dataType: "json",
			url: "/JobPortalv1/mail", 
			success: function(result){
				if(parseInt(result.count) > 0)
					$("#mail").attr("class", "glyphicon glyphicon-flash");
				else 
					$("#mail").attr("class", "glyphicon glyphicon-envelope");
			}
		});
	};
	
	checkMail();
	setInterval(checkMail, 5000);
	
});






