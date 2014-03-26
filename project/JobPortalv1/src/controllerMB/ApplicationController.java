package controllerMB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import modelMB.ApplicationBean;
import modelMB.JobBean;
import service.ApplicationService;
import service.JobService;
import service.UserService;
import data.entity.Application;
import data.entity.User;


@ManagedBean
@SessionScoped
public class ApplicationController implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8311357701396332685L;
	private String keyword = "";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public List<ApplicationBean> search() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession)ec.getSession(false);
		
		List<ApplicationBean> appBeans = new ArrayList<ApplicationBean>();
		User user = null;

		if (session != null && (user = (User)session.getAttribute("loggedin_user")) != null)
			appBeans = UserService.searchApplications(keyword, user.getUser_id());
		else
			appBeans = ApplicationService.search(keyword);
		return appBeans;
	}
}
