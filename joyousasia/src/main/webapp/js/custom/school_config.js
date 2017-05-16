/**
 * JS for School List
 */

$(document)
		.ready(
				function() {
					
					var schoolTable = $('#schoolList').DataTable({
						"bSort" : true,
						"bServerSide" : true,
						"sDom" : '<""l>t<"F"p>',
						"bJQueryUI" : true,
						"bSearchable" : false,
						"bAutoWidth" : false,
						"sPaginationType" : "full_numbers",
						"sAjaxSource" : "ajax_school_list"+$("#SPRING_EXT").html(),
						"columnDefs": [
									    {
									        "targets": [0],
									        "visible": false,
									        "class":"hide_column"
									    }
						],
						"initComplete": function( settings, json ) {
							load_status_toggle();
						}
					});
					
					if($('#errmsg').html().trim().length!=0 && $('#errmsg').html().trim()!="")
					{
						alertNotification($('#errmsg').html());
						
					}
					
					$("#inactiveAll").click(function(){
						
						inactive_all_school();
						
					 });
					
					
					function save_school() {
						
						var parameters = {
							
							'schoolId' : $("#schoolId").val(),
							'status' : $("#status").val()
							
						};
							
						$.ajax({
							
							type : 'POST',
							url : "ajax_save_school"+$("#SPRING_EXT").html(),
							data : parameters,
							dataType : 'html',
							success : function(data) {
								
								alertNotification(data);
								schoolTable.ajax.reload( function () {
									load_status_toggle();
								} );
								
							}
						});

					 }
					
					function inactive_all_school() {
						
						$.ajax({
							
							type : 'GET',
							url : "ajax_inactive_all_schools"+$("#SPRING_EXT").html(),
							dataType : 'html',
							success : function(data) {
								
								alertNotification(data);
								schoolTable.ajax.reload( function () {
									load_status_toggle();
								} );
								
							}
						});

					 }
					
					function load_status_toggle() {
						$('.statusActive').bootstrapToggle('on');
						$('.statusSuspend').bootstrapToggle('off');
						$('.statusToggle').change(function() {	
							var rowData = schoolTable.row( $(this).parents('tr') ).data();
							$("#schoolId").val(rowData[0]);
							if($(this).prop('checked')){
								$("#status").val("1");
							}else{
								$("#status").val("0");
							}
							save_school();
						});
					}
					
});
