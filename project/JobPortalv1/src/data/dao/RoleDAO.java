package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.Role;

public class RoleDAO extends DAO {

	public List<Role> listEntities() {
		List<Role> roles = null;
		try {
			roles = getSession().createQuery("from Role").list();
			if (roles != null)
				for (Role role : roles)
					getSession().merge(role);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
	
		return roles;
	}
	
	public Role getByName(String name) {
		Role role = null;
		
		try {
			Query q = getSession().createQuery(
					"from Role where role_n = :name");
			q.setString("name", name);

			role = (Role) q.uniqueResult();
			if (role != null)
				getSession().merge(role);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
		
		return role;
	}

}
