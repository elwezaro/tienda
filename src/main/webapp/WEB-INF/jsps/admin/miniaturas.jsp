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
<a href="registrarMiniatura" class="m-5">nueva Miniatura</a><br>
<div class="row justify-content-center m-5">
<h2 >listado de Miniaturas de la pagina</h2>
</div>

<br>
<div style="text-align: center;">
<div class="row justify-content-center">


<form class="col-3" action="listarMiniaturas">
	<div class="d-flex">
		<input type="text" class="form-control" name="nombre" value="${nombre}"/>
		<input class="btn btn-secondary" type="submit" value="buscar">
	</div>
</form>
</div>
	total miniaturas: ${total}<br> 
	<c:if test="${anterior>=0}">
		<a href="listarMiniaturas?comienzo=${anterior}&nombre=${nombre}">anterior</a>
	</c:if>
		&nbsp;	
	<c:if test="${siguiente<total}">
	<a href="listarMiniaturas?comienzo=${siguiente}&nombre=${nombre}">siguiente</a>	
	</c:if>
</div>

<div class="d-flex align-content-center flex-wrap">
<c:forEach var="mini" items="${info}">

<!-- 	<div class="row justify-content-center m-5"> -->
	<div class="card col-4 m-5" >
 		<img src="../subidas/${mini.id}.jpg?t=${fecha_hora_actual}">
  		<div class="card-body">
    	<h5 class="card-title"> ${mini.nombre}</h5>
    	<p class="card-text">precio: ${mini.precio}</p>
  	</div>
  	<ul class="list-group list-group-flush">
    	<li class="list-group-item">formato:${mini.formato}</li>
    	<li class="list-group-item">marca: ${mini.marca}</li>
    	<li class="list-group-item">material: ${mini.material}</li>
    	<li class="list-group-item">categoria: ${mini.categoria.nombre}</li>
    	<li class="list-group-item">tipo unidad: ${mini.tipoUnidad}</li>
    	<li class="list-group-item">faccion: ${mini.faccion}</li>
  	</ul>
	 <div class="card-body">
    	<c:if test="${mini.alta==true}">
			<a class="card-link" href="darBajaMiniatura?id=${mini.id}" onclick="return confirm('¿estas seguro?')">dar de baja</a>
		</c:if>
		<c:if test="${mini.alta==false}">
			<a class="card-link" href="darAltaMiniatura?id=${mini.id}" onclick="return confirm('¿estas seguro?')">dar de alta</a>
		</c:if>
  
    	
    	<a class="card-link" href="editarMiniatura?id=${mini.id}">editar</a>
 	 </div>
</div>
<!-- </div> -->
</c:forEach>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>