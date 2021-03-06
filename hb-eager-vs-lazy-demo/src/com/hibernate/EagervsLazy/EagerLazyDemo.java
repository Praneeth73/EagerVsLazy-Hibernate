package com.hibernate.EagervsLazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Instructor;
import com.hibernate.model.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {

		session.beginTransaction();
		
		//get a instructor from db 
		int theId=1;
		Instructor tempInstructor = session.get(Instructor.class, theId);
		
		System.out.println("Instructor ... -->" + tempInstructor);
		
		System.out.println("Courses for a instructor...--->"+ tempInstructor.getCourses());

		session.getTransaction().commit();
		
		session.close();
		
		//To resolve the issue of lazy loading to retrieve data after session  is closed
		
		//option 1 --> we need to have have getter before session is closed
		
		

		//get courses for the instructor
		System.out.println("Courses for a instructor...--->"+ tempInstructor.getCourses());
		
		}
		finally {
			
			session.close();
			
			factory.close();
		}
	}

}
