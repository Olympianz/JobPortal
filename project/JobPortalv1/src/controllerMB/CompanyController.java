package controllerMB;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import service.CompanyService;


@ManagedBean
@SessionScoped
public class CompanyController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2741554998075064247L;
	
	public List<String> search(String query) {
		List<String> companies = CompanyService.search(query);
		companies.add(query);
		return companies;
	}
}