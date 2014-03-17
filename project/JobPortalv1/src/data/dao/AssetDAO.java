package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

import data.entity.Application;
import data.entity.Asset;

public class AssetDAO extends DAO {
	public List<Asset> listEntities() {
		List<Asset> assets = null;
		try {
			assets = getSession().createQuery("from Asset").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return assets;
	}
	
	public Asset getEntityById(Integer id) {
		Asset asset = null;
		
		try{
			asset = (Asset) getSession().createQuery("from Asset where id=" + id);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return asset;
	}
	
	public int saveOrUpdate(Asset asset) {
		int id = -1;
		
		try{
			if(asset.getId() != null && asset.getId() >= 0) {
				id = asset.getId();
				getSession().update(asset);
			}
			else {
				id = (Integer)getSession().save(asset); 
			}
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return id;
	}
}
