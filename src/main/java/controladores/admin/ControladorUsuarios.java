package controladores.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import servicios.servicioUsuarios;

@Controller
@RequestMapping("/admin")
public class ControladorUsuarios {
	
	@Autowired
	private servicioUsuarios servicioUsuarios;
	
	@RequestMapping("listarUsuarios")
	public String listarUsuarios(Model model) {
		model.addAttribute("info",servicioUsuarios.obtenerUsuarios());
		return "admin/usuarios";
	}
}
