package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="J_LOCATIONS")
public class Location {
	
	public void update(Location location) {
		setZip_c(location.getZip_c());
		setLongitude_n(location.getLongitude_n());
		setLatitude_n(location.getLatitude_n());
		
		setActive_status(location.getActive_status());
		setCreation_user_name(location.getCreation_user_name());
		setUpdate_user_name(location.getUpdate_user_name());
		setCreation_timestamp(location.getCreation_timestamp());
		setUpdate_timestamp(location.getUpdate_timestamp());
	}
	
	private Integer location_id;
	private Integer zip_c;
	private Double longitude_n;
	private Double latitude_n;
	
	private String active_status = "N";
	private String creation_user_name = "sysdba";
	private String update_user_name = "sysdba";
	private Calendar creation_timestamp;
	private Calendar update_timestamp;
	
	@Id @GeneratedValue
	@Column(name="LOCATION_I")
	public Integer getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}
	
	@Column(name="ZIP_C")
	public Integer getZip_c() {
		return zip_c;
	}

	public void setZip_c(Integer zip_c) {
		this.zip_c = zip_c;
	}

	@Column(name="LONGITUDE")
	public Double getLongitude_n() {
		return longitude_n;
	}

	public void setLongitude_n(Double longitude_n) {
		this.longitude_n = longitude_n;
	}

	@Column(name="LATITUDE_N")
	public Double getLatitude_n() {
		return latitude_n;
	}

	public void setLatitude_n(Double latitude_n) {
		this.latitude_n = latitude_n;
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
