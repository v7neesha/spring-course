package com.luv2code.curdDemo;

import com.luv2code.curdDemo.DAO.AppDAO;
import com.luv2code.curdDemo.entity.Course;
import com.luv2code.curdDemo.entity.Instructor;
import com.luv2code.curdDemo.entity.InstructorDetails;
import com.luv2code.curdDemo.service.InstructorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CurdDemoApplication {



	public static void main(String[] args) {
		SpringApplication.run(CurdDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner( @Autowired AppDAO appDAO){
		return runner ->{
//			findInstructor(appDAO);
//			createInstructor(appDAO);
//			deleteInstructor(appDAO);
//			findInstructorDetails(appDAO);
//			deleteInstructorDetails(appDAO);

//			createInstructorWithCourse(appDAO);
//			findInstructorWithCourse(appDAO); // by change @oneToMany Fetch Type to Eager
			findCourseByInstructorId(appDAO); // changed Fetch Type to Lazy and separate query to bring list courses
//			findInstructorByIdJoinFetch(appDAO);
//			updateInstructorById(appDAO);
//			findCoursesById(appDAO);
//			deleteInstructorWithoutCourse(appDAO);
//			deleteCoursesById(appDAO);

		};
	}

	private void deleteCoursesById(AppDAO appDAO) {
		int theId = 11;

		appDAO.deleteCourseById(theId);
		System.out.println("Done");
	}

	private void deleteInstructorWithoutCourse(AppDAO appDAO) {
		int theId=3;
		/*Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);
		appDAO.deleteInstructorByDetachCourse(instructor);*/
//		Instructor instructor = appDAO.findInstructorById(theId);
		appDAO.deleteInstructorByDetachCourse(theId);

		System.out.println("Delete Instructor By Id " + theId);
	}

	private void findCoursesById(AppDAO appDAO){
		int theId = 13;
		System.out.println("Finding courses By Id" + theId);
		Course course = appDAO.findCourseById(theId);
		course.setTitle("employee");
		appDAO.updateCourseById(course);
		System.out.println(course);

	}

	private void updateInstructorById(AppDAO appDAO) {
		int theId = 4;
		Instructor instructor = appDAO.findInstructorById(theId);
		instructor.setFirstname("ben");
		instructor.setLastName("whitaker");
		instructor.setEmail("benWhitaker@gmail.com");
		appDAO.updateInstructorById(instructor);

	}

	private void findInstructorByIdJoinFetch(AppDAO appDAO) {
		int theId = 3;
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);
		System.out.println("the tempInstructor: " + instructor);
		System.out.println("The associated Instructor Details" + instructor.getInstructorDetails());
		System.out.println("The associated Courses" + instructor.getCourseList());
	}

	private void findCourseByInstructorId(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding courses by instructor Id"+ theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("the temp instructor " + tempInstructor);
		List<Course> courseList = appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourseList(courseList);
		System.out.println("the associated courses list" + tempInstructor.getCourseList());
		System.out.println("the associated course list for the instructor id "+ theId + " "+ courseList);

	}

	private void findInstructorWithCourse(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Finding instructor Id" + theId);
		//only load the instructor doe not load the courses since they are lazily loaded
		//By making @OneToMany fetch = FetchType.EAGER then it will load course details
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor :" + tempInstructor);
//		System.out.println("the associated courses :" + tempInstructor.getCourseList());
		System.out.println("Associated Instructor Details" +tempInstructor.getInstructorDetails());

	}

	private void createInstructorWithCourse(AppDAO appDAO) {

		List<Course> courseList = new ArrayList<>();
		Course course = new Course("SAP");
		Course course1 = new Course("Accounts");
//		courseList.add(course);
//		courseList.add(course1);

		Instructor tempInstructor = new Instructor("Cooper", "D", "cooper@gmail.com");
		tempInstructor.add(course);
		tempInstructor.add(course1);
		InstructorDetails instructorDetails = new InstructorDetails("cooper-space","Research");
		tempInstructor.setInstructorDetails(instructorDetails);

		System.out.println("saving instructor : "+tempInstructor);
		System.out.println("saving courses" + tempInstructor.getCourseList());

		// Note that this will auto save the course
		// because of cascade persist

		appDAO.saveInstructor(tempInstructor);
		System.out.println("Done!!");
	}

	private void deleteInstructorDetails(AppDAO appDAO) {
		//delete instructor by instructorId
		int theId = 6;
		System.out.println("Deleting Instructor Details " + theId);
		appDAO.deleteInstructorDetailsById(6);

	}

	private void findInstructorDetails(AppDAO appDAO) {
		System.out.println("Finding the Instructor");
		InstructorDetails instructorDetails = appDAO.findInstructorDetailsById(3);
		System.out.println(instructorDetails);
		System.out.println("Associated Instructor" + instructorDetails.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO){
		int theId = 2;
		System.out.println("Deleting the instructor" + theId);
		appDAO.deleteInstructorById(theId);
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id" + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println( "tempInstructor:"+ tempInstructor);
		System.out.println("The associate instructor details " + tempInstructor.getInstructorDetails());
	}

	private void createInstructor(AppDAO appDAO) {

		InstructorDetails instructorDetails = new InstructorDetails("http://localhost:8080/dishes", "cooking");

		Instructor instructor = new Instructor("harsh", "g", "harsha@gmail.com");

		//associate the objects
		instructor.setInstructorDetails(instructorDetails);
		//this will also save the details object
		//because cascade type all
		appDAO.save(instructor);
	}

}
