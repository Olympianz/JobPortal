package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name = "J_NOTIFICATIONS")
public class Notification {
	private Integer id = null;
	private String title;
	private String content;
	private String read;
	private User from;
	private User to;
	private NotificationType type;

	// System columns
	private String active;
	private String creation_user;
	private String update_user;
	private Calendar creation_time;
	private Calendar update_time;


	public Notification() {
		this("sysdba");
	}
	
	public Notification(String creation_user) {
		this.active = "Y";
		this.creation_user = creation_user;
		this.update_user = creation_user;
		this.creation_time = Calendar.getInstance();
		this.update_time = Calendar.getInstance();		
	}
	
	@Id
	@GeneratedValue
	@Column(name = "NOTIFICATION_I")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "TITLE_N", nullable = false, length = 200)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "CONTENT_N", nullable = false, length = 1000)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "READ_S", nullable = false, length = 1)
	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name = "FROM_USER_I")
	public User getFrom() {
		return from;
	}

	public void setFrom(User user) {
		this.from = user;
	}

	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name = "TO_USER_I")
	public User getTo() {
		return to;
	}

	public void setTo(User user) {
		this.to = user;
	}

	@ManyToOne(fetch=FetchType.EAGER) 
	@JoinColumn(name = "NOTIFICATION_TYPE_I")
	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	@Column(name = "ACTIVE_S", nullable = false, length = 1)
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "CREATION_USER_N", nullable = false, length = 25)
	public String getCreation_user() {
		return creation_user;
	}

	public void setCreation_user(String creation_user) {
		this.creation_user = creation_user;
	}

	@Column(name = "UPDATE_USER_N", nullable = false, length = 25)
	public String getUpdate_user() {
		return update_user;
	}

	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	@Column(name = "CREATION_TS")
    @Temporal(TemporalType.TIMESTAMP)
	public Calendar getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Calendar creation_time) {
		this.creation_time = creation_time;
	}

	@Column(name = "UPDATE_TS")
    @Temporal(TemporalType.TIMESTAMP)
	public Calendar getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Calendar update_time) {
		this.update_time = update_time;
	}
}
