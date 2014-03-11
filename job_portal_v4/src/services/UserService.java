package services;

import java.util.Calendar;
import java.util.List;

import data.TimeStamp;
import data.dao.UserDAO;
import data.entity.User;

public class UserService implements MetaService {

	UserDAO userDao = new UserDAO();
	
	public Integer addEntity(Object obj) {
		// TODO Auto-generated method stub
		
		return userDao.addEntity(obj);
	}

	public void updateEntity(Integer Id, Object obj_new) {
		// TODO Auto-generated method stub
		
		userDao.updateEntity(Id, obj_new);
	}

	public void deleteEntity(Integer Id) {
		// TODO Auto-generated method stub
		
		userDao.deleteEntity(Id);
	}

	public List<User> listEntities() {
		// TODO Auto-generated method stub

		return userDao.listEntities();
	}

	public Integer addEntity_TS(Object obj) {
		// TODO Auto-generated method stub
		
		Integer UserId = null;
		if (obj instanceof User) {
			Calendar timeStamp = TimeStamp.getTimeStamp();
			((User)obj).setCreation_timestamp(timeStamp);
			UserId = addEntity(obj);
		} else {
			throw new ClassCastException("Not a valid type for User table");
		}
		
		return UserId;
	}

	public void updateEntity_TS(Integer Id, Object obj_new) {
		// TODO Auto-generated method stub
		
		if (obj_new instanceof User) {
			Calendar timeStamp = TimeStamp.getTimeStamp();
			((User) obj_new).setUpdate_timestamp(timeStamp);
			updateEntity(Id, obj_new);
		} else {
			throw new ClassCastException("Not a valid type for User table");
		}
	}

}
