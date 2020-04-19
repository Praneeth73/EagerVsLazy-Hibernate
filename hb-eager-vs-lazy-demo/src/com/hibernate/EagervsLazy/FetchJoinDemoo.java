package com.hibernate.EagervsLazy;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.hibernate.model.Instructor;
import com.hibernate.model.InstructorDetail;

public class FetchJoinDemoo {

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
		
		Query<Instructor> query= session.createQuery("select i from Instructor i "
											+"JOIN FETCH i.courses "
											+"where i.id=:theInstuctorId",
											Instructor.class);
		
		query.setParameter("theInstuctorId", theId);
		
		//excute the querey and get Instructor
		//get single result will load the instructor and courses at all once because of the query mentioned above
		Instructor tempInstructor = query.getSingleResult();
		
		System.out.println("Instructor ... -->" + tempInstructor);

		//commit transaction;
		session.getTransaction().commit();
		
		session.close();

		//get courses for the instructor
		System.out.println("Courses for a instructor...--->"+ tempInstructor.getCourses());
		
		}
		finally {
			
			session.close();
			
			factory.close();
		}
	}

}
