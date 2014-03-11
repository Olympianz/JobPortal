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
		setRole_id(user.getRole_id());
		setActive_status(user.getActive_status());
		setCreation_user_name(user.getCreation_user_name());
		setUpdate_user_name(user.getUpdate_user_name());
		setCreation_timestamp(user.getCreation_timestamp());
		setUpdate_timestamp(user.getUpdate_timestamp());
		setContact_id(user.getContact_id());
	}
	
	private int user_id;
	private String user_name;
	private String password;
	private String email;
	private int role_id;
	private String active_status = "N";
	private String creation_user_name = "sysdba";
	private String update_user_name = "sysdba";
	private Calendar creation_timestamp;
	private Calendar update_timestamp;
	private int contact_id;
	private String session_id;

	@Id @GeneratedValue
	@Column(name="USER_I")
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Column(name="USER_N")
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	@Column(name="PASSWORD_N")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="EMAIL_N")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="ROLE_I")
	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	@Column(name="ACTIVE_S")
	public String getActive_status() {
		return active_status;
	}

	public void setActive_status(String active_status) {
		this.active_status = active_status;
	}

	@Column(name="CREATION_USER_N")
	public String getCreation_user_name() {
		return creation_user_name;
	}

	public void setCreation_user_name(String creation_user_name) {
		this.creation_user_name = creation_user_name;
	}

	@Column(name="UPDATE_USER_N")
	public String getUpdate_user_name() {
		return update_user_name;
	}

	public void setUpdate_user_name(String update_user_name) {
		this.update_user_name = update_user_name;
	}

	@Column(name="CREATION_TS")
	public Calendar getCreation_timestamp() {
		return creation_timestamp;
	}

	public void setCreation_timestamp(Calendar creation_timestamp) {
		this.creation_timestamp = creation_timestamp;
	}

	@Column(name="UPDATE_TS")
	public Calendar getUpdate_timestamp() {
		return update_timestamp;
	}

	public void setUpdate_timestamp(Calendar update_timestamp) {
		this.update_timestamp = update_timestamp;
	}

	@Column(name="CONTACT_I")
	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	@Column(name="SESSION_I")
	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
}
