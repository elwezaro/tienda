package serviciosWEB;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import modelo.Usuario;
import servicios.servicioUsuarios;
import utilidadesArchivos.GestorArchivos;

@Controller
@RequestMapping("servicioWebUsuarios/")
public class ServicioWebUsuarios {
	
	@Autowired
	servicioUsuarios servicioUsuarios;
	
	@RequestMapping("registrarUsuario")
	public ResponseEntity<String> registrarUsuario(@RequestParam Map<String, Object> formData
			,@RequestParam("foto") CommonsMultipartFile foto,
			HttpServletRequest request){
		String respuesta = "";
		Gson gson = new Gson();
		JsonElement json = gson.toJsonTree(formData);
		Usuario u = gson.fromJson(json, Usuario.class);
		System.out.println("usuario a rigistrar: "+u);
		servicioUsuarios.registrarUsuario(u);
	
		String rutaRealDelProyecto = request.getServletContext().getRealPath("");
		GestorArchivos.guardarFotoUsuario(u, rutaRealDelProyecto, foto);
		respuesta="ok";
		
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
	@RequestMapping("identificarUsuario")
	public ResponseEntity<String> identificarUsuario(String email, String pass, HttpServletRequest request){
		Usuario u = servicioUsuarios.obtenerUsuarioPorEmailYpass(email, pass);
		String respuesta = "";
		if(u != null) {
			request.getSession().setAttribute("usuario", u);
			respuesta = "ok,"+((Usuario)request.getSession().getAttribute("usuario")).getNombre();
		}else {
			respuesta = "email o pass incorrectos";
		}
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
	@RequestMapping("comprobarIdentificacion")
	public ResponseEntity<String> identificarUsuario( HttpServletRequest request){
		String respuesta="";
		if(request.getSession().getAttribute("usuario")!=null) {
			respuesta="ok,"+((Usuario)request.getSession().getAttribute("usuario")).getNombre();
		}else {
			respuesta=	"no identificado";
		}
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
	
	@RequestMapping("logout")
	public ResponseEntity<String> logout( HttpServletRequest request){ 
		String respuesta="";
		request.getSession().invalidate();
		respuesta="ok";
		return new ResponseEntity<String>(
				respuesta,HttpStatus.OK);
	}
	
}
