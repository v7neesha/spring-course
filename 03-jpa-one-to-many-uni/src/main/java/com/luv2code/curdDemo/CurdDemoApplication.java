package com.luv2code.curdDemo;

import com.luv2code.curdDemo.DAO.AppDAO;
import com.luv2code.curdDemo.entity.Course;
import com.luv2code.curdDemo.entity.Instructor;
import com.luv2code.curdDemo.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class CurdDemoApplication {



	public static void main(String[] args) {
		SpringApplication.run(CurdDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner( @Autowired AppDAO appDAO){
		return runner ->{
//			createCourseAndReview(appDAO);
//			retrieveCourseAndReview(appDAO);
			deleteCourseAndReviews(appDAO);

		};
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 13;
		appDAO.deleteCourseById(theId);

		System.out.println("course deleted successfully" + theId);
	}

	private void retrieveCourseAndReview(AppDAO appDAO) {
		int theId = 12;
		//retrieve course by id
		 Course course = appDAO.finCourseById(theId);

		 System.out.println(course.getTitle());
		 System.out.println(course.getReviewList());

	}

	private void createCourseAndReview(AppDAO appDAO) {
//		Instructor instructor = new Instructor("derby", "h","derby@gmail.com");

		//created a tempCourse
		Course tempCourse = new Course();
		tempCourse.setTitle("sales force");

		//added some reviews
		tempCourse.add(new Review("booming technology"));
		tempCourse.add(new Review("Great course., loved it!! I hope i going start soon"));

		//saving the tempCourse
		appDAO.save(tempCourse);

		System.out.println("saving the tempCourse");
		System.out.println(tempCourse);
		System.out.println(tempCourse.getReviewList());
	}

}
