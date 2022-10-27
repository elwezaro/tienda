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
<div>introduce la contraseña de acceso:</div>

<form method="post" action="identificarAdmin">
contraseña:<input type="password" name="pass"/><br>
<input type="submit" value="Entrar">

</form>
</body>
</html>