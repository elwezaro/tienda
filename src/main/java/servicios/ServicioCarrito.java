package servicios;

import java.util.List;
import java.util.Map;

import modelo.Usuario;

public interface ServicioCarrito {

	void agregarProducto(Usuario u, int idProducto,int cantidad);
	List<Map<String, Object>> obtenerProductosCarrito(Usuario u);
	void borrarProductoCarrito(int idUsuario, int idProducto);
	void actualizarProductoCarrito(int idUsuario,int idProducto,int cantidad);
	
}
