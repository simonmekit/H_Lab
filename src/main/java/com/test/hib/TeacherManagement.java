package com.test.hib;

import com.test.hib.model.Department;
import com.test.hib.model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Scanner;

public class TeacherManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

            System.out.println("Enter operation (add/delete/update/addtoe):");
            String operation = scanner.nextLine().toLowerCase();

            switch (operation) {
                case "add":
                    addTeacher(scanner, factory);
                    break;
                case "delete":
                    deleteTeacher(scanner, factory);
                    break;
                case "update":
                    updateTeacher(scanner, factory);
                    break;
                case "addtoe":
                    addTeachersToExistingDept(scanner, factory);
                    break;
                default:
                    System.out.println("Invalid operation!");
        }
    }

    public static void addTeacher(Scanner scanner, SessionFactory factory){

        Session session = factory.openSession();
        Transaction transaction = null;
        ArrayList<Teacher> teacherList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();

            System.out.print("Enter the number of teachers to Add: ");
            int numT = scanner.nextInt();
            for (int i = 0; i < numT; i++) {
                System.out.println("Enter teacher Name: ");
                String tName = scanner.next();
                System.out.println("Enter teacher Salary: ");
                String salary = scanner.next();
                System.out.println("Enter teacher department: ");
                String depName = scanner.next();
                Department dep = new Department(depName);
                Teacher t1 = new Teacher(salary,tName,dep);
                teacherList.add(t1);
            }
            for (int i = 0; i < teacherList.size(); i++) {
                session.persist(teacherList.get(i));
            }

            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public static void addTeachersToExistingDept(Scanner scanner, SessionFactory factory){

        Session session = factory.openSession();
        Transaction transaction = null;
        ArrayList<Teacher> teacherList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            System.out.println("Enter teachers department ID: ");
            int depName = scanner.nextInt();
            Department dep = session.get(Department.class, depName);

            System.out.print("Enter the number of teachers to Add: ");
            int numT = scanner.nextInt();
            for (int i = 0; i < numT; i++) {
                System.out.println("Enter teacher Name: ");
                String tName = scanner.next();
                System.out.println("Enter teacher Salary: ");
                String salary = scanner.next();

                Teacher t1 = new Teacher(salary,tName,dep);
                teacherList.add(t1);
            }
            for (int i = 0; i < teacherList.size(); i++) {
                session.persist(teacherList.get(i));
            }

            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    private static void deleteTeacher(Scanner scanner, SessionFactory factory) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter teacher ID to delete:");
            int teacherIdToDelete = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Teacher teacherToDelete = session.get(Teacher.class, teacherIdToDelete);

            if (teacherToDelete != null) {
                session.remove(teacherToDelete);
                System.out.println("Teacher deleted successfully.");
            } else {
                System.out.println("Teacher not found.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void updateTeacher(Scanner scanner, SessionFactory factory) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter Teacher ID to update:");
            int teacherIdToUpdate = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Teacher teacherToUpdate = session.get(Teacher.class, teacherIdToUpdate);

            if (teacherToUpdate != null) {
                System.out.println("Enter new teacher name:");
                String newTeacherName = scanner.nextLine();
                teacherToUpdate.setTeacherName(newTeacherName);

                session.merge(teacherToUpdate);
                System.out.println("Teacher updated successfully.");
            } else {
                System.out.println("Teacher not found.");
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
