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

import constantes.EstadosPedido;
import modelo.Pedido;
import servicios.ServicioPedidos;

@Controller
@RequestMapping("admin/")
public class ControladorPedidos {
	@Autowired
	ServicioPedidos servicioPedidos;
	@RequestMapping("listarPedidos")
	public String listarPedidos(Model model) {
		model.addAttribute("info", servicioPedidos.obtenerPedidos());
		return "admin/pedidos";
	}
	
	@RequestMapping("editarPedido")
	public String editarPedido(String id, Model model) {
		Pedido p = servicioPedidos.obtenerPedidoPorId(Integer.parseInt(id));
		
		Map<String, String> mapEstados = new HashMap<String, String>();
		mapEstados.put(EstadosPedido.EN_PROCESO, EstadosPedido.EN_PROCESO);
		mapEstados.put(EstadosPedido.TERMINADO, EstadosPedido.TERMINADO);
		mapEstados.put(EstadosPedido.LISTO_PARA_ENVIAR, EstadosPedido.LISTO_PARA_ENVIAR);
		mapEstados.put(EstadosPedido.ENVIADO, EstadosPedido.ENVIADO);
		model.addAttribute("estados", mapEstados);
		//aparezca la categoria por defecto en el desplegable
		model.addAttribute("pedido", p);
		return "admin/formularioEditarPedido";
	}

}
