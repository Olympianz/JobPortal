package data.entity;

import java.util.Calendar;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "J_ASSETS")
public class Asset {
	private Integer id = null;
	private String name;
	private String location;
	private Integer size;
	private AssetType type;
	private User user;

	// System columns
	private String active;
	private String creation_user;
	private String update_user;
	private Calendar creation_time;
	private Calendar update_time;

	public Asset() {
		this("sysdba");
	}
	
	public Asset(String creation_user) {
		this.active = "Y";
		this.creation_user = creation_user;
		this.update_user = creation_user;
		this.creation_time = Calendar.getInstance();
		this.update_time = Calendar.getInstance();		
	}
	
	@Id
	@GeneratedValue
	@Column(name = "ASSET_I")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ASSET_N", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ASSET_LOCATION", nullable = false, length = 255)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "ASSET_SIZE", nullable = false)
	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	@ManyToOne(fetch=FetchType.EAGER) 
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "ASSET_TYPE_I")
	public AssetType getType() {
		return type;
	}

	public void setType(AssetType type) {
		this.type = type;
	}

	@ManyToOne(fetch=FetchType.EAGER) 
	@Cascade(CascadeType.ALL)
	@JoinColumn(name = "USER_I")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
