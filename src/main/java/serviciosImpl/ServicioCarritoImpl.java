package serviciosImpl;

import java.util.List;


import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import constantesSQL.ContantesSQL;
import modelo.Carrito;

import modelo.Miniatura;

import modelo.ProductosCarrito;
import modelo.Usuario;
import servicios.ServicioCarrito;

@Service
@Transactional
public class ServicioCarritoImpl implements ServicioCarrito{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void agregarProducto(Usuario u, int idProducto,int cantidad) {
		Usuario uBaseDeDatos = (Usuario)sessionFactory.getCurrentSession().get(Usuario.class, u.getId());
		Carrito c = uBaseDeDatos.getCarrito();
		if(c==null) {
			c= new Carrito();
			uBaseDeDatos.setCarrito(c);
			sessionFactory.getCurrentSession().save(c);
		}	
		boolean productoEnCarrito=false;
		for (ProductosCarrito pc_en_carrito : c.getProductosCarritos()) {
			if(pc_en_carrito.getMiniatura().getId()==idProducto) {
				productoEnCarrito=true;
				pc_en_carrito.setCantidad(pc_en_carrito.getCantidad()+cantidad);
				sessionFactory.getCurrentSession().merge(pc_en_carrito);
			}
		}
		if(!productoEnCarrito) {
			ProductosCarrito pc = new ProductosCarrito();
			pc.setCarrito(c);
			pc.setMiniatura((Miniatura)sessionFactory.getCurrentSession().get(Miniatura.class, idProducto));
			pc.setCantidad(cantidad);
			sessionFactory.getCurrentSession().save(pc);	
		}
		
	}

	@Override
	public List<Map<String, Object>> obtenerProductosCarrito(Usuario u) {
		Usuario uBaseDatos =(Usuario) sessionFactory.getCurrentSession().get(Usuario.class, u.getId());
		Carrito c = uBaseDatos.getCarrito();
		List<Map<String, Object>> res = null; 
		if(c!=null) {
			for (ProductosCarrito pc: c.getProductosCarritos()) {
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ContantesSQL.OBTENER_PRODUCTOS_CARRITO);
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				query.setParameter("carrito_id", c.getId());
				res=query.list();
			}
		}
		return res;
		}

	@Override
	public void borrarProductoCarrito(int idUsuario, int idProducto) {
		Usuario uBaseDatos = (Usuario)sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
		Carrito c = uBaseDatos.getCarrito();
		if(c !=null) {
			//sql para borrar el registro
			SQLQuery query =sessionFactory.getCurrentSession().createSQLQuery(ContantesSQL.SQL_BORRAR_PRODUCTO_CARRITO);
			query.setParameter("id_carrito", c.getId());
			query.setParameter("miniatura_id", idProducto);
			query.executeUpdate();
		}
	}

	@Override
	public void actualizarProductoCarrito(int idUsuario, int idProducto, int cantidad) {
		Usuario uBaseDatos = (Usuario)sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
		Carrito c = uBaseDatos.getCarrito();
		if(c !=null) {
			List<ProductosCarrito> pcs = c.getProductosCarritos();
			for (ProductosCarrito productoCarrito : pcs) {
				if(productoCarrito.getMiniatura().getId()==idProducto) {
					productoCarrito.setCantidad(cantidad);
					sessionFactory.getCurrentSession().update(productoCarrito);
				}
			}
		}
		
	}
	
}
	






