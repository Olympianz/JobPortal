package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.Contact_type;

public class ContactTypeDAO extends DAO {

	public List<Contact_type> listEntities() {
		List<Contact_type> types = null;
		try {
			types = getSession().createQuery("from Contact_type").list();
			for (Contact_type type : types)
				getSession().merge(type);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
	
		return types;
	}
	
	public Contact_type getByName(String name) {
		Contact_type type = null;
		
		try {
			Query q = getSession().createQuery(
					"from Contact_type where contact_type_n = :name");
			q.setString("name", name);

			type = (Contact_type) q.uniqueResult();
			getSession().merge(type);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
		
		return type;
	}

}
