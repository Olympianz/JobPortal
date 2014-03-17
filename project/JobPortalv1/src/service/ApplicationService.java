package service;

import java.util.Calendar;

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
		Application app = null;
		Integer id = appBean.getId();

		if (id != null && id >= 0) {
			// Get existing record
			app = applicationDao.getEntityById(id);
		} else {
			// Create new record
			app = new Application(SessionCtl.getLoggedInUser().getUser_name());
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
		
		int result = -1;
		if ( user != null && asset != null && job != null && status != null) {
			app.setApplicant(user);
			app.setAsset(asset);
			app.setPosition(job);
			app.setStatus(status);
			app.setUpdate_user(SessionCtl.getLoggedInUser().getUser_name());
			app.setUpdate_time(Calendar.getInstance());
			result = applicationDao.saveOrUpdate(app);
		}
		
		return result;
	}
}
