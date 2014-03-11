function validate(data) {
	var ajaxStatus = data.status;
	
	if (ajaxStatus == "success") {
		if ('#{loginController.showAlert}'=='false') {
			alert('Invalide username/password');
		}
	}
/*
		var username = document.getElementById();
		if (username.value == "") {
			alert("Please enter username!");
			return false;
		}
		
		var password = document.getElementById();
		if (password.value == "") {
			alert("Please enter password!");
			return false;
		}
*/		
}