package controllerMB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import modelMB.ApplicationBean;
import modelMB.UserBean;
import service.ApplicationService;
import service.UserService;
import util.SessionCtl;
import data.entity.Application;
import data.entity.User;

@ManagedBean
@ViewScoped
public class ApplicationMB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3396433360484691293L;
	private List<ApplicationBean> applications = null;

	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@PostConstruct
	public void loadUserInfo() {
		
		User current_user = SessionCtl.getLoggedInUser();
		
		Integer current_user_id = current_user.getUser_id();
		String current_user_name = current_user.getUser_name();
    
		System.out.println("Current User Name: " + current_user_name);
		UserService.loadFromDB(userBean, current_user_id);
		
		// Load all applications
		List<ApplicationBean> allAppBeans = ApplicationService.allAppBeans();
		applications = new ArrayList<ApplicationBean>();
		for (ApplicationBean appBean: allAppBeans) {
            String job_creator_name = appBean.getJob().getAuthor().getUser_name();
            
            if ( job_creator_name.equals(current_user_name) ) {
                        applications.add(appBean);
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
		for (ApplicationBean app:applications) {
            ApplicationService.saveOrUpdate(app);
		}
	}

	public void onCancel() {
		
	}
}
