/**
 * JS for Faculty List
 */

$(document)
		.ready(
				function() {
					
					var facultyTable = $('#facultyList').DataTable({
						"bSort" : true,
						"bServerSide" : true,
						"sDom" : '<""l>t<"F"p>',
						"bJQueryUI" : true,
						"bSearchable" : false,
						"bAutoWidth" : false,
						"sPaginationType" : "full_numbers",
						"sAjaxSource" : "ajax_faculty_list"+$("#SPRING_EXT").html(),
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
						
						inactive_all_faculty();
						
					 });
					
					function save_faculty() {
						
						var parameters = {
							
							'facultyId' : $("#facultyId").val(),
							'status' : $("#status").val()
							
						};
							
						$.ajax({
							
							type : 'POST',
							url : "ajax_save_faculty"+$("#SPRING_EXT").html(),
							data : parameters,
							dataType : 'html',
							success : function(data) {
								
								alertNotification(data);
								facultyTable.ajax.reload( function () {
									load_status_toggle();
								});
								
							}
						});

					 }
					
					function inactive_all_faculty() {
						
						$.ajax({
							
							type : 'GET',
							url : "ajax_inactive_all_facultys"+$("#SPRING_EXT").html(),
							dataType : 'html',
							success : function(data) {
								
								alertNotification(data);
								facultyTable.ajax.reload( function () {
									load_status_toggle();
								});
								
							}
						});

					 }
					
					function load_status_toggle() {
						$('.statusActive').bootstrapToggle('on');
						$('.statusSuspend').bootstrapToggle('off');
						$('.statusToggle').change(function() {	
							var rowData = facultyTable.row( $(this).parents('tr') ).data();
							$("#facultyId").val(rowData[0]);
							if($(this).prop('checked')){
								$("#status").val("1");
							}else{
								$("#status").val("0");
							}
							save_faculty();
						});
					}
					
});
