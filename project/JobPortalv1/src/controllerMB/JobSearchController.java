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

import modelMB.JobBean;
import service.JobService;
import service.UserService;
import data.entity.Job;
import data.entity.User;


@ManagedBean
@SessionScoped
public class JobSearchController implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8311357701396332685L;
	private String keyword = "";
	private String mode = "";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public List<JobBean> search() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		HttpSession session = (HttpSession)ec.getSession(false);

		List<JobBean> jobBeans = null;
		User user = null;
		String mode = request.getParameter("mode");
        if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest())
            mode = this.mode;
        else
        	this.mode = mode;
		
		if (session != null && mode != null && (user = (User)session.getAttribute("loggedin_user")) != null){
			if(mode.equals("saved"))
				jobBeans = UserService.searchSavedJob(keyword, user.getUser_id());
			else if(mode.equals("posted"))
				jobBeans = UserService.searchPostedJob(keyword, user.getUser_id());
			else 
				jobBeans = JobService.search(keyword);
		}
		else	
			jobBeans = JobService.search(keyword);
		
		return jobBeans;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
