package data.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import data.entity.Application;
import data.entity.Company;
import data.entity.Contact;
import data.entity.Location;

public class ContactDAO extends DAO {
	public List<Contact> listEntities() {
		List<Contact> contacts = null;
		try {
			contacts = getSession().createQuery("from Contact").list();
			if (contacts != null)
				for (Contact contact : contacts)
					getSession().merge(contact);
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		}

		return contacts;
	}

	public Contact getEntityById(Integer id) {
		Contact contact = null;

		try {
			Query q = getSession().createQuery("from Contact where contact_id = :id");
			q.setInteger("id", id);
			contact = (Contact) q.uniqueResult();
			if (contact != null)
				getSession().merge(contact);
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		}

		return contact;
	}

	public int saveOrUpdate(Contact contact) {
		int id = -1;

		try {
			begin();
			if (contact.getContact_id() != null && contact.getContact_id() >= 0) {
				id = contact.getContact_id();
				getSession().update(contact);
			} else {
				id = (Integer) getSession().save(contact);
			}
			commit();
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		}

		return id;
	}

	public Location getLocation(Double lat, Double lng, Integer zip) {
		Location location = null;

		try {
			Query q = getSession()
					.createQuery(
							"from Location where longitude_n = :lng and latitude_n = :lat and zip_c = :zip");
			q.setDouble("lat", lat);
			q.setDouble("lng", lng);
			q.setInteger("zip", zip);

			location = (Location) q.uniqueResult();
			if (location != null)
				getSession().merge(location);
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		}

		return location;
	}

	public int saveOrUpdateLocation(Location location) {
		int id = -1;
		
		try {
			begin();
			id = (Integer) getSession().save(location);
			commit();
		} catch (HibernateException e) {
			if (getSession().getTransaction() != null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}

		return id;
	}
}
