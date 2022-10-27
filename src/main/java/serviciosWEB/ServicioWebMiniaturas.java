package serviciosWEB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import modelo.Miniatura;
import servicios.ServicioMiniaturas;

@Controller
@RequestMapping("servicioWebMiniaturas/")
public class ServicioWebMiniaturas {
	
	@Autowired
	private ServicioMiniaturas servicioMiniaturas;
	
	@RequestMapping("obtenerMiniaturas")
	public ResponseEntity<String> obtenerMiniaturas(){
		String json = new Gson().toJson(servicioMiniaturas.obtenerMiniaturasParaListado());
		return new ResponseEntity<String>(
				json,HttpStatus.OK);
	}
	
	@RequestMapping("obtenerDetallesMiniatura")
	public ResponseEntity<String> obtenerDetallesMiniatura(String id){
		
		String json = new Gson().toJson( servicioMiniaturas.obtenerDetallesMinitura(Integer.parseInt(id)));
		
		return new ResponseEntity<String>(
				json,HttpStatus.OK);
	}

	
}
