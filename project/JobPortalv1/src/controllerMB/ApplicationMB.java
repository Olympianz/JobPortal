package controllerMB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import modelMB.ApplicationBean;
import modelMB.UserBean;
import service.UserService;
import util.SessionCtl;
import data.entity.Application;
import data.entity.User;

@ManagedBean
@SessionScoped
public class ApplicationMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3396433360484691293L;
	private List<ApplicationBean> applications = null;
	private List<ApplicationBean> allApps = null;

	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@PostConstruct
	public void loadUserInfo() {
		
		User current_user = SessionCtl.getLoggedInUser();
		UserService.loadFromDB(userBean, current_user.getUser_id());
		
		allApps = userBean.getApplications();
		applications = new ArrayList<ApplicationBean>();
		for (ApplicationBean app: allApps) {
			if (app.getJob().getAuthor().getUser_id() == current_user.getUser_id()) {
				applications.add(app);
			}
		}
	}


	public List<ApplicationBean> getApplications() {
		return applications;
	}


	public void setApplications(List<ApplicationBean> applications) {
		this.applications = applications;
	}
	
	public UserBean getUserBean() {
		return userBean;
	}


	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	/******************* logic **********************/
	public void onEdit() {
		userBean.setApplications(allApps);
		for (ApplicationBean app:allApps) {
			System.out.println(app.getStatus());
		}
		UserService.saveOrUpdate(userBean);
	}

	public void onCancel() {
		
	}
}
