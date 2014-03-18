package service;

import java.util.Calendar;

import util.SessionCtl;
import modelMB.ContactBean;
import modelMB.NotificationBean;
import modelMB.UserBean;
import data.dao.ContactTypeDAO;
import data.dao.NotificationDAO;
import data.dao.NotificationTypeDAO;
import data.dao.StateDAO;
import data.dao.UserDAO;
import data.entity.Contact;
import data.entity.Contact_type;
import data.entity.Location;
import data.entity.Notification;
import data.entity.NotificationType;
import data.entity.State;
import data.entity.User;

public class NotificationService {
	static final NotificationDAO notifDao = new NotificationDAO();

	public static void loadFromEntity(NotificationBean notifBean,
			Notification notif) {
		if ( notifBean == null || notif == null)
			return;
		
		notifBean.setContent(notif.getContent());
		
		UserBean fromUser = new UserBean();
		UserService.loadFromEntity(fromUser, notif.getFrom(), false);
		notifBean.setFromUser(fromUser);
		
		UserBean toUser = new UserBean();
		UserService.loadFromEntity(toUser, notif.getTo(), false);
		notifBean.setToUser(toUser);
		
		notifBean.setId(notif.getId());
		notifBean.setRead(notif.getRead().equals("Y"));
		notifBean.setTitle(notif.getTitle());
		notifBean.setType(notif.getType().getName());
	}
	
	public static void loadFromDB(NotificationBean notifBean, Integer id) {
		Notification notif = notifDao.getEntityById(id);
		loadFromEntity(notifBean, notif);
	}

	public static int saveOrUpdate(NotificationBean notifBean) {
		Notification notif = null;
		Integer id = notifBean.getId();

		if (id != null && id >= 0) {
			// Get existing record
			notif = notifDao.getEntityById(id);
		} else {
			// Create new record
			notif = new Notification(SessionCtl.getLoggedInUser().getUser_name());
		}

		// Fetch all necessary object from database
		// Copy new data from bean to entity
		UserDAO userDao = new UserDAO();
		User fromUser = userDao.getEntityById(notifBean.getFromUser().getUser_id());
		User toUser = userDao.getEntityById(notifBean.getToUser().getUser_id());
		
		NotificationTypeDAO notifTypeDao = new NotificationTypeDAO();
		NotificationType notifType = notifTypeDao.getByName(notifBean.getType());

		int result = -1;
		if (fromUser != null && toUser != null && notifType != null) {
			notif.setContent(notifBean.getContent());
			notif.setFrom(fromUser);
			notif.setTo(toUser);
			notif.setRead(notifBean.getRead()? "Y" : "N");
			notif.setTitle(notifBean.getTitle());
			notif.setType(notifType);
			
			notif.setUpdate_user(SessionCtl.getLoggedInUser()
					.getUser_name());
			notif.setUpdate_time(Calendar.getInstance());
			result = notifDao.saveOrUpdate(notif);
		}

		return result;
	}
}
