package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

import data.entity.Application;
import data.entity.Company;

public class CompanyDAO extends DAO {
	public List<Company> listEntities() {
		List<Company> companies = null;
		try {
			companies = getSession().createQuery("from Company").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return companies;
	}
	
	public Company getEntityById(Integer id) {
		Company company = null;
		
		try{
			company = (Company) getSession().createQuery("from Company where company_id=" + id);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return company;
	}
	
	public int saveOrUpdate(Company company) {
		int id = -1;
		
		try{
			if(company.getCompany_id() != null && company.getCompany_id() >= 0) {
				id = company.getCompany_id();
				getSession().update(company);
			}
			else {
				id = (Integer)getSession().save(company); 
			}
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return id;
	}

}
