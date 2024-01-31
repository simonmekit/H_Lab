package com.test.hib.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Department implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int deptId;
    private String deptName;

    public Department(int deptId, String deptName) {
        super();
        this.deptId = deptId;
        this.deptName = deptName;
    }

    public Department() {}

    public Department(String deptName) {
        this.deptName = deptName;
    }

    @OneToMany(targetEntity = Teacher.class, cascade = {CascadeType.ALL})
        private List<Teacher> teacherList;


    public List<Teacher> getTeacherList(){
        return this.teacherList;
    }
    public void setTeacherList(List<Teacher> tList){
        this.teacherList = tList;
    }
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
