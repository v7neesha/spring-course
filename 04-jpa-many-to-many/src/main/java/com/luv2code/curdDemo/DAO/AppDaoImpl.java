package com.luv2code.curdDemo.DAO;

import com.luv2code.curdDemo.entity.Course;
import com.luv2code.curdDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AppDaoImpl implements AppDAO{

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public Course finCourseById(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "JOIN FETCH c.reviewList " +
                        "where c.id = :data", Course.class);
        query.setParameter("data",theId);
        return query.getSingleResult();
    }

    @Override
    public Course findCourseAndStudent(int theId) {
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "JOIN FETCH c.studentList " +
                        "where c.id= :data",Course.class);
        query.setParameter("data",theId);
        return query.getSingleResult();
    }

    @Override
    public Student findStudentAndCourse(int studentId) {
        TypedQuery<Student> query = entityManager.createQuery("select s from Student s " +
                                                                "JOIN FETCH s.courseList " +
                                                                "where s.id = :data",Student.class);
        query.setParameter("data",studentId);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        entityManager.merge(student);
    }
    @Transactional
    @Override
    public void deleteCourse(int courseId) {
        Course course = findCourseAndStudent(courseId);
        course.setStudentList(null);
        entityManager.remove(course);
    }

    @Transactional
    @Override
    public void

    @Transactional
    @Override
    public void deleteCourseById(int theId) {
        Course course = finCourseById(theId);
        entityManager.remove(course);
    }

}
