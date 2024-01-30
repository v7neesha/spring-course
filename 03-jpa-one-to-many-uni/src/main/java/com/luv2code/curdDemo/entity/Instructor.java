package com.luv2code.curdDemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    /** set up mapping to instructor details entity **/

    //cascadeType.all this will apply to any operations updating,deleting,persisting
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetails instructorDetails;

    @OneToMany(mappedBy = "instructor",fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,
                    CascadeType.REFRESH,CascadeType.DETACH})
    private List<Course> courseList;

    //adding a convenient methods for a bi-Directional relationship

    public void add(Course tempCourse){

        if(courseList == null)
            courseList = new ArrayList<>();
        courseList.add(tempCourse);
        tempCourse.setInstructor(this);

    }



    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public Instructor(String firstname, String lastName, String email){
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
    }

    public Instructor(){}

    @Override
    public String toString(){
        return "FirstName = " + firstname +
                "LastName = " + lastName +
                "Email = " + email ;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetails getInstructorDetails() {
        return instructorDetails;
    }

    public void setInstructorDetails(InstructorDetails instructorDetails) {
        this.instructorDetails = instructorDetails;
    }


}

// Instructor_detail_id defined in instructor table.
// In database foreign key is reference id instructor_details table
