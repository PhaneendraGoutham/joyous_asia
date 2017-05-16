<%@ page pageEncoding="UTF-8"%>
<%@ include file="../include/import.jsp"%>
<%@ include file="../home/header.jsp"%>
<jsp:include page="../home/leftnav.jsp" />

<!-- Attributes List -->
		
<div id="SPRING_EXT" style="display: none">${ SPRING_EXT }</div>
<div id="errmsg" style="display: none">${ errmsg }</div>

<!-- Attributes List -->

<div id="WEBAPPS" style="display: none;">${ WEBAPPS }</div>
<div id="content">
	<div id="content-header">
		<c:choose> 
			<c:when test="${editmode}">
			    <h1>Add Customer</h1>
			</c:when>
			<c:otherwise>
			    <h1>Edit Customer</h1>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="breadcrumb">
		<a href="${ WEBAPPS }/main${ SPRING_EXT }" title="Back to Dashboard" class="tip-bottom"><i class="icon-home"></i>Dashboard</a> 
		<a href="list${ SPRING_EXT }">Customer</a>
		<c:choose>
			<c:when test="${editmode}">
				<a class="current">Add Customer</a>
			</c:when>
			<c:otherwise>
		        <a class="current">Edit Customer</a>
		    </c:otherwise>
		</c:choose>
	</div>
	
	<!-- ----------------------------------------------------------- CUSTOMER EDITOR FORM ------------------------------------------------------------ -->
	<sf:form modelAttribute="CustomerDTO" method="POST" name="basic_validate" id="basic_validate" >
	<div class="container-fluid" >
		<div class="row">
			<div class="col-xs-12 center">
				
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="fa fa-pencil-square-o"></i></span>
						<c:choose>
							<c:when test="${editmode}">
								<h5>Add Customer</h5>
							</c:when>
							<c:otherwise>
								<h5>Edit Customer</h5>
						    </c:otherwise>
						</c:choose>
						<span style="float:right; margin-top: 8px; padding-right:12px; color:red;">*&nbsp;Mandatory Fields</span>
					</div>
					
					<div class="widget-content nopadding">
						<div class="form-horizontal">
						
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666"><span style="color:red;">*</span>&nbsp;Identification No.:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="identificationNum" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">School:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="school" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Name:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="name" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Faculty:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="faculty" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Contact No.:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="contactNum" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Address:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="address" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">E-mail Address:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="email" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Height(cm):</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="height" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Weight(cm):</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="weight" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Size(cm):</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="size" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Rental($):</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="rental" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Deposit($):</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="deposit" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Rental Venue:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="rentalVenue" type="text" />
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Remarks</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:textarea class="form-control input-sm" style="width: 37%" path="remarks" rows="6" cols="22"></sf:textarea>
                                </div>
							</div>
							
							<sf:hidden path="customerId"/>
							<sf:hidden path="rentalFrom"/>
							<sf:hidden path="rentalTo"/>
							<sf:hidden path="lastUpdated"/>
							<sf:hidden path="createdDate"/>
							
							<div class="form-actions">
								<input id="submitButton" type="button" style="margin-left: 0%" value='Save' class="btn btn-primary">
								<input onclick="history.go(-1);" type="button" style="margin-left:3%" value='Back' class="btn btn-primary">
							</div>
							
						</div>
					</div>
					
					
				</div>
			</div>
		</div>
	</div>
	</sf:form>
	<!-- ----------------------------------------------------------- CUSTOMER EDITOR FORM ------------------------------------------------------------ -->
</div>

<c:choose>
	<c:when test="${editmode}">
		<script src="../js/custom/customer_add.js"></script>
	</c:when>
	<c:otherwise>
        <script src="../js/custom/customer_edit.js"></script>
    </c:otherwise>
</c:choose>

<%@ include file="../include/copyright.jsp"%>
<%@ include file="../home/footer.jsp"%>	
