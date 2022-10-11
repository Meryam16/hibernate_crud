package org.example.hibernate;

import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class UpdateStudentDemo {
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
            int studentId = 2;
            //start a transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on the id : primary key
            System.out.println("\n Getting student with id: " + studentId );

            Student myStudent = session.get(Student.class, studentId);

            System.out.println("Updating student ... ");
            myStudent.setFirstName("Murad");

            //commit transaction
            session.getTransaction().commit();

        /*
            //New Code

            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            session.createQuery("update Student set email = 'application@gmail.com'")
                            .executeUpdate();

            session.getTransaction().commit();
        */

            System.out.println("Done!");
        }
        finally
        {
            factory.close();
        }
    }
}
