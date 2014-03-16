package service;

import modelMB.AssetBean;
import modelMB.UserBean;
import data.dao.AssetDAO;
import data.entity.Asset;

public class AssetService {
	AssetDAO assetDao = new AssetDAO();
	
	public static void loadFromEntity(AssetBean assetBean, Asset asset) {
		UserBean author = new UserBean();
		UserService.loadFromEntity(author, asset.getUser(), false);
		assetBean.setAuthor(author);
		assetBean.setId(asset.getId());
		assetBean.setLocation(asset.getLocation());
		assetBean.setName(asset.getName());
		assetBean.setSize(asset.getSize());
		assetBean.setType(asset.getType().getName());		
	}
}
