/**
 * JS for Event List
 */

$(document)
		.ready(
				function() {
					
					var eventTable = $('#eventList').DataTable({
						"bSort" : true,
						"bServerSide" : true,
						"sDom" : '<""l>t<"F"p>',
						"bJQueryUI" : true,
						"bSearchable" : false,
						"bAutoWidth" : false,
						"sPaginationType" : "full_numbers",
						"sAjaxSource" : "ajax_event_list"+$("#SPRING_EXT").html(),
						"drawCallback": function( settings ) {
								load_status_toggle();
					    },
						"columnDefs": [
									    {
									        "targets": [0],
									        "visible": false,
									        "class":"hide_column"
									    }
						]
					});
					
					
					if($('#errmsg').html().trim().length!=0 && $('#errmsg').html().trim()!="")
					{
						alertNotification($('#errmsg').html());
						
					}
					
					$("#inactiveAll").click(function(){
						
						inactive_all_event();
						
					 });
					
					function save_event() {
						
						var parameters = {
							
							'eventId' : $("#eventId").val(),
							'status' : $("#status").val()
							
						};
							
						$.ajax({
							
							type : 'POST',
							url : "ajax_save_event"+$("#SPRING_EXT").html(),
							data : parameters,
							dataType : 'html',
							success : function(data) {
								
								alertNotification(data);
								
							}
						});

					 }
					
					function inactive_all_event() {
						
						$.ajax({
							
							type : 'GET',
							url : "ajax_inactive_all_events"+$("#SPRING_EXT").html(),
							dataType : 'html',
							success : function(data) {
								
								alertNotification(data);
								eventTable.ajax.reload();
								
								
							}
						});

					 }
					
					function load_status_toggle() {
						$('.statusActive').bootstrapToggle('on');
						$('.statusSuspend').bootstrapToggle('off');
						$('.statusToggle').change(function() {	
							var rowData = eventTable.row( $(this).parents('tr') ).data();
							$("#eventId").val(rowData[0]);
							if($(this).prop('checked')){
								$("#status").val("1");
							}else{
								$("#status").val("0");
							}
							save_event();
						});
					}
					
});
