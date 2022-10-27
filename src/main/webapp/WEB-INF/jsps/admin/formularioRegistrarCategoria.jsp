<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>
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
<h2 class="m-4">Nueva categoria</h2>
<div class="col-6 m-5">
<springform:form modelAttribute="nuevaCategoria" action="guardarNuevaCategoria" enctype="multipart/form-data">
	<div>nombre:<br><springform:input  path="nombre" class="form-control"/><span style="color: red;"><springform:errors path="nombre"/></span></div>
	<div>descripcion:<br><springform:input path="descripcion" class="form-control"/> <span style="color: red;"><springform:errors path="descripcion"/></span></div>
<input type="submit" class="m-5 btn btn-secondary" value="Registrar Categoria">
</springform:form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>