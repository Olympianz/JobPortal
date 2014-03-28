package controllerMB;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import service.UserService;
import modelMB.AssetBean;
import data.entity.User;


@ManagedBean
@SessionScoped
public class AssetController implements Serializable {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -8311357701396332685L;

	public List<AssetBean> search() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		HttpSession session = (HttpSession)ec.getSession(false);
		
		List<AssetBean> assetBeans = new ArrayList<AssetBean>();
		User user = null;

		if (session != null && (user = (User)session.getAttribute("loggedin_user")) != null)
			assetBeans = UserService.searchAssets(user.getUser_id());

		return assetBeans;
	}
}
