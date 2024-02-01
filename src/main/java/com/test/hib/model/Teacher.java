package com.test.hib.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="Teacher")
public class Teacher implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int teacherId;
    private String salary;
    private String teacherName;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_dept")
    private Department department;

     @OneToOne(cascade = CascadeType.ALL)
     private Address address;

    @ManyToMany(targetEntity = Cohort.class)
    private Set<Cohort> cohort;

    public Set<Cohort> getCohort() {
        return cohort;
    }

    public void setCohort(Set<Cohort> cohort) {
        this.cohort = cohort;
    }

    public Address getAddress() {
         return address;
     }

     public void setAddress(Address address) {
         this.address = address;
    }

    public Teacher(String salary, String teacherName, Set<Cohort> cohort) {
        this.salary = salary;
        this.teacherName = teacherName;
        this.cohort = cohort;
    }
    public Teacher() {}

    public Teacher(String salary, String teacherName, Department department) {
        this.salary = salary;
        this.teacherName = teacherName;
        this.department = department;
    }

    public Teacher(String salary, String teacherName) {
        this.salary = salary;
        this.teacherName = teacherName;
    }

    public int getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;    }
    public Department getDepartment(){
        return department;
    }
    public void setDepartment(Department department){
        this.department = department;
    }
}

