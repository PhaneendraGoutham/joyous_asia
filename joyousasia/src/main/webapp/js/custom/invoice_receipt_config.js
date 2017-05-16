/**
 * JS for Invoice Receipt Configuration
 */

$(document).ready(function(){
	
	if($('#errmsg').html().trim().length!=0 && $('#errmsg').html().trim()!="")
	{
		alertNotification($('#errmsg').html());
		
	}
	if($('#logo').val().length > 0){
		var pathArr = $('#logo').val().split("\\\\");
		var path = "../img/joyous/"+pathArr[pathArr.length-1];
		$('#imageFrame').attr('src', path);
		$('#imageFrame').css("width", "310px");
        $('#imageFrame').css("height", "190px");
	}
	
	$( "#submitButton" ).click(function(e) {
		e.preventDefault(); //To prevent the browser from refreshing the page
		if($("#basic_validate").valid()){
			$( "#basic_validate" ).submit();
		}
	});
	
	$.validator.addMethod("maxFileSize", function(value, element, param) {
		var max = param;
		var size = element.files[0].size;
		return parseInt(size) < parseInt(max);
		
	}, "Please upload a file less than 2MB");
	
	$.validator.addMethod("checkExtension", function(value, element, param) {
		
		var file = $('input[type="file"]').val();
        var exts = param.split('|');
        // first check if file field has any value
        if ( file ) {
        	// split file name at dot
        	var get_ext = file.split('.');
        	// reverse name to check extension
        	get_ext = get_ext.reverse();
        	// check file type is valid as given in 'exts' array
	        if ( $.inArray ( get_ext[0].toLowerCase(), exts ) > -1 ){
	        	return true;
	        } else {
	        	return false;
	        }
        }else{
        	return false;
        }
	}, "Please upload a file ends with '.jpg', '.png' or '.gif' extension");
	
	// Form Validation
    $("#basic_validate").validate({
		rules:{
			adderss:{
				required: true
			},
			tel:{
				required: true
			},
			website:{
				required: true
			},
			email:{
				required:true,
				email:true
			},
			termsAndConditions:{
				required:true
			},
			/*upload:{
				checkExtension:'jpg|png|gif',
	            maxFileSize:'2048000',
			}*/
		},	
		errorClass: "help-inline",
		errorElement: "span",
		errorPlacement: function (error, element) {
			
			if (element.attr("name") == "upload" ){
				
				$("#uploadFileError").html( error );
				
			}else{
				error.insertAfter(element);
			}
				
		},
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.form-group').removeClass('has-success').addClass('has-error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.form-group').removeClass('has-error').addClass('has-success');
		}
	});
	
});