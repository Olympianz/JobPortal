package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="J_CONTACT_TYPE")
public class Contact_type {
	
	public void update(Contact_type ct) {
		setContact_type_n(ct.getContact_type_n());
		
		setActive_status(ct.getActive_status());
		setCreation_user_name(ct.getCreation_user_name());
		setUpdate_user_name(ct.getUpdate_user_name());
		setCreation_timestamp(ct.getCreation_timestamp());
		setUpdate_timestamp(ct.getUpdate_timestamp());
	}
	
	private int contact_type_i;
	private String contact_type_n;
	
	private String active_status = "N";
	private String creation_user_name = "sysdba";
	private String update_user_name = "sysdba";
	private Calendar creation_timestamp;
	private Calendar update_timestamp;
	
	@Id @GeneratedValue
	@Column(name="CONTACT_TYPE_I")
	public int getContact_type_i() {
		return contact_type_i;
	}

	public void setContact_type_i(int contact_type_i) {
		this.contact_type_i = contact_type_i;
	}

	@Column(name="CONTACT_TYPE_N")
	public String getContact_type_n() {
		return contact_type_n;
	}

	public void setContact_type_n(String contact_type_n) {
		this.contact_type_n = contact_type_n;
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
