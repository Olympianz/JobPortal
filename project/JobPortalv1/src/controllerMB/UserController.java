package controllerMB;

import java.io.Serializable;

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
public class UserController implements Serializable {
	
	private String name;
	private String email;
	private String password;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoggedInName() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession)ec.getSession(false);
		User user = null;
		if (session != null && (user = (User)session.getAttribute("loggedin_user")) != null){
			name = user.getUser_name();
		}
		return name;
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