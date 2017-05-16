/**
 * JS for Organization
 */

$(document)
		.ready(
				function() {
					
					var action = "add";
					var filter_paras = [];

					var oTable = $('.data-table').DataTable({
						"bSort" : true,
						"bServerSide" : true,
						"order": [[ 0, "desc" ]],
						"sDom" : '<""l>t<"F"p>',
						"bJQueryUI" : true,
						"bSearchable" : false,
						"bAutoWidth" : false,
						"sPaginationType" : "full_numbers",
						"sAjaxSource" : "ajax_empty_list"+$("#SPRING_EXT").html(),
						"columnDefs": [
									    {
									        "targets": [0],
									        "visible": false,
									        "class":"hide_column"
									    },
									    {
											"targets": [7],	
											"orderable": false,
										}
						]
					});
					
					
					$("#resetButton").click(function(){
						
						$("#identificationNum").val("")/*.change()*/;
						$("#name").val("")/*.change()*/;
						$("#contactNum").val("")/*.change()*/;
						$("#email").val("")/*.change()*/;
						$("#invoiceNum").val("")/*.change()*/;
						/*$("#event").val("all").change();*/
						 
					 });
					
					$("#purgeButton").click(function(){
						
						$("#purgeConfirm").show();
						purgeConfirmDiv.dialog("open");
						
					 });
					
					
					if($('#errmsg').html().trim().length!=0 && $('#errmsg').html().trim()!="")
					{
						alertNotification($('#errmsg').html());
						
					}

					
					$('.data-table tbody').on('click','.editButton', function() {
						
						var rowData = oTable.row($(this).parents('tr')).data();
						window.location.href='edit'+$("#SPRING_EXT").html()+'?id='+rowData[0];
						
					});
					
					$(document).on('click', '.data-table tbody tr', function() {
						if ($(this).hasClass('checked')) {

							$(this).removeClass('checked');

						} else {
							$(this).addClass('checked');
						}
					});
					
					/*--------------------------------------------Delete Confirm-------------------------------------------------*/
					var dialog_buttons = {}; 
					
					dialog_buttons["Yes"] = function(){
						
						var idSting = listId(oTable);
						console.log(idSting);
						if (idSting != null) {
							var ob = {
								'id' : idSting
							};

							$.ajax({
								type : 'POST',
								url : 'delete'+$("#SPRING_EXT").html(),
								data : ob,
								dataType : 'html',
								success : function(data) {
									alertNotification(data);
									oTable.ajax.reload();
								}
							});
						}
						deleteConfirmDiv.dialog("close");
						
					};
					
					dialog_buttons["Cancel"] = function(){
						
						deleteConfirmDiv.dialog("close");
						
					};
					
					var deleteConfirmDiv = $("#deleteConfirm").dialog({
						autoOpen : false,
						resizable: false,
						height : 300,
						width : $(window).width()*0.35,
						modal : true,
						position: ['center','center-20%'],
						buttons : dialog_buttons,
						close : function() {

						},
						show: {
							effect: "puff",
							duration: 250
						},
						hide: {
							effect: "puff",
							duration: 250
						}
					});
					/*--------------------------------------------Delete Confirm-------------------------------------------------*/
					
					
					/*--------------------------------------------Purge Confirm-------------------------------------------------*/
					var purge_dialog_buttons = {}; 
					
					purge_dialog_buttons["Yes"] = function(){
						
						$.ajax({
							type : 'GET',
							url : 'purge_database'+$("#SPRING_EXT").html(),
							dataType : 'html',
							success : function(data) {
								
								alertNotification(data);
								
							}
						});
						purgeConfirmDiv.dialog("close");
						
					};
					
					purge_dialog_buttons["Cancel"] = function(){
						
						purgeConfirmDiv.dialog("close");
						
					};
					
					var purgeConfirmDiv = $("#purgeConfirm").dialog({
						autoOpen : false,
						resizable: false,
						height : 300,
						width : $(window).width()*0.35,
						modal : true,
						position: ['center','center-20%'],
						buttons : purge_dialog_buttons,
						close : function() {

						},
						show: {
							effect: "puff",
							duration: 250
						},
						hide: {
							effect: "puff",
							duration: 250
						}
					});
					/*--------------------------------------------Purge Confirm-------------------------------------------------*/
					
					
					$('#deleterow').click(function() {
						
						var selectedRows = oTable.$('tr.checked');

						if (selectedRows.length <= 0) {
							
							alertNotification("Please select at least 1 record");
							
						}else{
							$("#deleteConfirm").show();
							deleteConfirmDiv.dialog("open");
						}

					});
					
					$("#identificationNum, #email, #name, #contactNum, #invoiceNum, #event").on("change paste", function() {
						showSearchData();
					});
					
					$("#submitButton").click(function(){
						showSearchData();
					});
					
					function showSearchData(){
						oTable = null;
						oTable = $('.data-table').DataTable({
							"bSort" : true,
							"bDestroy": true,
							"order": [[ 0, "desc" ]],
							"bServerSide" : true,
							"sDom" : '<""l>t<"F"p>',
							"bJQueryUI" : true,
							"bSearchable" : false,
							"bAutoWidth" : false,
							"sPaginationType" : "full_numbers",
							"sAjaxSource" : "ajax_list"+$("#SPRING_EXT").html(),
							"fnServerParams": function (aoData) {
								aoData.push({"name": "identificationNum", "value": $("#identificationNum").val()});
								aoData.push({"name": "name", "value": $("#name").val()});
								aoData.push({"name": "contactNum", "value": $("#contactNum").val()});
								aoData.push({"name": "email", "value": $("#email").val()});
								aoData.push({"name": "invoiceNum", "value": $("#invoiceNum").val()});
								aoData.push({"name": "event", "value": $("#event").val()=="all" ? "" : $("#event").val()});
								filter_paras = [];
								filter_paras.push({"name": "identificationNum", "value": $("#identificationNum").val()});
								filter_paras.push({"name": "name", "value": $("#name").val()});
								filter_paras.push({"name": "contactNum", "value": $("#contactNum").val()});
								filter_paras.push({"name": "email", "value": $("#email").val()});
								filter_paras.push({"name": "invoiceNum", "value": $("#invoiceNum").val()});
								filter_paras.push({"name": "event", "value": $("#event").val()});
							},
							"columnDefs": [
										    {
										        "targets": [0],
										        "visible": false,
										        "class":"hide_column"
										    },
										    {
												"targets": [7],	
												"orderable": false,
											}
							]
						});
						$('select[name=dataTable_length]').select2();
					}

					function listId(ObjectTable) {
						
						var idString = "";
						
						var customerIdArray = [];
						var selectedRows = ObjectTable.$('tr.checked');

						for (var i = 0; i < selectedRows.length; i++) {

							customerIdArray[i] = ObjectTable.row(selectedRows[i]).data()[0];
							
						}
						
						for (var j = 0; j < customerIdArray.length; j++) {
								
							if(j==0){
								idString = customerIdArray[j];
							}else{
								idString += ","+customerIdArray[j];
							}
							
						}
						
						console.log("idString: "+idString);
						return idString;
						
					}
					
					$("#download-btn").click(function(){
						
						var params = null;
						var path = "download"+$("#SPRING_EXT").html();
						if(filter_paras.length == 0){
							
							params = {
									'identificationNum' : "",
									'name' : "",
									'contactNum' : "",
									'email' : "",
									'invoiceNum' : "",
									'email' : "",
								};
							
						}else{
							
							params = {
									'identificationNum' : filter_paras[0].value,
									'name' : filter_paras[1].value,
									'contactNum' : filter_paras[2].value,
									'email' : filter_paras[3].value,
									'invoiceNum' : filter_paras[4].value,
									'event' : filter_paras[5].value,
								};
						}
						post(path, params);
						
					});
					
					function post(path, params) {
					    // The rest of this code assumes you are not using a library.
					    // It can be made less wordy if you use one.
					    var form = document.createElement("form");
					    form.setAttribute("method", "POST");
					    form.setAttribute("action", path);

					    for(var key in params) {
					        if(params.hasOwnProperty(key)) {
					            var hiddenField = document.createElement("input");
					            hiddenField.setAttribute("type", "hidden");
					            hiddenField.setAttribute("name", key);
					            hiddenField.setAttribute("value", params[key]);

					            form.appendChild(hiddenField);
					         }
					    }

					    document.body.appendChild(form);
					    form.submit();
					}
					
				});
