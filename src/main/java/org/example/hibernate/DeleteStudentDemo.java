package org.example.hibernate;

import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args)
    {
        //create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        //create session
        Session session = factory.getCurrentSession();

        try{
            int studentId = 5;
            //start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id : primary key
            System.out.println("\n Getting student with id: " + studentId );

            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Deleting student: " + myStudent);
            session.delete(myStudent);

            //commit transaction
            session.getTransaction().commit();


            System.out.println("Done!");
        }
        finally
        {
            factory.close();
        }
    }
}
