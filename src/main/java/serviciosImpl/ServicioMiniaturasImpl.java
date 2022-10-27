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

import constantesSQL.ContantesSQL;
import modelo.Categoria;
import modelo.Miniatura;
import servicios.ServicioMiniaturas;

@Service
@Transactional
public class ServicioMiniaturasImpl implements ServicioMiniaturas{


	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public int obtenerTotalMiniaturas(String nombre) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ContantesSQL.OBTENER_TOTAL_MINIATURAS);
		query.setParameter("nombre", "%"+nombre+"%");
		return  Integer.parseInt(query.list().get(0).toString());
	}

	@Override
	public List<Miniatura> obtenerMiniaturas(String nombre,int comienzo) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Miniatura.class);
		c.add(Restrictions.like("nombre", "%"+nombre+"%"));
		c.addOrder(Order.desc("id"));
		c.setFirstResult(comienzo);
		c.setMaxResults(10);
		return c.list();
	}
	
	@Override
	public List<Map<String, Object>> obtenerMiniaturasParaListado() {
		SQLQuery query = sessionFactory.getCurrentSession().
				createSQLQuery(ContantesSQL.SQL_OBTENER_MINIATURAS_PARA_LISTADO);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String, Object>> res = query.list();
		
		return res;
	}
	
	@Override
	public void registrarMIniatura(Miniatura m) {
		// TODO Auto-generated method stub
		Categoria c =
				(Categoria)sessionFactory.getCurrentSession().get(Categoria.class, m.getIdCategoria());
		m.setCategoria(c);
		sessionFactory.getCurrentSession().save(m);
	}

	@Override
	public void borrarMiniatura(int id) {
		// TODO Auto-generated method stub
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(ContantesSQL.SQL_BORRAR_LIBRO);
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Override
	public Miniatura editarMiniaturaPorId(int id) {
		// TODO Auto-generated method stub
		return (Miniatura)sessionFactory.getCurrentSession().get(Miniatura.class, id);
		
	}

	@Override
	public void guardarCambiosMiniatura(Miniatura m) {
		// TODO Auto-generated method stub
		Categoria c = (Categoria)sessionFactory.getCurrentSession().get(Categoria.class, m.getIdCategoria());
		m.setCategoria(c);
		sessionFactory.getCurrentSession().merge(m);
	}

	
	@Override
	public Map<String, Object> obtenerDetallesMinitura(int id) {
		SQLQuery query = sessionFactory.getCurrentSession().
				createSQLQuery(ContantesSQL.OBTENER_MINIATURA_POR_ID);
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		query.setParameter("id", id);
		return (Map<String, Object>) query.uniqueResult();
	}

	@Override
	public void darBajaMiniatura(int id) {
		Miniatura m =(Miniatura)sessionFactory.getCurrentSession().get(Miniatura.class,id);
		m.setAlta(false);
		sessionFactory.getCurrentSession().update(m);
	}

	@Override
	public void darAltaMiniatura(int id) {
		Miniatura m =(Miniatura)sessionFactory.getCurrentSession().get(Miniatura.class,id);
		m.setAlta(true);
		sessionFactory.getCurrentSession().update(m);
		
	}




}
