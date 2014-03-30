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
			if (types != null)
				for (AssetType type : types)
					getSession().merge(type);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
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
			if (type != null)
				getSession().merge(type);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		}
		
		return type;
	}
}
