package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.Application;
import data.entity.Asset;

public class AssetDAO extends DAO {
	public List<Asset> listEntities() {
		List<Asset> assets = null;
		try {
			assets = getSession().createQuery("from Asset").list();
			if (assets != null)
				for(Asset asset : assets)
					getSession().merge(asset);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
	
		return assets;
	}
	
	public Asset getEntityById(Integer id) {
		Asset asset = null;
		
		try{
			Query q = getSession().createQuery("from Asset where id = :id");
			q.setInteger("id", id);
			asset = (Asset) q.uniqueResult();
			if (asset != null)
				getSession().merge(asset);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
		
		return asset;
	}
	
	public int saveOrUpdate(Asset asset) {
		int id = -1;
		
		try{
			begin();
			if(asset.getId() != null && asset.getId() >= 0) {
				id = asset.getId();
				getSession().update(asset);
			}
			else {
				id = (Integer)getSession().save(asset); 
			}
			commit();
			getSession().flush();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (getSession().getTransaction()!=null) {
				rollback();
			}
		}
		
		return id;
	}
}
