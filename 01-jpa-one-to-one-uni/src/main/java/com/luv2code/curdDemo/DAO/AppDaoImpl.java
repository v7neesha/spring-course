package com.luv2code.curdDemo.DAO;

import com.luv2code.curdDemo.entity.Instructor;
import com.luv2code.curdDemo.entity.InstructorDetails;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
