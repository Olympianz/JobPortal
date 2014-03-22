package controllerMB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelMB.JobBean;
import service.JobService;
import data.entity.Job;


@ManagedBean
@SessionScoped
public class JobSearchController implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8311357701396332685L;
	private String keyword = "";

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public List<JobBean> search() {
		List<JobBean> jobBeans = JobService.search(keyword);
		return jobBeans;
	}
}
