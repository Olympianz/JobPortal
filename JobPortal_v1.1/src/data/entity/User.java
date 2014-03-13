package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="J_USERS")
public class User {
	
	public void update(User user) {
		setUser_name(user.getUser_name());
		setPassword(user.getPassword());
		setEmail(user.getEmail());
		setActive_status(user.getActive_status());
		setCreation_user_name(user.getCreation_user_name());
		setUpdate_user_name(user.getUpdate_user_name());
		setCreation_timestamp(user.getCreation_timestamp());
		setUpdate_timestamp(user.getUpdate_timestamp());
	}
	
	private Integer user_id;
	private String user_name;
	private String password;
	private String email;
	private String active_status = "N";
	private String creation_user_name = "sysdba";
	private String update_user_name = "sysdba";
	private String session_id;
	private Calendar creation_timestamp;
	private Calendar update_timestamp;

	private Role role;
	private Contact contact;
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="role_id", nullable=false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="contact_id")
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Id @GeneratedValue
	@Column(name="USER_I")
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	@Column(name = "USER_N", nullable = false, unique = true, length = 25)
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	
	@Column(name = "ACTIVE_S", nullable = false, length = 1)
	public String getActive_status() {
		return active_status;
	}

	public void setActive_status(String active) {
		this.active_status = active;
	}

	@Column(name = "CREATION_USER_N", nullable = false, length = 25)
	public String getCreation_user_name() {
		return creation_user_name;
	}

	public void setCreation_user_name(String creation_user) {
		this.creation_user_name = creation_user;
	}

	@Column(name = "UPDATE_USER_N", nullable = false, length = 25)
	public String getUpdate_user_name() {
		return update_user_name;
	}

	public void setUpdate_user_name(String update_user) {
		this.update_user_name = update_user;
	}

	@Column(name = "CREATION_TS")
    @Temporal(TemporalType.TIMESTAMP)
	public Calendar getCreation_timestamp() {
		return creation_timestamp;
	}

	public void setCreation_timestamp(Calendar creation_time) {
		this.creation_timestamp = creation_time;
	}

	@Column(name = "UPDATE_TS")
    @Temporal(TemporalType.TIMESTAMP)
	public Calendar getUpdate_timestamp() {
		return update_timestamp;
	}

	public void setUpdate_timestamp(Calendar update_time) {
		this.update_timestamp = update_time;
	}
}
