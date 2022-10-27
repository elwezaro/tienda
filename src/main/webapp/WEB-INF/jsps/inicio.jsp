<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>


<nav class="flex-wrap flex-md-nowrap" style="background-color:#777 ">
	<div class="navbar navbar-dark">
	 <div class="navbar-brand p-0 me-2 " href="#">TraderMania</div>
    <ul class="navbar-nav flex-row flex-wrap bd-navbar-nav pt-2">
	<li class="nav-item col-auto"><a href="#" class="nav-link p-2" id="enlace_listado"><spring:message code="inicio.miniaturas"/></a></li>
	<li class="nav-item col-auto"><a href="#" class="nav-link p-2" id="enlace_carrito"><spring:message code="inicio.carrito"/></a></li>
	<li class="nav-item col-auto"><a href="#" class="nav-link p-2" id="enlace_registrarme"><spring:message code="inicio.registrarme"/></a></li>
	<li class="nav-item col-auto"><a href="#" class="nav-link p-2" id="enlace_identificarme"><spring:message code="inicio.identificarme"/></a></li>
	<li class="nav-item col-auto"><a href="#" class="nav-link p-2" id="enlace_logout"><spring:message code="inicio.cerrar"/></a></li>
	</ul>
	<ul class="navbar-nav flex-row flex-wrap bd-navbar-nav pt-2">
	<li class="nav-item col-auto"><a href="?lang=es" class="nav-link p-2" id="enlace_listado">español</a></li>
	<li class="nav-item col-auto"><a href="?lang=en" class="nav-link p-2" id="enlace_carrito">ingles</a></li>
	</ul>
  </div>
</nav>



<div id="mensaje_login"></div>
<div><a href="#" id="enlace_mis_pedidos" class="m-2"></a><a href="#" id="enlace_mis_datos" class="m-2"></a></div>

<div style="text-align: center;"><spring:message code="inicio.bienvenida"/></div>
<div id="contenedor"> </div>

<script type="text/javascript" src="librerias_javascript/jquery.js" ></script>
<script type="text/javascript" src="librerias_javascript/mustache.js" ></script>
<script type="text/javascript" src="js/validaciones.js" ></script>
<script type="text/javascript" src="js/cargar_plantillas.js" ></script>
<script type="text/javascript" src="librerias_javascript/js.cookie.min.js"></script>


<script type="text/javascript">

//voy a crear una variable que indica el nombre del usuario
const idioma = '<spring:message code="codigo.idioma"/>';
var nombre_login="";
//parte de carga de las plantillas en variables:
var plantillaListarMiniaturas = "";
var plantillaCarrito = "";
var plantillaRegistrarUsuario = "";
var plantillaIdentificarUsuario = "";
var plantillaDetallesMiniatura = "";
var checkout_1 = "";
var checkout_2 = "";
var checkout_3 = "";
var plantillaPedidos = "";
var plantillaDetallesUsuario="";
cargar_plantillas_del_servidor(idioma);

//funciones ajax
function obtener_listado(){
	
	//usando jquery podemos hacerlo usando la funcion $.ajax
	$.ajax("servicioWebMiniaturas/obtenerMiniaturas",
			{
			//este es un objeto que configura la llamada por ajax a ServicioLibros
				success : function(data){
					//esto volcaria directamente el json recibido
					//$("#contenedor").html(data);
				
					var productos = JSON.parse(data);
					for(i in productos){
						productos[i].fecha_hora_actual = new Date();
						productos[i].precio = productos[i].precio.toString().replace(".",",");
					}
					var texto_html = "";
					//vamos a usar una plantilla de html con mustache
					texto_html = Mustache.render(plantillaListarMiniaturas,productos);
					$("#contenedor").html(texto_html);
					
					$(".enlace_detalles").click(function(e){
						var id= $(this).attr("id_producto");
						alert("pedir al servidor todos los detalles del producto de id:"+id+" para mostrarlo al usuario");
								
						$.ajax("servicioWebMiniaturas/obtenerDetallesMiniatura?id="+id,{
							
							success:function(res){
								alert("recibido del server: "+ res);	
								var objeto_miniatura_recibido=JSON.parse(res);
								var texto_html =  Mustache.render(plantillaDetallesMiniatura,objeto_miniatura_recibido);
								$("#contenedor").html(texto_html);
								$("#enlace_comprar").click(
									function(e){
										comprar_producto($(this).attr("id_producto"));
									}		
								);
							}
							
						});
						
					});//end enlace_detalles
					
					
				}//end success
			}
	);//end ajax
}//end function



function comprar_producto(id){
		var cantidad = $("#campo_cantidad").val();
		$.ajax("identificado/servicioWebCarrito/agregarMiniatura?id="+id+"&cantidad="+cantidad,{
			success:function(res){
				alert(res);
			}
			
		});//end ajax
}//end function

function obtenerProductosCarrito(){
	$.ajax("identificado/servicioWebCarrito/obtenerProductosCarrito",{
		success:function(res){
			if(res=="te has colado."){
				alert("tienes que identificarte para acceder a tu carrito");
				
				
			}else{
				var objeto_carrito_recibido=JSON.parse(res);
				var texto_html =  Mustache.render(plantillaCarrito,objeto_carrito_recibido);
				$("#contenedor").html(texto_html);
				$(".enlace_borrar_producto").click(function(){
					var id = $(this).attr("id_producto");
					alert("indicar al servicio web que quite del carrito el producto de "+
							"id: "+id);
					$.post("identificado/servicioWebCarrito/borrarProductosCarrito",{
						id_producto:id
					}).done(function(res){
						if(res!="ok"){
							alert(res);
							}else{
								obtenerProductosCarrito();
							}
					}); 
				});
				$(".input_cantidad").change(function(){
					var cantidad = $(this).val();
					var id = $(this).attr("id_producto");

					$.post("identificado/servicioWebCarrito/actualizarCantidadProductoCarrito",{
						cantidad:cantidad,
						id_producto:id
					}).done(function(res){
						alert(res);
					}); 
				});
				$("#realizar_pedido").click(function(){
					if(objeto_carrito_recibido!=null){
						checkout_paso_0();
					}else{
						alert("carrito vacio")
					}
					
				});
				
			}
		}
	});
}
function checkout_paso_0(){
	$("#contenedor").html(checkout_1);	
	$("#aceptar_paso_1").click(function(){
		checkout_paso_1_aceptar();
	});
}

function checkout_paso_1_aceptar(){
	//recoger los valores introducidos y mandarlos al servidor
	var nombre = $("#campo_nombre").val();
	var direccion = $("#campo_direccion").val();
	var codigoPostal = $("#campo_codigoPostal").val();
	var pais = $("#campo_pais").val();
	var provincia = $("#campo_provincia").find(":selected").val();
	var validacion =false;
	//ahora lo suyo seria validar los valores recogidos
	
	
	//mandar los valores al servicio web 
	$.post("identificado/servicioWebPedidos/paso1",
			{
				nombre : nombre ,
				direccion : direccion ,
				provincia :	provincia ,
				codigoPostal : codigoPostal ,
				pais : pais
			}
	).done(function(res){
		if(res=="ok"){
			//mostrar checkout_2.html
			$("#contenedor").html(checkout_2);
			$("#checkout2_aceptar").click(checkout_paso_2_aceptar);
		}else{
			alert(res);
		}
	});
}//end checkout_paso_1_aceptar
function checkout_paso_2_aceptar(){
	var tipo_tarjeta = $("#tipo_tarjeta").find(":selected").val();
	var numero_tarjeta = $("#numero_tarjeta").val();
	var cvp = $("#tarjeta_cvp").val();
	var titular_tarjeta = $("#titular_tarjeta").val();
	$.post("identificado/servicioWebPedidos/paso2",{
		tarjeta : tipo_tarjeta ,
		numero : numero_tarjeta ,
		titular : titular_tarjeta ,
		cvp : cvp
	}).done(function(res){
		if(res.substring(0,2)=="ok"){
				//mostrar el paso 3
				var json =JSON.parse(res.substring(3,res.length));
				var html = Mustache.render(checkout_3,json);
				$("#contenedor").html(html);
				$("#boton_confirmar_pedido").click(checkout_paso_3_confirmar);
		}
	});
}//end checkout_paso_2_aceptar
function checkout_paso_3_confirmar(){
	$.ajax("identificado/servicioWebPedidos/paso3",{
		
		success:function(res){
		alert("respuesta del servicio web:"+res);
		obtener_listado();
		}
		
	});
	
}
//registro usuario
function mostrarRegistroUsuario(){
	$("#contenedor").html(plantillaRegistrarUsuario);
	$("#form_registro_usuario").submit(function(e){
		var nombre = $("#nombre").val();
		var email = $("#email").val();
		var pass = $("#pass").val();
		if( validarNombre(nombre) && validarEmail(email) && 
			validarPass(pass)){
			//vamos a usar FormData para mandar el form al servicio web
			var formulario = document.forms[0];
			var formData = new FormData(formulario);
			$.ajax("servicioWebUsuarios/registrarUsuario",{
				type: "POST",
				data: formData,
				cache: false,
				contentType: false,
				processData: false,
				success: function(res){
					if(res == "ok"){
						alert("registrado correctamente, ya puedes identificarte");
						mostrarIdentificacionUsuario();
					}else{
						alert(res);
					}
				}
			});
				
		}//end if validaciones
		e.preventDefault();
	});
}


//identidicar usuario

var email = "";
var pass = "";
function mostrarIdentificacionUsuario(){
	$("#contenedor").html(plantillaIdentificarUsuario);
	
	if( typeof(Cookies.get("email")) != "undefined" ){
		$("#email").val(Cookies.get("email"));
	}
	if( typeof(Cookies.get("pass")) != "undefined" ){
		$("#pass").val(Cookies.get("pass"));
	}
	
	var email = $("#email").val();
	var pass = $("#pass").val();
	$("#form_login").submit(function(e){
		email = $("#email").val();
		pass = $("#pass").val();
		
		$.ajax("servicioWebUsuarios/identificarUsuario",{
			data: "email="+email+"&pass="+pass,
			success: function(res){
				if (res.split(",")[0] == "ok"){
					nombre_login=res.split(",")[1];
					alert("identificado correctamente, ya puedes comprar productos "+nombre_login);
					
					if( $("#recordar_datos").prop('checked') ){
						alert("guardar datos en cookie");
						Cookies.set('email', email, { expires: 365 });
						Cookies.set('pass', pass, { expires: 365 });	
					}
					
					$("#mensaje_login").html("identificado como:"+ nombre_login);
					$("#contenedor").html("login ok");
					$("#enlace_mis_pedidos").html("mis pedidos");
					$("#enlace_mis_datos").html("mis datos");
				}else{
					$("#mensaje_login").html("no estas identificado");
				}
			}			
		});
		e.preventDefault();
	});
	
}


function logout(){
	$.ajax("servicioWebUsuarios/logout",{
		success:function(res){
			if(res=="ok"){
				$("#contenedor").html("hasta pronto: "+nombre_login);
				$("#mensaje_login").html("no estas identificado");
				$("#enlace_mis_pedidos").html("");
				$("#enlace_mis_datos").html("");
			}
		}
	});
}
$.ajax("servicioWebUsuarios/comprobarIdentificacion",{
	success:function(res){
		if (res.split(",")[0] == "ok"){
			nombre_login=res.split(",")[1];
			$("#mensaje_login").html("identificado como:"+ nombre_login);
			$("#enlace_mis_pedidos").html("mis pedidos");
			$("#enlace_mis_datos").html("mis datos");
		}else{
			alert(res);
		}
		
	}
});


function mostrarPedidosUsuario() {
	$.ajax("identificado/servicioWebPedidos/pedidosUsusario",{
		success:function(res){
			alert(res);
			var pedidosUsuario=JSON.parse(res);
			var texto_html =  Mustache.render(plantillaPedidos,pedidosUsuario);
			$("#contenedor").html(texto_html);
		}
	});
}
function mostrarDatosUsuario() {
	$.ajax("identificado/servicioWebUsuarios/mostrarDatosUsuario",{
		success:function(res){
			var objeto_datos_usuario=JSON.parse(res);
			var texto_html =  Mustache.render(plantillaDetallesUsuario,objeto_datos_usuario);
			$("#contenedor").html(texto_html);
		
		}
		
	});
}
//eventos de los enlaces:
$("#enlace_listado").click(obtener_listado);
$("#enlace_carrito").click(obtenerProductosCarrito);
$("#enlace_registrarme").click(mostrarRegistroUsuario);
$("#enlace_identificarme").click(mostrarIdentificacionUsuario);
$("#enlace_logout").click(logout);
$("#enlace_mis_pedidos").click(mostrarPedidosUsuario);
$("#enlace_mis_datos").click(mostrarDatosUsuario);
//la siguiente linea sera eliminada en breve:
$("#contenedor").html("hola desde jquery");

//interaccion visual:


</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>