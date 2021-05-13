<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
     <%@page import = "com.Review" %>
      
     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review handling</title>


<link rel = "stylesheet" href = "Views/bootstrap.min.css">
<script src = "Components/jquery-3.6.0.min.js"></script>
<script src = "Components/Review.js"></script>



</head>
<body>

<div class = "container"> 
	<div class="row">
		<div class="col">

		<h1>Review Management</h1>
		
	<form id="formItem" name="formItem"  >
		project Code :
		<input id="projectCode" name="projectCode" type="text" class="form-control form-control-sm"><br>
		 Review :
		<input id="review" name="review" type="text" class="form-control form-control-sm"><br> 
		Decision :
		<input id="decision" name="decision" type="text" class="form-control form-control-sm"><br>
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="reviewID" name="reviewID" value="">
	</form>
    
    <div id="alertSuccess" class="alert alert-success"></div>
     <div id="alertError" class="alert alert-danger"></div>
    
    <br>
	<div id="divItemsGrid">
	<%
	Review ReviewObj = new Review();
	out.print(ReviewObj.readreview());
	%>
	</div>

<br>


</div>
</div>
</div>


</body>
</html>