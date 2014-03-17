package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

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
			notif = (Notification) getSession().createQuery("from Notification where id=" + id);
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
			if(notif.getId() != null && notif.getId() >= 0) {
				id = notif.getId();
				getSession().update(notif);
			}
			else {
				id = (Integer)getSession().save(notif); 
			}
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
