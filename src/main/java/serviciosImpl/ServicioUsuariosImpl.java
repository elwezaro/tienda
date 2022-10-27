package serviciosImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import modelo.Miniatura;
import modelo.Usuario;
import servicios.servicioUsuarios;

@Service
@Transactional
public class ServicioUsuariosImpl implements servicioUsuarios {
	@Resource(name = "fff")
		private SessionFactory sessionFactory;
	
	
	@Override
	public List<Usuario> obtenerUsuarios() {
		
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		return c.list();	
	}

	@Override
	public void registrarUsuario(Usuario u) {
		sessionFactory.getCurrentSession().save(u);
	}

	@Override
	public Usuario obtenerUsuarioPorEmailYpass(String email, String pass) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("email", email));
		c.add(Restrictions.eq("pass",pass));		
		return (Usuario)c.uniqueResult();
	
	}

	@Override
	public boolean comprobarEmail(String email) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
		c.add(Restrictions.eq("email", email));
		if (c.uniqueResult() == null) {
			return false;
		}else {
			return true;
		}
	}


	
}
