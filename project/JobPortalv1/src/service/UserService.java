package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import modelMB.ApplicationBean;
import modelMB.AssetBean;
import modelMB.CompanyBean;
import modelMB.ContactBean;
import modelMB.JobBean;
import modelMB.NotificationBean;
import modelMB.SkillBean;
import modelMB.UserBean;
import data.dao.UserDAO;
import data.entity.Application;
import data.entity.Asset;
import data.entity.Job;
import data.entity.Notification;
import data.entity.Skill;
import data.entity.User;

public class UserService implements MetaService {

	static final UserDAO userDao = new UserDAO();

	public Integer addEntity(Object obj) {

		return userDao.addEntity(obj);
	}

	public void updateEntity(Integer Id, Object obj_new) {

		userDao.updateEntity(Id, obj_new);
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
			((User) obj).setCreation_timestamp(timeStamp);
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
	 * Check if the email and password pair matches the database record. If yes,
	 * update new sessionId to database. Otherwise, return null.
	 * 
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
	 * 
	 * @param user
	 *            : fetched from HTTP session
	 */
	public void logout(User user) {
		if (user != null) {
			user.setSession_id("");
			userDao.updateEntity(user);
		}
	}

	public User checkAutoLogin(String email, String sessionId,
			String newSessionId) {
		User user = userDao.getUserByEmailSessionId(email, newSessionId);

		if (user != null) {
			user.setSession_id(newSessionId);
			userDao.updateEntity(user);
		}

		return user;
	}

	public static void loadFromEntity(UserBean userBean, User user,
			boolean deepLoad) {
		userBean.setFull_record(false);
		
		CompanyBean companyBean = new CompanyBean();
		CompanyService.loadFromEntity(companyBean, user.getCompany());
		userBean.setCompany(companyBean);
		
		ContactBean contactBean = new ContactBean();
		ContactService.loadFromEntity(contactBean, user.getContact());
		userBean.setContact(contactBean);
		userBean.setCreation_timestamp(user.getCreation_timestamp());
		userBean.setEmail(user.getEmail());
		userBean.setExperience(user.getExperience().getName());
		userBean.setRole(user.getRole().getRole_n());
		userBean.setUpdate_timestamp(user.getUpdate_timestamp());
		userBean.setUser_id(user.getUser_id());
		userBean.setUser_name(user.getUser_name());
		
		if (deepLoad) {
			userBean.setFull_record(true);
			// Application list
			List<ApplicationBean> applications = new ArrayList<ApplicationBean>();
			ApplicationBean appBean = null;
			for(Application app : user.getApplications()){
				appBean = new ApplicationBean();
				ApplicationService.loadFromEntity(appBean, app);
				applications.add(appBean);
			}
			userBean.setApplications(applications);
			
			// Asset list
			List<AssetBean> assets = new ArrayList<AssetBean>();
			AssetBean assetBean = null;
			for(Asset asset : user.getAssets()){
				assetBean = new AssetBean();
				AssetService.loadFromEntity(assetBean, asset);
				assets.add(assetBean);
			}
			userBean.setAssets(assets);
			
			// Post job list
			List<JobBean> jobs = new ArrayList<JobBean>();
			JobBean jobBean = null;
			for(Job job : user.getPost_jobs()) {
				jobBean = new JobBean();
				JobService.loadFromEntity(jobBean, job, false);
				jobs.add(jobBean);
			}
			userBean.setPost_jobs(jobs);
			
			// Saved job list
			List<JobBean> saved_jobs = new ArrayList<JobBean>();
			JobBean sJobBean = null;
			for(Job job: user.getSaved_jobs()) {
				jobBean = new JobBean();
				JobService.loadFromEntity(sJobBean, job, false);
				saved_jobs.add(sJobBean);
			}
			userBean.setSaved_jobs(saved_jobs);
			
			// Received notification list
			List<NotificationBean> recv_notif = new ArrayList<NotificationBean>();
			NotificationBean rNotifBean = null;
			for(Notification notif : user.getRecv_notif()) {
				rNotifBean = new NotificationBean();
				NotificationService.loadFromEntity(rNotifBean, notif);
				recv_notif.add(rNotifBean);
			}
			userBean.setRecv_notif(recv_notif);
			
			// Sent notification list
			List<NotificationBean> sent_notif = new ArrayList<NotificationBean>();
			NotificationBean sNotifBean = null;
			for(Notification notif : user.getSent_notif()) {
				sNotifBean = new NotificationBean();
				NotificationService.loadFromEntity(sNotifBean, notif);
				sent_notif.add(sNotifBean);
			}
			userBean.setSent_notif(sent_notif);
			
			// Skill list
			List<SkillBean> skills = new ArrayList<SkillBean>();
			SkillBean skillBean = null;
			for(Skill skill : user.getSkills()) {
				skillBean = new SkillBean();
				SkillService.loadFromEntity(skillBean, skill);
				skills.add(skillBean);
			}
			userBean.setSkills(skills);
		}
	}

	public static void loadFromDB(UserBean userBean, Integer id) {
		User user = userDao.getEntityById(id);
		loadFromEntity(userBean, user, true);
	}
}
