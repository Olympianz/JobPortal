package hibernates;

import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import org.hibernate.type.CalendarTimeType;
import org.hibernate.type.CalendarType;

import java.sql.*;

@Entity
@Table(name="AWSCOGENT.J_USERS", appliesTo = "")
public class User implements Serializable{

	@Id @Generated
	@Column(name="USER_I")
	private int user_id;
	
	@Column(name="USER_N")
	private String user_name;
	
	@Column(name="PASSWORD_N")
	private String password;
	
	@Column(name="EMAIL_N")
	private String email;
	
	@Column(name="ROLE_I")
	private int role_id;
	
	@Column(name="ACTIVE_S")
	private String active_status;
	
	@Column(name="CREATION_USER_N")
	private String creation_user_name;
	
	@Column(name="UPDATE_USER_N")
	private String update_user_name;
	
	@Column(name="CREATION_TS")
	private CalendarTimeType creation_timestamp;
	
	@Column(name="UPDATE_TS")
	private CalendarType update_timestamp;
	
	@Column(name="CONTACT_I")
	private int contact_id;
	
}
