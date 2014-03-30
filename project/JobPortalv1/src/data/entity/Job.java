package data.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import java.util.HashSet;

@Entity
@Table(name = "J_POSTINGS")
public class Job {
	
	private Integer id = null;
	private String title;
	private String description;
	private String requirement;
	private String responsibility;
	private Experience experience;
	private User author;
	private List<Skill> skills = new ArrayList<Skill>();
	private List<Application> applications = new ArrayList<Application>();

	// System columns
	private String active;
	private String creation_user;
	private String update_user;
	private Calendar creation_time;
	private Calendar update_time;


	public Job() {
		this("sysdba");
	}
	
	public Job(String creation_user) {
		this.active = "Y";
		this.creation_user = creation_user;
		this.update_user = creation_user;
		this.creation_time = Calendar.getInstance();
		this.update_time = Calendar.getInstance();		
	}
	
	@Id
	@GeneratedValue
	@Column(name = "POSTING_I")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "POST_TITLE_N", nullable = false, length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "POST_DESC_N", nullable = false, length = 1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "POST_REQUIRE_N", nullable = false, length = 500)
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}

	@Column(name = "POST_RESP_N", nullable = false, length = 500)
	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@ManyToOne(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	@JoinColumn(name = "EXPERIENCE_I")
	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinTable(name="J_POSTING_SKILL", 
                joinColumns={@JoinColumn(name="POSTING_I")}, 
                inverseJoinColumns={@JoinColumn(name="SKILL_I")})
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	@OneToMany(fetch=FetchType.EAGER, mappedBy="position", cascade={CascadeType.MERGE})
	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	@Column(name = "ACTIVE_S", nullable = false, length = 1)
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "CREATION_USER_N", nullable = false, length = 25)
	public String getCreation_user() {
		return creation_user;
	}

	public void setCreation_user(String creation_user) {
		this.creation_user = creation_user;
	}

	@Column(name = "UPDATE_USER_N", nullable = false, length = 25)
	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	@Column(name = "CREATION_TS")
    @Temporal(TemporalType.TIMESTAMP)
	public Calendar getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Calendar creation_time) {
		this.creation_time = creation_time;
	}

	@Column(name = "UPDATE_TS")
    @Temporal(TemporalType.TIMESTAMP)
	public Calendar getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Calendar update_time) {
		this.update_time = update_time;
	}
}
