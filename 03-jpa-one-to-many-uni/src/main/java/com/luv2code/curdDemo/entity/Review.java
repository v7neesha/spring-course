package com.luv2code.curdDemo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GeneratedColumn;

@Entity
@Table(name = "review")
public class Review {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "comment")
    private String comment;

//    @JoinColumn(name = "course_id")
//    @ManyToOne(cascade = CascadeType.ALL)
//    private Course course;

    public Review(){}

    public Review(String comment){
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /*public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }*/
}
