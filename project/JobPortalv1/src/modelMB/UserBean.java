package modelMB;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.entity.User;
import util.SessionCtl;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -84599783517567922L;
	private String password;
	private String name;
	private String role = "jobseeker";

	private Integer user_id;
	private String username;
	private String email;

	private Calendar creation_timestamp;
	private Calendar update_timestamp;
	
	private Integer role_id;
	private Integer contact_id;
	
	
	public String getName() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession)ec.getSession(false);
		User user = null;
		if (session != null && (user = (User)session.getAttribute("loggedin_user")) != null){
			name = user.getUser_name();
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public boolean isLoggedIn() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		return SessionCtl.checkLogIn((HttpServletRequest)ec.getRequest(), (HttpServletResponse)ec.getResponse());
	}
	
	public void login() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		SessionCtl.login((HttpServletRequest)ec.getRequest(), (HttpServletResponse)ec.getResponse());
	}
	
	public void logout() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		SessionCtl.logout((HttpServletRequest)ec.getRequest(), (HttpServletResponse)ec.getResponse());
	}
}
