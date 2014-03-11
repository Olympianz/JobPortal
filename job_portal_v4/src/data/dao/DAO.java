package data.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import data.Util;

public abstract class DAO {

	public abstract Integer save(Object obj);
	public abstract void delete(Object obj);
	
	protected DAO() {
	}
	
	protected void begin() {
		session.beginTransaction();
	}
	
	protected void commit() {
		session.getTransaction().commit();
	}
	
	protected void rollback() {
		try {
			session.getTransaction().rollback();
		} catch (HibernateException e) {
			System.out.println("Can't roll back!");
		}
		
		try {
			session.close();	
		} catch (HibernateException e) {
			System.out.println("Can't close session!");
		}		
	}
	
	protected void close() {
		session.close();
	}
	
	protected Session session = Util.getSessionFactory().openSession(); 
			
}
