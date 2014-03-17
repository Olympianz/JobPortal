package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="J_ROLES")
public class Role {
	
	public void update(Role role) {
		setRole_n(role.getRole_n());
		
		setActive_status(role.getActive_status());
		setCreation_user_name(role.getCreation_user_name());
		setUpdate_user_name(role.getUpdate_user_name());
		setCreation_timestamp(role.getCreation_timestamp());
		setUpdate_timestamp(role.getUpdate_timestamp());
	}
	
	private Integer role_id;
	private String role_n;
	
	private String active_status = "N";
	private String creation_user_name = "sysdba";
	private String update_user_name = "sysdba";
	private Calendar creation_timestamp;
	private Calendar update_timestamp;
	
	@Id @GeneratedValue
	@Column(name="ROLE_I")
	public Integer getRole_id() {
		return role_id;
	}

	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	
	@Column(name="ROLE_N")
	public String getRole_n() {
		return role_n;
	}

	public void setRole_n(String role_n) {
		this.role_n = role_n;
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
}
