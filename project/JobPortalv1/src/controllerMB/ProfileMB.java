package controllerMB;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import service.CompanyService;
import service.ContactService;
import service.UserService;
import util.SessionCtl;

import data.entity.User;

import modelMB.CompanyBean;
import modelMB.ContactBean;
import modelMB.SkillBean;
import modelMB.UserBean;

@ManagedBean(name="profileMB")
@SessionScoped
public class ProfileMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 400747030976990728L;
	private User user;
	private boolean skip;
	
//	public ProfileMB() {
//		FacesContext context = FacesContext.getCurrentInstance();
//		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//		setUser((User)(request.getSession(false)).getAttribute("loggedin_user"));
//	}
	
	@ManagedProperty(value="#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty(value="#{contactBean}")
	private ContactBean contactBean;
	
	@ManagedProperty(value="#{companyBean}")
	private CompanyBean companyBean;
	
	public ContactBean getContactBean() {
		return contactBean;
	}

	public void setContactBean(ContactBean contactBean) {
		this.contactBean = contactBean;
	}

	public CompanyBean getCompanyBean() {
		return companyBean;
	}

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}
	
	
	/******************** logic **********************/
	
	public void save(ActionEvent actionEvent) {
		System.out.println(userBean.getContact());
		System.out.println(userBean.getContact().getId());
	    
		companyBean.setContact(contactBean);
		userBean.setContact(contactBean);
		userBean.setCompany(companyBean);
//		CompanyService.saveOrUpdate(companyBean);
	    UserService.saveOrUpdate(userBean);
	    
	    System.out.println(userBean.getExperience());
	    System.out.println(userBean.getRole());	    
	}
	
	@PostConstruct
	public void loadUserInfo() {
		
		User current_user = SessionCtl.getLoggedInUser();
		UserService.loadFromEntity(userBean, current_user, true);
		ContactService.loadFromEntity(contactBean, current_user.getContact());
		CompanyService.loadFromEntity(companyBean, current_user.getCompany());
		
		StringBuilder skillString = new StringBuilder();
		if(userBean.getSkills() != null) {
			for (SkillBean skill : userBean.getSkills()){
				skillString.append(skill.getName());
				skillString.append(",");
			}
		}
		userBean.setSkillInput(skillString.toString());
	}
}
