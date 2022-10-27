package servicios;

import java.util.List;

import modelo.Usuario;

public interface servicioUsuarios {
	
	List<Usuario> obtenerUsuarios();
	void registrarUsuario(Usuario u);
	Usuario obtenerUsuarioPorEmailYpass(String email, String pass);
	boolean comprobarEmail(String email);
}

