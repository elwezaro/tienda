<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">


</head>
<body>
<jsp:include page="cabecera.jsp"></jsp:include>

<c:forEach var="pedido" items="${info}">
	<div style="margin: 20px">
		nombre completo: ${pedido.nombreCompleto}<br>
		direccion: ${pedido.direccion}<br>
		provincia: ${pedido.provincia}<br>
		<a href="editarPedido?id=${pedido.id}">editar</a>
	</div>
</c:forEach>
</body>
</html>