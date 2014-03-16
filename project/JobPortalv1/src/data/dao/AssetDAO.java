package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

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
}
