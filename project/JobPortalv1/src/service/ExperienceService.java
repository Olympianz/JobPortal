package service;

import java.util.ArrayList;
import java.util.List;

import data.dao.ExperienceDAO;
import data.entity.Experience;

public class ExperienceService {
	static final ExperienceDAO expDao = new ExperienceDAO();
	
	public static List<String> all() {
		List<Experience> exps = expDao.listEntities();
		List<String> expsString = new ArrayList<String>();
		
		for(Experience exp : exps) {
			expsString.add(exp.getName());
		}
		
		return expsString;
	}
}
