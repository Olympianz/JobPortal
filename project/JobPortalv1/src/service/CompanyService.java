package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import util.SessionCtl;
import modelMB.AssetBean;
import modelMB.CompanyBean;
import modelMB.ContactBean;
import data.dao.AssetTypeDAO;
import data.dao.CompanyDAO;
import data.dao.ContactDAO;
import data.dao.UserDAO;
import data.entity.Asset;
import data.entity.AssetType;
import data.entity.Company;
import data.entity.Contact;
import data.entity.User;

public class CompanyService {

	static final CompanyDAO companyDao = new CompanyDAO();

	public static void loadFromEntity(CompanyBean companyBean, Company company) {
		if (companyBean == null || company == null)
			return;

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

	public static void loadFromDBByName(CompanyBean companyBean, String name) {
		Company company = companyDao.getEntityByName(name);
		loadFromEntity(companyBean, company);
	}
	
	public static int saveOrUpdate(CompanyBean companyBean) {
		Company company = null;
		Integer id = companyBean.getId();

		if (id != null && id >= 0) {
			// Get existing record
			company = companyDao.getEntityById(id);
		} else {
			// Create new record
			company = new Company(SessionCtl.getLoggedInUser().getUser_name());
		}

		// Fetch all necessary object from database
		// Copy new data from bean to entity
		ContactDAO contactDao = new ContactDAO();
		Contact contact = contactDao.getEntityById(companyBean.getContact()
				.getId());
		if (contact == null) {
			ContactService.saveOrUpdate(companyBean.getContact());
			contact = contactDao
					.getEntityById(companyBean.getContact().getId());
		}

		String name = companyBean.getName();

		int result = -1;
		if (contact != null && name != null) {
			company.setCompany_n(name);
			company.setContact(contact);
			company.setUpdate_user_name(SessionCtl.getLoggedInUser()
					.getUser_name());
			company.setUpdate_timestamp(Calendar.getInstance());
			result = companyDao.saveOrUpdate(company);
		}

		return result;
	}

	public static List<String> search(String query) {
		List<Company> companies = companyDao.search(query);
		List<String> companyStrings = new ArrayList<String>();

		if (companies != null) {
			for (Company company : companies) {
				companyStrings.add(company.getCompany_n());
			}
		}

		return companyStrings;
	}
}
