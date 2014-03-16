package modelMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import data.entity.Asset;

@ManagedBean
@SessionScoped
public class AssetBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -824928103879540624L;

	private Integer id;
	private String type;
	private String name;
	private String location;
	private Integer size;
	private UserBean author;

	public void loadFromDB(int id) {

	}

	public void loadFromEntity(Asset entity) {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public UserBean getAuthor() {
		return author;
	}

	public void setAuthor(UserBean author) {
		this.author = author;
	}
}
