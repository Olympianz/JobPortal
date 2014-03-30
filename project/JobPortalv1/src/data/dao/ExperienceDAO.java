package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.Experience;

public class ExperienceDAO extends DAO {

	public List<Experience> listEntities() {
		List<Experience> exps = null;
		try {
			exps = getSession().createQuery("from Experience").list();
			for (Experience exp : exps)
				getSession().merge(exp);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
	
		return exps;
	}
	
	public Experience getByName(String name) {
		Experience exp = null;
		
		try {
			Query q = getSession().createQuery(
					"from Experience where name = :name");
			q.setString("name", name);

			exp = (Experience) q.uniqueResult();
			getSession().merge(exp);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
		
		return exp;
	}

}
