package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            insertStudents(sessionFactory);
            try (Session session = sessionFactory.openSession()) {
                Student student = session.find(Student.class, 1);
                System.out.println(student);

            }

            try (Session session = sessionFactory.openSession()) {
                Query<Student> q = session.createQuery("select s from Student s where  age >=:age ", Student.class);
                q.setParameter("age", 20);
                List<Student> resultList = q.getResultList();
                System.out.println(resultList);


            }


            Student student = new Student();
            student.setId(1);
            student.setFirstName("Dmitrii");
            student.setSecondName("Petrov");
            student.setAge(23);

            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(student);
                transaction.commit();
            }
            try (Session session = sessionFactory.openSession()){
                Transaction transaction1 = session.beginTransaction();
                student.setFirstName(" Max");
                session.merge(student);
                transaction1.commit();
            }
            try (Session session = sessionFactory.openSession()){
                Transaction transaction2 = session.beginTransaction();
                session.remove(student);
                transaction2.commit();


            }


            try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:test", "sa", "");
                 Statement statement = connection.createStatement()) {
                ResultSet rs = statement.executeQuery("select id,firstname, secondname, age from Students");
                while (rs.next()) {
                    System.out.println("ID = " + rs.getInt("id"));
                    System.out.println("FIRSTNAME = " + rs.getString("firstname"));
                    System.out.println("SECONDNAME = " + rs.getString("secondname"));
                    System.out.println("AGE = " + rs.getInt("age"));
                }


            }

        }

    }
    private static void insertStudents(SessionFactory sessionFactory){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            for (int i = 1; i <= 10; i++) {
                Student student = new Student();
                student.setId(i);
                student.setFirstName("Student №" + i);

            }

        }
    }


}
