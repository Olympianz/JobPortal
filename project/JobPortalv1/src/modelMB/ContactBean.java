package modelMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import data.entity.Contact;

@ManagedBean
@SessionScoped
public class ContactBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1384884317141617401L;

	private Integer id;
	private String address;
	private String city;
	private String type;
	private String state;
	private Integer zip;
	private Double lng;
	private Double lat;

	public void loadFromDB(Integer id) {

	}

	public void loadFromEntity(Contact entity) {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZip() {
		return zip;
	}

	public void setZip(Integer zip) {
		this.zip = zip;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
}