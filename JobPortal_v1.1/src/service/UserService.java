package service;

import java.util.Calendar;
import java.util.List;

import data.dao.UserDAO;
import data.entity.User;

public class UserService implements MetaService {

	UserDAO userDao = new UserDAO();
	
	public Integer addEntity(Object obj) {
		return userDao.addEntity(obj);
	}

	public void updateEntity(Integer Id, Object obj_new) {
		userDao.updateEntity(obj_new);
	}

	public void deleteEntity(Integer Id) {
		userDao.deleteEntity(Id);
	}

	public List<User> listEntities() {
		return userDao.listEntities();
	}

	public Integer addEntity_TS(Object obj) {
		Integer UserId = null;
		if (obj instanceof User) {
			Calendar timeStamp = Calendar.getInstance();
			((User)obj).setCreation_timestamp(timeStamp);
			UserId = addEntity(obj);
		} else {
			throw new ClassCastException("Not a valid type for User table");
		}
		
		return UserId;
	}

	public void updateEntity_TS(Integer Id, Object obj_new) {
		if (obj_new instanceof User) {
			Calendar timeStamp = Calendar.getInstance();
			((User) obj_new).setUpdate_timestamp(timeStamp);
			updateEntity(Id, obj_new);
		} else {
			throw new ClassCastException("Not a valid type for User table");
		}
	}
	
	/**
	 * Check if the email and password pair matches the database record.
	 * If yes, update new sessionId to database.
	 * Otherwise, return null.
	 * @param email
	 * @param password
	 * @param sessionId
	 * @return
	 */
	public User login(String email, String password, String sessionId) {
		User user = userDao.getUserByEmailPassword(email, password);
	
		if (user != null) {
			user.setSession_id(sessionId);
			userDao.updateEntity(user);
		}

		return user;
	}
	
	/**
	 * Log out a user by clearing the sessionId column.
	 * @param user : fetched from HTTP session
	 */
	public void logout(User user) {
		if (user != null) {
			user.setSession_id("");
			userDao.updateEntity(user);
		}
	}
	
	public User checkAutoLogin(String email, String sessionId, String newSessionId) {
		User user = userDao.getUserByEmailSessionId(email, newSessionId);
	
		if (user != null) {
			user.setSession_id(newSessionId);
			userDao.updateEntity(user);
		}

		return user;
	}
}
