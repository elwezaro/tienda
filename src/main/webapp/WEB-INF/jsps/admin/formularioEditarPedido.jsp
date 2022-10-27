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
<div>Actualiza los datos del pedido:</div>


direccion:${pedido.direccion}<br>

nombreCompleto: ${pedido.nombreCompleto}<br>
provincia:  ${pedido.provincia}<br>
numeroTarjeta: ${pedido.numeroTarjeta}<br>
tipoTarjeta: ${pedido.tipoTarjeta}<br>
titularTarjeta: ${pedido.titularTarjeta}<br>

<div style="text-align: center;">productos del pedido:</div>
<c:forEach var="productoPedido" items="${pedido.productosPedido}">
	<div style="margin: 20px">
		titulo: ${productoPedido.miniatura.nombre}<br>
		precio/unidad: ${productoPedido.miniatura.precio} <br>
		cantidad: ${productoPedido.cantidad}<br>
	</div>
</c:forEach>

<select id="select_estado">
	<c:forEach var="estado" items="${estados}">
		<option 
			<c:if test="${ estado.key == pedido.estado }">
				 selected="selected"
			</c:if>
		  value="${estado.key}">${estado.value}</option>
	</c:forEach>	
</select>


<input type="hidden" id="id_pedido" value="${pedido.id}"/>



<script type="text/javascript" src="../librerias_javascript/jquery.js" ></script>
<script type="text/javascript">
$("#select_estado").change(function(e){
	//obtener el estado seleccionado y mandarlo a un servicio web
	var estado = $("#select_estado").find(":selected").val();
	var idPedido = $("#id_pedido").val();
	$.post("servicioWebPedidos/actualizarEstadoPedido",
		{
			id : idPedido,
			estado: estado
		}
	).done(function(res){
			if(res != "ok"){
				alert(res);
			}				
	});//end done
	
});



</script>


</body>
</html>