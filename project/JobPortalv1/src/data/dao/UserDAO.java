package data.dao;

import java.util.List;

import modelMB.UserBean;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.User;
import util.TableManipulation;

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
			if (getSession().getTransaction() != null) {
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
				getSession().update((User) (obj_new));
				commit();
			} catch (HibernateException e) {
				if (getSession().getTransaction() != null) {
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
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}

		return users;
	}

	public User getEntityById(Integer id) {
		User user = null;

		try {
			Query q = getSession().createQuery("from User where user_id = :id");
			q.setInteger("id", id);
			user = (User) q.uniqueResult();
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}

		return user;
	}

	public User getEntityByEmail(String email) {
		User user = null;

		try {
			Query q = getSession().createQuery("from User where email = :email");
			q.setString("email", email);
			user = (User) q.uniqueResult();
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}

		return user;
	}

	public User getUserByEmailPassword(String email, String password) {
		User user = null;

		try {
			begin();

			Query q = getSession().createQuery(
					"from User where email = :email and password = :password");
			q.setString("email", email);
			q.setString("password", password);

			user = (User) q.uniqueResult();

			commit();
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
			begin();

			Query q = getSession().createQuery(
					"select password from User where email=:email").setParameter("email", email);
			
			System.out.println(q.list());
			
			l = (List<String>) q.list();
			
			System.out.println(l);

			commit();
		} catch (Exception e) {
			rollback();
		}
		return l;
	}

	public User getUserByEmailSessionId(String email, String sessionId) {
		User user = null;

		try {
			begin();

			Query q = getSession().createQuery(
					"from User where email=:email and session_id=:session");
			q.setString("email", email);
			q.setString("session", sessionId);
			user = (User) q.uniqueResult();

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
	
	public int merge(User user) {
		int id = -1;
		
		try{
			begin();
			if(user.getUser_id() != null && user.getUser_id() >= 0) {
				id = user.getUser_id();
				getSession().merge(user);
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

	public List<User> search(String query) {
		List<User> users = null;
		try {
			Query q = getSession().createQuery("from User where user_name like :query");
			q.setString("query", "%" + query + "%");
			users = q.list();
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

	public List<User> searchEmail(String query) {
		List<User> users = null;
		try {
			Query q = getSession().createQuery("from User where email like :query");
			q.setString("query", query + "%");
			users = q.list();
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
}
