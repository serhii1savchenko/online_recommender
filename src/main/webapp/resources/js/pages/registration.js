function register() {
	var name = $("#username").val();
	var pass = $("#password").val();
	var passC = $("#passwordC").val();
	
	var user = {
			name: name,
        	password: pass
        };
/*	if (window.jQuery) {
	    alert('You are running jQuery version: ' + jQuery.fn.jquery);
	} else {
	    alert('jQuery is not installed');
	}
*/	
	if(pass != passC){
		alert("Passwords don't match");
	}else{
	    $.ajax({
	        cache: false,
	        timeout: 10000,
	    	url: context+"/createUser/",
	        method: 'POST',
	        data: JSON.stringify(user),
	        contentType: "application/json; charset=utf-8",
	        dataType: "json"
	    }).done(function (data) {
	    	//alert('success');
	    	document.getElementsByClassName("okBox")[0].style.display = "block";
	    	document.getElementsByClassName("okBox")[0].style.backgroundColor = "green";
	    	document.getElementsByClassName("okBox")[0].style.width = "30%";
	    	setTimeout(redirect, 1000);
	    	function redirect() { document.location.href = context+'/login'; }
	    }).fail(function (error) {
	    	//alert(error+"ERROR");
	    	document.getElementsByClassName("alertBox")[0].style.display = "block";
	    	document.getElementsByClassName("alertBox")[0].style.backgroundColor = "red";
	    	document.getElementsByClassName("alertBox")[0].style.width = "30%";
	    });
	}
}