package modelMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.ApplicationService;
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

	private String s_id;
	public String getS_id() {
		return s_id;
	}

	public void setS_id(String s_id) {
		this.s_id = s_id;
	}

	public void load(){
		System.out.println("Reached");
		loadFromDB(Integer.parseInt(this.getS_id()));
	}
	
	public void loadFromDB(Integer id) {
		ApplicationService.loadFromDB(this, id);
		System.out.println(this);
	}

	// ===========================================================
	public void loadFromEntity(Application entity) {
		ApplicationService.loadFromEntity(this, entity);
	}
	
	public void saveOrUpdate() {
		ApplicationService.saveOrUpdate(this);
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
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("ApplicationBean:\n");
		output.append("=" + this.getId() + "\n");
		output.append("=" + this.getApplicant() + "\n");
		output.append("=" + this.getJob() + "\n");
		output.append("=" + this.getAsset() + "\n");
		output.append("=" + this.getStatus() + "\n");
		return output.toString();
	}
}