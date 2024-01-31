package com.test.hib.model;

import jakarta.persistence.*;

@Entity
@Table(name="cohort")
public class Cohort {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY )
    private int cohortId;
    private String cohortName;
    private String duration;

    public Cohort(){}
    public Cohort(String cName, String duration){
        this.cohortName = cName;
        this.duration = duration;
    }
    public int getCohortId() {
        return cohortId;
    }

    public void setCohortId(int cohortId) {
        this.cohortId = cohortId;
    }

    public String getCohortName() {
        return cohortName;
    }

    public void setCohortName(String cohortName) {
        this.cohortName = cohortName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
