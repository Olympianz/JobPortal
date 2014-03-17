package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.State;

public class StateDAO extends DAO {

	public List<State> listEntities() {
		List<State> states = null;
		try {
			states = getSession().createQuery("from State").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return states;
	}
	
	public State getByName(String name) {
		State state = null;
		
		try {
			Query q = getSession().createQuery(
					"from State where state_n = :name");
			q.setString("name", name);

			state = (State) q.uniqueResult();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
		
		return state;
	}

}
