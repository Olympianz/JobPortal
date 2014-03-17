package service;

import java.util.Calendar;

import util.SessionCtl;
import modelMB.CompanyBean;
import modelMB.ContactBean;
import data.dao.ContactDAO;
import data.dao.ContactTypeDAO;
import data.dao.StateDAO;
import data.entity.Company;
import data.entity.Contact;
import data.entity.Contact_type;
import data.entity.Location;
import data.entity.State;

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

	public static int saveOrUpdate(ContactBean contactBean) {
		Contact contact = null;
		Integer id = contactBean.getId();

		if (id != null && id >= 0) {
			// Get existing record
			contact = contactDao.getEntityById(id);
		} else {
			// Create new record
			contact = new Contact(SessionCtl.getLoggedInUser().getUser_name());
		}

		// Fetch all necessary object from database
		// Copy new data from bean to entity
		StateDAO stateDao = new StateDAO();
		State state = stateDao.getByName(contactBean.getState());

		ContactTypeDAO contactTypeDao = new ContactTypeDAO();
		Contact_type contact_type = contactTypeDao.getByName(contactBean
				.getType());

		// TODO: Find a better way to check existing location
		Location location = contactDao.getLocation(
				contactBean.getLat(),
				contactBean.getLng(), 
				contactBean.getZip());
		
		if (location == null) {
			location = new Location(SessionCtl.getLoggedInUser().getUser_name());
			location.setLatitude_n(contactBean.getLat());
			location.setLongitude_n(contactBean.getLng());
			location.setZip_c(contactBean.getZip());
			contactDao.saveOrUpdateLocation(location);
			location = contactDao.getLocation(
					contactBean.getLat(),
					contactBean.getLng(), 
					contactBean.getZip());
		}

		int result = -1;
		if (location != null && state != null && contact_type != null) {
			contact.setCompany_email_name(contactBean.getCompany_email());
			contact.setStreet_address_name(contactBean.getAddress());
			contact.setStreet_city_name(contactBean.getCity());
			contact.setState(state);
			contact.setContact_type(contact_type);
			contact.setLocation(location);
			
			contact.setUpdate_user_name(SessionCtl.getLoggedInUser()
					.getUser_name());
			contact.setUpdate_timestamp(Calendar.getInstance());
			result = contactDao.saveOrUpdate(contact);
		}

		return result;
	}
}
