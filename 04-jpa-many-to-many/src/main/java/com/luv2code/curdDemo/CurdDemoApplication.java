package com.luv2code.curdDemo;

import com.luv2code.curdDemo.DAO.AppDAO;
import com.luv2code.curdDemo.entity.Course;
import com.luv2code.curdDemo.entity.Instructor;
import com.luv2code.curdDemo.entity.Review;
import com.luv2code.curdDemo.entity.Student;
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
//			createCourseAndStudents(appDAO);
			//getting course details using courseId and associated
//			findCourseAndStudents(appDAO);
			//getting student details using studentId and associated course
//			findStudentAndCourses(appDAO);
			//adding more courses to student
//			addCoursesToStudent(appDAO);
//			deleteCourse(appDAO);
			deleteStudent(appDAO);


//			createCourseAndReview(appDAO);
//			retrieveCourseAndReview(appDAO);
//			deleteCourseAndReviews(appDAO);

		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int studentId = 3;
		appDAO.deleteStudent(studentId);
	}

	private void deleteCourse(AppDAO appDAO) {
		int courseId = 14;
		appDAO.deleteCourse(courseId);
	}

	private void addCoursesToStudent(AppDAO appDAO) {
		int studentId= 3;
		Student student = appDAO.findStudentAndCourse(studentId);
		student.addCourse(new Course("sales force"));
		student.addCourse(new Course("azure"));
		appDAO.updateStudent(student);
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int studentId = 3;
		Student student = appDAO.findStudentAndCourse(studentId);
		System.out.println("Retrieving student" + student);
		System.out.println("Retrieving Associated courses" +student.getCourseList());
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int courseId = 11;

		Course course = appDAO.findCourseAndStudent(courseId);
		System.out.println("Retrieving course " + course);
		System.out.println("Retrieving Associated Students" + course.getStudentList());
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		//create the course
		Course course = new Course("BigData");
		//create the students and add students to the course
		course.addStudent(new Student("john","p","john123@gmail.com"));
		course.addStudent(new Student("debby","g","debby@gmail.com"));

		//save the course and associated students
		System.out.println("saving the course" + course);
		System.out.println("associated students" +course.getStudentList());

		appDAO.save(course);
		System.out.println("done!!");
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
