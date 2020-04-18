package com.hibernate.EagervsLazy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Instructor;
import com.hibernate.model.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		try {
		
		
		Instructor tempInstructor = new Instructor("praneeth","Public","praneeth@gmail.com");
		
		InstructorDetail tempInstructorDetail = new InstructorDetail("www.youtube.com","Video Games");
		
		System.out.println("saving instructor..."+tempInstructor);
		
		tempInstructor.setInstructorDetail(tempInstructorDetail);
		
		session.beginTransaction();
		
		//save the instructor
		session.save(tempInstructor);//because of cascade it automatically saves the associated table (instructor detail)
		
		session.getTransaction().commit();
		}
		finally {
			
			session.close();
			
			factory.close();
		}
	}

}
