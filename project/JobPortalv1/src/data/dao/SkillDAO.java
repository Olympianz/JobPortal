package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.Application;
import data.entity.Notification;
import data.entity.Skill;

public class SkillDAO extends DAO {
	public List<Skill> listEntities() {
		List<Skill> skills = null;
		try {
			skills = getSession().createQuery("from Skill").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return skills;
	}
	
	public Skill getEntityById(Integer id) {
		Skill skill = null;
		
		try{
			Query q = getSession().createQuery("from Skill where id = :id");
			q.setInteger("id", id);
			skill = (Skill) q.uniqueResult();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return skill;
	}
	
	public int saveOrUpdate(Skill skill) {
		int id = -1;
		
		try{
			begin();
			if(skill.getId() != null && skill.getId() >= 0) {
				id = skill.getId();
				getSession().update(skill);
			}
			else {
				id = (Integer)getSession().save(skill); 
			}
			commit();
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
	
	public Skill getEntityByName(String name) {
		Skill skill = null;
		
		try{
			Query q = getSession().createQuery("from Skill where name = :name");
			q.setString("name", name);
			
			skill = (Skill) q.uniqueResult();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return skill;
		
	}

}
