package data.dao;

import java.util.List;

import modelMB.UserBean;

import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import data.entity.Notification;
import data.entity.User;
import util.TableManipulation;
import util.Util;

public class UserDAO extends DAO implements TableManipulation {

	public Integer save(Object obj) {

		Integer UserId = 0;
		if (obj instanceof User) {
			UserId = (Integer) getSession().save((User) (obj));
		} else {
			throw new ClassCastException("Not a valid type for User table");
		}

		return UserId;
	}

	public void delete(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof User) {
			getSession().delete((User) (obj));
		} else {
			throw new ClassCastException("Not a valid type for User Table");
		}
	}

	// Implementations of Interface {TableMainipualtion}
	public Integer addEntity(Object obj) {

		Integer UserId = 0;
		if (obj instanceof User) {
			try {
				begin();
				UserId = save(obj);
				commit();
			} catch (HibernateException e) {
				if (getSession().getTransaction() != null) {
					rollback();
				}
				e.printStackTrace();
			} finally {
				//close();
			}
		} else {
			throw new ClassCastException("Not a valid type for User Table");
		}

		return UserId;
	}

	public void deleteEntity(Integer ID) {
		// TODO Auto-generated method stub

		try {
			begin();
			User user = (User) getSession().get(User.class, ID);
			delete(user);
			commit();
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			//close();
		}
	}

	public void updateEntity(Object obj_new) {
		// TODO Auto-generated method stub
		if (obj_new instanceof User) {
			try {
				begin();
				getSession().update((User) (obj_new));
				commit();
			} catch (HibernateException e) {
				if (getSession().getTransaction() != null) {
					rollback();
				}
				e.printStackTrace();
			} finally {
				//close();
			}
		} else {
			throw new ClassCastException("Not a valid type for User Table");
		}
	}

	public List<User> listEntities() {
		// TODO Auto-generated method stub
		List<User> users = null;
		try {
			users = getSession().createQuery("from User").list();
			if(users != null) {
				for(User user : users)
					getSession().merge(user);
			}
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		}

		return users;
	}

	public User getEntityById(Integer id, boolean newSession) {
		User user = null;
		Session session = null;
		if (newSession){
			SessionFactory sessionFactory = Util.getSessionFactory();		
			session = sessionFactory.openSession();
		}
		else 
			session = getSession();
		
		try {
			Query q = session.createQuery("from User where user_id = :id");
			q.setInteger("id", id);

			user = (User) q.uniqueResult();
			
			if (user != null) {
				session.merge(user);
			}
			if (newSession) {
				session.close();
			}
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		}

		return user;
	}

	public User getEntityByEmail(String email) {
		User user = null;

		try {
			Query q = getSession().createQuery("from User where email = :email");
			q.setString("email", email);
			user = (User) q.uniqueResult();
			if (user != null)
				getSession().merge(user);
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		}

		return user;
	}

	public User getUserByEmailPassword(String email, String password) {
		User user = null;

		try {
			Query q = getSession().createQuery(
					"from User where email = :email and password = :password");
			q.setString("email", email);
			q.setString("password", password);

			user = (User) q.uniqueResult();
			if(user != null)
				getSession().merge(user);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}

		return user;
	}
	
	//
	public List<String> getUserByEmail(String email) {
		List<String> l = null;

		try {
			Query q = getSession().createQuery(
					"select password from User where email=:email").setParameter("email", email);
			l = (List<String>) q.list();
		} catch (Exception e) {
			rollback();
		}
		
		return l;
	}

	public User getUserByEmailSessionId(String email, String sessionId) {
		User user = null;

		try {
			Query q = getSession().createQuery(
					"from User where email=:email and session_id=:session");
			q.setString("email", email);
			q.setString("session", sessionId);
			user = (User) q.uniqueResult();

			if(user != null)
				getSession().merge(user);
		} catch (Exception e) {
			rollback();
		}
		return user;
	}

	@Override
	public void updateEntity(Integer Id, Object obj_new) {
		// TODO Auto-generated method stub

	}

	public void loadFromUser(User user, UserBean userBean, boolean deepLoad) {

	}
	
	public int saveOrUpdate(User user) {
		int id = -1;
		
		try{
			begin();
			if(user.getUser_id() != null && user.getUser_id() >= 0) {
				id = user.getUser_id();
				getSession().update(user);
			}
			else {
				id = (Integer)getSession().save(user); 
			}
			commit();
			getSession().flush();
		} catch (HibernateException e) {
			id = -1;
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
		
		return id;
	}

	public List<User> search(String query) {
		List<User> users = null;
		try {
			Query q = getSession().createQuery("from User where user_name like :query");
			q.setString("query", "%" + query + "%");
			users = q.list();
			if(users != null) {
				for (User user : users)
					getSession().merge(user);				
			}
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
	
		return users;
	}

	public List<User> searchEmail(String query) {
		List<User> users = null;
		try {
			Query q = getSession().createQuery("from User where email like :query");
			q.setString("query", query + "%");
			users = q.list();
			if(users != null) {
				for (User user : users)
					getSession().merge(user);				
			}
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
	
		return users;
	}
}
