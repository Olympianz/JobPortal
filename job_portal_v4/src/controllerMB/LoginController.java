package controllerMB;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelMB.UserBean;


@ManagedBean(name="loginController")
@SessionScoped
public class LoginController {
	private String username;
	private String password;
	
	private UserBean userBean = new UserBean();
	
	private boolean showAlert = false;
	
	
	public void verifyIdentity() {
		if (username==null || password==null) {
			showAlert = false;
			System.out.println("run false");
		} else {
			showAlert = true;
			System.out.println("run true");
		}
		/*
		
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse res = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		
		try {
			PrintWriter out = res.getWriter();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Failed to get PrintWriter");
			e1.printStackTrace();
		}
		
		if (username!=null) 
			showAlert = true;
		*/
//		if (isValidUser(username, password)) {
//			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//			try {
//				ec.redirect(ec.getRequestPathInfo() + "/mainMenu.xhtml");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			// return "login";
//		} else {
//			ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//			try {
//				ec.redirect(ec.getRequestPathInfo() + "/login.xhtml");
//				setShowAlert(true);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			//return "mainMenu";
//		}
		
	}
	
	private boolean isValidUser(String name, String password) {
		return isValidName(name) && isValidPS(password);
	}
	
	private boolean isValidName(String name) {
		return username==userBean.getUsername();
	}
	
	private boolean isValidPS(String password) {
		return password==userBean.getPassword();
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

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public boolean isShowAlert() {
		return showAlert;
	}

	public void setShowAlert(boolean showAlert) {
		this.showAlert = showAlert;
	}
}
