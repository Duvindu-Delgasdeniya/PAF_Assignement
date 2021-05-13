<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%@page import = "com.Product" %>



<!DOCTYPE html>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Item handling</title>


<link rel = "stylesheet" href = "Views/bootstrap.min.css">
<script src = "Components/jquery-3.6.0.min.js"></script>
<script src = "Components/Product.js"></script>



</head>
<body>

<div class = "container"> 
	<div class="row">
		<div class="col">

		<h1>Items Management</h1>
		
	<form id="formItem" name="formItem"  >
		reviewID :
		<input id="reviewID" name="reviewID" type="text" class="form-control form-control-sm"><br>

		 Item description:
		<input id="itemDesc" name="itemDesc" type="text" class="form-control form-control-sm"><br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
    
    <div id="alertSuccess" class="alert alert-success"></div>
     <div id="alertError" class="alert alert-danger"></div>
    
    <br>
	<div id="divItemsGrid">
	<%
	Product itemObj = new Product();
	out.print(itemObj.readProduct());
	%>
	</div>

<br>


</div>
</div>
</div>


</body>
</html>