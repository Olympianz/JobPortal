package controllerMB;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.ExperienceService;

@ManagedBean
@SessionScoped
public class ExperienceController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7030309933985349491L;
	private List<String> all = null;

	public List<String> getAll() {
		if (all == null)
			all = ExperienceService.all();
		return all;
	}

	public void setAll(List<String> all) {
		this.all = all;
	}
}