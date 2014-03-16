package service;

import modelMB.SkillBean;
import data.dao.SkillDAO;
import data.entity.Skill;

public class SkillService {
	SkillDAO skillDao = new SkillDAO();
	
	public static void loadFromEntity(SkillBean skillBean, Skill skill) {
		skillBean.setId(skill.getId());
		skillBean.setName(skill.getName());
	}
}
