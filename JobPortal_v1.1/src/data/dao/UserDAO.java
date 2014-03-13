package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.User;
import util.TableManipulation;

public class UserDAO extends DAO implements TableManipulation{

	public Integer save(Object obj) {
		// TODO Auto-generated method stub
		
		Integer UserId = 0;
		if (obj instanceof User) {
			UserId = (Integer) getSession().save((User)(obj));
		} else {
			throw new ClassCastException("Not a valid type for User table");
		}
		
		return UserId;
	}

	public void delete(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof User) {
			getSession().delete((User)(obj));
		} else {
			throw new ClassCastException("Not a valid type for User Table");
		}
	}

	// Implementations of Interface {TableMainipualtion} 
	public Integer addEntity(Object obj) {
		// TODO Auto-generated method stub
		
		Integer UserId = 0;
		if (obj instanceof User) {
			try {
				begin();
				UserId = save(obj);
				commit();
			} catch (HibernateException e) {
				if (getSession().getTransaction()!=null) {
					rollback();
				}
				e.printStackTrace();
			} finally {
				close();
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
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	}

	public void updateEntity(Object obj_new) {
		// TODO Auto-generated method stub
		if (obj_new instanceof User) {
			try {
				begin();
				getSession().update((User)(obj_new));
				commit();
			} catch (HibernateException e) {
				if (getSession().getTransaction()!=null) {
					rollback();
				}
				e.printStackTrace();
			} finally {
				close();
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
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return users;
	}
	
	public User getUserByEmailPassword(String email, String password) {
		User user = null;

		try {
			begin();
			
			Query q = getSession().createQuery("from User where email = :email and password = :password");
			q.setString("email", email);
			q.setString("password", password);
			user = (User)q.uniqueResult();
			
			commit();
		} catch (Exception e) {
			e.printStackTrace();	
			rollback();
		}

		return user;
	}
	
	
	public User getUserByEmailSessionId(String email, String sessionId) {
		User user = null;

		try {
			begin();
			
			Query q = getSession().createQuery("from User where email=:email and session_id=:session");
			q.setString("email", email);
			q.setString("session", sessionId);
			user = (User)q.uniqueResult();
			
			commit();
		} catch (Exception e) {
			rollback();
		}
		return user;
	}

	@Override
	public void updateEntity(Integer Id, Object obj_new) {
		// TODO Auto-generated method stub
		
	}
}
