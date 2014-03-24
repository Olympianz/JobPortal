package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import util.SessionCtl;
import modelMB.ApplicationBean;
import modelMB.AssetBean;
import modelMB.JobBean;
import modelMB.UserBean;
import data.dao.ApplicationDAO;
import data.dao.ApplicationStatusDAO;
import data.dao.AssetDAO;
import data.dao.JobDAO;
import data.dao.UserDAO;
import data.entity.Application;
import data.entity.ApplicationStatus;
import data.entity.Asset;
import data.entity.Job;
import data.entity.User;

public class ApplicationService {
	static final ApplicationDAO applicationDao = new ApplicationDAO();

	public static void loadFromEntity(ApplicationBean appBean, Application app) {
		if (appBean == null || app == null)
			return;

		AssetBean assetBean = new AssetBean();
		AssetService.loadFromEntity(assetBean, app.getAsset());
		appBean.setAsset(assetBean);

		UserBean applicant = new UserBean();
		UserService.loadFromEntity(applicant, app.getApplicant(), false);
		appBean.setApplicant(applicant);

		JobBean jobBean = new JobBean();
		JobService.loadFromEntity(jobBean, app.getPosition(), false);
		appBean.setJob(jobBean);

		appBean.setId(app.getId());
		appBean.setStatus(app.getStatus().getName());
	}

	public static void loadFromDB(ApplicationBean appBean, Integer id) {
		Application app = applicationDao.getEntityById(id);
		loadFromEntity(appBean, app);
	}

	public static int saveOrUpdate(ApplicationBean appBean) {
		int result = -1;
		String loggedInUserName = null;
		User loggedInUser = SessionCtl.getLoggedInUser();
		if (loggedInUser == null)
			loggedInUserName = "sysdba";
		// return -1;
		else
			loggedInUserName = loggedInUser.getUser_name();
		Application app = null;
		Integer id = appBean.getId();

		if (id != null && id >= 0) {
			// Get existing record
			app = applicationDao.getEntityById(id);
		} else {
			// Create new record
			User user = SessionCtl.getLoggedInUser();
			if (user == null) {
				app = new Application();
			} else {
				app = new Application(user.getUser_name());
			}
		}

		// Fetch all necessary object from database
		// Copy new data from bean to entity
		UserDAO userDao = new UserDAO();
		User user = userDao.getEntityById(appBean.getApplicant().getUser_id());

		AssetDAO assetDao = new AssetDAO();
		Asset asset = assetDao.getEntityById(appBean.getAsset().getId());

		JobDAO jobDao = new JobDAO();
		Job job = jobDao.getEntityById(appBean.getJob().getId());

		ApplicationStatusDAO appStatusDao = new ApplicationStatusDAO();
		ApplicationStatus status = appStatusDao.getByName(appBean.getStatus());

		if (user != null && asset != null && job != null && status != null) {
			app.setApplicant(user);
			app.setAsset(asset);
			app.setPosition(job);
			app.setStatus(status);
			app.setUpdate_user(loggedInUserName);
			app.setUpdate_time(Calendar.getInstance());

			result = applicationDao.saveOrUpdate(app);
		}

		return result;
	}

	public static List<ApplicationBean> search(String query) {
		List<Application> apps = applicationDao.search(query);
		List<ApplicationBean> appBeans = new ArrayList<ApplicationBean>();
		ApplicationBean appBean = null;

		if (apps != null) {
			for (Application app : apps) {
				appBean = new ApplicationBean();
				loadFromEntity(appBean, app);
				appBeans.add(appBean);
			}
		}

		return appBeans;
	}
}
