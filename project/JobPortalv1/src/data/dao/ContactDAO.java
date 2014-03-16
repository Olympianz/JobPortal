package data.dao;

import java.util.List;

import org.hibernate.HibernateException;

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
}
