package servicios;

import java.util.List;
import java.util.Map;

import datos.servicios.ResumenPedido;
import modelo.Pedido;

public interface ServicioPedidos {

	void procesarPaso1(String nombre, String direccion, String provincia,String codigoPostal,String pais, int idUsuario);
	void procesarPaso2(String titular, String numero, String tipoTarjeta,String cvp, int idUsuario);
	ResumenPedido obtenerResumenPedido(int idUsuario);
	void confirmarPedido(int idusuario);
	List<Pedido> obtenerPedidos();
	Pedido obtenerPedidoPorId(int id);
	void actualizarEstadoPedido(int idPedido, String estado);
	List<Map<String, Object>>  obtenerPedidosUsuario(int id);
}
