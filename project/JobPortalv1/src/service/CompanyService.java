package service;

import modelMB.CompanyBean;
import modelMB.ContactBean;
import data.dao.CompanyDAO;
import data.entity.Company;

public class CompanyService {

	static final CompanyDAO companyDao = new CompanyDAO();
	
	public static void loadFromEntity(CompanyBean companyBean, Company company) {
		ContactBean contactBean = new ContactBean();
		ContactService.loadFromEntity(contactBean, company.getContact());
		companyBean.setContact(contactBean);
		
		companyBean.setId(company.getCompany_id());
		companyBean.setName(company.getCompany_n());
	}
	
	public static void loadFromDB(CompanyBean companyBean, Integer id) {
		Company company = companyDao.getEntityById(id);
		loadFromEntity(companyBean, company);
	}
}