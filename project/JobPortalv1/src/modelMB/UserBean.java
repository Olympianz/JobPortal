package modelMB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.entity.Application;
import data.entity.Asset;
import data.entity.Contact;
import data.entity.Experience;
import data.entity.Job;
import data.entity.Notification;
import data.entity.Role;
import data.entity.Skill;
import data.entity.User;
import service.CompanyService;
import service.UserService;
import util.SessionCtl;

@ManagedBean
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

	private List<JobBean> saved_jobs = new ArrayList<JobBean>();

	private List<NotificationBean> sent_notif = new ArrayList<NotificationBean>();
	private List<NotificationBean> recv_notif = new ArrayList<NotificationBean>();

	private List<SkillBean> skills = new ArrayList<SkillBean>();
	private List<AssetBean> assets = new ArrayList<AssetBean>();
	private List<ApplicationBean> applications = new ArrayList<ApplicationBean>();

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

}
