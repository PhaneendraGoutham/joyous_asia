/**
 * JS for Item List
 */

$(document)
		.ready(
				function() {
					
					var itemTable = $('#itemList').DataTable({
						"bSort" : true,
						"bServerSide" : true,
						"sDom" : '<""l>t<"F"p>',
						"bJQueryUI" : true,
						"bSearchable" : false,
						"bAutoWidth" : false,
						"sPaginationType" : "full_numbers",
						"sAjaxSource" : "ajax_list"+$("#SPRING_EXT").html(),
						"columnDefs": [
									    {
									        "targets": [0],
									        "visible": false,
									        "class":"hide_column"
									    },
									    {
									        "targets": [4],
									        "visible": false,
									        "class":"hide_column"
									    },
									    {
											"targets": [5],
											"orderable": false,
										}
						]
					});
					
					if($('#errmsg').html().trim().length!=0 && $('#errmsg').html().trim()!="")
					{
						alertNotification($('#errmsg').html());
						
					}
					
					/* ------------------------------------------------Add Item------------------------------------------------------*/
					$("#add-btn").click(function(){
						
						/*Clean the error message*/
						validator.resetForm();
						$("#itemId").val("");
						$("#itemName").val("");
						$("#itemPrice").val("");
						$("#status").val("1").change();
						dialogDiv.dialog("open");
						 
					 });
					
					/* ------------------------------------------------Add Item------------------------------------------------------*/
					
					/* ------------------------------------------------Edit Item------------------------------------------------------*/
					
					$('.data-table tbody').on('click','.editButton', function() {
						
						/*Clean the error message*/
						validator.resetForm();
						
						var rowData = itemTable.row( $(this).parents('tr') ).data();
						$("#itemId").val(rowData[0]);
						$("#itemName").val(rowData[1]);
						$("#itemPrice").val(rowData[2]);
						$("#status").val(rowData[4]).change();
						
						dialogDiv.dialog("open");
						
					});
					
					/* ------------------------------------------------Edit Item------------------------------------------------------*/
					
					/* ------------------------------------------------ Add/Edit Item Popup-----------------------------------------------------*/
					var dialog_buttons = {}; 
					
					dialog_buttons["Save"] = function(){
						
						if(validator.valid()){
							save_item();
						}
						
					};
					
					dialog_buttons["Cancel"] = function(){
						dialogDiv.dialog("close");
					};
					
					var dialogDiv = $("#dialog-form").dialog({
						autoOpen : false,
						resizable: false,
						height : 300,
						width : 600,
						position: ['center','center-20%'],
						modal : true,
						buttons : dialog_buttons,
						close : function() {

						},
						show: {
							effect: "puff",
							duration: 300
						},
						hide: {
							effect: "puff",
							duration: 300
						}
					});
					/* ------------------------------------------------ Add/Edit Item Popup-----------------------------------------------------*/
					
					function save_item() {
						
						if($("#saveItem").valid()){
							var parameters = {
								
								'itemId' : $("#itemId").val(),
								'itemName' : $("#itemName").val(),
								'itemPrice' : $("#itemPrice").val(),
								'status' : $("#status").val()
								
							};
								
							$.ajax({
								
								type : 'POST',
								url : "ajax_save_item"+$("#SPRING_EXT").html(),
								data : parameters,
								dataType : 'html',
								success : function(data) {
									
									alertNotification(data);
									itemTable.ajax.reload();
									dialogDiv.dialog("close");
									
								}
							});
						}

					 }
					$.validator.addMethod('isValidPrice', function(value) {
						
						var isValidPrice = /^\d{0,5}(\.\d{0,2})?$/.test(value);
						return isValidPrice;
			            
			        }, "Please enter a price format.(Example: 10.85)");
					
					var validator = $("#saveItem").validate({
						rules:{
							
							itemName:{
								required:true,
								maxlength:100,
							},
							itemPrice:{
								required:true,
								isValidPrice:true,
							}
							
						},
						errorClass: "help-inline",
						errorElement: "span",
						highlight:function(element, errorClass, validClass) {
							$(element).parents('.form-group').removeClass('has-success').addClass('has-error');
						},
						unhighlight: function(element, errorClass, validClass) {
							$(element).parents('.form-group').removeClass('has-error').addClass('has-success');
						}
					});
					
					
					/* ------------------------------------------------------ Delete Item-----------------------------------------------------------*/
					
					$(document).on('click', '.data-table tbody tr', function() {
						if ($(this).hasClass('checked')) {

							$(this).removeClass('checked');

						} else {
							$(this).addClass('checked');
						}
					});
					
					$('#deleterow').click(function() {
						
						var selectedRows = itemTable.$('tr.checked');

						if (selectedRows.length <= 0) {
							
							alertNotification("Please select at least 1 record");
							
						}else{
							$("#deleteConfirm").show();
							deleteConfirmDiv.dialog("open");
						}
							
					});
					
					var delete_dialog_buttons = {}; 
					
					delete_dialog_buttons["Confirm"] = function(){
						
						var idSting = listId(itemTable);
						console.log(idSting);
						if (idSting != null) {
							var parameters = {
								'id' : idSting
							};

							$.ajax({
								type : 'POST',
								url : 'ajax_delete_item'+$("#SPRING_EXT").html(),
								data : parameters,
								dataType : 'html',
								success : function(data) {
									alertNotification(data);
									itemTable.ajax.reload();
								}
							});
						}
						deleteConfirmDiv.dialog("close");
						
					};
					
					delete_dialog_buttons["Cancel"] = function(){
						
						deleteConfirmDiv.dialog("close");
						
					};
					
					var deleteConfirmDiv = $("#deleteConfirm").dialog({
						autoOpen : false,
						resizable: false,
						height : 300,
						width : $(window).width()*0.35,
						modal : true,
						position: ['center','center-20%'],
						buttons : delete_dialog_buttons,
						close : function() {

						},
						show: {
							effect: "puff",
							duration: 300
						},
						hide: {
							effect: "puff",
							duration: 300
						}
					});
					
					
					function listId(ObjectTable) {
						var idString = "";
						
						var itemIdArray = [];
						var selectedRows = ObjectTable.$('tr.checked');

						for (var i = 0; i < selectedRows.length; i++) {

							itemIdArray[i] = ObjectTable.row(selectedRows[i]).data()[0];
							
						}
						
						for (var j = 0; j < itemIdArray.length; j++) {
								
							if(j==0){
								idString = itemIdArray[j];
							}else{
								idString += ","+itemIdArray[j];
							}
							
						}
						
						console.log("idString: "+idString);
						return idString;
					}
					
					/* ------------------------------------------------------ Delete Item-----------------------------------------------------------*/
					
					
});
