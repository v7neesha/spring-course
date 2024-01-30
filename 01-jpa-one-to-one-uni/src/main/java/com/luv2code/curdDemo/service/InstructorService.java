package com.luv2code.curdDemo.service;

import com.luv2code.curdDemo.entity.Instructor;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


public interface InstructorService {

    public void saveInstructor(Instructor instructor);
}
