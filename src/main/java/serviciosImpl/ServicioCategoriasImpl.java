package serviciosImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import constantesSQL.ContantesSQL;
import modelo.Categoria;
import modelo.Miniatura;
import servicios.ServicioCategorias;

@Service
@Transactional
public class ServicioCategoriasImpl implements ServicioCategorias{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Categoria> obtenerCategorias() {
		return sessionFactory.getCurrentSession().createCriteria(Categoria.class).list();
	}

	@Override
	public void crearCategoria(Categoria c) {
		sessionFactory.getCurrentSession().persist(c);//hace basicamente lo mismo que save
	}

	@Override
	public Map<String, String> obtenerCategoriasParaDesplegable() {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
				ContantesSQL.SQL_OBTENER_CATEGORIAS_PARA_DESPLEGABLE);
		//lo siguiente es para que me como resultado elementos tipo Map
		query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
		List<Map<String,Object>>  res = query.list();
		Map<String, String> valoresDesplegable = new HashMap<String, String>();
		System.out.println("obtenido de la base de datos:");
		for (Map<String, Object> map : res) {
			System.out.println("id: " + map.get("id") + " nombre: " + map.get("nombre"));
			valoresDesplegable.put(map.get("id").toString(), map.get("nombre").toString());
		}
		return valoresDesplegable;
	}

	@Override
	public void darBajaCategoria(long id) {
		Categoria c =(Categoria)sessionFactory.getCurrentSession().get(Categoria.class,id);
		c.setAlta(false);
		for (Miniatura m : c.getMiniaturas()) {
			m.setAlta(false);
			sessionFactory.getCurrentSession().update(m);
		}
		sessionFactory.getCurrentSession().update(c);
	}

	@Override
	public void darAltaCategoria(long id) {
		Categoria c =(Categoria)sessionFactory.getCurrentSession().get(Categoria.class,id);
		c.setAlta(true);
		for (Miniatura m : c.getMiniaturas()) {
			m.setAlta(true);
			sessionFactory.getCurrentSession().update(m);
		}
		sessionFactory.getCurrentSession().update(c);
		
	}
	
}




