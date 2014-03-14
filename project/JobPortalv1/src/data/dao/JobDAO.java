package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

import data.entity.Job;

public class JobDAO extends DAO{

	public List<Job> listEntities() {
		List<Job> jobs = null;
		try {
			jobs = getSession().createQuery("from Job").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return jobs;
	}
	
	public Job getJobById(Integer id) {
		Job job = null;
		
		try{
			job = (Job) getSession().createQuery("from Job where id=" + id);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return job;
	}
}
