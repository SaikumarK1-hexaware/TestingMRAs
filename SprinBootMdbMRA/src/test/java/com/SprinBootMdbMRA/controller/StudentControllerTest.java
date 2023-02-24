package com.SprinBootMdbMRA.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.SprinBootMdbMRA.controller.StudentController;
import com.SprinBootMdbMRA.entity.Student;
import com.SprinBootMdbMRA.service.StudentService;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
	@Mock
	private StudentService service;

	@InjectMocks
	private StudentController controller;
	
	private List<Student> prepareStudentRecords(){
		List<Student> studentList = new ArrayList<Student>();
		Student student1 = new Student("62d6b821618d9f7c7024ccc5", "mDhBl","SEDYy");
		Student student2 = new Student("62d6b821618d9f7c7024ccc4", "cLQ5k","9YLVm");
		studentList.add(student1);
		studentList.add(student2);
		return studentList;
	}
	
	@Test
	public void testFetchAllPass() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareStudentRecords());
		List<Student> studentList = prepareStudentRecords();
		List<Student> studentListFromController =  controller.fetchAll();
		for(int i=0; i<studentList.size();i++) {
			Assertions.assertEquals(studentList.get(i).getId(), studentListFromController.get(i).getId());
            Assertions.assertEquals(studentList.get(i).getName(), studentListFromController.get(i).getName());
            Assertions.assertEquals(studentList.get(i).getRollNo(), studentListFromController.get(i).getRollNo());
		}
		
	}

	@Test
	public void testFetchAllFailure() {
		Mockito
        .when(controller.fetchAll()).thenReturn(prepareStudentRecords());
		List<Student> studentList = null; //Intentionally made null to fail the test.
		List<Student> studentListFromController =  controller.fetchAll();
		Assertions.assertNotEquals(studentList, studentListFromController);
	}
	
	
	 @Test public void fetchByIdPass() { 
		 Mockito
	        .when(controller.fetchById("62d6b821618d9f7c7024ccc5"))
            .thenReturn(prepareStudentRecords().get(0));

        Student studentById = prepareStudentRecords().get(0);
        Student studentByIdFromController = controller.fetchById("62d6b821618d9f7c7024ccc5");
        
        Assertions.assertEquals(studentById.getId(), studentByIdFromController.getId());
        Assertions.assertEquals(studentById.getName(), studentByIdFromController.getName());
        Assertions.assertEquals(studentById.getRollNo(), studentByIdFromController.getRollNo());
		 
	 }

	 @Test public void fetchByIdFailure() { 
		Mockito
	        .when(controller.fetchById("62d6b821618d9f7c7024ccc5"))
            .thenReturn(prepareStudentRecords().get(0));

        Student studentById = prepareStudentRecords().get(1);
        Student studentByIdFromController = controller.fetchById("62d6b821618d9f7c7024ccc5");
        
        Assertions.assertNotEquals(studentById.getId(), studentByIdFromController.getId());
        Assertions.assertNotEquals(studentById.getName(), studentByIdFromController.getName());
        Assertions.assertNotEquals(studentById.getRollNo(), studentByIdFromController.getRollNo());
		 
	 }
	 
	 @Test
	 public void deletePass() { 
		 controller.delete("62d6b821618d9f7c7024ccc5");
		 Assertions.assertTrue(true); // This line will be executed only if there is no error in calling the controller for delete as there is no return value.
	 }

	@Test
	public void createPass() {
		Student studentToBeCreated 			= prepareStudentRecords().get(0);
		Student studentReturned = prepareStudentRecords().get(0);
		studentReturned.setId("62d6b821618d9f7c7024ccc9"); //Changed the ID.
		
		Mockito
			.when(controller.create(studentToBeCreated))
            .thenReturn(studentReturned);
		
		Student studentFromController  = controller.create(studentToBeCreated);
		Assertions.assertNotEquals(studentToBeCreated.getId(), studentFromController.getId()); //Since Id of created one is mocked as changed from within serviceid, it cannot be equal.
        Assertions.assertEquals(studentToBeCreated.getName(), studentFromController.getName());
        Assertions.assertEquals(studentToBeCreated.getRollNo(), studentFromController.getRollNo());
	}
	
	@Test
	public void createFailure() {
		Student studentToBeCreated = prepareStudentRecords().get(0);
		Student studentReturned = null; // Intentionally left to null to fail the case. 
				
		Mockito
			.when(controller.create(studentToBeCreated))
            .thenReturn(studentReturned);
		
		Student studentFromController  = controller.create(studentToBeCreated);
		Assertions.assertNull(studentFromController);
	}
}
