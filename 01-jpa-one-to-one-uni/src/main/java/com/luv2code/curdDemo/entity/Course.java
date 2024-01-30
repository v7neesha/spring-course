package com.luv2code.curdDemo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GeneratedColumn;

@Table(name = "course")
@Entity
public class Course {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;

    private String title;


    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Course(String title) {
        this.title = title;
    }
    public Course(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
