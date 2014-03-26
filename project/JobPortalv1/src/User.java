import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean
@SessionScoped
public class User {

	private String firstname;
	private String lastname;
	private Integer age;
	private String street;
	private String city;
	private String postalCode;
	private String info;
	private String email;
	private String phone;

	public User(String firstname, String lastname, Integer age, String street,
			String city, String postalCode, String info, String email,
			String phone) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.info = info;
		this.email = email;
		this.phone = phone;
	}

	public User() {

	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}