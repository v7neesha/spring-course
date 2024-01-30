package com.luv2code.curdDemo.DAO;

import com.luv2code.curdDemo.entity.Course;
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

    @Transactional

    @Override
    public void deleteCourseById(int theId) {
        Course course = finCourseById(theId);
        entityManager.remove(course);
    }
}
