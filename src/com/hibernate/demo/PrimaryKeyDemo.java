package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			/*
			 * use the session object to save Java object
			 */
			// create 3 the student objects
			System.out.println("Creating 3 student object...");
			Student tempStudent1 = new Student("Carol", "Danvers", "Carol@gmail.com");
			Student tempStudent2 = new Student("Steve", "Rogers", "Steve@gmail.com");
			Student tempStudent3 = new Student("Robert", "Downey", "Rob@gmail.com");
			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}


}