package controladores.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import modelo.Categoria;
import servicios.ServicioCategorias;

@Controller
@RequestMapping("admin/")
public class ControladorCategorias {

	@Autowired
	private ServicioCategorias servicioCategorias;
	
	@RequestMapping("listarCategorias")
	public String listarCategorias(Model model) {
		model.addAttribute("info",servicioCategorias.obtenerCategorias());
		return "admin/categorias";
	}
	@RequestMapping("registrarCategoria")
	public String registrarCategoria(Model model) {
		Categoria c = new Categoria();
		c.setNombre("40k");
		c.setAlta(true);
		model.addAttribute("nuevaCategoria",c);
		return "admin/formularioRegistrarCategoria";
	}
	@RequestMapping("guardarNuevaCategoria")
	public String guardarNuevaCategoria(@ModelAttribute("nuevaCategoria") @Valid Categoria c,BindingResult br,Model model, HttpServletRequest request) {
		if(!br.hasErrors()) {
			servicioCategorias.crearCategoria(c);
			return "admin/registroMiniaturasOK";
		}else {
			model.addAttribute("nuevaMini",c);
			return "admin/formularioRegistrarCategoria";
		}
	}
	@RequestMapping("darBajaCategoria")
	public String darBajaCategoria(Model model,String id) {
		servicioCategorias.darBajaCategoria(Long.parseLong(id));
		return listarCategorias(model);
	}
	@RequestMapping("darAltaCategoria")
	public String darAltaCategoria(Model model,String id) {
		servicioCategorias.darAltaCategoria(Long.parseLong(id));
		return listarCategorias(model);
	}
	
	
}
