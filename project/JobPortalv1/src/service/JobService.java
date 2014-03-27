package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import util.SessionCtl;
import modelMB.ApplicationBean;
import modelMB.CompanyBean;
import modelMB.JobBean;
import modelMB.UserBean;
import data.dao.ExperienceDAO;
import data.dao.JobDAO;
import data.dao.SkillDAO;
import data.dao.UserDAO;
import data.entity.Application;
import data.entity.Experience;
import data.entity.Job;
import data.entity.Skill;
import data.entity.User;

public class JobService {

	static final JobDAO jobDao = new JobDAO();
	//
	static final UserDAO userDao = new UserDAO();
	
	//saveJob
	public static void saveJob(int id){
		User user = null;
		Job savejob = jobDao.getEntityById(id);
		user = SessionCtl.getLoggedInUser();
		user.getSaved_jobs().add(savejob);
		userDao.saveOrUpdate(user);
	
	}
	
	//delete job
	public static void deleteJob(int id){
		User user = null;
		user = SessionCtl.getLoggedInUser();

		for(Job job : user.getSaved_jobs()) {
			if(job.getId() == id) {
				user.getSaved_jobs().remove(job);
				break;
			}
		}
		
		userDao.saveOrUpdate(user);
	}
	
    //search job by job id  in user's saved_job
	public static boolean searchJob(int id){
		User user = null;
		user = SessionCtl.getLoggedInUser();
		for (Job job : user.getSaved_jobs()) {
			if( job.getId() == id)
				return true;
		}
		return false;
	}
	
	public static List<JobBean> all() {
		List<Job> jobs = jobDao.listEntities();
		List<JobBean> jobBeans = new ArrayList<JobBean>();
		JobBean jobBean = null;
		
		for (Job job : jobs) {
			jobBean = new JobBean();
			loadFromEntity(jobBean, job, false);
			jobBeans.add(jobBean);
		}
		
		return jobBeans;
	}
	
	public static List<JobBean> search(String query) {
		List<Job> jobs = jobDao.search(query);
		List<JobBean> jobBeans = new ArrayList<JobBean>();
		JobBean jobBean = null;
		
		for (Job job : jobs) {
			jobBean = new JobBean();
			loadFromEntity(jobBean, job, false);
			jobBeans.add(jobBean);
		}
		
		return jobBeans;
	}
	
	public static void getJobById(JobBean jobBean, Integer jobId, boolean deepLoad) {
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

			
			UserBean userBean = new UserBean();
			UserService.loadFromEntity(userBean, job.getAuthor(), false);
			jobBean.setAuthor(userBean);
			
			jobBean.setSkills(skills);
			jobBean.setApplications(applications);
		}
	}
	

	public static void loadFromEntity(JobBean jobBean, Job job, boolean deepLoad) {
		if ( jobBean == null || job == null )
			return;
		
		jobBean.setFull_record(false);
		jobBean.setActive(job.getActive().equals('Y'));
		
		CompanyBean companyBean = new CompanyBean();
		CompanyService.loadFromEntity(companyBean, job.getAuthor().getCompany());
		jobBean.setCompany(companyBean);
		
		UserBean userBean = new UserBean();
		UserService.loadFromEntity(userBean, job.getAuthor(), false);
		jobBean.setAuthor(userBean);
		
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
	
	public static void loadFormDB(JobBean jobBean, Integer id) {
		Job job = jobDao.getEntityById(id);
		loadFromEntity(jobBean, job, true);
	}

	public static int saveOrUpdate(JobBean jobBean) {
		Job job = null;
		Integer id = jobBean.getId();

		if (id != null && id >= 0) {
			// Get existing record
			job = jobDao.getEntityById(id);
		} else {
			// Create new record
			job = new Job(SessionCtl.getLoggedInUser().getUser_name());
		}

		// Fetch all necessary object from database
		// Copy new data from bean to entity
		ExperienceDAO expDao = new ExperienceDAO();
		Experience exp = expDao.getByName(jobBean.getExperience());
		
		UserDAO userDao = new UserDAO();
		User author = userDao.getEntityById(jobBean.getAuthor().getUser_id());
		
		
		SkillDAO skillDao = new SkillDAO();
		Skill skill = null;
		List<Skill> skills = new ArrayList<Skill>();
		String [] skillStrings = jobBean.getSkillInput().split(",");
		for (String skillString : skillStrings) {
			skill = skillDao.getEntityByName(skillString);
			if (skill == null){
				skill = new Skill(SessionCtl.getLoggedInUser().getUser_name());
				skill.setName(skillString);
			}
			skills.add(skill);
		}
		
		int result = -1;
		if (exp != null && author != null) {
			job.setDescription(jobBean.getDescription());
			job.setRequirement(jobBean.getRequirement());
			job.setResponsibility(jobBean.getResponsibility());
			job.setTitle(jobBean.getTitle());
			
			job.setExperience(exp);
			job.setAuthor(author);
			job.setSkills(skills);
			
			job.setUpdate_user(SessionCtl.getLoggedInUser()
					.getUser_name());
			job.setUpdate_time(Calendar.getInstance());
			result = jobDao.saveOrUpdate(job);
		}

		return result;
	}
}
