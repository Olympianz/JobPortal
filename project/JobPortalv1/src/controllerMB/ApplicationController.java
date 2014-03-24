package controllerMB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import modelMB.ApplicationBean;
import service.ApplicationService;
import data.entity.Application;


@ManagedBean
@SessionScoped
public class ApplicationController implements Serializable {
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
	
	public List<ApplicationBean> search() {
		List<ApplicationBean> appBeans = ApplicationService.search(keyword);
		return appBeans;
	}
}
