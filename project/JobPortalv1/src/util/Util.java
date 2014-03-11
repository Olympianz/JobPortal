package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Util {

	private static final ServiceRegistry serviceRegistry = buildServiceRegistry();
	private static final SessionFactory sessionFactory = buildFactory(serviceRegistry);

	private static SessionFactory buildFactory(ServiceRegistry serviceRegistry) {
		 try {
			 return new Configuration().configure().configure()
						.buildSessionFactory(serviceRegistry);
			 
		 } catch(Throwable ex) {
			 ex.printStackTrace();
			 System.err.println("Hibernate connection failed");
			 throw new ExceptionInInitializerError(ex);
		 }
	}
	
	private static ServiceRegistry buildServiceRegistry() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
			.applySettings(
					new Configuration().configure()
						.getProperties()).build();
		return serviceRegistry;
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
