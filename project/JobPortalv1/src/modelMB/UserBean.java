package modelMB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import data.entity.User;
import service.UserService;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -84599783517567922L;

	private boolean full_record;
	private Integer user_id;
	private String user_name;
	private String email;
	private String password;
	private Calendar creation_timestamp;
	private Calendar update_timestamp;

	private String experience;
	private String role;
	private ContactBean contact;
	private CompanyBean company;
	private String skillInput;

	private List<JobBean> saved_jobs = new ArrayList<JobBean>();

	private List<NotificationBean> sent_notif = new ArrayList<NotificationBean>();
	private List<NotificationBean> recv_notif = new ArrayList<NotificationBean>();

	private List<SkillBean> skills = new ArrayList<SkillBean>();
	private List<AssetBean> assets = new ArrayList<AssetBean>();
	private List<ApplicationBean> applications = new ArrayList<ApplicationBean>();

	//delete account
	public void deactiveUser(){
		UserService.deactiveUser();
	}
		
	public void clear() {
		this.setEmail("");
	}
	
	public void load() {
		UserService.loadFromDB(this, 62);
	}
	
	public void loadFromDB(int id) {
		UserService.loadFromDB(this, id);
	}

	public void loadFromEntity(User entity) {
		UserService.loadFromEntity(this, entity, true);
	}
	
	public void saveOrUpdate() {
		UserService.saveOrUpdate(this);
	}

	public boolean isFull_record() {
		return full_record;
	}

	public void setFull_record(boolean full_record) {
		this.full_record = full_record;
	}
	
	public String getSkillInput() {
		return skillInput;
	}
	
	public void setSkillInput(String skillInput) {
		this.skillInput = skillInput;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Calendar getCreation_timestamp() {
		return creation_timestamp;
	}

	public void setCreation_timestamp(Calendar creation_timestamp) {
		this.creation_timestamp = creation_timestamp;
	}

	public Calendar getUpdate_timestamp() {
		return update_timestamp;
	}

	public void setUpdate_timestamp(Calendar update_timestamp) {
		this.update_timestamp = update_timestamp;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public ContactBean getContact() {
		return contact;
	}

	public void setContact(ContactBean contact) {
		this.contact = contact;
	}

	public CompanyBean getCompany() {
		return company;
	}

	public void setCompany(CompanyBean company) {
		this.company = company;
	}

	public List<JobBean> getSaved_jobs() {
		return saved_jobs;
	}

	public void setSaved_jobs(List<JobBean> saved_jobs) {
		this.saved_jobs = saved_jobs;
	}

	public List<JobBean> getPost_jobs() {
		return post_jobs;
	}

	public void setPost_jobs(List<JobBean> post_jobs) {
		this.post_jobs = post_jobs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private List<JobBean> post_jobs = new ArrayList<JobBean>();

	public List<NotificationBean> getSent_notif() {
		return sent_notif;
	}

	public void setSent_notif(List<NotificationBean> sent_notif) {
		this.sent_notif = sent_notif;
	}

	public List<NotificationBean> getRecv_notif() {
		return recv_notif;
	}

	public void setRecv_notif(List<NotificationBean> recv_notif) {
		this.recv_notif = recv_notif;
	}

	public List<SkillBean> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillBean> skills) {
		this.skills = skills;
	}

	public List<AssetBean> getAssets() {
		return assets;
	}

	public void setAssets(List<AssetBean> assets) {
		this.assets = assets;
	}

	public List<ApplicationBean> getApplications() {
		return applications;
	}

	public void setApplications(List<ApplicationBean> applications) {
		this.applications = applications;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("UserBean:\n");
		output.append("= Full Record: " + this.isFull_record());
		output.append("=" + this.getUser_id() + "\n");
		output.append("=" + this.getUser_name() + "\n");
		output.append("=" + this.getRole() + "\n");
		output.append("=" + this.getEmail() + "\n");
		output.append("=" + this.getExperience() + "\n");
		output.append("=" + this.getPassword() + "\n");
		output.append("=" + this.getCompany() + "\n");
		output.append("=" + this.getContact() + "\n");
		output.append("=" + this.getUpdate_timestamp() + "\n");
		output.append("=" + this.getCreation_timestamp() + "\n");

		output.append("= Skills: \n");
		for ( SkillBean skillBean : this.getSkills()){
			output.append("==" + skillBean + "\n");
		}
		output.append("= Assets: \n");
		for ( AssetBean assetBean : this.getAssets()) {
			output.append("==" + assetBean + "\n");
		}
		output.append("= Saved Jobs: \n");
		for ( JobBean jobBean : this.getSaved_jobs()){
			output.append("==" + jobBean + "\n");
		}
		output.append("= Applications :\n");
		for ( ApplicationBean appBean : this.getApplications()){
			output.append("==" + appBean + "\n");
		}
		output.append("= Post jobs\n");
		for ( JobBean jobBean : this.getPost_jobs()){
			output.append("==" + jobBean + "\n");
		}
		output.append("= Recv notif:\n");
		for ( NotificationBean notifBean : this.getRecv_notif()) {
			output.append("==" + notifBean + "\n");
		}
		output.append("= Sent notif:\n");
		for ( NotificationBean notifBean : this.getSent_notif()) {
			output.append("==" + notifBean + "\n");
		}
		return output.toString();
	}
	
	public void validatePassword(ComponentSystemEvent event) {
		 
		  FacesContext fc = FacesContext.getCurrentInstance();
	 
		  UIComponent components = event.getComponent();
	 
		  // get password
		  UIInput uiInputPassword = (UIInput) components.findComponent("password");
		  String password = uiInputPassword.getLocalValue() == null ? ""
			: uiInputPassword.getLocalValue().toString();
		  String passwordId = uiInputPassword.getClientId();
	 
		  // get confirm password
		  UIInput uiInputConfirmPassword = (UIInput) components.findComponent("password3");
		  String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
			: uiInputConfirmPassword.getLocalValue().toString();
	 
		  // Let required="true" do its job.
		  if (password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		  }
	 
		  if (!password.equals(confirmPassword)) {
	 
			FacesMessage msg = new FacesMessage("Password must match confirm password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(passwordId, msg);
			fc.renderResponse();
	 
		  }
	}
	
	public void validatePassword1(ComponentSystemEvent event) {
		 
		  FacesContext fc = FacesContext.getCurrentInstance();
	 
		  UIComponent components = event.getComponent();
	 
		  // get password
		  UIInput uiInputPassword = (UIInput) components.findComponent("password1");
		  String password = uiInputPassword.getLocalValue() == null ? ""
			: uiInputPassword.getLocalValue().toString();
		  String passwordId = uiInputPassword.getClientId();
	 
		  // get confirm password
		  UIInput uiInputConfirmPassword = (UIInput) components.findComponent("password2");
		  String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
			: uiInputConfirmPassword.getLocalValue().toString();
	 
		  // Let required="true" do its job.
		  if (password.isEmpty() || confirmPassword.isEmpty()) {
			return;
		  }
	 
		  if (!password.equals(confirmPassword)) {
	 
			FacesMessage msg = new FacesMessage("Password must match confirm password");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(passwordId, msg);
			fc.renderResponse();
	 
		  }
	}
}
