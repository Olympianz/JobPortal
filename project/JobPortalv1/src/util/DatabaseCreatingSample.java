package util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import data.entity.*;

public class DatabaseCreatingSample {
	// 1.
	private static Skill newSkill(String name, String active, String creation,
			String update, Calendar ct, Calendar ut) {
		Skill sk = new Skill();
		sk.setName(name);
		sk.setActive(active);
		sk.setCreation_user(creation);
		sk.setUpdate_user(update);
		sk.setCreation_time(ct);
		sk.setUpdate_time(ut);
		return sk;
	}

	// 2.
	private static Role newRole(String active_status,
			Calendar creation_timestamp, String creation_user_name,
			String role_n, Calendar update_timestamp, String update_user_name) {
		Role role = new Role();
		role.setActive_status(active_status);
		role.setCreation_timestamp(creation_timestamp);
		role.setCreation_user_name(creation_user_name);
		role.setRole_n(role_n);
		role.setUpdate_timestamp(update_timestamp);
		role.setUpdate_user_name(update_user_name);
		return role;
	}

	// 3.
	private static Contact_type newContactType(String contact_type_n,
			String active_status, Calendar creation_timestamp,
			String creation_user_name, Calendar update_timestamp,
			String update_user_name) {
		Contact_type ct = new Contact_type();
		ct.setContact_type_n(contact_type_n);
		ct.setActive_status(active_status);
		ct.setCreation_timestamp(creation_timestamp);
		ct.setCreation_user_name(creation_user_name);
		ct.setUpdate_timestamp(update_timestamp);
		ct.setUpdate_user_name(update_user_name);
		return ct;
	}

	// 4.
	private static Location newLocation(Double latitude_n, Double longitude_n,
			Integer zip_c, String active_status, Calendar creation_timestamp,
			String creation_user_name, Calendar update_timestamp,
			String update_user_name) {
		Location loc = new Location();
		loc.setLatitude_n(latitude_n);
		loc.setLongitude_n(longitude_n);
		loc.setZip_c(zip_c);
		loc.setActive_status(active_status);
		loc.setCreation_timestamp(creation_timestamp);
		loc.setCreation_user_name(creation_user_name);
		loc.setUpdate_timestamp(update_timestamp);
		loc.setUpdate_user_name(update_user_name);
		return loc;
	}

	// 5.
	private static State newState(String state_n, String active_status,
			Calendar creation_timestamp, String creation_user_name,
			Calendar update_timestamp, String update_user_name) {
		State state = new State();
		state.setState_n(state_n);
		state.setActive_status(active_status);
		state.setCreation_timestamp(creation_timestamp);
		state.setCreation_user_name(creation_user_name);
		state.setUpdate_timestamp(update_timestamp);
		state.setUpdate_user_name(update_user_name);
		return state;
	}

	// 6.
	private static Contact newContact(String company_email_name,
			Contact_type contact_type, Location location, State state,
			String street_address_name, String street_city_name,
			String active_status, Calendar creation_timestamp,
			String creation_user_name, Calendar update_timestamp,
			String update_user_name) {
		Contact contact = new Contact();
		contact.setCompany_email_name(company_email_name);
		contact.setContact_type(contact_type);
		contact.setLocation(location);
		contact.setState(state);
		contact.setStreet_address_name(street_address_name);
		contact.setStreet_city_name(street_city_name);
		contact.setActive_status(active_status);
		contact.setCreation_timestamp(creation_timestamp);
		contact.setCreation_user_name(creation_user_name);
		contact.setUpdate_timestamp(update_timestamp);
		contact.setUpdate_user_name(update_user_name);
		return contact;
	}

	// 7.
	private static Experience newExperience(String name, String active,
			Calendar creation_time, String creation_user, Calendar update_time,
			String update_user) {
		Experience exp = new Experience();
		exp.setName(name);
		exp.setActive(active);
		exp.setCreation_user(creation_user);
		exp.setUpdate_user(update_user);
		exp.setCreation_time(creation_time);
		exp.setUpdate_time(update_time);
		return exp;
	}

	// 8
	private static Company newCompany(String company_n, Contact contact,
			String active, Calendar creation_time, String creation_user,
			Calendar update_time, String update_user) {
		Company company = new Company();
		company.setCompany_n(company_n);
		company.setContact(contact);
		return company;
	}

	// 9.
	private static User newUser(String name, String active, String email,
			String password, String session_id, Experience exp, Company company, Contact contact,
			Calendar creation_time, String creation_user, Calendar update_time,
			String update_user, Role role) {
		User user = new User();
		user.setUser_name(name);
		user.setActive_status(active);
		user.setCreation_timestamp(creation_time);
		user.setCreation_user_name(creation_user);
		user.setEmail(email);
		user.setExperience(exp);
		user.setCompany(company);
		user.setPassword(password);
		user.setRole(role);
		user.setSession_id(session_id);
		user.setUpdate_timestamp(update_time);
		user.setUpdate_user_name(update_user);
		user.setContact(contact);
		return user;
	}

	// 10.
	private static AssetType newAssetType(String name, String active,
			Calendar creation_time, String creation_user, Calendar update_time,
			String update_user) {
		AssetType at = new AssetType();
		at.setName(name);
		at.setActive(active);
		at.setCreation_user(creation_user);
		at.setUpdate_user(update_user);
		at.setCreation_time(creation_time);
		at.setUpdate_time(update_time);
		return at;
	}

	// 11.
	private static Asset newAssets(String name, User user, AssetType at,
			String loc, Integer size, String active, Calendar creation_time,
			String creation_user, Calendar update_time, String update_user) {

		Asset asset = new Asset();
		asset.setName(name);
		asset.setType(at);
		asset.setUser(user);
		asset.setLocation(loc);
		asset.setSize(size);
		asset.setActive(active);
		asset.setCreation_user(creation_user);
		asset.setUpdate_user(update_user);
		asset.setCreation_time(creation_time);
		asset.setUpdate_time(update_time);
		return asset;
	}

	// 12.
	private static NotificationType newNotificationType(String name,
			String active, Calendar creation_time, String creation_user,
			Calendar update_time, String update_user) {
		NotificationType ntype = new NotificationType();
		ntype.setName(name);
		ntype.setActive(active);
		ntype.setCreation_user(creation_user);
		ntype.setUpdate_user(update_user);
		ntype.setCreation_time(creation_time);
		ntype.setUpdate_time(update_time);
		return ntype;
	}

	// 13.
	private static Notification newNotification(User from, User to,
			NotificationType ntype, String read, String title, String content,
			String active, Calendar creation_time, String creation_user,
			Calendar update_time, String update_user) {
		Notification notif = new Notification();
		notif.setFrom(from);
		notif.setTo(to);
		notif.setType(ntype);
		notif.setRead(read);
		notif.setTitle(title);
		notif.setContent(content);
		notif.setActive(active);
		notif.setCreation_user(creation_user);
		notif.setUpdate_user(update_user);
		notif.setCreation_time(creation_time);
		notif.setUpdate_time(update_time);
		return notif;
	}

	// 14.
	private static Job newJob(List<Skill> skills, Experience exp, User author,
			String desc, String req, String res, String title, String active,
			Calendar creation_time, String creation_user, Calendar update_time,
			String update_user) {
		Job job = new Job();
		job.setAuthor(author);
		job.setExperience(exp);
		job.setDescription(desc);
		job.setRequirement(req);
		job.setResponsibility(res);
		job.setTitle(title);
		job.setSkills(skills);
		job.setActive(active);
		job.setCreation_user(creation_user);
		job.setUpdate_user(update_user);
		job.setCreation_time(creation_time);
		job.setUpdate_time(update_time);
		return job;
	}

	// 15.
	private static ApplicationStatus newApplicationStatus(String name,
			String active, Calendar creation_time, String creation_user,
			Calendar update_time, String update_user) {
		ApplicationStatus status = new ApplicationStatus();
		status.setName(name);
		status.setActive(active);
		status.setCreation_user(creation_user);
		status.setUpdate_user(update_user);
		status.setCreation_time(creation_time);
		status.setUpdate_time(update_time);
		return status;
	}

	// 16.
	private static Application newApplication(User user, Asset asset,
			ApplicationStatus status, Job job, String active,
			Calendar creation_time, String creation_user, Calendar update_time,
			String update_user) {
		Application app = new Application();
		app.setApplicant(user);
		app.setAsset(asset);
		app.setStatus(status);
		app.setPosition(job);
		app.setActive(active);
		app.setCreation_user(creation_user);
		app.setUpdate_user(update_user);
		app.setCreation_time(creation_time);
		app.setUpdate_time(update_time);
		return app;
	}

	public static void main(String[] args) {
		loadNSaveUser();
	}
	
	public static void loadNSaveUser() {
		// -----------------------------------------------
		ServiceRegistry sr = new StandardServiceRegistryBuilder()
				.applySettings(new Configuration().configure().getProperties())
				.build();
		SessionFactory sessionFactory = new Configuration().configure()
				.configure().buildSessionFactory(sr);
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			// -----------------------------------------------

			Query q = session.createQuery("from User where user_id=62");
			User user = (User) q.uniqueResult();
			
			user.setUser_name("Adam10");
			user.getContact().setStreet_address_name("aaaaaaaaaaaaa");
			session.saveOrUpdate(user);

			// -----------------------------------------------
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		} finally {
			((StandardServiceRegistryImpl) sr).destroy();
		}
		
	}
	
	public static void testLike () {
		// -----------------------------------------------
		ServiceRegistry sr = new StandardServiceRegistryBuilder()
				.applySettings(new Configuration().configure().getProperties())
				.build();
		SessionFactory sessionFactory = new Configuration().configure()
				.configure().buildSessionFactory(sr);
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			// -----------------------------------------------

			//List<Company> companies = session.createQuery("from Company where company_n like '%og%'").list();
			String query = "";
			//Query q = session.createQuery("from Application where position.title like :query");
			Query q = session.createQuery("from Company where company_n like :query");
			q.setString("query", ("%" + query + "%"));
			List<Company> companies = q.list();
			for (Company company: companies){
				System.out.println(company.getCompany_n());
			}

			// -----------------------------------------------
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		} finally {
			((StandardServiceRegistryImpl) sr).destroy();
		}
		
	}
	
	public static void accessDB() {
		// -----------------------------------------------
		ServiceRegistry sr = new StandardServiceRegistryBuilder()
				.applySettings(new Configuration().configure().getProperties())
				.build();
		SessionFactory sessionFactory = new Configuration().configure()
				.configure().buildSessionFactory(sr);
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			// -----------------------------------------------

			List<Job> jobs = session.createQuery("from Job").list();

			List<Skill> skills = jobs.get(0).getSkills();
			for (Skill skill : skills) {
				System.out.println(skill.getName());
			}

			// -----------------------------------------------
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		} finally {
			((StandardServiceRegistryImpl) sr).destroy();
		}
	}

	public static void createObjects() {
		Calendar cal = Calendar.getInstance();

		// 1.Skill
		Skill sk1 = newSkill("Python", "Y", "cchan", "cchan", cal, cal);
		Skill sk2 = newSkill("Java", "Y", "cchan", "cchan", cal, cal);

		// 2. Role
		Role admin = newRole("Y", cal, "cchan", "administrator", cal, "cchan");
		Role employer = newRole("Y", cal, "cchan", "employer", cal, "cchan");
		Role jobseeker = newRole("Y", cal, "cchan", "jobseeker", cal, "cchan");

		// 3.
		Contact_type ct1 = newContactType("company", "Y", cal, "sysdba", cal,
				"sysdba");
		Contact_type ct2 = newContactType("individual", "Y", cal, "sysdba",
				cal, "sysdba");

		// 4.
		Location loc1 = newLocation(-80.066016, 40.310621, 15102, "Y", cal,
				"sysdba", cal, "sysdba");
		Location loc2 = newLocation(-80.113900, 40.319151, 15241, "Y", cal,
				"sysdba", cal, "sysdba");

		// 5.
		List<State> states = new ArrayList<State>();
		states.add(newState("Alabama", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Alaska", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Arizona", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Arkansas", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("California", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Colorado", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Connecticut", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Delaware", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Florida", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Georgia", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Hawaii", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Idaho", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Illinois", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Indiana", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Iowa", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Kansas", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Kentucky", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Louisiana", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Maine", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Maryland", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Massachusetts", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Michigan", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Minnesota", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Mississippi", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Missouri", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Montana", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Nebraska", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Nevada", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("New Hampshire", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("New Jersey", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("New Mexico", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("New York", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("North Carolina", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("North Dakota", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Ohio", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Oklahoma", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Oregon", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Pennsylvania", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Rhode Island", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("South Carolina", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("South Dakota", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Tennessee", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Texas", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Utah", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Vermont", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Virginia", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Washington", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("West Virginia", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Wisconsin", "Y", cal, "sysdba", cal, "sysdba"));
		states.add(newState("Wyoming", "Y", cal, "sysdba", cal, "sysdba"));

		// 6.
		Contact contact1 = newContact("information@cogentinfo.com", ct1, loc2,
				states.get(37), "1035 Boyce Road, Suite 108", "Pittsburgh",
				"Y", cal, "sysdba", cal, "sysdba");
		Contact contact2 = newContact("information@cogentinfo.com", ct2, loc1,
				states.get(37), "1126 Highfield Ct", "Bethel Park", "Y", cal,
				"sysdba", cal, "sysdba");

		// 7. Experience
		Experience exp1 = newExperience("Entry level", "Y", cal, "sysdba", cal,
				"sysdba");
		Experience exp2 = newExperience("Junior level", "Y", cal, "sysdba",
				cal, "sysdba");
		Experience exp3 = newExperience("Intermediate level", "Y", cal,
				"sysdba", cal, "sysdba");
		Experience exp4 = newExperience("Senior level", "Y", cal, "sysdba",
				cal, "sysdba");
		Experience exp5 = newExperience("Intern", "Y", cal, "sysdba", cal,
				"sysdba");
		Experience exp6 = newExperience("Manager", "Y", cal, "sysdba", cal,
				"sysdba");
		Experience exp7 = newExperience("Director", "Y", cal, "sysdba", cal,
				"sysdba");
		Experience exp8 = newExperience("Executive", "Y", cal, "sysdba", cal,
				"sysdba");

		// 8. Company
		Company company1 = newCompany("Cogent Infotech", contact1, "Y", cal, "sysdba", cal,
				"sysdba");
		// 8. User
		User user1 = newUser("Adam", "Y", "adam@yy.com", "123", "", exp2, company1, contact1, cal,
				"sysdba", cal, "sysdba", employer);
		User user2 = newUser("Bob", "Y", "bob@yy.com", "123", "", exp3, company1, contact1, cal,
				"sysdba", cal, "sysdba", admin);
		User user3 = newUser("Cathy", "Y", "cathy@yy.com", "123", "", exp1, company1, contact1,
				cal, "sysdba", cal, "sysdba", jobseeker);
		User user4 = newUser("David", "Y", "david@yy.com", "123", "", exp2,
				company1, contact1, cal, "sysdba", cal, "sysdba", jobseeker);

		// 9. Asset type
		AssetType at1 = newAssetType("pdf", "Y", cal, "sysdba", cal, "sysdba");
		AssetType at2 = newAssetType("doc", "Y", cal, "sysdba", cal, "sysdba");
		AssetType at3 = newAssetType("txt", "Y", cal, "sysdba", cal, "sysdba");

		// 10. Asset
		Asset asset1 = newAssets("CV", user3, at1, "/files/cvs/323232.pdf",
				100, "Y", cal, "sysdba", cal, "sysdba");

		// 11. Notification type
		NotificationType ntype1 = newNotificationType("Information", "Y", cal,
				"sysdba", cal, "sysdba");
		NotificationType ntype2 = newNotificationType("Offer", "Y", cal,
				"sysdba", cal, "sysdba");

		// 12. Notification
		Notification notif1 = newNotification(user1, user2, ntype1, "N",
				"This is a testing message.",
				"If the message has a body, it must be shown here.", "Y", cal,
				"sysdba", cal, "sysdba");
		Notification notif2 = newNotification(user2, user1, ntype1, "N",
				"This is another testing message.",
				"Response to the previous message.", "Y", cal, "sysdba", cal,
				"sysdba");

		// 13. Job

		List<Skill> skills = new ArrayList<Skill>();
		skills.add(sk1);
		skills.add(sk2);

		Job job1 = newJob(skills, exp1, user1, "We describ our job here.",
				"We need a super hero for this position.",
				"You need to save us.", "Super hero job", "Y", cal, "sysdba",
				cal, "sysdba");
		Job job2 = newJob(skills, exp1, user1, "It's an assistance job.",
				"We need a sidekick for this position.",
				"You need to help super hero to save us.", "Side kick job",
				"Y", cal, "sysdba", cal, "sysdba");

		// 14. Application status
		ApplicationStatus status1 = newApplicationStatus("Rejected", "Y", cal,
				"sysdba", cal, "sysdba");
		ApplicationStatus status2 = newApplicationStatus("Accepted", "Y", cal,
				"sysdba", cal, "sysdba");
		ApplicationStatus status3 = newApplicationStatus("Pending", "Y", cal,
				"sysdba", cal, "sysdba");
		ApplicationStatus status4 = newApplicationStatus("Processing", "Y",
				cal, "sysdba", cal, "sysdba");

		// 15. Application
		Application app1 = newApplication(user3, asset1, status3, job1, "Y",
				cal, "sysdba", cal, "sysdba");

		List<Application> applications = new ArrayList<Application>();
		applications.add(app1);

		job1.setApplications(applications);
		job2.setApplications(applications);

		// -----------------------------------------------
		ServiceRegistry sr = new StandardServiceRegistryBuilder()
				.applySettings(new Configuration().configure().getProperties())
				.build();
		SessionFactory sessionFactory = new Configuration().configure()
				.configure().buildSessionFactory(sr);
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			// -----------------------------------------------

			session.saveOrUpdate(sk1);
			session.saveOrUpdate(sk2);
			session.saveOrUpdate(employer);
			session.saveOrUpdate(admin);
			session.saveOrUpdate(jobseeker);
			session.saveOrUpdate(ct1);
			session.saveOrUpdate(ct2);
			session.saveOrUpdate(loc1);
			session.saveOrUpdate(loc2);
			for (State st : states) {
				session.saveOrUpdate(st);
			}
			session.saveOrUpdate(contact1);
			session.saveOrUpdate(contact2);
			session.saveOrUpdate(user1);
			session.saveOrUpdate(user2);
			session.saveOrUpdate(user3);
			session.saveOrUpdate(user4);

			session.saveOrUpdate(at1);
			session.saveOrUpdate(at2);
			session.saveOrUpdate(at3);
			session.saveOrUpdate(asset1);
			session.saveOrUpdate(exp1);
			session.saveOrUpdate(exp2);
			session.saveOrUpdate(exp3);
			session.saveOrUpdate(exp4);
			session.saveOrUpdate(exp5);
			session.saveOrUpdate(exp6);
			session.saveOrUpdate(exp7);
			session.saveOrUpdate(exp8);
			session.saveOrUpdate(notif1);
			session.saveOrUpdate(notif2);
			session.saveOrUpdate(job1);
			session.saveOrUpdate(job2);
			session.saveOrUpdate(status1);
			session.saveOrUpdate(status2);
			session.saveOrUpdate(status3);
			session.saveOrUpdate(status4);
			session.saveOrUpdate(app1);

			// -----------------------------------------------
			session.getTransaction().commit();
			session.close();
			sessionFactory.close();
		} finally {
			((StandardServiceRegistryImpl) sr).destroy();
		}
	}
}
