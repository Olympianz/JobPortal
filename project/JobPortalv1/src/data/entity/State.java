package data.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="J_US_STATES")
public class State {
	
	public void update(State state) {
		setState_n(state.getState_n());
		
		setActive_status(state.getActive_status());
		setCreation_user_name(state.getCreation_user_name());
		setUpdate_user_name(state.getUpdate_user_name());
		setCreation_timestamp(state.getCreation_timestamp());
		setUpdate_timestamp(state.getUpdate_timestamp());
	}
	
	private Integer state_i;
	private String state_n;
	
	private String active_status = "N";
	private String creation_user_name = "sysdba";
	private String update_user_name = "sysdba";
	private Calendar creation_timestamp;
	private Calendar update_timestamp;
	
	@Id @GeneratedValue
	@Column(name="STATE_I")
	public Integer getState_i() {
		return state_i;
	}

	public void setState_i(Integer state_i) {
		this.state_i = state_i;
	}

	@Column(name="STATE_N")
	public String getState_n() {
		return state_n;
	}

	public void setState_n(String state_n) {
		this.state_n = state_n;
	}

	@Column(name="ACTIVE_S")
	public String getActive_status() {
		return active_status;
	}

	public void setActive_status(String active_status) {
		this.active_status = active_status;
	}

	@Column(name="CREATION_USER_N")
	public String getCreation_user_name() {
		return creation_user_name;
	}

	public void setCreation_user_name(String creation_user_name) {
		this.creation_user_name = creation_user_name;
	}

	@Column(name="UPDATE_USER_N")
	public String getUpdate_user_name() {
		return update_user_name;
	}

	public void setUpdate_user_name(String update_user_name) {
		this.update_user_name = update_user_name;
	}

	@Column(name="CREATION_TS")
	public Calendar getCreation_timestamp() {
		return creation_timestamp;
	}

	public void setCreation_timestamp(Calendar creation_timestamp) {
		this.creation_timestamp = creation_timestamp;
	}

	@Column(name="UPDATE_TS")
	public Calendar getUpdate_timestamp() {
		return update_timestamp;
	}

	public void setUpdate_timestamp(Calendar update_timestamp) {
		this.update_timestamp = update_timestamp;
	}
}
