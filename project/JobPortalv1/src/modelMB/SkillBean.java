package modelMB;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.SkillService;
import data.entity.Skill;

@ManagedBean
@SessionScoped
public class SkillBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1928897431481529615L;
	
	private Integer id;
	private String name;

	public void loadFromDB(int id) {
		SkillService.loadFromDB(this, id);
	}

	public void loadFromEntity(Skill entity) {
		SkillService.loadFromEntity(this, entity);
	}
	
	public void saveOrUpdate() {
		SkillService.saveOrUpdate(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("SkillBean:\n");
		output.append("=" + this.getId() + "\n");
		output.append("=" + this.getName() + "\n");
		return output.toString();
	}
}
