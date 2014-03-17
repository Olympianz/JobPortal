package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.NotificationType;

public class NotificationTypeDAO extends DAO {

	public List<NotificationType> listEntities() {
		List<NotificationType> types = null;
		try {
			types = getSession().createQuery("from NotificationType").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return types;
	}
	
	public NotificationType getByName(String name) {
		NotificationType type = null;
		
		try {
			Query q = getSession().createQuery(
					"from NotificationType where name = :name");
			q.setString("name", name);

			type = (NotificationType) q.uniqueResult();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
		
		return type;
	}

}
