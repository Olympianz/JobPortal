package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="J_COMPANIES")
public class Company {
	
	public void update(Company company) {
		setCompany_n(company.getCompany_n());
		setContact_id(company.getContact_id());
		
		setActive_status(company.getActive_status());
		setCreation_user_name(company.getCreation_user_name());
		setUpdate_user_name(company.getUpdate_user_name());
		setCreation_timestamp(company.getCreation_timestamp());
		setUpdate_timestamp(company.getUpdate_timestamp());
	}
	
	private int company_id;
	private int contact_id;
	private String company_n;
	
	private String active_status = "N";
	private String creation_user_name = "sysdba";
	private String update_user_name = "sysdba";
	private Calendar creation_timestamp;
	private Calendar update_timestamp;
	
	@Id @GeneratedValue
	@Column(name="COMPANY_I")
	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}

	@Column(name="CONTACT_I")
	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}

	@Column(name="COMPANY_N")
	public String getCompany_n() {
		return company_n;
	}

	public void setCompany_n(String company_n) {
		this.company_n = company_n;
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
