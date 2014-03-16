package modelMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import data.entity.Application;

@ManagedBean
@SessionScoped
public class ApplicationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 83241570884848213L;

	private Integer id;
	private String status;
	private AssetBean asset;
	private UserBean applicant;
	private JobBean job;

	public void loadFromDB(Integer id) {

	}

	public void loadFromEntity(Application entity) {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AssetBean getAsset() {
		return asset;
	}

	public void setAsset(AssetBean asset) {
		this.asset = asset;
	}

	public UserBean getApplicant() {
		return applicant;
	}

	public void setApplicant(UserBean applicant) {
		this.applicant = applicant;
	}

	public JobBean getJob() {
		return job;
	}

	public void setJob(JobBean job) {
		this.job = job;
	}
}
