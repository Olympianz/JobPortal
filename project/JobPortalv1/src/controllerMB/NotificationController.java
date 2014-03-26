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

import data.entity.User;
import modelMB.JobBean;
import modelMB.NotificationBean;
import service.JobService;
import service.NotificationService;
import service.NotificationTypeService;
import service.UserService;

@ManagedBean
@SessionScoped
public class NotificationController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7868423943195249173L;
	private List<String> allTypes = null;
	private String keyword = "";
	private String mode = "";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public List<NotificationBean> search() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		HttpSession session = (HttpSession)ec.getSession(false);

		User user = null;
		List<NotificationBean> notifBeans = new ArrayList<NotificationBean>();
		
		String mode = request.getParameter("mode");
        if (FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest())
            mode = this.mode;
        else
        	this.mode = mode;
		
		if (mode != null && session != null && (user = (User)session.getAttribute("loggedin_user")) != null) {
			if ( mode.equals("sent") )
				notifBeans = UserService.searchSentNotification(keyword, user.getUser_id());
			else if ( mode.equals("recv") )
				notifBeans = UserService.searchRecvNotification(keyword, user.getUser_id());
			else
				notifBeans = NotificationService.search(keyword);
		}
		else
			notifBeans = NotificationService.search(keyword);
		
		return notifBeans;
	}

	public List<String> getAllTypes() {
		if (allTypes == null)
			allTypes = NotificationTypeService.all();
		return allTypes;
	}

	public void setAllTypes(List<String> allTypes) {
		this.allTypes = allTypes;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
