<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springform" %>
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
<springform:form modelAttribute="miniatura" action="guardarCambiosMiniatura" enctype="multipart/form-data">
nombre:<springform:input path="nombre"/> <br>
faccion:<springform:input path="faccion"/> <br>
marca:<springform:input path="marca"/> <br>
formato:<springform:input path="formato"/> <br>
material:<springform:input path="material"/> <br>
precio:<springform:input path="precio"/> <br>
tipo Unidad:<springform:input path="tipoUnidad"/> <br>
portada: <img src="../subidas/${miniatura.id}.jpg" style="height: 120px"/><br>
categoria<springform:select path="idCategoria">
			<springform:options items="${categorias}"/>
	</springform:select><br>
alta: <springform:checkbox path="alta"/> <br/>
<springform:input path="imagen" type="file" /> <br>
<springform:hidden path="id"/>
<input type="submit" value="Registrar mini">
</springform:form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</body>
</html>