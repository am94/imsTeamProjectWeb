<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clients!</title>
</head>
<body>
	<div>
		<table id="invoiceTable">
			<tr>
		    <th>ID</th>
		    <th>Name</th>
		    <th>Email</th>
		    <th>Address</th>
		    <th>Type</th>
		    <th></th>
		  </tr>
		  <c:forEach var="c" items="${clients}">
		  	<tr>
		  		<td><c:out value="${c.id}"></c:out></td>
		  		<td><c:out value="${c.name}"></c:out></td>
		  		<td><c:out value="${c.email}"></c:out></td>
		  		<td><c:out value="${c.address.line1}, ${c.address.line2}, ${c.address.city}, ${c.address.state} ${c.address.zip}"></c:out></td>
		  		<td>
		  			<c:if test="${c.type.id} == 0"><c:out value="Buyer"></c:out></c:if>
		  			<c:if test="${c.type.id} == 1"><c:out value="Supplier"></c:out></c:if>
		  		</td>
		  		<td><input type="button" value="Edit!"></td> <!-- Need to make this...Ajax-y... -->
		  	</tr>
		  </c:forEach>
		  <tr id="blanktr" class="blanktr"></tr>
		  <tr>
		  <td></td>
		  	 <td><input type="text" name="name" placeholder="Name!"/></td>
		  	 <td><input type="text" name="name" placeholder="Email!"/></td>
		  	 <td><input type="text" name="name" placeholder="Address!"/></td>
		  	 <td><input type="text" name="name" placeholder="Type!"/></td>
		  </tr>
		</table>
	</div>
</body>
</html>