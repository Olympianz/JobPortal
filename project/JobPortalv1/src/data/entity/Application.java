package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "J_APPLICATIONS")
public class Application {
	private Integer id = null;
	private ApplicationStatus status;
	private Asset asset;
	private User applicant;
	private Job position;
	
	// System columns
	private String active;
	private String creation_user;
	private String update_user;
	private Calendar creation_time;
	private Calendar update_time;

	public Application() {
		this("sysdba");
	}
	
	public Application(String creation_user) {
		this.active = "Y";
		this.creation_user = creation_user;
		this.update_user = creation_user;
		this.creation_time = Calendar.getInstance();
		this.update_time = Calendar.getInstance();
	}
	
	@Id
	@GeneratedValue
	@Column(name = "APPLICATION_I")
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "APPLICATION_STATUS_I")
	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name = "ASSET_I")
	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name = "USER_I")
	public User getApplicant() {
		return applicant;
	}

	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}

	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name = "POSTING_I")
	public Job getPosition() {
		return position;
	}

	public void setPosition(Job position) {
		this.position = position;
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
