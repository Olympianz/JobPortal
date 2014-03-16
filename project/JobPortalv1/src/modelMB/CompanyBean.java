package modelMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import data.entity.Company;


@ManagedBean
@SessionScoped
public class CompanyBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4805145002178682854L;

	private Integer id;
	private String name;
	private ContactBean contact;
	
	public void loadFromDB(Integer id) {
		
	}
	
	public void loadFromEntity(Company entity) {
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ContactBean getContact() {
		return contact;
	}
	public void setContact(ContactBean contact) {
		this.contact = contact;
	}
	
}
