package com.test.hib;

import com.test.hib.model.Address;
import com.test.hib.model.Cohort;
import com.test.hib.model.Department;
import com.test.hib.model.Teacher;
import jakarta.persistence.NamedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class App {
    public static void main(String[] args) {
   // manyToOne();
      //  oneToOne();
       //manyToMany();
       // oneToMany();
        manyToOneInteractive();
    }
public static void manyToOne(){
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    Session session = factory.openSession();
    Transaction transaction = session.beginTransaction();

    //creating departments
    Department dept1 = new Department("Finance");
    Department dept2 = new Department("Project");

    //creating teacher
    Teacher t1 = new Teacher("1000","MHaseeb",dept1);
    Teacher t2 = new Teacher("2220","Shahparan",dept1);
    Teacher t3 = new Teacher("3000","James",dept1);
    Teacher t4 = new Teacher("40000","Joseph",dept2);
    t1.setDepartment(dept1);
    t2.setDepartment(dept1);
    t3.setDepartment(dept2);
    //Storing Departments in database
     session.persist(dept1);
     session.persist(dept2);
    //Storing teachers  in database
    session.persist(t1);
    session.persist(t2);
    session.persist(t3);
    session.persist(t4);
    transaction.commit();
}

    public static void oneToMany(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        //creating teacher
        Teacher t1 = new Teacher("1000","MHaseeb");
        Teacher t2 = new Teacher("2220","Shahparan");
        Teacher t3 = new Teacher("3000","James");
        Teacher t4 = new Teacher("40000","Joseph");
        Teacher t5 = new Teacher("200","Ali");

        //Add teacher entity object to the list
        ArrayList<Teacher> teachersList = new ArrayList<>();
        teachersList.add(t1);
        teachersList.add(t2);
        teachersList.add(t3);
        teachersList.add(t4);
        teachersList.add(t5);
        session.persist(t1);
        session.persist(t2);
        session.persist(t3);
        session.persist(t4);
        session.persist(t5);
        //Creating Department
        Department department = new Department();
        department.setDeptName("Development");
        department.setTeacherList(teachersList);
        //Storing Department
        session.persist(department);
        t.commit();
    }


    public static void oneToOne(){
        System.out.println("Maven + Hibernate + SQL One to One Mapping Annotations");

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();

        Transaction t = session.beginTransaction();
        Address a1 = new Address("27th street","NYC","NY",11103);
        Address a2 = new Address("28th street","Buffalo","NY",15803);

        Teacher t1 = new Teacher("1000","MHaseeb");
        Teacher t2 = new Teacher("2220","Shahparan");
        t1.setAddress(a1);
        t2.setAddress(a2);



        session.persist(a1);
        session.persist(a2);
        session.persist(t1);
        session.persist(t2);

        t.commit();
    }

    public static void manyToMany(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction t = session.beginTransaction();
        //----Create Cohort/class Entity set one----
        Cohort Class1 = new Cohort("Java Developer", "14 weeks");
        Cohort Class2 = new Cohort("FullStack Developer", "7 Weeks");
        Cohort Class3 = new Cohort("Python Developer", "12 Weeks");
        //------  Store Cohort  / Class  --------
        session.persist(Class1);
        session.persist(Class2);
        session.persist(Class3);

        //-----Create Cohort one / Class one --------
        Set<Cohort> ClassSet1 = new HashSet<Cohort>();
        ClassSet1.add(Class1);
        ClassSet1.add(Class2);
        ClassSet1.add(Class3);
        //-----Create Cohort two / Class two --------
        Set<Cohort> ClassSet2 = new HashSet<Cohort>();
        ClassSet2.add(Class2);
        ClassSet2.add(Class3);
        ClassSet2.add(Class1);
        //-----Create Cohort Three / Class Three --------
        Set<Cohort> ClassSet3 = new HashSet<Cohort>();
        ClassSet3.add(Class3);
        ClassSet3.add(Class1);
        ClassSet3.add(Class2);

        Teacher t1 = new Teacher("100", "Haseeb", ClassSet1);
        Teacher t2 = new Teacher("200", "Jenny", ClassSet2);
        Teacher t3 = new Teacher("200", "Charlie", ClassSet3);

        session.persist(t1);
        session.persist(t2);
        session.persist(t3);
        t.commit();
    }

    public static void manyToOneInteractive(){
        System.out.print("Welcome to  manyToOneInteractive!!");
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<Department> depsArray = new ArrayList<Department>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("==============================================");
        System.out.println("==============================================");
        System.out.println("Welcome to  manyToOneInteractive!!");
        System.out.print("Enter the number of departments to Add: ");
        int numDep = scanner.nextInt();
        for (int i = 0; i < numDep; i++) {
            System.out.print("Enter Dept Name: ");
            String depName = scanner.next();
            Department dep = new Department(depName);
            depsArray.add(dep);
        }
        for (int i = 0; i < depsArray.size(); i++) {
            session.persist(depsArray.get(i));
        }
        transaction.commit();


        try{
        transaction = session.beginTransaction();
        System.out.println("==============================================");
        System.out.println("==============================================");
        System.out.print("Enter the name of department to delete: ");
        Department department = new Department(scanner.next());

        String query = "Delete from Department d where d.deptName = :name";
        int del = session.createQuery(query).setParameter("name", department).executeUpdate();
            System.out.println("Department deleted: " + del);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }



        try{
            transaction = session.beginTransaction();
            System.out.println("==============================================");
            System.out.println("==============================================");
            System.out.print("Enter the name of department to update: ");
            Department department = new Department(scanner.next());
            System.out.println("Enter new name: ");
            String newName = scanner.next();

            String query = "update Department d set d.deptName = :newName where d.deptName = :name";
            int del = session.createQuery(query).setParameter("newName", newName)
                    .setParameter("name", department)
                    .executeUpdate();
            System.out.println("Department updated: " + del);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }


    }



}

