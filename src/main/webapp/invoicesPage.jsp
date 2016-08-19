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
</head>
<body>
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
</body>
</html>