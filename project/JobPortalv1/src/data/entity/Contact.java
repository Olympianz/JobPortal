package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="J_CONTACTS")
public class Contact {
	
	public void update(Contact contact) {
		setContact_ty(contact.getContact_ty());
		setState_id(contact.getState_id());
		setStreet_address_name(contact.getStreet_address_name());
		setStreet_city_name(contact.getStreet_city_name());
		setCompany_email_name(contact.getCompany_email_name());
		setLocation_id(contact.getLocation_id());
		
		setActive_status(contact.getActive_status());
		setCreation_user_name(contact.getCreation_user_name());
		setUpdate_user_name(contact.getUpdate_user_name());
		setCreation_timestamp(contact.getCreation_timestamp());
		setUpdate_timestamp(contact.getUpdate_timestamp());
	}
	
	private int contact_id;
	private int contact_ty;
	private String street_address_name;
	private String street_city_name;
	private int state_id;
	private int location_id;
	private String company_email_name;
	
	private String active_status = "N";
	private String creation_user_name = "sysdba";
	private String update_user_name = "sysdba";
	private Calendar creation_timestamp;
	private Calendar update_timestamp;
	
	@Id @GeneratedValue
	@Column(name="CONTACT_I")
	public int getContact_id() {
		return contact_id;
	}

	public void setContact_id(int contact_id) {
		this.contact_id = contact_id;
	}
	
	@Column(name="CONTACT_TYPE_I")
	public int getContact_ty() {
		return contact_ty;
	}

	public void setContact_ty(int contact_ty) {
		this.contact_ty = contact_ty;
	}

	@Column(name="STREET_ADDRESS_N")
	public String getStreet_address_name() {
		return street_address_name;
	}

	public void setStreet_address_name(String street_address_name) {
		this.street_address_name = street_address_name;
	}

	@Column(name="STREET_CITY_N")
	public String getStreet_city_name() {
		return street_city_name;
	}

	public void setStreet_city_name(String street_city_name) {
		this.street_city_name = street_city_name;
	}

	@Column(name="STATE_I")
	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	@Column(name="LOCATION_I")
	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	@Column(name="COMPANY_EAMIL_N")
	public String getCompany_email_name() {
		return company_email_name;
	}

	public void setCompany_email_name(String company_email_name) {
		this.company_email_name = company_email_name;
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
