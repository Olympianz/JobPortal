package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "J_USERS")
public class User {

	private int id;
	private String name;
	private String password;
	private String email;
	private String sessionId;
	//private Role role;
	//private Contact contact;

	// System columns
	private String active;
	private String creation_user;
	private String update_user;
	private Calendar creation_time;
	private Calendar update_time;
	
	@Id
	@GeneratedValue
	@Column(name = "USER_I")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "USER_N", nullable = false, unique = true, length = 25)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PASSWORD_N", nullable = false, length = 20)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL_N", nullable = false, unique = true, length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "SESSION_TOKEN", nullable = true, length = 50)
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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
