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
			if(skills != null)
				for (Skill skill : skills)
					getSession().merge(skill);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
	
		return skills;
	}
	
	public Skill getEntityById(Integer id) {
		Skill skill = (Skill) getSession().load("Skill", id);
		
		if (skill == null) {
			try{
				Query q = getSession().createQuery("from Skill where id = :id");
				q.setInteger("id", id);
				skill = (Skill) q.uniqueResult();
				if (skill != null)
					getSession().merge(skill);
			} catch (HibernateException e) {
				if (getSession().getTransaction()!=null) {
					rollback();
				}
				e.printStackTrace();
			}
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
			getSession().flush();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			//close();
		}
		
		return id;
	}
	
	public Skill getEntityByName(String name) {
		Skill skill = null;
		
		try{
			Query q = getSession().createQuery("from Skill where name = :name");
			q.setString("name", name);
			skill = (Skill) q.uniqueResult();
			if (skill != null)
				getSession().merge(skill);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}

		return skill;
		
	}

}
