package modelMB;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import service.JobService;
import data.entity.Job;
import data.entity.User;

@ManagedBean
public class JobBean {
	JobService jobService = new JobService();

	private boolean full_record;
	private Integer id;
	private String title;
	private String description;
	private String requirement;
	private String responsibility;
	private String experience;
	private CompanyBean company;
	private List<String> skills;
	private List<ApplicationBean> applications;
	private Boolean active;
	private UserBean author;

	public void init() {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();

		String jobId = request.getParameter("jobId");

		if (jobId != null) {
			jobService.getJobById(this, Integer.parseInt(jobId), true);
		}
	}

	public void loadFromDB(Integer id) {

	}

	public void loadFromEntity(Job entity) {

	}

	public boolean isFull_record() {
		return full_record;
	}

	public void setFull_record(boolean full_record) {
		this.full_record = full_record;
	}

	public Boolean getActive() {
		return active;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public CompanyBean getCompany() {
		return company;
	}

	public void setCompany(CompanyBean company) {
		this.company = company;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<ApplicationBean> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationBean> applications) {
		this.applications = applications;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public UserBean getAuthor() {
		return author;
	}

	public void setAuthor(UserBean author) {
		this.author = author;
	}
}
