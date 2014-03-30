package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.ApplicationStatus;

public class ApplicationStatusDAO extends DAO {

	public List<ApplicationStatus> listEntities() {
		List<ApplicationStatus> status = null;
		try {
			status = getSession().createQuery("from ApplicationStatus").list();
			for (ApplicationStatus a_status : status)
				getSession().merge(a_status);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
	
		return status;
	}
	
	public ApplicationStatus getByName(String name) {
		ApplicationStatus status = null;
		
		try {
			Query q = getSession().createQuery(
					"from ApplicationStatus where name = :name");
			q.setString("name", name);

			status = (ApplicationStatus) q.uniqueResult();
			getSession().merge(status);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
		
		return status;
	}
}
