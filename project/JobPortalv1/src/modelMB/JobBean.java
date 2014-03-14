package modelMB;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import service.JobService;
import data.entity.Job;


@ManagedBean
public class JobBean {
	JobService jobService = new JobService();

	private int id;
	private String title;
	private String description;
	private String requirement;
	private String responsibility;
	private String experience;
	private String company;
	private List<String> skills;
	private List<UserBean> applicants;
	private boolean active;
	
	public void init() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest)ec.getRequest();
		
		String jobId = request.getParameter("jobId");
		
		if (jobId != null) {
			jobService.getJobById(this, Integer.parseInt(jobId), true);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<UserBean> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<UserBean> applicants) {
		this.applicants = applicants;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
