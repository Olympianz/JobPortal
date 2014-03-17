package service;

import modelMB.ContactBean;
import data.dao.ContactDAO;
import data.entity.Contact;

public class ContactService {
	static final ContactDAO contactDao = new ContactDAO();
	
	public static void loadFromEntity(ContactBean contactBean, Contact contact) {
		contactBean.setAddress(contact.getStreet_address_name());
		contactBean.setCity(contact.getStreet_city_name());
		contactBean.setId(contact.getContact_id());
		contactBean.setLat(contact.getLocation().getLatitude_n());
		contactBean.setLng(contact.getLocation().getLongitude_n());
		contactBean.setState(contact.getState().getState_n());
		contactBean.setType(contact.getContact_type().getContact_type_n());
		contactBean.setZip(contact.getLocation().getZip_c());
	}
	
	public static void loadFromDB(ContactBean contactBean, Integer id) {
		Contact contact = contactDao.getEntityById(id);
		loadFromEntity(contactBean, contact);
	}
}
