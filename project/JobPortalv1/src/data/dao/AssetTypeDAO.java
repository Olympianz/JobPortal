package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.AssetType;

public class AssetTypeDAO extends DAO {

	public List<AssetType> listEntities() {
		List<AssetType> types = null;
		try {
			types = getSession().createQuery("from AssetType").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return types;
	}
	
	public AssetType getByName(String name) {
		AssetType type = null;
		
		try {
			Query q = getSession().createQuery(
					"from AssetType where name = :name");
			q.setString("name", name);

			type = (AssetType) q.uniqueResult();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
		
		return type;
	}

}
