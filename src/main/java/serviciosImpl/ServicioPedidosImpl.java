package serviciosImpl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantes.EstadosPedido;
import constantesSQL.ContantesSQL;
import datos.servicios.ResumenPedido;
import modelo.Carrito;
import modelo.Pedido;
import modelo.ProductoPedido;
import modelo.ProductosCarrito;
import modelo.Usuario;
import servicios.ServicioCarrito;
import servicios.ServicioPedidos;

@Service
@Transactional
public class ServicioPedidosImpl implements ServicioPedidos{

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private ServicioCarrito servicioCarrito;
	@Override
	public void procesarPaso1(String nombre, String direccion, String provincia,String codigoPostal,String pais, int idUsuario) {
		Pedido p = obtenerPedidoActual(idUsuario);
		
		p.setNombreCompleto(nombre);
		p.setDireccion(direccion);
		p.setProvincia(provincia);
		p.setEstado(EstadosPedido.EN_PROCESO);
		p.setCodigoPostal(codigoPostal);
		p.setPais(pais);
		
		
		sessionFactory.getCurrentSession().merge(p);
		
	}
	private Pedido obtenerPedidoActual(int idUsuario) {
		Usuario uBaseDatos = (Usuario)
				sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
		Object pedidoEnProceso = sessionFactory.getCurrentSession().createCriteria(Pedido.class).
				add(Restrictions.eq("estado", EstadosPedido.EN_PROCESO)).
				add(Restrictions.eq("usuario.id",uBaseDatos.getId())).uniqueResult();
		
		Pedido p = null;
		if(pedidoEnProceso == null) {
			p = new Pedido();
			p.setUsuario(uBaseDatos);
		}else {
			p = (Pedido)pedidoEnProceso;
		}
		return p;
	}
	@Override
	public void procesarPaso2(String titular, String numero, String tipoTarjeta,String cvp, int idUsuario) {
		Pedido p = obtenerPedidoPorId(idUsuario);
		p.setTitularTarjeta(titular);
		p.setNumeroTarjeta(numero);
		p.setTipoTarjeta(tipoTarjeta);
		p.setCvp(cvp);
		sessionFactory.getCurrentSession().merge(p);
	}	
	
	@Override
	public ResumenPedido obtenerResumenPedido(int idUsuario) {
		Pedido p =obtenerPedidoPorId(idUsuario);
		ResumenPedido  resumen = new ResumenPedido();
		resumen.setDireccion(p.getDireccion());
		resumen.setCodigoPostal(p.getCodigoPostal());
		resumen.setCvp(p.getCvp());
		resumen.setNombreCompleto(p.getNombreCompleto());
		resumen.setProvincia(p.getProvincia());
		resumen.setPais(p.getPais());
		resumen.setTipoTarjeta(p.getTipoTarjeta());
		resumen.setTitularTarjeta(p.getTitularTarjeta());
		String numeroOri=p.getNumeroTarjeta();
		if(numeroOri.length()>4) {
			String parte1=numeroOri.substring(0, numeroOri.length()-5);
			String parte2=numeroOri.substring( numeroOri.length()-5,numeroOri.length()-1);
			String parte1Asteriscos=parte1.replaceAll("[0-9]", "*");
			resumen.setNumeroTarjeta(parte1Asteriscos+parte2);
		}else{
			resumen.setNumeroTarjeta(numeroOri);
		}
		resumen.setMiniaturas(servicioCarrito.obtenerProductosCarrito(
				(Usuario)sessionFactory.getCurrentSession().get(Usuario.class, idUsuario)));
		return resumen;
	}
	@Override
	public void confirmarPedido(int idusuario) {
			Pedido p = obtenerPedidoPorId(idusuario);
			Usuario uBaseDatos = (Usuario)
					sessionFactory.getCurrentSession().get(Usuario.class, idusuario);
			Carrito c = uBaseDatos.getCarrito();
			if(c !=null) {
				for (ProductosCarrito pc: c.getProductosCarritos()) {
					ProductoPedido pp = new ProductoPedido();
					pp.setCantidad(pc.getCantidad());
					pp.setMiniatura(pc.getMiniatura());
					p.getProductosPedido().add(pp);
					pp.setPedido(p);
					sessionFactory.getCurrentSession().save(pp);
				}
			}
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ContantesSQL.SQL_BORRAR_TODOS_PRODUCTOS_CARRITO);
			query.setParameter("id_carrito", c.getId());
			query.executeUpdate();
			p.setEstado(EstadosPedido.TERMINADO);
	}
	@Override
	public List<Pedido> obtenerPedidos() {
		List<Pedido> pedidos = sessionFactory.getCurrentSession().
				createQuery("from Pedido").list();
		return pedidos;
	}
	@Override
	public Pedido obtenerPedidoPorId(int id) {
		return (Pedido)sessionFactory.getCurrentSession().get(Pedido.class, id);
	}

	@Override
	public void actualizarEstadoPedido(int idPedido, String estado) {
		Pedido p = obtenerPedidoPorId(idPedido);
		p.setEstado(estado);
		sessionFactory.getCurrentSession().update(p);
	}

	@Override
	public List<Map<String, Object>> obtenerPedidosUsuario(int id) {
//		List<Pedido> pedidos = sessionFactory.getCurrentSession().
//				createQuery("from Pedido where id= :id").setParameter("id",id).list();
//		
		SQLQuery query = sessionFactory.getCurrentSession().
				createSQLQuery(ContantesSQL.SQL_OBTENER_PEDIDOS_USUARIO);
		query.setInteger("idusuario", id);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> res = query.list();
		
		return res;
	}

	
}





