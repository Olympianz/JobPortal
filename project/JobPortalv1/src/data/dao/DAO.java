package data.dao;

/**
 * Copy from Beginning Hibernate 2nd Edition, ISBN: 978-1-4302-2851-6
 */

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;

public class DAO {

	protected DAO() {
	}

	public static Session getSession() {
		Session session = (Session) DAO.session.get();
		if (session == null) {
			if (sessionFactory == null) {
				if (serviceRegistry == null)
					serviceRegistry = new StandardServiceRegistryBuilder()
							.applySettings(
									new Configuration().configure("/hibernate.cfg.xml")
											.getProperties()).build();
				sessionFactory = new Configuration().configure().configure()
						.buildSessionFactory(serviceRegistry);
			}
			
			session = sessionFactory.openSession();
			DAO.session.set(session);
		}
		return session;
	}

	protected void begin() {
		getSession().beginTransaction();
	}

	protected void commit() {
		getSession().getTransaction().commit();
	}

	protected void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot rollback", e);
		}

		try {
			getSession().close();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot close", e);
		}
		DAO.session.set(null);
	}

	public static void close() {
		getSession().close();
		DAO.session.set(null);
		((StandardServiceRegistryImpl)serviceRegistry).destroy();
	}

	private static final Logger log = Logger.getAnonymousLogger();
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	private static ServiceRegistry serviceRegistry = null;
	private static SessionFactory sessionFactory = null;
	// private static final ServiceRegistry serviceRegistry = new
	// StandardServiceRegistryBuilder()
	// .applySettings(
	// new Configuration().configure()
	// .getProperties()).build();
	// private static final SessionFactory sessionFactory = new Configuration()
	// .configure()
	// .configure()
	// .buildSessionFactory(serviceRegistry);

	// protected void finalize() {
	// ((StandardServiceRegistryImpl) serviceRegistry).destroy();
	// }
}
