function register() {
	var name = $("#username").val();
	var pass = $("#password").val();
	var passC = $("#passwordC").val();
	
	if(pass != passC){
		alert("Passwords don't match");
	}else{
	    var newUser = {
	        	name: name,
	        	password: pass,
	        }
	    $.ajax({
	        url: "/createUser",
	        method: 'POST',
	        data: JSON.stringify(newUser),
	        contentType: "application/json; charset=utf-8",
	        dataType: 'json'
	    }).done(function (data) {
	    	window.location.href = "/userPage";
	    }).fail(function () {
	    	alert("Something went wrong. Please, try later...");
	    });
	}
}