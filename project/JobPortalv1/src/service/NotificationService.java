package service;

import modelMB.NotificationBean;
import modelMB.UserBean;
import data.dao.NotificationDAO;
import data.entity.Notification;

public class NotificationService {
	NotificationDAO notifDao = new NotificationDAO();

	public static void loadFromEntity(NotificationBean notifBean,
			Notification notif) {
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
}
