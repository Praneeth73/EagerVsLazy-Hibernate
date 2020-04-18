package com.hibernate.EagervsLazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Instructor;
import com.hibernate.model.InstructorDetail;

public class DeleteCourseDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {

		session.beginTransaction();
		
		//get a course id of 10
		int theId=10;
		Course tempCourse = session.get(Course.class,theId);
		session.delete(tempCourse);
		System.out.println("deleted the course ");
		session.getTransaction().commit();

		}
		finally {
			
			session.close();
			
			factory.close();
		}
	}

}
