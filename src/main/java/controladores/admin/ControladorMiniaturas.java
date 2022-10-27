package controladores.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import modelo.Categoria;
import modelo.Miniatura;
import servicios.ServicioCategorias;
import servicios.ServicioMiniaturas;
import utilidadesArchivos.GestorArchivos;

@Controller
@RequestMapping("/admin")
public class ControladorMiniaturas {

	@Autowired
	private ServicioMiniaturas servicioMiniaturas;
	
	@Autowired
	private ServicioCategorias servicioCategorias;
	
	@RequestMapping("listarMiniaturas")
	public String listarMiniaturas(@RequestParam(defaultValue = "") String nombre,Integer comienzo,Model model) {
		int comienzo_int=0;
		if(comienzo != null) {
			comienzo_int=comienzo.intValue();
		}		
		model.addAttribute("info",servicioMiniaturas.obtenerMiniaturas(nombre,comienzo_int));
		model.addAttribute("fecha_hora_actual",new Date().getTime());
		model.addAttribute("siguiente",comienzo_int+10);
		model.addAttribute("anterior",comienzo_int-10);
		model.addAttribute("total", servicioMiniaturas.obtenerTotalMiniaturas(nombre));
		model.addAttribute("nombre",nombre);
		return "admin/miniaturas";
	}

	@RequestMapping("registrarMiniatura")
	public String registrarMiniatura(Model model) {
		Miniatura m = new Miniatura();
		m.setFormato("caja");
		model.addAttribute("nuevaMini",m);
		Map<String, String> mapCategorias =servicioCategorias.obtenerCategoriasParaDesplegable();
		model.addAttribute("categorias", mapCategorias);
		
		
		return "admin/formularioRegistrarMiniatura";
	}

	@RequestMapping("guardarNuevaMini")
	public String guardarMiniatura(@ModelAttribute("nuevaMini") @Valid Miniatura m,BindingResult br,Model model, HttpServletRequest request) {
		if(!br.hasErrors()) {
			servicioMiniaturas.registrarMIniatura(m);
			String rutaRealProyecto=
					request.getServletContext().getRealPath("");
			GestorArchivos.guardarPortdaMiniatura(m, rutaRealProyecto);
			return "admin/registroMiniaturasOK";
		}else {
			Map<String, String> mapCategorias =servicioCategorias.obtenerCategoriasParaDesplegable();
			model.addAttribute("categorias", mapCategorias);
			model.addAttribute("nuevaMini",m);
			return "admin/formularioRegistrarMiniatura";
		}
	}
	@RequestMapping("borrarMiniatura")
	public String borrarMiniatura(String id,Model model){
		servicioMiniaturas.borrarMiniatura(Integer.parseInt(id));
		return listarMiniaturas("",0,model);
		
	}
	@RequestMapping("editarMiniatura")
	public String editarMiniatura(String id,Model model){
		Miniatura m = servicioMiniaturas.editarMiniaturaPorId(Integer.parseInt(id));
		Map<String, String> mapCategorias = servicioCategorias.obtenerCategoriasParaDesplegable();
		model.addAttribute("categorias", mapCategorias);
		m.setIdCategoria(m.getCategoria().getId());
		model.addAttribute("miniatura",m);
		return "admin/formularioEditarMiniatura";	
	}
	@RequestMapping("guardarCambiosMiniatura")
	public String guardarCambiosMiniatura(@ModelAttribute("miniatura") @Valid Miniatura miniatura,BindingResult br,Model model, HttpServletRequest request){
		
		if(!br.hasErrors()) {		
			System.out.println("datos del libro a actualizar: " + miniatura);
			servicioMiniaturas.guardarCambiosMiniatura(miniatura);
			System.out.println("archivo de portada: " + miniatura.getImagen());
			String rutaRealDelProyecto = 
					request.getServletContext().getRealPath("");
			GestorArchivos.guardarPortdaMiniatura(miniatura, rutaRealDelProyecto);
			return listarMiniaturas("",0,model);
		}else {
			model.addAttribute("miniatura",miniatura);
			model.addAttribute("categorias",servicioCategorias.obtenerCategoriasParaDesplegable());
			return "admin/formularioEditarMiniatura";
		}
		
	}
	
	@RequestMapping("darBajaMiniatura")
	public String darBajaMiniatura(String id,Model model) {
		servicioMiniaturas.darBajaMiniatura(Integer.parseInt(id));
		return listarMiniaturas("",0,model);
	}
	
	@RequestMapping("darAltaMiniatura")
	public String darAltaMiniatura(String id,Model model) {
		servicioMiniaturas.darAltaMiniatura(Integer.parseInt(id));
		return listarMiniaturas("",0,model);
	}
}
