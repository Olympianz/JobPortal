package data.dao;

import org.hibernate.Query;

import data.entity.User;

public class UserDAO extends DAO {

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
		User user = null;

		try {
			begin();
			
			Query q = getSession().createQuery("from User where email = :email and password = :password");
			q.setString("email", email);
			q.setString("password", password);

			user = (User)q.uniqueResult();
			
			if (user != null) {
				user.setSessionId(sessionId);
				getSession().update(user);
			}
			commit();
		} catch (Exception e) {
			//e.printStackTrace();	
			rollback();
		}

		return user;
	}
	
	/**
	 * Log out a user by clearing the sessionId column.
	 * @param user : fetched from HTTP session
	 */
	public void logout(User user) {
System.out.println("Logout in UserDAO");
		try {
			begin();
			
			user.setSessionId("");
			getSession().update(user);
			
			commit();
		}
		catch(Exception e) {
			rollback();
		}
	}
	
	public User checkAutoLogin(String email, String sessionId, String newSessionId) {
		User user = null;

		try {
			begin();
			
			Query q = getSession().createQuery("from J_USERS where EMAIL_N=:email and SESSION_TOKEN=:session");
			q.setString("email", email);
			q.setString("session", sessionId);
			user = (User)q.uniqueResult();
			
			if (user != null) {
				user.setSessionId(newSessionId);
				getSession().update(user);
			}
			commit();
		} catch (Exception e) {
			rollback();
		}
		return user;
	}
	
}
