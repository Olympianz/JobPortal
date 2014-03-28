package modelMB;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import service.NotificationService;
import service.UserService;
import util.SessionCtl;
import data.entity.Notification;
import data.entity.User;

@ManagedBean
@SessionScoped
public class NotificationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2584096866195127348L;

	private Integer id;
	private String title;
	private String content;
	private Boolean read = false;
	private UserBean fromUser = new UserBean();
	private UserBean toUser = new UserBean();
	private String type;

	public void init() {
		if (FacesContext.getCurrentInstance().getPartialViewContext()
				.isAjaxRequest()) {
			return; // Skip ajax requests.
		}

		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();

		String notifId = request.getParameter("id");

		if (notifId != null) {
			NotificationService.loadFromDB(this, Integer.parseInt(notifId));
System.out.println("+++++++++++++++");			
			// Read
			User user = SessionCtl.getLoggedInUser();
			if (user != null) {
				if (user.getUser_id().equals(this.getToUser().getUser_id()) && !this.read) {
System.out.println("reached");
					this.read = true;
					NotificationService.saveOrUpdate(this);
				}	
			}
		} else {
			try {
				ec.redirect("index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void loadFromDB(int id) {
		NotificationService.loadFromDB(this, id);
	}

	public void loadFromEntity(Notification entity) {
		NotificationService.loadFromEntity(this, entity);
	}
	
	public void saveOrUpdate() {
		int id = NotificationService.saveOrUpdate(this);
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();

		if (id > 0) {
			try {
				ec.redirect("notification_description.xhtml?id=" + id);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {			 
			FacesMessage facesMessage = new FacesMessage(
				FacesMessage.SEVERITY_ERROR,"Error message", 
					"Fail to send the message.");
			FacesContext.getCurrentInstance().addMessage(null, 
					facesMessage);  
		}

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getRead() {
		return read;
	}

	public void setRead(Boolean read) {
		this.read = read;
	}

	public UserBean getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserBean fromUser) {
		this.fromUser = fromUser;
	}

	public UserBean getToUser() {
		return toUser;
	}

	public void setToUser(UserBean toUser) {
		this.toUser = toUser;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("NotificationBean:\n");
		output.append("=" + this.getId() + "\n");
		output.append("=" + this.getTitle() + "\n");
		output.append("=" + this.getContent() + "\n");
		output.append("=" + this.getType() + "\n");
		output.append("=" + this.getFromUser() + "\n");
		output.append("=" + this.getToUser() + "\n");
		output.append("=" + this.getRead()+ "\n");
		return output.toString();
	}
}
