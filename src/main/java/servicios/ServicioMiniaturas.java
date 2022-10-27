package servicios;

import java.util.List;
import java.util.Map;

import modelo.Miniatura;



public interface ServicioMiniaturas {
	public int obtenerTotalMiniaturas(String nombre);
	List<Miniatura> obtenerMiniaturas(String nombre,int comienzo);

	void registrarMIniatura(Miniatura m);
	void borrarMiniatura(int id);
	Miniatura editarMiniaturaPorId(int id);
	void guardarCambiosMiniatura(Miniatura m);
	void darBajaMiniatura(int id);
	void darAltaMiniatura(int id);
	
	//funciones de ajax 
	List<Map<String, Object>> obtenerMiniaturasParaListado();
	Map<String, Object> obtenerDetallesMinitura(int id);
}
