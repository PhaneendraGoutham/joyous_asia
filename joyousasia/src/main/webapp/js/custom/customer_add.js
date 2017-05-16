/**
 * JS for Customer Add
 */

$(document).ready(function(){
	
	if($('#errmsg').html().trim().length!=0 && $('#errmsg').html().trim()!="")
	{
		alertNotification($('#errmsg').html());
		
	}
	
	function blinker() {
	    $('.blink_text').fadeOut(600);
	    $('.blink_text').fadeIn(600);
	}

	setInterval(blinker, 1000);
	
	$("#datetimePickerStartDate").datetimepicker({
		viewMode: 'days',
		format:'DD-MM-YYYY'
	});
	
	$("#datetimePickerEndDate").datetimepicker({
		viewMode: 'days',
		format:'DD-MM-YYYY'
	});
	
	$("#readonlyTransferAmount").val($("#transferAmount").val());
	
	//to set default start date and end date = today's date
	$("#rentalFrom").val(moment(new Date()).format('DD-MM-YYYY'));
	$("#rentalTo").val(moment(new Date()).format('DD-MM-YYYY'));
	
	//set min date and max date by default
	$('#datetimePickerEndDate').data("DateTimePicker").minDate(new Date());
	$('#datetimePickerStartDate').data("DateTimePicker").maxDate(new Date());
	
	$("#datetimePickerStartDate").on("dp.change",function (e) {
		$('#datetimePickerEndDate').data("DateTimePicker").minDate(e.date);
	});
	
	$("#datetimePickerEndDate").on("dp.change",function (e) {
		$('#datetimePickerStartDate').data("DateTimePicker").maxDate(e.date);
		getTotalPrice();
	});
	
	$("#timePickerStartTime").datetimepicker({
		format:'LT'
	});
	
	$("#timePickerEndTime").datetimepicker({
		format:'LT'
	});
	
	$("#timeFrom").val("12:00 AM");
	$("#timeTo").val("12:00 AM");
	$('#returnGownFromtimeTotime').val($("#timeFrom").val()+"-"+$("#timeTo").val());
	
	$("#timePickerStartTime").on("dp.change",function () {
		$('#returnGownFromtimeTotime').val($("#timeFrom").val()+"-"+$("#timeTo").val());
	});
	
	$("#timePickerEndTime").on("dp.change",function () {
		$('#returnGownFromtimeTotime').val($("#timeFrom").val()+"-"+$("#timeTo").val());
	});
	
	$(window).load(function () {
		$('#deposit_transfer_into_js').on('ifChecked', function(event){
			$(".jsRelativeDiv").show("fade", 400);
		});
		
		$('#deposit_transfer_into_js').on('ifUnchecked', function(event){
			$(".jsRelativeDiv").hide("fade", 400);
			$("#jsReceiptNum").val("");
		});
		
		$('#transfer_all_refund_into_js').on('ifChecked', function(event){
			var refundArr = $("#refundIncludingPenalty").html().split('$');
			$("#readonlyTransferAmount").val(refundArr[1]).change();
			$("#readonlyTransferAmount").prop('disabled', true);
		});
		
		$('#transfer_all_refund_into_js').on('ifUnchecked', function(event){
			$("#readonlyTransferAmount").val("0.00").change();
			$("#readonlyTransferAmount").prop('disabled', false);
		});
	});
	
	$( ".saveAndPrintButton" ).click(function(e) {
		e.preventDefault(); //To prevent the browser from refreshing the page
		if($("#basic_validate").valid()){
			dialogDiv.dialog("open");
		}
	});
	
	$( ".saveOnlyButton" ).click(function(e) {
		e.preventDefault(); //To prevent the browser from refreshing the page
		if($("#basic_validate").valid()){
			fillInReturnDate($('#rentalReturnDetails').val());
			console.log("itemQuantityString: "+getitemQuantityString());
			$('#itemQuantityString').val(getitemQuantityString());
			$( "#basic_validate" ).submit();
		}
	});
	
	/* ------------------------------------------------ Save Confirm Popup-----------------------------------------------------*/
	var dialog_buttons = {}; 
	
	dialog_buttons["Yes"] = function(){
		
		fillInReturnDate($('#rentalReturnDetails').val());
		console.log("itemQuantityString: "+getitemQuantityString());
		$('#itemQuantityString').val(getitemQuantityString());
		$("#transferAmount").val($("#readonlyTransferAmount").val());
		$("#isPrint").val("1");
		$( "#basic_validate" ).submit();
		
	};
	
	dialog_buttons["Cancel"] = function(){
		dialogDiv.dialog("close");
	};
	
	var dialogDiv = $("#saveConfirm").dialog({
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
	/* ------------------------------------------------ Save Confirm Popup-----------------------------------------------------*/

	// Form Validation
    var basic_validate = $("#basic_validate").validate({
		rules:{
			
			identificationNum:{
				required:true,
				maxlength:100
			},
			school:{
				required:true,
				maxlength:150
			},
			name:{
				required:true,
				maxlength:150
			},
			contactNum:{
				maxlength:50
			},
			address:{
				maxlength:255
			},
			email:{
				email: true,
				maxlength:150
			},
			height:{
				digits:true,
				maxlength:3
			},
			weight:{
				digits:true,
				maxlength:3
			},
			size:{
				maxlength:4
			},
			rental:{
				digits:true,
				maxlength:5
			},
			deposit:{
				digits:true,
				maxlength:5
			},
			event:{
				required:true
			},
			rentalVenue:{
				maxlength:255
			},
			remarks:{
				maxlength:255
			}
			
		},
//		messages: {
//			
//			organizationName:{
//				required:$("#errormsgOrganizationNameRequired").html(),
//				remote:$("#errormsgOrganizationNameExists").html(),
//				maxlength:$("#errormsgMaxlength").html()
//			}
		
//		},
		errorClass: "help-inline",
		errorElement: "span",
		highlight:function(element, errorClass, validClass) {
			$(element).parents('.form-group').removeClass('has-success').addClass('has-error');
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).parents('.form-group').removeClass('has-error').addClass('has-success');
		}
	});
    
    console.log($("#itemListJson").html());
    var itemList = $.parseJSON($("#itemListJson").html());
	for ( var i=0; i<itemList.length; i+=2) {
		
		var evenItem = "";
			
		
		if(i+1<itemList.length){
			
			evenItem = 
				'<label class="col-sm-2 col-md-2 col-lg-2 control-label" style="font-weight: bold;  font-size: 12px; color:#666666">'+itemList[i+1].itemName+':</label>'+
					'<div class="col-sm-1 col-md-1 col-lg-1">'+
					'<input class="form-control input-sm item-quantity" style="width: 100%; text-align: center;" id="item_'+itemList[i+1].itemId+'" value="'+itemList[i+1].itemQuantity+'" type="text" />'+
				'</div>'+
				'<div class="col-sm-3 col-md-3 col-lg-3">'+
					'<div class="btn btn-primary btn-quantity" id="plus-btn_'+itemList[i+1].itemId+'" style="width: 25%;"><i id="plus-icon_'+itemList[i+1].itemId+'" class="fa fa-plus" ></i></div>'+
					'<div class="btn btn-primary btn-quantity" id="minus-btn_'+itemList[i+1].itemId+'" style="width: 25%; margin-left: 5%"><i id="minus-icon_'+itemList[i+1].itemId+'" class="fa fa-minus"></i></div>'+
				'</div>';
				
		}
		
		var html = 
			'<div class="form-group">'+
				'<label class="col-sm-2 col-md-2 col-lg-2 control-label" style="font-weight: bold;  font-size: 12px; color:#666666">'+itemList[i].itemName+':</label>'+
				'<div class="col-sm-1 col-md-1 col-lg-1">'+
					'<input class="form-control input-sm item-quantity" style="width: 100%; text-align: center;" id="item_'+itemList[i].itemId+'" value="'+itemList[i].itemQuantity+'" type="text" />'+
				'</div>'+
				'<div class="col-sm-3 col-md-3 col-lg-3">'+
					'<div class="btn btn-primary btn-quantity" id="plus-btn_'+itemList[i].itemId+'" style="width: 25%;"><i id="plus-icon_'+itemList[i].itemId+'" class="fa fa-plus" ></i></div>'+
					'<div class="btn btn-primary btn-quantity" id="minus-btn_'+itemList[i].itemId+'" style="width: 25%; margin-left: 5%"><i id="minus-icon_'+itemList[i].itemId+'" class="fa fa-minus"></i></div>'+
				'</div>'+
				evenItem+
			'</div>';
		
		$("#itemMenu").append(html);
		
	}
	
	getTotalPrice();
	
	$(".btn-quantity").on("click", function(e) {
		var btnId = $(e.target).attr('id');
		var btnIdArray = btnId.split("_");
		if(btnIdArray[0]=="plus-btn"){
			$("#item_"+btnIdArray[1]).val(parseInt($("#item_"+btnIdArray[1]).val())+1).change();
		}else if(btnIdArray[0]=="minus-btn"){
			var result = parseInt($("#item_"+btnIdArray[1]).val())-1;
			if(result < 0){
				result = 0;
			}
			$("#item_"+btnIdArray[1]).val(result).change();
		}
	});
	
	$(".fa-plus").on("click", function(e) {
		var btnId = $(e.target).attr('id');
		var btnIdArray = btnId.split("_");
		$("#item_"+btnIdArray[1]).val(parseInt($("#item_"+btnIdArray[1]).val())+1).change();
	});
	
	$(".fa-minus").on("click", function(e) {
		var btnId = $(e.target).attr('id');
		var btnIdArray = btnId.split("_");
		var result = parseInt($("#item_"+btnIdArray[1]).val())-1;
		if(result < 0){
			result = 0;
		}
		$("#item_"+btnIdArray[1]).val(result).change();
	});
	
	$("#rental, #deposit, #event, #readonlyTransferAmount").on("change", function(e) {
		getTotalPrice();
	});
	
	$(".item-quantity").on("change paste keyup", function(e) {
		var isValidInteger = /^([0-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9])$/.test($(e.target).val());
		if(!isValidInteger){
			$(e.target).val("");
		}
		getTotalPrice();
	});
	
	$("#height").on("change", function() {
		heightConvertToSize();
	});
	
	function heightConvertToSize(){
		var height = parseInt($("#height").val());
		if(height < 160) {
			$("#readonlySize").val("XS");
			$("#size").val("XS");
		}else if(height >= 160 && height <170) {
			$("#readonlySize").val("S");
			$("#size").val("S");
		}else if(height >=170 && height < 180) {
			$("#readonlySize").val("M");
			$("#size").val("M");
		}else if(height >=180 && height < 190) {
			$("#readonlySize").val("L");
			$("#size").val("L");
		}else if(height >= 190) {
			$("#readonlySize").val("XL");
			$("#size").val("XL");
		}
	}
	
	function getTotalPrice(){
		var inputs = $(".item-quantity");
		var totalSales = 0;
		for(var i=0; i<inputs.length; i++ ){
			var quantity = $(inputs[i]).val();
			if(quantity != ""){
				var itemIdArray = $(inputs[i]).attr('id').split("item_");
				var itemId = itemIdArray[1];
				var item = getObject(itemList, "itemId", parseInt(itemId));
				var subtotal = parseFloat(item.itemPrice) * parseFloat(quantity);
				totalSales += subtotal;
			}
		}
		amountTransferIntoJs = parseFloat($("#readonlyTransferAmount").val());
		totalSales += amountTransferIntoJs
		$("#totalPrice").html("$"+totalSales.toFixed(2));
		var rental = $("#rental").val();
		var higher = parseFloat(totalSales)>parseFloat($("#deposit").val())? totalSales : $("#deposit").val();
		var collect = parseFloat(rental)+parseFloat(higher);
		$("#collect").html("$"+collect.toFixed(2));
		
		var currentDate = Math.floor(new Date());
		var parameter = {
				'eventName' : $("#event").val()
		};
		
		$.ajax({
			type : 'POST',
			url : 'get_latest_return_date_by_event'+$("#SPRING_EXT").html(),
			data : parameter,
			dataType : 'html',
			success : function(data) {
				$("#latestReturnDate").html(data);
				var latestReturnDateArr = data.split('-');
				var latestReturnDate = new Date(latestReturnDateArr[1] + "-" + latestReturnDateArr[0] + "-" + latestReturnDateArr[2]).getTime();
				
				var penalty = 0;
				var gownReturnDateString = $("#rentalReturnDate").val();
				var delayDays = 0;
				if(gownReturnDateString.length == 0){
					delayDays = getDelayDays(currentDate, latestReturnDate);
				}else{
					var rentalReturnDate = moment($("#rentalReturnDate").val(), 'DD-MM-YYYY HH:mm:ss').toDate();
					delayDays = getDelayDays(rentalReturnDate.getTime(), latestReturnDate);
				}
				
				$("#delayDays").html((delayDays>0 ? delayDays : 0)+" days");
				if(delayDays>0){
					if(delayDays == 1){
						penalty = 30;
					}else{
						penalty = 30 + delayDays*10;
					}
				}
				
				var refundWithoutPenalty = parseFloat(higher) - parseFloat(totalSales);
				refundWithoutPenalty = refundWithoutPenalty < 0 ? 0 : refundWithoutPenalty;
				$("#refund").html("$"+refundWithoutPenalty.toFixed(2));
				
				var refundIncludingPenalty = parseFloat(higher) - parseFloat(totalSales) - parseFloat(penalty);
				refundIncludingPenalty = refundIncludingPenalty < 0 ? 0 : refundIncludingPenalty;
				$("#refundIncludingPenalty").html("$"+refundIncludingPenalty.toFixed(2));
			}
		});
		
	}
	
	function getDelayDays(from, to){
		return Math.floor((parseInt(from) - parseInt(to))/86400000);
	}
	
	function getObject(obj, key, val) {
	    var object = null;
	    for (var i in obj) {
	        if (!obj.hasOwnProperty(i)) continue;
	        if (obj[i][key] == val) {
	            object = obj[i];
	        }
	    }
	    return object;
	}
	
	$("input.rentalReturnDetailsRadio:radio").on("ifClicked", function(e){
		rentalReturnDetailSelector($(e.target).val());
	});
	
	function rentalReturnDetailSelector(value){
		
		switch (value) {
		
			case '0': 
				$('#no_return').iCheck('check');
				$('#rental_return').iCheck('uncheck');
				$('#deposit_return').iCheck('uncheck');
				$('#both_return').iCheck('uncheck');
				$('#rentalReturnDetails').val('0');
				break;
				
			case '1': 
				$('#no_return').iCheck('uncheck');
				$('#rental_return').iCheck('check');
				$('#deposit_return').iCheck('uncheck');
				$('#both_return').iCheck('uncheck');
				$('#rentalReturnDetails').val('1');
				break;
				
			case '2': 
				$('#no_return').iCheck('uncheck');
				$('#rental_return').iCheck('uncheck');
				$('#deposit_return').iCheck('check');
				$('#both_return').iCheck('uncheck');
				$('#rentalReturnDetails').val('2');
				break;	
				
			case '3': 
				$('#no_return').iCheck('uncheck');
				$('#rental_return').iCheck('uncheck');
				$('#deposit_return').iCheck('uncheck');
				$('#both_return').iCheck('check');
				$('#rentalReturnDetails').val('3');
				break;
		
		}
	}
	
	$("input.payByRadio:radio").on("ifClicked", function(e){
		payBySelector($(e.target).val());
	});
	
	function payBySelector(value){
		
		switch (value) {
		
			case '0': 
				$('#cash').iCheck('check');
				$('#credit_card').iCheck('uncheck');
				$('#nets').iCheck('uncheck');
				$('#others').iCheck('uncheck');
				$('#payByDetail').val('0');
				break;
				
			case '1': 
				$('#cash').iCheck('uncheck');
				$('#credit_card').iCheck('check');
				$('#nets').iCheck('uncheck');
				$('#others').iCheck('uncheck');
				$('#payByDetail').val('1');
				break;
				
			case '2': 
				$('#cash').iCheck('uncheck');
				$('#credit_card').iCheck('uncheck');
				$('#nets').iCheck('check');
				$('#others').iCheck('uncheck');
				$('#payByDetail').val('2');
				break;	
				
			case '3': 
				$('#cash').iCheck('uncheck');
				$('#credit_card').iCheck('uncheck');
				$('#nets').iCheck('uncheck');
				$('#others').iCheck('check');
				$('#payByDetail').val('3');
				break;
		
		}
	}
	
	function fillInReturnDate(value){
		
		switch (value) {
		
		case '1': 
			$("#rentalReturnDate").val(moment(new Date()).format('DD-MM-YYYY HH:mm:ss'));
			break;
			
		case '2': 
			$("#depositReturnDate").val(moment(new Date()).format('DD-MM-YYYY HH:mm:ss'));
			break;	
			
		case '3': 
			var rentalReturnDate = $("#rentalReturnDate").val();
			var depositReturnDate = $("#depositReturnDate").val();
			if(rentalReturnDate.length == 0){
				$("#rentalReturnDate").val(moment(new Date()).format('DD-MM-YYYY HH:mm:ss'));
			}
			
			if(depositReturnDate.length == 0){
				$("#depositReturnDate").val(moment(new Date()).format('DD-MM-YYYY HH:mm:ss'));
			}
			break;
	
		}
		
	}
	
	function getitemQuantityString(){
		var itemQuantityArray = $(".item-quantity");
//		var isFirstItem = false;
//		var firstItemIdx = 0;
		var itemQuantityString = "";
		for ( var i=0; i<itemQuantityArray.length; i++) {
//			if(parseInt($(itemQuantityArray[i]).val()) > 0){
//				if(isFirstItem == false){
//					firstItemIdx = i;
//					isFirstItem = true;
//				}
				var itemIdArray = $(itemQuantityArray[i]).attr('id').split("item_");
//				if(i == firstItemIdx){
				if(i == 0){
					itemQuantityString += itemIdArray[1] + ":" + $(itemQuantityArray[i]).val();
				}else{
					itemQuantityString += "," + itemIdArray[1] + ":" + $(itemQuantityArray[i]).val();
				}
				
//			}
		}
		return itemQuantityString;
	}
	
	
});


