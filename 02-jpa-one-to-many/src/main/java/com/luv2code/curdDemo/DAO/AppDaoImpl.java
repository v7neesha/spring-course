package com.luv2code.curdDemo.DAO;

import com.luv2code.curdDemo.entity.Course;
import com.luv2code.curdDemo.entity.Instructor;
import com.luv2code.curdDemo.entity.InstructorDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppDaoImpl implements AppDAO{

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    //This will also retrieve the instructor details object
    //Because default behaviour of @OneToOne fetch type is eager
    @Override
    public Instructor findInstructorById(int theId) {
       return entityManager.find(Instructor.class,theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor instructor = entityManager.find(Instructor.class,theId);
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetails findInstructorDetailsById(int theId){
        InstructorDetails instructorDetails = entityManager.find(InstructorDetails.class,theId);
        return instructorDetails;
    }

    @Override
    @Transactional
    public void deleteInstructorDetailsById(int theId) {
        InstructorDetails instructorDetails = entityManager.find(InstructorDetails.class,theId);
        //remove the associated instructor object reference
        // break bidirectional link
        instructorDetails.getInstructor().setInstructorDetails(null);

        entityManager.remove(instructorDetails);
    }

    @Override
    @Transactional
    public void saveInstructor(Instructor instructor) {
        entityManager.persist(instructor);

    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
//        Instructor instructor = entityManager.find(Instructor.class,theId);
//        TypedQuery<Course> query = entityManager.createQuery("FROM Course c WHERE c.instructor_id =:data",Course.class);
        TypedQuery<Course> query = entityManager.createQuery("from Course c where instructor.id =:data",Course.class);
        query.setParameter("data",theId);
        List<Course> courseList = query.getResultList();
        return courseList;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery("select i from Instructor i "
                                        + "join FETCH i.courseList "
                                        + "join FETCH i.instructorDetails "
                                        +  "where i.id =: data",Instructor.class);
        query.setParameter("data",theId);
        Instructor instructor = query.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void updateInstructorById(Instructor instructor) {
        entityManager.merge(instructor);
    }

    @Override
    public Course findCourseById(int theId){
         return entityManager.find(Course.class,theId);
    }

    @Override
    @Transactional
    public void updateCourseById(Course course) {
        entityManager.merge(course);
    }

    @Override
    @Transactional
    public void deleteInstructorByDetachCourse(int theId) {
        Instructor instructor = entityManager.find(Instructor.class,theId);
        List<Course> courseList = instructor.getCourseList();
        // Break the association of all the courses for instructor
        for(Course course : courseList){
            course.setInstructor(null);
        }
        //sqlIntegrityConstraintViolationException cannot delete or update a parent row foreign key constraint fails
        //An instructor cannot be deleted if it referenced by course table
        //you must remove the instructor from courseList
        entityManager.remove(instructor);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        Course course = entityManager.find(Course.class,theId);
        entityManager.remove(course);
    }
}
