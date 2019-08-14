package com.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Student;

public class QueryStudentDemo {

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
			 * querying through hibernate
			 */			
			// start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").list();
			
			// display the students results
			displayStudents(theStudents);
			
			// query students: lastName='Wick'
			theStudents = session.createQuery("from Student s where s.lastName='Wick'").getResultList();
			
			// display the students result
			System.out.println("\n\nStudent who have last name of Wick");
			displayStudents(theStudents);
			
			// query student with lastName = 'Downey' or firstName = 'Robert'
			theStudents = 
					session.createQuery("from Student s where s.lastName='Downey' OR s.firstName='Robert'").getResultList();
			
			// display the students result
			System.out.println("\n\nStudent who have last name of Downey or first name of Robert");
			displayStudents(theStudents);
			
			// query with like clause
			theStudents = session.createQuery("from Student s where s.email "
								+ " LIKE '%gmail.com'").getResultList();
			
			System.out.println("\nList of students with same email using LIKE");
			displayStudents(theStudents);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
