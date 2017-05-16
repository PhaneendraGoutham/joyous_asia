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
		<h1>Invoice Receipt Configuration</h1>
		<div class="btn-group">
			<a id="submitButton" class="btn btn-large tip-bottom" title="Save"><i class="fa fa-save"></i></a>
		</div>
	</div>

	<div id="breadcrumb">
		<a class="current">Invoice Receipt Configuration</a>
	</div>
	
	<!-- ----------------------------------------------------------- ORGANIZATION ADDING FORM ------------------------------------------------------------ -->
	
	<div class="container-fluid" >
		<div class="row">
			<div class="col-xs-12 center">
				
				
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="fa fa-edit"></i></span>
						<h5>Invoice Receipt Configuration</h5>
					</div>
					
					<div class="widget-content nopadding">
						<sf:form class="form-horizontal" modelAttribute="InvoiceReceiptDTO" method="post" name="basic_validate" id="basic_validate" autocomplete="off" enctype="multipart/form-data">
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Upload Logo:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <input type="file" id="upload" name="upload" onchange="readURL(this);" accept="image/*" style="margin-bottom: 7px; margin-top: 5px; width: 200px;height: 22px;"/>
                                </div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Logo Preview:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <img id="imageFrame" style="width:auto; height:auto;" src="" />
                                </div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Address:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="address" type="text" />
                                    <sf:errors path="address" cssClass="help-inline" style="margin-left: 10px; color: #B94A48"/>
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Telephone:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="tel" type="text" />
                                    <sf:errors path="tel" cssClass="help-inline" style="margin-left: 10px; color: #B94A48"/>
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Website:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="website" type="text" />
                                    <sf:errors path="website" cssClass="help-inline" style="margin-left: 10px; color: #B94A48"/>
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Email:</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:input class="form-control input-sm" style="width: 37%" path="email" type="text" />
                                    <sf:errors path="email" cssClass="help-inline" style="margin-left: 10px; color: #B94A48"/>
                                </div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Terms &amp; Conditions:&nbsp;&nbsp;&nbsp;
									<span title="Please ues '\n' as line break, instead of pressing the 'Enter' key on the keyboard." class="label label-info tip-left" style="float:right;">
									<i class="fa fa-info" style="font-size:11.5px; text-shadow:none!important;"></i>
								</label>
                                <div class="col-sm-9 col-md-9 col-lg-10">
                                    <sf:textarea class="form-control input-sm" style="width: 37%; height: 200px;" path="termsAndConditions" type="text" />
                                    <sf:errors path="termsAndConditions" cssClass="help-inline" style="margin-left: 10px; color: #B94A48"/>
                                </div>
							</div>
							
							<sf:hidden path="invoiceId"/>
							<sf:hidden path="logo"/>
							
							<div class="form-actions" style="height: 50px"></div>
							
						</sf:form>
					</div>
					
					
				</div>
			</div>
		</div>
	</div>
	
	<!-- ----------------------------------------------------------- ORGANIZATION ADDING FORM ------------------------------------------------------------ -->
</div>
<script type="text/javascript">
	
	function readURL(input)
	{
		$('#imageFrame').show();
		
		//displays preview
		if (input.files && input.files[0])
		{
		    var reader = new FileReader();
		
		    reader.onload = function(e)
		    {
		    	$('#imageFrame').attr('src', e.target.result);
		        $('#imageFrame').css("width", "310px");
		        $('#imageFrame').css("height", "190px");
		    };
		
		    reader.readAsDataURL(input.files[0]);
	
		    //document.getElementById("download"+index).style.visibility="visible"; 
		}
		else
		{
			$('#imageFrame').attr('src', "");
			$('#imageFrame').css("width", "auto");
	        $('#imageFrame').css("height", "auto");
			//document.getElementById("download"+index).style.visibility="hidden"; 
		}
	}
	
</script>
<script src="../js/custom/invoice_receipt_config.js"></script>

<%@ include file="../include/copyright.jsp"%>
<%@ include file="../home/footer.jsp"%>	
