package modelMB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import service.JobService;
import data.entity.Job;

@ManagedBean
@SessionScoped
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
	private List<String> skills = new ArrayList<String>();
	private List<ApplicationBean> applications = new ArrayList<ApplicationBean>();
	private Boolean active;
	private UserBean author;
	private String skillInput;

	public void init() {
        if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()) { 
            return; // Skip ajax requests.
        }
        
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();

		String jobId = request.getParameter("id");

		if (jobId != null) {
			jobService.getJobById(this, Integer.parseInt(jobId), true);
			//System.out.println(this);
		}
		
		StringBuilder skillString = new StringBuilder();
		for (String skill : this.getSkills()){
			skillString.append(skill);
			skillString.append(",");
		}
		this.setSkillInput(skillString.toString());
	}

	public void loadFromDB(Integer id) {
		JobService.loadFormDB(this, id);
	}

	public void loadFromEntity(Job entity) {
		JobService.loadFromEntity(this, entity, true);
	}
	
	public void saveOrUpdate() {
		int id = JobService.saveOrUpdate(this);
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		
		if ( id > 0 ) {
			try {
				ec.redirect("job_description.xhtml?id=" + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			String message = "Fail to create/update the job.";
			FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, message, null);
			 
			throw new ValidatorException(facesMessage);
		}
		
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

	public Integer getId() {
		return id;
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
	
	public String getSkillInput() {
		return skillInput;
	}

	public void setSkillInput(String skillInput) {
		this.skillInput = skillInput;
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("JobBean:\n");
		output.append("= Full Record: " + this.isFull_record());
		output.append("=" + this.getId() + "\n");
		output.append("=" + this.getTitle() + "\n");
		output.append("=" + this.getDescription() + "\n");
		output.append("=" + this.getExperience() + "\n");
		output.append("=" + this.getRequirement() + "\n");
		output.append("=" + this.getResponsibility() + "\n");
		output.append("=" + this.getApplications() + "\n");
		output.append("=" + this.getCompany() + "\n");
		output.append("=" + this.getAuthor() + "\n");
		output.append("= Skills: \n");
		for ( String skillBean : this.getSkills()){
			output.append("==" + skillBean + "\n");
		}
		return output.toString();
	}
}
