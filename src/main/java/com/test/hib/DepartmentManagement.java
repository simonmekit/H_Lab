package com.test.hib;

import com.test.hib.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Scanner;

public class DepartmentManagement {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
       // Session session = factory.openSession();

        System.out.println("Enter operation (add/delete/update):");
        String operation = scanner.nextLine().toLowerCase();

        switch (operation) {
            case "add":
                addDepartment(scanner, factory);
                break;
            case "delete":
                deleteDepartment(scanner, factory);
                break;
            case "update":
                updateDepartment(scanner, factory);
                break;
            default:
                System.out.println("Invalid operation.");
        }

        scanner.close();
    }

    private static void addDepartment(Scanner scanner, SessionFactory factory) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            ArrayList<Department> depsArray = new ArrayList<Department>();
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
            System.out.println("Department added successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void deleteDepartment(Scanner scanner, SessionFactory factory) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter department ID to delete:");
            int departmentIdToDelete = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Department departmentToDelete = session.get(Department.class, departmentIdToDelete);

            if (departmentToDelete != null) {
                session.remove(departmentToDelete);
                System.out.println("Department deleted successfully.");
            } else {
                System.out.println("Department not found.");
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

    private static void updateDepartment(Scanner scanner, SessionFactory factory) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            System.out.println("Enter department ID to update:");
            int departmentIdToUpdate = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            Department departmentToUpdate = session.get(Department.class, departmentIdToUpdate);

            if (departmentToUpdate != null) {
                System.out.println("Enter new department name:");
                String newDepartmentName = scanner.nextLine();
                departmentToUpdate.setDeptName(newDepartmentName);

                session.merge(departmentToUpdate);
                System.out.println("Department updated successfully.");
            } else {
                System.out.println("Department not found.");
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