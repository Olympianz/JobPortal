package service;

import java.util.ArrayList;
import java.util.List;

import modelMB.ApplicationBean;
import modelMB.CompanyBean;
import modelMB.JobBean;
import modelMB.UserBean;
import data.dao.JobDAO;
import data.entity.Application;
import data.entity.Job;
import data.entity.Skill;

public class JobService {

	JobDAO jobDao = new JobDAO();
	
	public List<Job> all() {
		return jobDao.listEntities();
	}
	
	public void getJobById(JobBean jobBean, Integer jobId, boolean deepLoad) {
		Job job = jobDao.getEntityById(jobId);
		
		if (job != null) {
			List<String> skills = new ArrayList<String>();
			List<ApplicationBean> applications = new ArrayList<ApplicationBean>();
			
			if (deepLoad){
				// Create skill string list
				for (Skill skill : job.getSkills()){
					skills.add(skill.getName());
				}
				
				// Create application bean list
				ApplicationBean appBean = null;
				for (Application app : job.getApplications() ) {
					appBean = new ApplicationBean();
					ApplicationService.loadFromEntity(appBean, app);
					applications.add(appBean);
				}
				jobBean.setApplications(applications);
			}
			

			jobBean.setId(job.getId());
			jobBean.setTitle(job.getTitle());
			jobBean.setDescription(job.getDescription());
			jobBean.setRequirement(job.getRequirement());
			jobBean.setResponsibility(job.getResponsibility());
			jobBean.setExperience(job.getExperience().getName());
			
			CompanyBean companyBean = new CompanyBean();
			companyBean.loadFromEntity(job.getAuthor().getCompany());
			jobBean.setCompany(companyBean);
			jobBean.setActive(job.getActive().equals('Y')? true : false);
			
			jobBean.setSkills(skills);
			jobBean.setApplications(applications);
		}
	}
	

	public static void loadFromEntity(JobBean jobBean, Job job, boolean deepLoad) {
		jobBean.setFull_record(false);
		jobBean.setActive(job.getActive().equals('Y'));
		
		CompanyBean companyBean = new CompanyBean();
		CompanyService.loadFromEntity(companyBean, job.getAuthor().getCompany());
		jobBean.setCompany(companyBean);
		
		jobBean.setDescription(job.getDescription());
		jobBean.setExperience(job.getExperience().getName());
		jobBean.setId(job.getId());
		jobBean.setRequirement(job.getRequirement());
		jobBean.setResponsibility(job.getResponsibility());
		jobBean.setTitle(job.getTitle());
		
		if (deepLoad) {
			jobBean.setFull_record(true);
			
			List<ApplicationBean> applications = new ArrayList<ApplicationBean>();
			ApplicationBean appBean = null;
			for (Application app : job.getApplications() ) {
				appBean = new ApplicationBean();
				ApplicationService.loadFromEntity(appBean, app);
				applications.add(appBean);
			}
			jobBean.setApplications(applications);
			
			List<String> skills = new ArrayList<String>();
			for (Skill skill : job.getSkills()) {
				skills.add(skill.getName());
			}
			jobBean.setSkills(skills);
		}
	}
}
