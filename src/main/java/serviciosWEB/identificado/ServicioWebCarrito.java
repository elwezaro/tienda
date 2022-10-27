package serviciosWEB.identificado;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import modelo.Usuario;
import servicios.ServicioCarrito;

@Controller
@RequestMapping("identificado/servicioWebCarrito/")
public class ServicioWebCarrito {
	
	@Autowired
	private ServicioCarrito carrito;
	
	@RequestMapping("agregarMiniatura")
	public ResponseEntity<String> agregarMiniatura(String id,String cantidad, HttpServletRequest request){
		String respuesta = "";
		if(request.getSession().getAttribute("usuario")!=null) {
			//esto es que el usuario se identifico
			Usuario u = (Usuario)request.getSession().getAttribute("usuario");
			carrito.agregarProducto(u, Integer.parseInt(id), Integer.parseInt(cantidad));
			respuesta = "producto agregado a tu carrito " + u.getNombre() + 
					" puedes acceder a tu carrito para ver lo que has comprado - por hacer";			
		}else {
			respuesta="tienes que identificarte para poder comprar productos";
		}		
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
	@RequestMapping("obtenerProductosCarrito")
	ResponseEntity<String> obtenerProductosCarrito(HttpServletRequest request){
		String respuesta = "";
		
		respuesta = new Gson().toJson(carrito.obtenerProductosCarrito(
				(Usuario)request.getSession().getAttribute("usuario")));
		
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
		
	}

	@RequestMapping("borrarProductosCarrito")
	ResponseEntity<String> borrarProductosCarrito(String id_producto,HttpServletRequest request){
		String respuesta = "";
		int idProducto_int= Integer.parseInt(id_producto);
		int id_usuario=((Usuario)request.getSession().getAttribute("usuario")).getId();
		respuesta = "ok";
		carrito.borrarProductoCarrito(id_usuario, idProducto_int);
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
	@RequestMapping("actualizarCantidadProductoCarrito")
	public ResponseEntity<String> actualizarCantidadProductoCarrito(String cantidad,String id_producto,HttpServletRequest request){
		String respuesta = "";
		int cantidad_int= Integer.parseInt(cantidad);
		int id_int= Integer.parseInt(id_producto);
		int id_usuario=((Usuario)request.getSession().getAttribute("usuario")).getId();
		respuesta = "actualizar sobre el carrito del usuario de is "+id_usuario+" el producto de id "+id_producto+" la cantidad "+cantidad_int ;
		carrito.actualizarProductoCarrito(id_usuario, id_int, cantidad_int);
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
}
