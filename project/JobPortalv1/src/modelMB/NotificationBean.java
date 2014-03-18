package modelMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.CompanyService;
import service.NotificationService;
import data.entity.Notification;

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
	private Boolean read;
	private UserBean fromUser;
	private UserBean toUser;
	private String type;

	public void loadFromDB(int id) {
		NotificationService.loadFromDB(this, id);
	}

	public void loadFromEntity(Notification entity) {
		NotificationService.loadFromEntity(this, entity);
	}
	
	public void saveOrUpdate() {
		NotificationService.saveOrUpdate(this);
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
}
