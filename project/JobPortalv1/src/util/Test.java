package util;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="test")
@SessionScoped
public class Test  implements Serializable {
	String id;
	String status;
	
	public void load(){
		System.out.println("Reached");
		loadFromDB(Integer.parseInt(this.getId()));
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void loadFromDB(Integer id) {
		System.out.println(id + ":" + status);
	}
	public void saveOrUpdate() {
		System.out.println("Reached save");
	}
}


