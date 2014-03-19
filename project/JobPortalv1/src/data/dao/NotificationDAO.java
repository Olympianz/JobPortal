package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.Application;
import data.entity.Job;
import data.entity.Notification;

public class NotificationDAO extends DAO {
	public List<Notification> listEntities() {
		List<Notification> notifs = null;
		try {
			notifs = getSession().createQuery("from Notification").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return notifs;
	}
	
	public Notification getEntityById(Integer id) {
		Notification notif = null;
		
		try{
			Query q = getSession().createQuery("from Notification where id = :id");
			q.setInteger("id", id);
			notif = (Notification) q.uniqueResult();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return notif;
	}
	
	public int saveOrUpdate(Notification notif) {
		int id = -1;
		
		try{
			begin();
			if(notif.getId() != null && notif.getId() >= 0) {
				id = notif.getId();
				getSession().update(notif);
			}
			else {
				id = (Integer)getSession().save(notif); 
			}
			commit();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return id;
	}


}
