/**
 * JS for CSV Upload
 */

$(document).ready(function(){
	
	if($('#errmsg').val().trim().length!=0 && $('#errmsg').val().trim()!="")
	{
		$('#content_feedback').show();
		$('#feedback').html($('#errmsg').val().trim());
		
	}
	
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
	}, "Please upload a file ends with '.csv' extension");
	
	// Form Validation
    $("#basic_validate").validate({
		rules:{
			upload:{
				required: true,
				checkExtension:'csv',
	            maxFileSize:'2048000',
			}
		},	
		messages: {
			upload:{
				required:"Please upload a file",
			}
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