package service;

import java.util.ArrayList;
import java.util.List;

import modelMB.JobBean;
import modelMB.UserBean;
import data.dao.JobDAO;
import data.entity.Application;
import data.entity.Job;
import data.entity.Skill;
import data.entity.User;

public class JobService {

	JobDAO jobDao = new JobDAO();
	
	public List<Job> all() {
		return jobDao.listEntities();
	}
	
	public void getJobById(JobBean jobBean, Integer jobId, boolean deepLoad) {
		UserService userService = new UserService();
		Job job = jobDao.getJobById(jobId);
		
		if (job != null) {
			List<String> skills = new ArrayList<String>();
			List<UserBean> applicants = new ArrayList<UserBean>();
			
			if (deepLoad){
				// Create skill string list
				for (Skill skill : job.getSkills()){
					skills.add(skill.getName());
				}
				
				// Create application bean list
				UserBean userBean = null;
				for (Application app : job.getApplications()) {
					userBean = new UserBean();
					userService.loadFromEntity(userBean, app.getApplicant(), false);
					applicants.add(userBean);
				}
			}
			

			jobBean.setId(job.getId());
			jobBean.setTitle(job.getTitle());
			jobBean.setDescription(job.getDescription());
			jobBean.setRequirement(job.getRequirement());
			jobBean.setResponsibility(job.getResponsibility());
			jobBean.setExperience(job.getExperience().getName());
			jobBean.setCompany(job.getAuthor().getUpdate_user_name());
			jobBean.setActive(job.getActive().equals('Y')? true : false);
			
			jobBean.setSkills(skills);
			jobBean.setApplicants(applicants);
		}
	}
}
