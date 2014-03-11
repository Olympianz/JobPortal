package controllerMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="navigationController", eager=true)
@SessionScoped
public class NavigationController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{param.pageId}")
	private String pageId;
	
	@ManagedProperty(value="#{loginController}")
	private LoginController loginController;
	
	public String moveToLogin() {
		return "login";
	}
	
	public String moveToUserMenu() {
		return "UserMenu";
	}
	
	public String moveToPage3() {
		return "page3";
	}
	
	// show page functions

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public LoginController getLoginController() {
		return loginController;
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
	}
	
	
}
