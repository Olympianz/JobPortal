package test;

import data.dao.DAO;
import data.dao.UserDAO;
import data.entity.User;

public class test_hibernate {
	public static void main(String[] args) {
		String name = "Chong";
		String password = "123";
		String email = "chong@gmail.com";
		
		
		User user = new User();
		user.setUser_name(name);
		user.setPassword(password);
		user.setEmail(email);
		
		try {
			UserDAO userDao = new UserDAO();
			userDao.addEntity(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
