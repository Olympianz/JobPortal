package controllerMB;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import service.AssetService;
import service.CompanyService;
import service.ContactService;
import service.UserService;
import util.SessionCtl;
import data.entity.User;
import modelMB.AssetBean;
import modelMB.CompanyBean;
import modelMB.ContactBean;
import modelMB.SkillBean;
import modelMB.UserBean;

@ManagedBean(name="profileMB")
@ViewScoped
public class ProfileMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 400747030976990728L;
	private User user;
	private boolean skip;

    public void submit(FileUploadEvent event) {
    	UploadedFile file = event.getFile();
    	String filename = file.getFileName();
    	Integer size = file.getContents().length;
    	String loc = "/resources/files/" + filename;
    	String[] tmp = filename.split("\\.");
    	String type = tmp[tmp.length - 1];
   	

		InputStream inputStr = null;
		try {
		    inputStr = file.getInputstream();
		} catch (IOException e) {
		    //log error
			FacesMessage msg = new FacesMessage("Fail to upload file " + event.getFile().getFileName() + ".");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}
		
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		String directory = externalContext.getInitParameter("uploadDirectory");
		String fileName = FilenameUtils.getName(file.getFileName());
		File destFile = new File(directory, fileName);
		try {
		    FileUtils.copyInputStreamToFile(inputStr, destFile);
		} catch (IOException e) {
		    //log error
			FacesMessage msg = new FacesMessage("Fail to upload file " + event.getFile().getFileName() + ".");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
	    }
		
		User user = SessionCtl.getLoggedInUser();
		UserBean userBean = new UserBean();
		UserService.loadFromEntity(userBean, user, false);
	
		AssetBean assetBean = new AssetBean();
		assetBean.setAuthor(userBean);
		assetBean.setLocation(loc);
		assetBean.setName(filename);
		assetBean.setSize(size);
		assetBean.setType(type);
			
		int id = AssetService.saveOrUpdate(assetBean);

		if (id < 0) {
			FacesMessage msg = new FacesMessage("Fail to upload file " + event.getFile().getFileName() + ".");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return;
		}

		FacesMessage msg = new FacesMessage(event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
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
		companyBean.setContact(contactBean);
		userBean.setContact(contactBean);
		userBean.setCompany(companyBean);
//		CompanyService.saveOrUpdate(companyBean);
		
		stringToSkills();       
        for (SkillBean sk : userBean.getSkills()) {
        	System.out.println(sk.getName());
        }
	    UserService.saveOrUpdate(userBean);  
	}
	
	@PostConstruct
	public void loadUserInfo() {
		
		User current_user = SessionCtl.getLoggedInUser();
		if(current_user == null)
			return;
		
		UserService.loadFromDB(userBean, current_user.getUser_id());
		if (current_user.getContact() != null)
			ContactService.loadFromDB(contactBean, current_user.getContact().getContact_id());
		if (current_user.getCompany() != null)
			CompanyService.loadFromDB(companyBean, current_user.getCompany().getCompany_id());
		
		skillsToString();
		//System.out.println(userBean);
	}
	
	public void skillsToString() {
		StringBuilder skillString = new StringBuilder();
		if(userBean.getSkills() != null) {
			for (SkillBean skill : userBean.getSkills()){
				skillString.append(skill.getName());
				skillString.append(",");
			}
		}
		userBean.setSkillInput(skillString.toString());
	}
	
	public void stringToSkills() {
       String[] skills = userBean.getSkillInput().split(",");
       userBean.getSkills().clear();
       for (String skill: skills) {
    	   if(!skill.equals("")){
	           SkillBean sb = new SkillBean();
	           sb.setName(skill);
	           userBean.getSkills().add(sb);
    	   }
       }
   }
}
