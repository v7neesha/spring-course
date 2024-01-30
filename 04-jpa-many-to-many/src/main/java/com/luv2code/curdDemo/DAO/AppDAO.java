package com.luv2code.curdDemo.DAO;

import com.luv2code.curdDemo.entity.Course;
import com.luv2code.curdDemo.entity.Instructor;
import com.luv2code.curdDemo.entity.InstructorDetails;
import com.luv2code.curdDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppDAO {

    void save(Course course);

    Course finCourseById(int theId);

    void deleteCourseById(int theId);

    Course findCourseAndStudent(int theId);
    Student findStudentAndCourse(int studentId);
    void updateStudent(Student student);
    void deleteCourse(int courseId);
    void deleteStudent(int studentId);

}
