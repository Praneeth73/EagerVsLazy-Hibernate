package com.hibernate.EagervsLazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Instructor;
import com.hibernate.model.InstructorDetail;

public class CreateInstructorDemo {

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
		
		// create some courses
		Course tempCourse1  =  new Course("Air Guiter");
		Course tempCourse2  = new Course("paintball");
		// add the course to the instructor
		
		 tempInstructor.add(tempCourse1);
		 tempInstructor.add(tempCourse2);
		
		//save the courses
		 session.save(tempCourse1);
		 session.save(tempCourse2);

		session.getTransaction().commit();
		
		
		
		}
		finally {
			
			session.close();
			
			factory.close();
		}
	}

}
