package util;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import data.entity.*;

public class DatabaseCreatingSample {

	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.setTimeZone(TimeZone.getDefault());

		
		// 1.Skill
		Skill sk1 = new Skill();
		sk1.setName("Python");
		sk1.setActive("Y");
		sk1.setCreation_user("cchan");
		sk1.setUpdate_user("cchan");
		sk1.setCreation_time(new GregorianCalendar(TimeZone.getTimeZone("GMT")));
		sk1.setUpdate_time(new GregorianCalendar(TimeZone.getTimeZone("GMT")));
		Skill sk2 = new Skill();
		sk2.setName("Java");
		sk2.setActive("Y");
		sk2.setCreation_user("cchan");
		sk2.setUpdate_user("cchan");
		sk2.setCreation_time(new GregorianCalendar(TimeZone.getTimeZone("GMT")));
		sk2.setUpdate_time(new GregorianCalendar(TimeZone.getTimeZone("GMT")));
 

		// 2. User
		User user = new User();
		User user2 = new User();

		// 3. Asset type
		AssetType at = new AssetType();
		at.setName("pdf");
		at.setActive("Y");
		at.setCreation_user("cchan");
		at.setUpdate_user("cchan");
		at.setCreation_time(cal);
		at.setUpdate_time(cal);

		// 4. Asset
		Asset asset = new Asset();
		asset.setName("CV");
		asset.setType(at);
		asset.setUser(user);
		asset.setLocation("/files/cvs/323232.pdf");
		asset.setSize(100);
		asset.setActive("Y");
		asset.setCreation_user("cchan");
		asset.setUpdate_user("cchan");
		asset.setCreation_time(cal);
		asset.setUpdate_time(cal);

		// 5. Experience
		Experience exp = new Experience();
		exp.setName("Entry");
		exp.setActive("Y");
		exp.setCreation_user("cchan");
		exp.setUpdate_user("cchan");
		exp.setCreation_time(cal);
		exp.setUpdate_time(cal);
		
		// 6. Notification type
		NotificationType ntype = new NotificationType();
		ntype.setName("offer");
		ntype.setActive("Y");
		ntype.setCreation_user("cchan");
		ntype.setUpdate_user("cchan");
		ntype.setCreation_time(cal);
		ntype.setUpdate_time(cal);
		
		// 7. Notification
		Notification notif = new Notification();
		notif.setFrom(user);
		notif.setTo(user2);
		notif.setType(ntype);
		notif.setRead("N");
		notif.setTitle("This is a testing message.");
		notif.setContent("If the message has a body, it must be shown here.");
		notif.setActive("Y");
		notif.setCreation_user("cchan");
		notif.setUpdate_user("cchan");
		notif.setCreation_time(cal);
		notif.setUpdate_time(cal);
		
		// 8. Job
		Set<User> applicants = new HashSet<User>();
		applicants.add(user);
		applicants.add(user2);
		
		Set<Skill> skills = new HashSet<Skill>();
		skills.add(sk1);
		skills.add(sk2);
		
		Job job = new Job();
		job.setApplicants(applicants);
		job.setAuthor(user);
		job.setExperience(exp);
		job.setDescription("We describ our job here.");
		job.setRequirement("We need a super hero for this position.");
		job.setResponsibility("You need to save us.");
		job.setTitle("Super hero job");
		job.setSkills(skills);
		job.setActive("Y");
		job.setCreation_user("cchan");
		job.setUpdate_user("cchan");
		job.setCreation_time(cal);
		job.setUpdate_time(cal);
		
		// 9. Application status
		ApplicationStatus status = new ApplicationStatus();
		status.setName("Rejected");
		status.setActive("Y");
		status.setCreation_user("cchan");
		status.setUpdate_user("cchan");
		status.setCreation_time(cal);
		status.setUpdate_time(cal);
		
		// 10. Application
		Application app = new Application();
		app.setApplicant(user2);
		app.setAsset(asset);
		app.setStatus(status);
		app.setPosition(job);
		app.setActive("Y");
		app.setCreation_user("cchan");
		app.setUpdate_user("cchan");
		app.setCreation_time(cal);
		app.setUpdate_time(cal);
		
		
		// -----------------------------------------------
		ServiceRegistry sr = new StandardServiceRegistryBuilder()
			.applySettings(
				new Configuration().configure()
					.getProperties()).build();
		SessionFactory sessionFactory = new Configuration()
				.configure()
				.configure()
				.buildSessionFactory(sr);

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		// -----------------------------------------------

		session.saveOrUpdate(sk1);
		session.saveOrUpdate(sk2);
		session.saveOrUpdate(notif);
		session.saveOrUpdate(app);

		// -----------------------------------------------
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
		((StandardServiceRegistryImpl) sr).destroy();
		
	}
}
