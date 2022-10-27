

function cargar_plantillas_del_servidor(idioma){
	
	var carpetaPlantillas = "plantillas_mustache";
	if(idioma == "en"){
		carpetaPlantillas = "plantillas_mustache_en";
	}
	$.get(carpetaPlantillas+"/listado_miniaturas.html",function(data){	
		plantillaListarMiniaturas = data;
	});
	$.get(carpetaPlantillas+"/carrito.html",function(data){
		plantillaCarrito = data;
	});
	$.get(carpetaPlantillas+"/registrar_usuario.html",function(data){
		plantillaRegistrarUsuario = data;
	});
	$.get(carpetaPlantillas+"/identificar_usuario.html",function(data){
		plantillaIdentificarUsuario = data;
	});
	$.get(carpetaPlantillas+"/detalles_miniatura.html",function(data){
		plantillaDetallesMiniatura = data;
	});
	$.get(carpetaPlantillas+"/checkout_1.html",function(data){
		checkout_1 = data;
	});
	$.get(carpetaPlantillas+"/checkout_2.html",function(data){
		checkout_2 = data;
	});
	$.get(carpetaPlantillas+"/checkout_3.html",function(data){
		checkout_3 = data;
	});
	$.get(carpetaPlantillas+"/pedidos_usuario.html",function(data){
		plantillaPedidos = data;
	});
		$.get(carpetaPlantillas+"/detalles_usuario.html",function(data){
		plantillaDetallesUsuario = data;
	});
}	