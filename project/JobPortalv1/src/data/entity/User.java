package data.entity;

import javax.persistence.*;

@Entity
@Table(name = "J_USERS")
public class User {

	private int id;
	
	@Id
	@GeneratedValue
	@Column(name = "user_I")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
