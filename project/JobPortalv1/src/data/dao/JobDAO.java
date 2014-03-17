package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

import data.entity.Application;
import data.entity.Contact;
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
	
	public Job getEntityById(Integer id) {
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
	
	public int saveOrUpdate(Job job) {
		int id = -1;
		
		try{
			if(job.getId() != null && job.getId() >= 0) {
				id = job.getId();
				getSession().update(job);
			}
			else {
				id = (Integer)getSession().save(job); 
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
