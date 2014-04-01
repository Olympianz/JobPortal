package service;

import java.util.Calendar;

import util.SessionCtl;
import modelMB.AssetBean;
import modelMB.UserBean;
import data.dao.AssetDAO;
import data.dao.AssetTypeDAO;
import data.dao.UserDAO;
import data.entity.Asset;
import data.entity.AssetType;
import data.entity.User;

public class AssetService {
	static final AssetDAO assetDao = new AssetDAO();
	
	public static void loadFromEntity(AssetBean assetBean, Asset asset) {
		if ( assetBean == null || asset == null)
			return;
		
		UserBean author = new UserBean();
		UserService.loadFromEntity(author, asset.getUser(), false);
		assetBean.setAuthor(author);
		assetBean.setId(asset.getId());
		assetBean.setLocation(asset.getLocation());
		assetBean.setName(asset.getName());
		assetBean.setSize(asset.getSize());
		assetBean.setType(asset.getType().getName());		
	}
	
	public static void loadFromDB(AssetBean assetBean, Integer id) {
		Asset asset = assetDao.getEntityById(id);
		loadFromEntity(assetBean, asset);
	}
	
	public static int saveOrUpdate(AssetBean assetBean) {
		Asset asset = null;
		Integer id = assetBean.getId();
		User loggedInUser = SessionCtl.getLoggedInUser();

		if (id != null && id >= 0) {
			// Get existing record
			asset = assetDao.getEntityById(id);
		} else {
			// Create new record
			asset = new Asset(loggedInUser.getUser_name());
		}

		// Fetch all necessary object from database
		// Copy new data from bean to entity
		UserDAO userDao = new UserDAO();
		User user = userDao.getEntityById(assetBean.getAuthor().getUser_id(), false);
		
		String location = assetBean.getLocation();
		String name = assetBean.getName();
		Integer size = assetBean.getSize();
		
		AssetTypeDAO assetTypeDao = new AssetTypeDAO();
		AssetType type = assetTypeDao.getByName(assetBean.getType());
		
		int result = -1;

		if ( user != null && type != null) {
			asset.setUser(user);
			asset.setLocation(location);
			asset.setName(name);
			asset.setSize(size);
			asset.setType(type);
			asset.setUpdate_user(loggedInUser.getUser_name());
			asset.setUpdate_time(Calendar.getInstance());

			result = assetDao.saveOrUpdate(asset);
		}
		
		return result;
	}
}
