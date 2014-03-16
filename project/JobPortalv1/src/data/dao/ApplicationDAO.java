package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

import data.entity.Application;

public class ApplicationDAO extends DAO {
	public List<Application> listEntities() {
		List<Application> apps = null;
		try {
			apps = getSession().createQuery("from Application").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return apps;
	}
	
	public Application getEntityById(Integer id) {
		Application app = null;
		
		try{
			app = (Application) getSession().createQuery("from Application where id=" + id);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return app;
	}
}
