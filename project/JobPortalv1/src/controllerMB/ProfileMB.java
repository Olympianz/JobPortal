package controllerMB;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import service.CompanyService;
import service.ContactService;
import service.UserService;
import util.SessionCtl;
import data.entity.User;
import modelMB.SkillBean;
import modelMB.UserBean;

@ManagedBean(name="profileMB")
@ViewScoped
public class ProfileMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 400747030976990728L;
	private static Logger logger = Logger.getLogger(ProfileMB.class.getName());

	//private User user;
	private boolean skip;
	
//	public ProfileMB() {
//		FacesContext context = FacesContext.getCurrentInstance();
//		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//		setUser((User)(request.getSession(false)).getAttribute("loggedin_user"));
//	}
	
	//@ManagedProperty(value="#{userBean}")
	private UserBean userBean = new UserBean();
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	
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
		//		FacesMessage msg = new FacesMessage("Successful", "Welcome :" + userBean.getUser_name());
		//	    FacesContext.getCurrentInstance().addMessage(null, msg);
	    
	    UserService.saveOrUpdate(userBean);
	    
	    System.out.println(userBean.getEmail());
	    System.out.println(userBean.getContact().getAddress());
	}
	
	public String onFlowProcess(FlowEvent event) {
        logger.info("Current wizard step:" + event.getOldStep());
        logger.info("Next step:" + event.getNewStep());
         
        if(skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        }
        else {
            return event.getNewStep();
        }
    }

	@PostConstruct
	public void loadUserInfo() {
//		UserBean ub = new UserBean();
//		UserService.loadFromDB(ub, 62);
//		ub.setEmail("adam2@yy.com");
//		UserService.saveOrUpdate(ub);
		
		User current_user = SessionCtl.getLoggedInUser();
		System.out.println(current_user);
		System.out.println(current_user.getPassword());


		UserService.loadFromEntity(userBean, current_user, true);
		
		StringBuilder skillString = new StringBuilder();
		for (SkillBean skill : userBean.getSkills()){
			skillString.append(skill.getName());
			skillString.append(",");
		}
		userBean.setSkillInput(skillString.toString());
		
		userBean.getContact().setCity("BBBBBBBBBBBBBB");
	    
		System.out.println(userBean);
		System.out.println(userBean.getPassword());

		//UserService.saveOrUpdate(userBean);

	}
}
