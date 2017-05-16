<%@ page pageEncoding="UTF-8"%>
<%@ include file="../include/import.jsp"%>
<%@ include file="../home/header.jsp"%>
<jsp:include page="../home/leftnav.jsp" />

<!-- Attributes List -->
		
<div id="SPRING_EXT" style="display: none">${ SPRING_EXT }</div>
<div id="errmsg" style="display: none">${ errmsg }</div>
<div id="itemListJson" style="display: none">${ itemList }</div>

<!-- Attributes List -->

<div id="WEBAPPS" style="display: none;">${ WEBAPPS }</div>
<div id="content">
	<div id="content-header">
		<h1>Add Customer</h1>
		<div class="btn-group">
			<a class="btn btn-large tip-bottom saveAndPrintButton" title="Save and Print"><i class="fa fa-print"></i></a>
			<a class="btn btn-large tip-bottom saveOnlyButton" title="Save Only"><i class="fa fa-save"></i></a>
		</div>
	</div>

	<div id="breadcrumb">
		<a href="list${ SPRING_EXT }">Customer Management</a>
		<a class="current">Add Customer</a>
	</div>
	
	<!-- ----------------------------------------------------------- CUSTOMER EDITOR FORM ------------------------------------------------------------ -->
	<sf:form modelAttribute="CustomerDTO" method="POST" name="basic_validate" id="basic_validate" >
	<div class="container-fluid" >
		<div class="row">
			<div class="col-xs-12 center">
				
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="fa fa-pencil-square-o"></i></span>
						<h5>Add Customer</h5>
						<span style="float:right; margin-top: 8px; padding-right:12px; color:red;">*&nbsp;Mandatory Fields</span>
					</div>
					
					<div class="widget-content nopadding">
						<div class="form-horizontal">
						
							<!-- ------------------------------------------------------------ Order Details ------------------------------------------------------------- -->
							<div style="padding-bottom: 55px;">
								<div class="form-group" style="background: lavender; width: 48%; float: left;">
									<label class="col-sm-9 col-md-9 col-lg-9 control-label" style=" font-weight: bold;  text-align: left; font-size: 17px; color:#666666;">Order Details</label>
								</div>
								<div class="form-group" style="background: cornsilk; width: 13%; float: left;">
									<label class="col-sm-7 col-md-7 col-lg-7 control-label" style=" font-weight: bold;  text-align: right; font-size: 17px; color:#666666;">Sales:</label>
									<label id="totalPrice" class="col-sm-5 col-md-5 col-lg-5 control-label" style="margin-left: -15px; font-weight: bold;  text-align: left; font-size: 17px; color:#666666;">$0.00</label>
								</div>
								<div class="form-group" style="background: bisque; width: 13%; float: left;">
									<label class="col-sm-7 col-md-7 col-lg-7 control-label" style=" font-weight: bold;  text-align: right; font-size: 17px; color:#666666;">Collect:</label>
									<label id="collect" class="col-sm-5 col-md-5 col-lg-5 control-label" style="margin-left: -15px; font-weight: bold;  text-align: left; font-size: 17px; color:#666666;">$0.00</label>
								</div>
								<div class="form-group" style="background: antiquewhite; width: 13%; float: left;">
									<label class="col-sm-7 col-md-7 col-lg-7 control-label" style=" font-weight: bold;  text-align: right; font-size: 17px; color:#666666;">Refund:</label>
									<label id="refund" class="col-sm-5 col-md-5 col-lg-5 control-label" style="margin-left: -15px; font-weight: bold;  text-align: left; font-size: 17px; color:#666666;">$0.00</label>
								</div>
								<div class="form-group" style="background: lavenderblush; width: 13%; float: right;">
									<label class="col-sm-7 col-md-7 col-lg-7 control-label" style=" font-weight: bold;  text-align: right; font-size: 17px; color:#666666;">Refund(P):</label>
									<label id="refundIncludingPenalty" class="col-sm-5 col-md-5 col-lg-5 control-label" style="margin-left: -15px; font-weight: bold;  text-align: left; font-size: 17px; color:#666666;">$0.00</label>
								</div>
							</div>
							
							<div id="itemMenu"></div>
							
							<div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 15px; margin-top: -7px; color:#666666">Pay By:</label>
								<div class="col-sm-10 col-md-10 col-lg-10" style=" width: 7%; float: left;">
						  			<input type="radio" id="cash" class="payByRadio" value="0" checked>
						  			<label for="cash" style="float: right;" >Cash</label>
					  			</div>
					  			<div class="col-sm-10 col-md-10 col-lg-10" style=" width: 10%; float: left;">
						  			<input type="radio" id="credit_card" class="payByRadio" value="1">
						  			<label for="credit_card" style="float: right;" >Credit Card</label>
					  			</div>
					  			<div class="col-sm-10 col-md-10 col-lg-10" style=" width: 7%; float: left;">
						  			<input type="radio" id="nets" class="payByRadio" value="2">
						  			<label for="nets" style="float: right;" >NETS</label>
					  			</div>
					  			<div class="col-sm-10 col-md-10 col-lg-10" style=" width: 8%; float: left;">
						  			<input type="radio" id="others" class="payByRadio" value="3">
						  			<label for="others" style="float: right;" >Others</label>
					  			</div>
							</div>
							<!-- ------------------------------------------------------------ Order Details ------------------------------------------------------------- -->
						
						
							<!-- ----------------------------------------------------------- Graduand Details ------------------------------------------------------------ -->
							<div class="form-group" style="background: antiquewhite;">
								<label class="col-sm-12 col-md-12 col-lg-12 control-label" style=" font-weight: bold;  text-align: left; font-size: 15px; color:#666666;">Graduand Details</label>
							</div>
						
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666"><span style="color:red;">*</span>&nbsp;Identification No.:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:input class="form-control input-sm" path="identificationNum" type="text" />
                                </div>
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Height(cm):</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:input class="form-control input-sm" path="height" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666"><span style="color:red;">*</span>&nbsp;School:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:select path="school" type="text" style="width:100%">
                                    	<c:forEach items="${schoolList}" var="s">									  				
										  	<sf:option value="${ s.schoolName }">${ s.schoolName }</sf:option>
									  	</c:forEach>
						  			</sf:select>
                                </div>
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Weight(kg):</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:input class="form-control input-sm" path="weight" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666"><span style="color:red;">*</span>&nbsp;Name:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:input class="form-control input-sm" path="name" type="text" />
                                </div>
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Size:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <input id="readonlySize" class="form-control input-sm" type="text" readonly/>
                                    <sf:hidden path="size"/>
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Faculty:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                	<sf:select path="faculty" type="text" style="width:100%">
                                    	<c:forEach items="${facultyList}" var="f">									  				
										  	<sf:option value="${ f.facultyName }">${ f.facultyName }</sf:option>
									  	</c:forEach>
						  			</sf:select>
                                </div>
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Rental($):</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:input class="form-control input-sm" path="rental" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Contact No.:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:input class="form-control input-sm" path="contactNum" type="text" />
                                </div>
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Deposit($):</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:input class="form-control input-sm" path="deposit" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Address:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:input class="form-control input-sm" path="address" type="text" />
                                </div>
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666"><span style="color:red;">*</span>&nbsp;Event:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:select path="event" type="text" style="width:100%">
                                    	<c:forEach items="${eventList}" var="e">									  				
										  	<sf:option value="${ e.eventName }">${ e.eventName }</sf:option>
									  	</c:forEach>
						  			</sf:select>
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">E-mail Address:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                    <sf:input class="form-control input-sm" path="email" type="text" />
                                </div>
							</div>
							<!-- ----------------------------------------------------------- Graduand Details ------------------------------------------------------------ -->
							
							
							<!-- ------------------------------------------------------------ Rental Details ------------------------------------------------------------- -->
							<div style="padding-bottom: 55px;">
								<div class="form-group" style="background: wheat; width: 67%; float: left;">
									<label class="col-sm-12 col-md-12 col-lg-12 control-label" style=" font-weight: bold;  text-align: left; font-size: 15px; color:#666666;">Rental Details</label>
								</div>
								<div class="form-group" style="background: cornsilk; width: 20%; float: left;">
									<label class="col-sm-7 col-md-7 col-lg-7 control-label" style=" font-weight: bold;  text-align: right; font-size: 15px; color:#666666;">Latest Return Date:</label>
									<label id="latestReturnDate" class="col-sm-5 col-md-5 col-lg-5 control-label" style="margin-left: -15px; font-weight: bold;  text-align: left; font-size: 15px; color:#666666;">Unknown</label>
								</div>
								<div class="form-group" style="background: lightsalmon; width: 13%; float: right;">
									<label class="col-sm-6 col-md-6 col-lg-6 control-label" style=" font-weight: bold;  text-align: right; font-size: 15px; color:#ffffff;">Delay:</label>
									<label id="delayDays" class="col-sm-6 col-md-6 col-lg-6 control-label" style="margin-left: -15px; font-weight: bold;  text-align: left; font-size: 15px; color:#ffffff;">0 days</label>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Rental From:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                	<div class='input-group date' id='datetimePickerStartDate' style="width: 100%">
                                		<sf:input type='text' class="form-control" path="rentalFrom"/>
										<span class="input-group-addon" style="width: 15%">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
                                </div>
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Rental To:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                	<div class='input-group date' id='datetimePickerEndDate' style="width: 100%">
					 					<sf:input type='text' class="form-control" path="rentalTo"/>
										<span class="input-group-addon" style="width: 15%">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Return Gown From:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                	<div class='input-group date' id='timePickerStartTime' style="width: 100%">
                                		<input type='text' class="form-control" id="timeFrom"/>
										<span class="input-group-addon" style="width: 15%">
											<span class="glyphicon glyphicon-time"></span>
										</span>
									</div>
                                </div>
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">To:</label>
                                <div class="col-sm-4 col-md-4 col-lg-4">
                                	<div class='input-group date' id='timePickerEndTime' style="width: 100%">
					 					<input type='text' class="form-control" id="timeTo"/>
										<span class="input-group-addon" style="width: 15%">
											<span class="glyphicon glyphicon-time"></span>
										</span>
									</div>
                                </div>
							</div>
							
							<div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Return Venue:</label>
                                <div class="col-sm-10 col-md-10 col-lg-10">
                                    <sf:input class="form-control input-sm" path="rentalVenue" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Remarks</label>
                                <div class="col-sm-10 col-md-10 col-lg-10">
                                    <sf:textarea class="form-control input-sm" path="remarks" rows="6" cols="22"></sf:textarea>
                                </div>
							</div>
							<!-- ------------------------------------------------------------ Rental Details ------------------------------------------------------------- -->
							
							
							<!-- -------------------------------------------------------- Rental Return Details ---------------------------------------------------------- -->
							<div class="form-group" style="background: mistyrose/* powderblue */;">
								<label class="col-sm-12 col-md-12 col-lg-12 control-label" style=" font-weight: bold;  text-align: left; font-size: 15px; color:#666666;">Rental Return Details</label>
							</div>
							
							<div class="form-group">
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; margin-top: -5px; color:#666666">Rental Return Details:</label>
								<div class="col-sm-10 col-md-10 col-lg-10" style=" width: 10%; float: left;">
						  			<input type="radio" id="no_return" class="rentalReturnDetailsRadio" value="0" checked>
						  			<label for="no_return" style="float: right;" >No Returned</label>
					  			</div>
					  			<div class="col-sm-10 col-md-10 col-lg-10" style=" width: 12%; float: left;">
						  			<input type="radio" id="rental_return" class="rentalReturnDetailsRadio" value="1">
						  			<label for="rental_return" style="float: right;" >Rental Returned</label>
					  			</div>
					  			<div class="col-sm-10 col-md-10 col-lg-10" style=" width: 12%; float: left;">
						  			<input type="radio" id="deposit_return" class="rentalReturnDetailsRadio" value="2">
						  			<label for="deposit_return" style="float: right;" >Deposit Returned</label>
					  			</div>
					  			<div class="col-sm-10 col-md-10 col-lg-10" style=" width: 11%; float: left;">
						  			<input type="radio" id="both_return" class="rentalReturnDetailsRadio" value="3">
						  			<label for="both_return" style="float: right;" >Both Returned</label>
					  			</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; margin-top: -5px; color:#666666">Deposit Transfer into JS:</label>
								<div class="col-sm-1 col-md-1 col-lg-1">
									<input id="deposit_transfer_into_js" type="checkbox" >
					            </div>
							</div>
							
							<div id="jsReceiptNumDiv" class="form-group jsRelativeDiv" style="display: none">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">JS Receipt No.:</label>
                                <div class="col-sm-10 col-md-10 col-lg-10">
                                    <sf:input class="form-control input-sm" path="jsReceiptNum" type="text" />
                                </div>
							</div>
							
							<div class="form-group jsRelativeDiv" style="display: none">
								<label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; margin-top: -5px; color:#666666">Transfer All Refund into JS:</label>
								<div class="col-sm-1 col-md-1 col-lg-1">
									<input id="transfer_all_refund_into_js" type="checkbox" >
					            </div>
                                <label class="col-sm-2 col-md-2 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Transfer Amount:</label>
                                <div class="col-sm-7 col-md-7 col-lg-7">
                                    <input class="form-control input-sm" id="readonlyTransferAmount" type="text" >
                                    <sf:hidden path="transferAmount" />
                                </div>
							</div>
							
							<!-- -------------------------------------------------------- Rental Return Details ---------------------------------------------------------- -->
							
							<sf:hidden path="customerId"/>
							<sf:hidden path="returnGownFromtimeTotime"/>
							<sf:hidden path="rentalReturnDetails"/>
							<sf:hidden path="rentalReturnDate"/>
							<sf:hidden path="depositReturnDate"/>
							<sf:hidden path="lastUpdated"/>
							<sf:hidden path="createdDate"/>
							<sf:hidden path="itemQuantityString"/>
							<sf:hidden path="payByDetail"/>
							<input type="hidden" name="isPrint" id="isPrint"/>
							
							<div class="form-actions" style="height: 50px">
								<input type="button" style="margin-left: 72%; background-color: #34495e; border-color: #34495e;" value='Save and Print' class="btn btn-primary saveAndPrintButton">
								<input type="button" style="margin-left: 1%; background-color: #34495e; border-color: #34495e;" value='Save Only' class="btn btn-primary saveOnlyButton">
							</div>
							
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	</sf:form>
	<!-- ----------------------------------------------------------- CUSTOMER EDITOR FORM ------------------------------------------------------------ -->
	<!-- ------------------------------------------------------------ SAVE CONFIRM POPUP WINDOW --------------------------------------------------------------- -->
	<div class="widget-content" id="saveConfirm" title='Notification' style="margin-top: 20px; display: none">
		<div id="mainPanel" class="form-horizontal" style="position: relative; top: 22%; margin-left:60px; margin-right:60px; text-align:center; font-size:50px">
			Please collect the <span class="blink_text" style="font-weight: bold">money</span> before printing receipt!
		</div>
	</div>
	<!-- ------------------------------------------------------------ SAVE CONFIRM POPUP WINDOW --------------------------------------------------------------- -->
</div>

<script src="../js/custom/customer_add.js"></script>

<%@ include file="../include/copyright.jsp"%>
<%@ include file="../home/footer.jsp"%>
