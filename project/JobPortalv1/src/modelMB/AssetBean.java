package modelMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.AssetService;
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
		AssetService.loadFromDB(this, id);
	}

	public void loadFromEntity(Asset entity) {
		AssetService.loadFromEntity(this, entity);
	}
	
	public void saveOrUpdate() {
		AssetService.saveOrUpdate(this);
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
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("AssetBean:\n");
		output.append("=" + this.getId() + "\n");
		output.append("=" + this.getName() + "\n");
		output.append("=" + this.getType() + "\n");
		output.append("=" + this.getLocation() + "\n");
		output.append("=" + this.getSize() + "\n");
		output.append("=" + this.getAuthor() + "\n");
		return output.toString();
	}
}
