package org.example.hibernate;

import org.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
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

            //start a transaction
            session.beginTransaction();


            //  query students
            List<Student> theStudents = session.createQuery("from Student").list();

            //display the students
            displayStudents(theStudents);


            //query students: lastName='Jafar'
            theStudents = session.createQuery("from Student s where s.lastName='Jafar'").list();

            //Display students
            System.out.println("\n\n Students who have last name of Jafar");
            displayStudents(theStudents);


            //query students: lastName='Guliyef' OR firstName="Maryam"
            theStudents=
                    session.createQuery("from Student s where" +
                            " s.lastName='Guliyef' OR s.firstName = 'Maryam'").list();

            //Display students
            System.out.println("\n\n Students who have last name of Guliyef or first name of Maryam");
            displayStudents(theStudents);


            //query students where email LIKE "%a@gmail.com"
            theStudents=
                    session.createQuery("from Student s where "
                    +" s.email LIKE '%a@gmail.com'").list();

            //Display students
            System.out.println("\n\n Students whose email ends with 'a@gmail.com");
            displayStudents(theStudents);

            //commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally
        {
            factory.close();
        }
    }

    private static void displayStudents(List<Student> theStudents) {
        for(Student tempStudent : theStudents)
        {
            System.out.println(tempStudent);
        }
    }
}
