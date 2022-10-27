package serviciosWEB.identificado;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import datos.servicios.ResumenPedido;
import modelo.Usuario;
import servicios.ServicioPedidos;

@Controller
@RequestMapping("identificado/servicioWebPedidos/")
public class ServicioWebPedidos {
	
	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("paso1")
	public ResponseEntity<String> paso1(String nombre, String direccion, String provincia,String codigoPostal,String pais, 
			HttpServletRequest request){
		
		String respuesta = "";
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		System.out.println("provincia: " + provincia);
		servicioPedidos.procesarPaso1(nombre, direccion, provincia,codigoPostal,pais, u.getId());
		respuesta = "ok";
		
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
	
	@RequestMapping("paso2")
	public ResponseEntity<String> paso2 (String tarjeta, String numero, String titular,String cvp, 
			HttpServletRequest request){
		String respuesta = "";
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso2(titular, numero, tarjeta,cvp, u.getId());
		ResumenPedido resumen =servicioPedidos.obtenerResumenPedido(u.getId());
		String json= new Gson().toJson(resumen);
		respuesta = "ok:"+json;
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);		
	}
	@RequestMapping("paso3")
	public ResponseEntity<String> paso3 ( 
			HttpServletRequest request){
		String respuesta = "";
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.confirmarPedido(u.getId());
		respuesta = "pedido ok";
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);		
	}
	@RequestMapping("pedidosUsusario")
	public ResponseEntity<String> PedidosUsuario ( 
			HttpServletRequest request){
		String respuesta = "";
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		respuesta = new Gson().toJson(servicioPedidos.obtenerPedidosUsuario(u.getId()));
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);		
	}
}
