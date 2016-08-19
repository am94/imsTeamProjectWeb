<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">

</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clients!</title>
<!-- BootStrap -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.css">
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
	<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.js"></script>
	<script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
	
	<!-- jQuery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	
	<!-- CSS Google Text -->
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,900" rel="stylesheet" type="text/css">
	
	<!-- Custom CSS -->
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/index.css">
</head>
<body>
	<div class="container main .col-xs-12 .col-sm-6 .col-lg-8">

	<div class="container header .col-xs-12 .col-sm-6 .col-lg-8">
		<jsp:include page="/headerPage/header.jsp"></jsp:include>
	</div>
	
	<div class="container nav .col-xs-12 .col-sm-6 .col-lg-8">
		<jsp:include page="/navPage/nav.jsp"></jsp:include>
	</div>
	<br />
	
	<div class="container body .col-xs-12 .col-sm-6 .col-lg-8">

	<div>
		<table id="invoiceTable">
			<tr>
		    <th>ID</th>
		    <th>Price</th>
		    <th>Product</th>
		    <th>Quantity</th>
		    <th>Client</th>
		    <th></th>
		  </tr>
		  <c:forEach var="i" items="${invoices}">
		  	<tr onclick=""> <!-- WILL INSERT JS TO SHOW MORE DETAILS IN POP-UP -->
		  		<td><c:out value="${i.invoiceCK}"></c:out></td>
		  		<td><c:out value="${i.price}"></c:out></td>
		  		<td><c:out value="${i.prodId.name}, ${i.prodId.cost}"></c:out></td>
		  		<td><c:out value="${i.quantity}"></c:out></td>
		  		<td><c:out value="THIS NEEDS STUFF"></c:out></td> <!-- NEED AJAX CALL TO GET CLIENT -->
		  		<td><input type="button" value="Edit!"></td> <!-- Need to make this...Ajax-y... -->
		  	</tr>
		  </c:forEach>
		  <tr id="blanktr" class="blanktr"></tr>
		  <tr>
		  <td></td>
		  <td></td>
		  	 <td><input type="text" name="name" placeholder="Product!"/></td>  <!-- THESE ALL NEED WIDTH: 100% IN CSS -->
		  	 <td><input type="text" name="name" placeholder="Quantity!"/></td>
		  	 <td><input type="text" name="name" placeholder="Client!"/></td>
		  	 <td><input type="button" value="Add!">
		  </tr>
		</table>
	</div>
	</div>
	<br />
	
		<div class="container footer .col-xs-12 .col-sm-6 .col-lg-8">
		<jsp:include page="/footerPage/footer.jsp"></jsp:include>
	</div>
	<br />
	</div>
</body>
</html>