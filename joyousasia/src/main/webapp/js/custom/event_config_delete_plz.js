/**
 * JS for Event Configuration
 */

$(document).ready(function(){
	
	if($('#errmsg').html().trim().length!=0 && $('#errmsg').html().trim()!="")
	{
		alertNotification($('#errmsg').html());
		
	}
	
	$( "#submitButton" ).click(function(e) {
		e.preventDefault(); //To prevent the browser from refreshing the page
		if($("#basic_validate").valid()){
			$( "#basic_validate" ).submit();
		}
	});
	
});