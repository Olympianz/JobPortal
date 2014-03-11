package modelMB;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import data.entity.User;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean {
	
	private String username;
	private String password;
	
	public UserBean() {
		User user = new User();
		username = user.getUser_name();
		password = user.getPassword();
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
