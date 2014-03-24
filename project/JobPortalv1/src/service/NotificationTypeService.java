package service;

import java.util.ArrayList;
import java.util.List;

import data.dao.NotificationTypeDAO;
import data.entity.NotificationType;

public class NotificationTypeService {
	static final NotificationTypeDAO notifTypeDao = new NotificationTypeDAO();
	
	public static List<String> all() {
		List<NotificationType> notifTypes = notifTypeDao.listEntities();
		List<String> notifTypeString = new ArrayList<String>();
		
		for(NotificationType notifType : notifTypes) {
			notifTypeString.add(notifType.getName());
		}
		
		return notifTypeString;
	}

}

