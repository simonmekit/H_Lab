package com.test.hib.controller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.test.hib.model.User;

public class UpdatingUser {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        User u = new User();
        u.setId(3);
        u.setEmail("maheberekidusan@eotc.org");
        u.setFullname("Bate amanuel tsiwa");
        u.setPassword("282828");
        session.merge(u);
        session.getTransaction().commit();
        session.close();
    }
}