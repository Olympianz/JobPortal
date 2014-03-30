package modelMB;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import service.ApplicationService;
import service.AssetService;
import service.CompanyService;
import service.JobService;
import service.UserService;
import util.SessionCtl;
import data.entity.Job;
import data.entity.User;

@ManagedBean
@ViewScoped
public class JobBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8983329096246519715L;

	private boolean full_record;
	private Integer id;
	private String title;
	private String description;
	private String requirement;
	private String responsibility;
	private String experience;
	private CompanyBean company = new CompanyBean();;
	private List<String> skills = new ArrayList<String>();
	private List<ApplicationBean> applications = new ArrayList<ApplicationBean>();
	private Boolean active;
	private UserBean author = new UserBean();

	private Integer asset_id = -1;
	private String skillInput;

	public void init() {
		if (FacesContext.getCurrentInstance().getPartialViewContext()
				.isAjaxRequest()) {
			return; // Skip ajax requests.
		}

		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();

		String jobId = request.getParameter("id");

		if (jobId != null) {
			JobService.getJobById(this, Integer.parseInt(jobId), true);
			// System.out.println(this);
		} else {
			User current_user = SessionCtl.getLoggedInUser();
			if (current_user != null) {
				UserBean userBean = new UserBean();
				UserService.loadFromEntity(userBean, current_user, false);
				author = userBean;
			}
		}

		StringBuilder skillString = new StringBuilder();
		if (this.getSkills() != null) {
			for (String skill : this.getSkills()) {
				skillString.append(skill);
				skillString.append(",");
			}
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
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();

		if (id > 0) {
			try {
				ec.redirect("job_description.xhtml?id=" + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {			 
			FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_INFO,"Info message", 
					"Fail to create/update the job.");
			FacesContext.getCurrentInstance().addMessage(null, 
					facesMessage);  
		}

	}
	
	//savejob
	 public void saveInfo(ActionEvent actionEvent) {  
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info message", "Job successfully saved"));  
	 }  
	   
	 public void loginInfo(ActionEvent actionEvent) {  
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info message", "Please log in before you save the job"));  
	 } 
	   
	
	
	 public void saveJob(ActionEvent actionEvent){
		if (SessionCtl.getLoggedInUser() != null &&	JobService.saveJob(this.id) >= 0) {
			saveInfo(actionEvent); 
		}else{
			loginInfo(actionEvent);
		}
	}
	 
	//check if this job is a saved job
	public boolean checkSavedJob() {
		return JobService.searchJob(this.id);
	}
	 
    //delete job
	public void deleteJob(){
		JobService.deleteJob(this.id);
	}

	public void fetchCompany() {
		CompanyService.loadFromDBByName(this.getCompany(), this.getCompany()
				.getName());
	}

	public void apply() {
		User current_user = SessionCtl.getLoggedInUser();
		if (current_user == null || asset_id < 0
				|| !current_user.getRole().getRole_n().equals("jobseeker"))
			return;

		UserBean userBean = new UserBean();
		UserService.loadFromEntity(userBean, current_user, false);
		
		AssetBean assetBean = new AssetBean();
		AssetService.loadFromDB(assetBean, this.getAsset_id());
		
		String status = "Pending";
		
		ApplicationBean appBean = new ApplicationBean();
		appBean.setApplicant(userBean);
		appBean.setJob(this);
		appBean.setStatus(status);
		appBean.setAsset(assetBean);
		
		int id = ApplicationService.saveOrUpdate(appBean);
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		
		if (id >= 0) {
			try {
				ec.redirect("application_description.xhtml?id=" + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			String message = "Fail to apply this job.";
			FacesMessage facesMessage = new FacesMessage(
					FacesMessage.SEVERITY_WARN, message, null);

			throw new ValidatorException(facesMessage);
		}
	}
	
	public boolean owned() {

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession)ec.getSession(false);

		User user = null;
		if(session != null && author != null &&
			(user = (User)session.getAttribute("loggedin_user")) != null &&
			user.getUser_id() == author.getUser_id()) {
			return true;
		}
		
		return false;
	}

	// ==============================================================
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

	public int getAsset_id() {
		return asset_id;
	}

	public void setAsset_id(int asset_id) {
		this.asset_id = asset_id;
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
		for (String skillBean : this.getSkills()) {
			output.append("==" + skillBean + "\n");
		}
		return output.toString();
	}
}
