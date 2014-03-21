package controllerMB;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.StateService;

@ManagedBean
@SessionScoped
public class StateController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1860700732722169295L;
	private List<String> all = null;

	public List<String> getAll() {
		if (all == null)
			all = StateService.all();
		return all;
	}

	public void setAll(List<String> all) {
		this.all = all;
	}
}