package com.luv2code.curdDemo.DAO;

import com.luv2code.curdDemo.entity.Instructor;
import com.luv2code.curdDemo.entity.InstructorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDAO {
    void save(Instructor instructor);

    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
    InstructorDetails findInstructorDetailsById(int theId);

    void deleteInstructorDetailsById(int theId);
}
