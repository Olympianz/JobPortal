package service;

import modelMB.ApplicationBean;
import modelMB.AssetBean;
import modelMB.JobBean;
import modelMB.UserBean;
import data.dao.ApplicationDAO;
import data.entity.Application;

public class ApplicationService {
	ApplicationDAO applicationDao = new ApplicationDAO();
	
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
}
