package service;

import java.util.Calendar;

import util.SessionCtl;
import modelMB.NotificationBean;
import modelMB.SkillBean;
import data.dao.NotificationTypeDAO;
import data.dao.SkillDAO;
import data.dao.UserDAO;
import data.entity.Notification;
import data.entity.NotificationType;
import data.entity.Skill;
import data.entity.User;

public class SkillService {
	static final SkillDAO skillDao = new SkillDAO();
	
	public static void loadFromEntity(SkillBean skillBean, Skill skill) {
		skillBean.setId(skill.getId());
		skillBean.setName(skill.getName());
	}
	
	public static void loadFromDB(SkillBean skillBean, Integer id) {
		Skill skill = skillDao.getEntityById(id);
		loadFromEntity(skillBean, skill);
	}

	public static int saveOrUpdate(SkillBean skillBean) {
		Skill skill = null;
		Integer id = skillBean.getId();

		if (id != null && id >= 0) {
			// Get existing record
			skill = skillDao.getEntityById(id);
		} else {
			// Create new record
			skill = new Skill(SessionCtl.getLoggedInUser().getUser_name());
		}

		// Fetch all necessary object from database
		// Copy new data from bean to entity
		int result = -1;
		skill = skillDao.getEntityByName(skillBean.getName());
		
		if (skill == null) {
			skill = new Skill(SessionCtl.getLoggedInUser().getUser_name());
			skill.setName(skillBean.getName());
			result = skillDao.saveOrUpdate(skill);
		}
		
		return result;
	}
}
