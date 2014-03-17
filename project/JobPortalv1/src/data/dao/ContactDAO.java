package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

import data.entity.Application;
import data.entity.Company;
import data.entity.Contact;


public class ContactDAO extends DAO {
	public List<Contact> listEntities() {
		List<Contact> contacts = null;
		try {
			contacts = getSession().createQuery("from Contact").list();
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		} 
	
		return contacts;
	}
	
	public Contact getEntityById(Integer id) {
		Contact contact = null;
		
		try{
			contact = (Contact) getSession().createQuery("from Contact where contact_id=" + id);
		} catch (HibernateException e) {
			if (getSession().getTransaction()!=null) {
				rollback();
			}
			e.printStackTrace();
		} finally {
			close();
		}
		
		return contact;
	}
	
	public int saveOrUpdate(Contact contact) {
		int id = -1;
		
		try{
			if(contact.getContact_id() != null && contact.getContact_id() >= 0) {
				id = contact.getContact_id();
				getSession().update(contact);
			}
			else {
				id = (Integer)getSession().save(contact); 
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
