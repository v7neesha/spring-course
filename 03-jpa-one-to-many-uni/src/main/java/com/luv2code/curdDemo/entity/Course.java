package com.luv2code.curdDemo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GeneratedColumn;

import java.util.ArrayList;
import java.util.List;

@Table(name = "course")
@Entity
public class Course {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    @Id
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    //using join column we are going to tell hibernate add associate review with the given course
    //now join column is = course_id that is the column in review table that will point back to the actual course
    @JoinColumn(name = "course_id")
    @OneToMany( fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE,
                CascadeType.DETACH, CascadeType.REFRESH})
    List<Review> reviewList = new ArrayList<>();

    //adding a convenient method
    public void add(Review theReview){
        if (reviewList == null){
            reviewList = new ArrayList<>();
        }
        reviewList.add(theReview);
    }

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

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }
}
