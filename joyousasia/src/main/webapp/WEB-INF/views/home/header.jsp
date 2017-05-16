<!DOCTYPE html>
<html lang="en">
<head>
<link rel="icon" type="image/png" href="${ WEBAPPS }/img/favicon.ico">

<%@ include file="../include/style.jsp"%>
<%@ include file="../include/script.jsp"%>

<style type="text/css">

.hide_column{
    display : none;
}
.table>thead>tr>th, .table>tbody>tr>th, .table>tfoot>tr>th, .table>thead>tr>td, .table>tbody>tr>td, .table>tfoot>tr>td {
    vertical-align: middle;
  }
</style>

</head>

<body data-color="grey" class="flat">
	<div id="wrapper" style="min-height: 1068px;">
		<div id="header">
			<h1>
				<a href="#">Joyous Asia CMS</a>
			</h1>
			<a id="menu-trigger" href="#"><i class="fa fa-bars"></i></a>
		</div>
		<div id="user-nav">
		</div>
		
		<div id="WEBAPPS" style="display: none;">${ WEBAPPS }</div>
