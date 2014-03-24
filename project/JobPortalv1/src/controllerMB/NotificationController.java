package controllerMB;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelMB.JobBean;
import modelMB.NotificationBean;
import service.JobService;
import service.NotificationService;
import service.NotificationTypeService;

@ManagedBean
@SessionScoped
public class NotificationController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7868423943195249173L;
	private List<String> allTypes = null;
	private String keyword = "";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public List<NotificationBean> search() {
		List<NotificationBean> notifBeans = NotificationService.search(keyword);
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
}
