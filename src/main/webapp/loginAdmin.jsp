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
<div class="row col-6 m-5">
	<form method="post" action="admin/">
		<div class="mb-3">
 			 <label for="pass" class="form-label">introduce pass de administrador:</label>
 			 <input type="password" class="form-control" value="${sessionScope.campo_pass}" id="pass" name="pass">
 			 <input type="checkbox" checked="checked" name="recordar_pass"/> recordar pass<br>
		</div>
		<button  type="submit" value="Identificarme"  class="btn btn-primary mb-3"> entrar</button>
	
	
	</form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>