package com.luv2code.curdDemo.DAO;

import com.luv2code.curdDemo.entity.Course;
import com.luv2code.curdDemo.entity.Instructor;
import com.luv2code.curdDemo.entity.InstructorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetails findInstructorDetailsById(int theId);

    void deleteInstructorDetailsById(int theId);


    //Course table methods
    //create instructor with multiple courses;
    public void saveInstructor(Instructor instructor);

    public List<Course> findCoursesByInstructorId(int theId);

    public Instructor findInstructorByIdJoinFetch(int theId);

    public void updateInstructorById(Instructor instructor);

    public Course findCourseById(int id);

    public void updateCourseById(Course course);

    public void deleteInstructorByDetachCourse(int theId);

    public void deleteCourseById(int theId);


}
