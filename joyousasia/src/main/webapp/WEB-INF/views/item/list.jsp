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
		<h1>Item</h1>
		
		<div class="btn-group">
			<a id="add-btn" href="#" class="btn btn-large tip-bottom" title="Add Item"><i class="fa fa-plus"></i></a>
			<a id="deleterow" href="#" class="btn btn-large tip-bottom" title="Delete Item" ><i class="fa fa-trash-o"></i></a>
		</div>
	</div>

	<div id="breadcrumb">
		<a class="#">Item Management</a>
		<a class="current">View Item</a>
	</div>
	
	<!-- ----------------------------------------------------------- ITEM TABLE ------------------------------------------------------------- -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 center">

				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="fa fa-table"></i></span>
						<h5>Items</h5>
					</div>
					<div class="widget-content nopadding">
						<table id ="itemList" class="table table-bordered table-striped table-hover data-table" style="text-align: center">
							<thead>
								<tr>
									<th style="background-color: #E2E2E2; border-radius: 0;" width="0%">Item ID</th>
									<th style="background-color: #DEDEDE; border-radius: 0;" width="50%">Item Name</th>
									<th style="background-color: #E2E2E2; border-radius: 0;" width="20%">Price($)</th>
									<th style="background-color: #DEDEDE; border-radius: 0;" width="15%">Status</th>
									<th style="background-color: #E2E2E2; border-radius: 0;" width="0%">StatusInteger</th>
									<th style="background-color: #DEDEDE; border-radius: 0;" width="15%">Detail</th>
								</tr>
							</thead>

							<tbody>
								<tr>
									<td colspan="4" class="dataTables_empty" >Loading data from server</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- ----------------------------------------------------------- ITEM TABLE ------------------------------------------------------------- -->
	
	<!-- -------------------------------------------------------- SAVE ITEM POPUP ---------------------------------------------------------- -->
	<div class="widget-content" id="dialog-form" title='Item' style="margin-top: 15px">
		<form id="saveItem">
			<div class="form-horizontal">
			
				<input id="itemId" type="hidden" />
				
				<div class="form-group">
					<label class="col-sm-3 col-md-3 col-lg-3 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Item Name:</label>
		            <div class="col-sm-8 col-md-8 col-lg-7">
		                <input class="form-control input-sm" id="itemName" name="itemName" type="text" />
		            </div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 col-md-3 col-lg-3 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Price($):</label>
		            <div class="col-sm-8 col-md-8 col-lg-7">
		                <input class="form-control input-sm" id="itemPrice" name="itemPrice" type="text" />
		            </div>
				</div>
				
				<div class="form-group">
					<label class="col-sm-3 col-md-3 col-lg-3 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Status:</label>
		            <div class="col-sm-8 col-md-8 col-lg-7">
		                <select id="status" style="text-align: center; width: 100%">
							<option value="1">Active</option>
                          	<option value="0">Suspend</option>
						</select>
		            </div>
				</div>
				
			</div>
		</form>
	</div>
	<!-- -------------------------------------------------------- SAVE ITEM POPUP ---------------------------------------------------------- -->
	
	<!-- ------------------------------------------------------------ DELETE CONFIRM POPUP WINDOW --------------------------------------------------------------- -->
	
	<div class="widget-content" id="deleteConfirm" title='Notification' style="margin-top: 20px; display: none">
		<div id="mainPanel" class="form-horizontal" style="position: relative; top: 40%; text-align:center; font-size:18px">
			Do you want to delete the item(s)?
		</div>
	</div>
	
	<!-- ------------------------------------------------------------ DELETE CONFIRM POPUP WINDOW --------------------------------------------------------------- -->
</div>

<script src="../js/custom/item_list.js"></script>

<%@ include file="../include/copyright.jsp"%>
<%@ include file="../home/footer.jsp"%>	
