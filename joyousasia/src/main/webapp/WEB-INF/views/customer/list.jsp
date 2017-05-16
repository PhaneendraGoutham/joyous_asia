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
		<h1>Customer</h1>
		<div class="btn-group">
			<a id="download-btn" class="btn btn-large tip-bottom" title="Download CSV"><i class="fa fa-download"></i></a>
			<a id="add-btn" href="${ WEBAPPS }/customer/add${ SPRING_EXT }" class="btn btn-large tip-bottom" title="Add Customer"><i class="fa fa-plus"></i></a>
			<a id="deleterow" href="#" class="btn btn-large tip-bottom" title="Delete Customer" ><i class="fa fa-trash-o"></i></a>
			<a id="purgeButton" href="#" class="btn btn-large tip-bottom" title="Purge old data from database" ><i class="fa fa-database"></i></a>
		</div>
	</div>

	<div id="breadcrumb">
		<a class="#">Customer Management</a>
		<a class="current">View Customer</a>
	</div>
	
	<!-- ----------------------------------------------------------- SEARCH FILTER ------------------------------------------------------------ -->
	<div class="container-fluid" >
		<div class="row">
			<div class="col-xs-12 center">
				
				
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="fa fa-filter"></i></span>
						<h5>Search Filter</h5>
					</div>
					
					<div class="widget-content nopadding">
						<div class="form-horizontal">
						
							<div class="row">
								<div class="col-sm-6 col-md-6 col-lg-6">
									<div class="form-group">
										<label class="col-sm-3 col-md-3 col-lg-3 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Identification No.</label>
		                                <div class="col-sm-9 col-md-9 col-lg-9">
		                                    <input style="width: 80%" id="identificationNum" type="text" class='filter'  maxlength='50'/>
		                                </div>
									</div>
								</div>
								
								<div class="col-sm-6 col-md-6 col-lg-6">
									<div class="form-group">
										<label class="col-sm-3 col-md-3 col-lg-3 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">E-mail</label>
		                                <div class="col-sm-9 col-md-9 col-lg-9">
		                                    <input style="width: 80%" id="email" type="text" class='filter'  maxlength='80'/>
		                                </div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-6 col-md-6 col-lg-6">
									<div class="form-group">
										<label class="col-sm-3 col-md-3 col-lg-3 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Name</label>
		                                <div class="col-sm-9 col-md-9 col-lg-9">
		                                    <input style="width: 80%" id="name" type="text" class='filter'  maxlength='100'/>
		                                </div>
									</div>
								</div>
								
								<div class="col-sm-6 col-md-6 col-lg-6">
									<div class="form-group">
										<label class="col-sm-3 col-md-3 col-lg-3 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Contact No.</label>
		                                <div class="col-sm-9 col-md-9 col-lg-9">
		                                    <input style="width: 80%" id="contactNum" type="text" class='filter'  maxlength='50'/>
		                                </div>
									</div>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-6 col-md-6 col-lg-6">
									<div class="form-group">
										<label class="col-sm-3 col-md-3 col-lg-3 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Invoice No.</label>
		                                <div class="col-sm-9 col-md-9 col-lg-9">
		                                    <input style="width: 80%" id="invoiceNum" type="text" class='filter'  maxlength='50'/>
		                                </div>
									</div>
								</div>
								
								<div class="col-sm-6 col-md-6 col-lg-6">
									<div class="form-group">
										<label class="col-sm-3 col-md-3 col-lg-3 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Event</label>
		                                <div class="col-sm-9 col-md-9 col-lg-9">
		                                    <select id="event" type="text" style="width:80%">
		                                    	<option value="all">Select All</option>
		                                    	<c:forEach items="${eventList}" var="e">
												  	<option value="${ e.eventName }">${ e.eventName }</option>
											  	</c:forEach>
								  			</select>
		                                </div>
									</div>
								</div>
							</div>
							
							<div class="form-actions" >
								<input id="resetButton"   type="button" value='Reset' class="btn btn-inverse" />
								<input id="submitButton" type="button" value='Search' class="btn btn-info" style="margin-left: 10%;"/>
							</div>	
						</div>						
					</div>
				</div>
			</div>
			
			
		</div>
	</div>
	<!-- ----------------------------------------------------------- SEARCH FILTER ------------------------------------------------------------ -->
	
	<!-- ----------------------------------------------------------- CUSTOMER TABLE ------------------------------------------------------------- -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 center">

				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="fa fa-table"></i></span>
						<h5>Customer</h5>
						<%-- <span title="<spring:message code="table.icon.help" />" class="label label-info tip-right" style="float:left;">
							<i class="fa fa-info"></i>
						</span> --%>
					</div>
					<div class="widget-content nopadding">
						<table id ="dataTable" class="table table-bordered table-striped table-hover data-table" style="text-align: center">
							<thead>
								<tr>
									<th style="background-color: #E2E2E2; border-radius: 0;" width="0%">id</th>
									<th style="background-color: #DEDEDE; border-radius: 0;" width="15%">Identification No.</th>
									<th style="background-color: #E2E2E2; border-radius: 0;" width="10%">Invoice No.</th>
									<th style="background-color: #DEDEDE; border-radius: 0;" width="15%">Name</th>
									<th style="background-color: #E2E2E2; border-radius: 0;" width="20%">Event</th>
									<th style="background-color: #DEDEDE; border-radius: 0;" width="10%">Contact No.</th>
									<th style="background-color: #E2E2E2; border-radius: 0;" width="20%">E-mail</th>
									<th style="background-color: #DEDEDE; border-radius: 0;" width="10%">Details</th>
								</tr>
							</thead>

							<tbody>
								<tr>
									<td colspan="7" class="dataTables_empty" style="text-align: center;">Loading data from server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ----------------------------------------------------------- CUSTOMER TABLE ------------------------------------------------------------- -->
</div>

	<!-- ------------------------------------------------------------ DELETE CONFIRM POPUP WINDOW --------------------------------------------------------------- -->
	
	<div class="widget-content" id="deleteConfirm" title='Notification' style="margin-top: 20px; display: none">
		<div id="mainPanel" class="form-horizontal" style="position: relative; top: 40%; text-align:center; font-size:18px">
			Do you want to delete the customer(s)?
		</div>
	</div>
	
	<!-- ------------------------------------------------------------ DELETE CONFIRM POPUP WINDOW --------------------------------------------------------------- -->
	
	<!-- ------------------------------------------------------------ PURGE CONFIRM POPUP WINDOW --------------------------------------------------------------- -->
	
	<div class="widget-content" id="purgeConfirm" title='Notification' style="margin-top: 20px; display: none">
		<div id="mainPanel" class="form-horizontal" style="position: relative; top: 40%; text-align:center; font-size:18px">
			Do you want to purge customer records from database?
		</div>
	</div>
	
	<!-- ------------------------------------------------------------ PURGE CONFIRM POPUP WINDOW --------------------------------------------------------------- -->

<script src="../js/custom/customer_list.js"></script>

<%@ include file="../include/copyright.jsp"%>
<%@ include file="../home/footer.jsp"%>
