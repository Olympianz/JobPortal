package modelMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.ContactService;
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
	private String company_email;
	private Integer zip = 0;
	private Double lng = 0.0;
	private Double lat = 0.0;

	public void loadFromDB(Integer id) {
		ContactService.loadFromDB(this, id);
	}

	public void loadFromEntity(Contact entity) {
		ContactService.loadFromEntity(this, entity);
	}
	
	public void saveOrUpdate() {
		ContactService.saveOrUpdate(this);
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

	public String getCompany_email() {
		return company_email;
	}

	public void setCompany_email(String company_email) {
		this.company_email = company_email;
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
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("ContactBean:\n");
		output.append("=" + this.getId() + "\n");
		output.append("=" + this.getAddress() + "\n");
		output.append("=" + this.getCity() + "\n");
		output.append("=" + this.getState() + "\n");
		output.append("=" + this.getZip() + "\n");
		output.append("=" + this.getType() + "\n");
		output.append("=" + this.getLat() + "\n");
		output.append("=" + this.getLng() + "\n");
		output.append("=" + this.getCompany_email() + "\n");
		return output.toString();
	}
}
