package data;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class Util {
	
	private static final SessionFactory sessionFactory = buildFactory();

	private static SessionFactory buildFactory() {
		 try {
			 return new AnnotationConfiguration().configure().buildSessionFactory();
			 
		 } catch(Throwable ex) {
			 ex.printStackTrace();
			 System.err.println("Hibernate connection failed");
			 throw new ExceptionInInitializerError(ex);
		 }
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
