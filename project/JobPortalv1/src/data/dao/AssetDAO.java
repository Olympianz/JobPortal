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
			Query q = getSession().createQuery("from Asset where id = :id");
			q.setInteger("id", id);
			asset = (Asset) q.uniqueResult();
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
			begin();
System.out.println("Begin!!!!!!!!");
			if(asset.getId() != null && asset.getId() >= 0) {
				id = asset.getId();
				getSession().update(asset);
			}
			else {
				id = (Integer)getSession().save(asset); 
			}
System.out.println("commit!!!!!!!!");
			commit();
		} catch (HibernateException e) {
System.out.println("EXception!!!!!!!!");
			e.printStackTrace();
			if (getSession().getTransaction()!=null) {
				rollback();
			}
		} finally {
			close();
		}
		
		return id;
	}
}
