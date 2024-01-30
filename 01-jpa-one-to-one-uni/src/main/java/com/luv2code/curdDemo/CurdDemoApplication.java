package com.luv2code.curdDemo;

import com.luv2code.curdDemo.DAO.AppDAO;
import com.luv2code.curdDemo.entity.Instructor;
import com.luv2code.curdDemo.entity.InstructorDetails;
import com.luv2code.curdDemo.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			deleteInstructorDetails(appDAO);
		};
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
