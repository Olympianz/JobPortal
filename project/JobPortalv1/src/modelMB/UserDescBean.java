package modelMB;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import service.UserService;
import util.SessionCtl;

@ManagedBean(name="userDesc")
@ViewScoped
public class UserDescBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5201915856267563991L;

	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	private Integer currentUserId;
	
	@PostConstruct
	public void init() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		
		String user_id = request.getParameter("id");
		UserService.loadFromDB(userBean, Integer.parseInt(user_id));
		
		currentUserId = SessionCtl.getLoggedInUser().getUser_id();
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public Integer getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(Integer currentUserId) {
		this.currentUserId = currentUserId;
	}
}
