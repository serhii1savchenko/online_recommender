function rate(){
	
	var sel = document.getElementById("rating");
	var rating = sel.options[sel.selectedIndex].text;
	
	if (rating == sel.defaultValue){
		alert("lol");
	}
	
}