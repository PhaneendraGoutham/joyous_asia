<%@ page pageEncoding="UTF-8"%>
<%@ include file="../include/import.jsp"%>
<%@ include file="../home/header.jsp"%>
<jsp:include page="../home/leftnav.jsp" />

<!-- Attributes List -->
		
<div id="SPRING_EXT" style="display: none">${ SPRING_EXT }</div>
<input id="errmsg" style="display: none" value='${ errmsg }'/>

<!-- Attributes List -->

<div id="WEBAPPS" style="display: none;">${ WEBAPPS }</div>
<div id="content">
	<div id="content-header">
		<h1>CSV Upload</h1>
	</div>
	
	<div id="breadcrumb">
		<a class="current">
			CSV Upload
		</a>
	</div>
	
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 center">
				<div class="widget-box">
					<div class="widget-title">
			
						<span class="icon"> <i class="fa fa-upload"></i></span>
						<h5>CSV Upload</h5>
					</div>

					<form id="basic_validate" class="form-horizontal" method="post" action="upload${ SPRING_EXT }" enctype="multipart/form-data">
							<!-- name="basic_validate" id="basic_validate" novalidate="novalidate" modelAttribute="batch" --> 
							
						<div class="widget-content nopadding">
							<div class="form-horizontal">
								
								<div class="form-group" id="content-upload">
									<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666"><span style="color:red;">*</span>&nbsp;Choose File:</label>
									<div class="col-sm-5 col-md-5 col-lg-3">
										<!-- <input type="file" name="upload" onchange="ValidateSingleInput(this);"> -->
										<input id="upload_file" type="file" name="upload">
	                               	</div>
	                               	<div class="col-sm-4 col-md-4 col-lg-7" id="uploadFileError" style="margin-left:0px"></div>
								</div>
								
								<div class="form-group" id="content_feedback" style="display:none">
									<label class="col-sm-3 col-md-3 col-lg-2 control-label" style=" font-weight: bold;  font-size: 12px; color:#666666">Feedback:</label>
									<div class="col-sm-9 col-md-9 col-lg-10" id="feedback" style=" font-weight: bold;  font-size: 12px; color:#FF0000; margin-top: 5px"></div>
								</div>
							
								<div class="form-actions">
									<input id="submitButton" type="submit" value='Upload' class="btn btn-primary">
								</div>
								
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
			
<script src="${ WEBAPPS }/js/custom/csv_upload.js"></script>				
<%@ include file="../include/copyright.jsp"%>
<%@ include file="../home/footer.jsp"%>
