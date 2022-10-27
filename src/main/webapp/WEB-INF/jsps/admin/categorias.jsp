<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body>

<jsp:include page="cabecera.jsp"></jsp:include>
<a href="registrarCategoria">nueva categoria</a><br>

Listado de categorias de la tienda:<br>
<div class="row justify-content-center">
	<div class="col-6">
		<table class="table">
			<thead>
				<tr>
					<th>nombre</th>
					<th>descripcion</th>
					<th>descripcion</th>
				</tr>		
			</thead>
		<c:forEach var="categoria" items="${info}">
	
		<tbody>
			<tr>
			 <th>${categoria.nombre}</th>
			 <th>${categoria.descripcion}</th>
			 <th>
				<c:if test="${categoria.alta==true}">
					<a href="darBajaCategoria?id=${categoria.id}" onclick="return confirm('¿estas seguro?')">dar de baja</a>
				</c:if>
				<c:if test="${categoria.alta==false}">
					<a  href="darAltaCategoria?id=${categoria.id}" onclick="return confirm('¿estas seguro?')">dar de alta</a>
				</c:if>
			 </th>
			</tr>
		</tbody>
		</c:forEach>
		</table>
	</div>
</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>