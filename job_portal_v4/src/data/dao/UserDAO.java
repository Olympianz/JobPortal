package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

import data.TableManipulation;
import data.entity.User;

public class UserDAO extends DAO implements TableManipulation{

	@Override
	public Integer save(Object obj) {
		// TODO Auto-generated method stub
		
		Integer UserId = 0;
		if (obj instanceof User) {
			UserId = (Integer) session.save((User)(obj));
		} else {
			throw new ClassCastException("Not a valid type for User table");
		}
		
		return UserId;
	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof User) {
			session.delete((User)(obj));
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
				if (session.getTransaction()!=null) {
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
			User user = (User) session.get(User.class, ID);
			delete(user);
			commit();
		} catch (HibernateException e) {
			if (session.getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	}

	public void updateEntity(Integer UserId, Object obj_new) {
		// TODO Auto-generated method stub
		if (obj_new instanceof User) {
			try {
				begin();
				User user = (User) session.get(User.class, UserId);
				user.update((User)(obj_new));
				session.update(user);
				commit();
			} catch (HibernateException e) {
				if (session.getTransaction()!=null) {
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

	public List listEntities() {
		// TODO Auto-generated method stub
		List users = null;
		try {
			users = session.createQuery("from User").list();
		} catch (HibernateException e) {
			if (session.getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return users;
	}
}
