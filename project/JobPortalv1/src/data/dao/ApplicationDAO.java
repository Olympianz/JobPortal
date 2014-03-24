package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.Application;
import data.entity.Job;

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

	public List<Application> search(String query) {
		if ( query == null )
			query = "";
		
		List<Application> apps = null;
		try {
			Query q = getSession().createQuery("from Application where position.title like :query");
			q.setString("query", "%" + query + "%");
			apps = q.list();
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
			Query q = getSession().createQuery("from Application where id = :id");
			q.setInteger("id", id);
			app = (Application) q.uniqueResult();
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
	
	public int saveOrUpdate(Application app) {
		int id = -1;

		try{
			begin();
			if(app.getId() != null && app.getId() >= 0) {
				id = app.getId();
				getSession().update(app);
			}
			else {
				id = (Integer)getSession().save(app); 
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
