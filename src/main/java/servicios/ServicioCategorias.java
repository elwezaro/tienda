package servicios;

import java.util.List;
import java.util.Map;
import modelo.Categoria;

public interface ServicioCategorias {

	Map<String, String> obtenerCategoriasParaDesplegable();
	List<Categoria> obtenerCategorias();
	void crearCategoria(Categoria c);
	void darBajaCategoria(long id);
	void darAltaCategoria(long id);
}
