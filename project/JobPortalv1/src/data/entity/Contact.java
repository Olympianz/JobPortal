package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="J_CONTACTS")
public class Contact {
	
	public void update(Contact contact) {
		setStreet_address_name(contact.getStreet_address_name());
		setStreet_city_name(contact.getStreet_city_name());
		setCompany_email_name(contact.getCompany_email_name());
		
		setActive_status(contact.getActive_status());
		setCreation_user_name(contact.getCreation_user_name());
		setUpdate_user_name(contact.getUpdate_user_name());
		setCreation_timestamp(contact.getCreation_timestamp());
		setUpdate_timestamp(contact.getUpdate_timestamp());
	}
	
	private Integer contact_id = null;
	private String street_address_name;
	private String street_city_name;
	private String company_email_name;
	
	private String active_status = "N";
	private String creation_user_name = "sysdba";
	private String update_user_name = "sysdba";
	private Calendar creation_timestamp;
	private Calendar update_timestamp;
	
	private Contact_type contact_type;
	private State state;
	private Location location;


	public Contact() {
		this("sysdba");
	}
	
	public Contact(String creation_user) {
		this.active_status = "Y";
		this.creation_user_name = creation_user;
		this.update_user_name = creation_user;
		this.creation_timestamp = Calendar.getInstance();
		this.update_timestamp = Calendar.getInstance();		
	}
	
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="location_id")
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="state_id")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="contact_type_id", nullable=false)
	public Contact_type getContact_type() {
		return contact_type;
	}

	public void setContact_type(Contact_type contact_type) {
		this.contact_type = contact_type;
	}

	@Id @GeneratedValue
	@Column(name="CONTACT_I")
	public Integer getContact_id() {
		return contact_id;
	}

	public void setContact_id(Integer contact_id) {
		this.contact_id = contact_id;
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
