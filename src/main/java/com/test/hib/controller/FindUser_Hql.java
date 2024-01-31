package com.test.hib.controller;
import java.util.List;

import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.test.hib.model.User;
import org.hibernate.query.Query;

public class FindUser_Hql {
    public void findUser() {
        SessionFactory factory = new   Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "FROM User"; // Example of HQL to get all records of user class
        TypedQuery query = session.createQuery(hql);
        List<User> results = query.getResultList();
//        for (User u : results) {
//            System.out.println("User Id: " +u.getId() + "|" + " Full name:" + u.getFullname() +"|"+ "Email: " + u.getEmail() +"|"+ "password" + u.getPassword());
//        }
        System.out.printf("%s%13s%17s%34s%n","|User Id","|Full name","|Email","|Password");
        for (User u:results) {
            System.out.printf(" %-10d %-20s %-30s %s %n", u.getId(), u.getFullname(), u.getEmail(), u.getPassword());
        }

    }

    public void findUserSelect() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        /* ------------  Example of HQL to get all the records------- */
        String hql = "SELECT u FROM User u";
        Query query = session.createQuery(hql);
        List<User> list = query.getResultList();

        for (User u : list) {
            System.out.println("User Id: " + u.getId() + "|" + " Full name:" + u.getFullname() + "|" + "Email: " + u.getEmail() + "|" + "password" + u.getPassword());

        }
    }

    public void getRecordbyId() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "FROM User E WHERE E.id = 2 ORDER BY E.salary DESC";
        TypedQuery query = session.createQuery(hql);
        List<User> results = query.getResultList();
        for (User u : results) {
            System.out.println("User Id: " +u.getId() + "|" + " Full name:" +
                    u.getFullname() +"|"+ "Email: " + u.getEmail() +"|"+ "password" + u.getPassword());
        }
    }

    public void getrecords()
    {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        TypedQuery<Object[]> queryy = session.createQuery(
                "SELECT U.salary, U.fullname FROM User AS U", Object[].class);
        List<Object[]> resultss = queryy.getResultList();
        for (Object[] a : resultss) {
            System.out.println("Salary: " + a[0] + ", City: " + a[1]);
        }
    }

    public void  getmaxSalary()
    {
        SessionFactory factory = new   Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "SELECT max(U.salary) FROM User U";
        TypedQuery query = session.createQuery(hql);
        double result =(double) query.getSingleResult();
        System.out.println(result);
    }

    public void   getmaxSalaryGroupBy()
    {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "SELECT SUM(U.salary), U.city FROM User U GROUP BY U.city";
        TypedQuery query = session.createQuery(hql);
        List<Object[]> result =query.getResultList();
        for (Object[] o : result) {
            System.out.println("Total salary " +o[0] +" | city: "+ o[1] );
        }
    }

    public void NamedQueryExample() {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        String hql = "FROM User u WHERE u.id = :id";
        TypedQuery query = session.createQuery(hql);
        query.setParameter("id", 2);
        List<User> result = query.getResultList();
        for (User u : result) {
            System.out.println("User Id: " + u.getId() + "|" + " Full name:" + u.getFullname() + "|" + "Email: " + u.getEmail() + "|" + "password" + u.getPassword());
        }
    }

}