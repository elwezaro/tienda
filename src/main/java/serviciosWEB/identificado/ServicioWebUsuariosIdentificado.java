package serviciosWEB.identificado;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import modelo.Usuario;
import servicios.servicioUsuarios;


@Controller
@RequestMapping("identificado/servicioWebUsuarios/")
public class ServicioWebUsuariosIdentificado {
	
	@Autowired
	servicioUsuarios servicioUsuarios;


	@RequestMapping("mostrarDatosUsuario")
	public ResponseEntity<String> registrarUsuario(HttpServletRequest request){
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		Usuario usuario = new Usuario();
		usuario.setNombre(u.getNombre());
		usuario.setEmail(u.getEmail());
		usuario.setPass(u.getPass());
		String json = new Gson().toJson(usuario);
	
		return new ResponseEntity<String>(
				json,HttpStatus.OK);
	}
	
}
