package com.demo;

import com.demo.DTO.StudentDTO;
import com.demo.Validate.StudentVO;
import com.demo.model.Student;
import com.demo.repository.StudentRepository;
import com.demo.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DemoApplicationTests {

	@Mock
	StudentRepository studentRepository;

	@InjectMocks
	StudentService studentService;


//	StudentService studentService=new StudentService();
	@Test
	void contextLoads() {
	}

	@BeforeEach
	void setUp() {
	}

	@Test
	void testForAddStudent(){
		//arrange
		StudentVO arrange=new StudentVO("vinay",100);

		//expected data
		Student student=new Student();
		student.setName("vinay");
		student.setMarks(100);

		when(studentRepository.save(any(Student.class))).thenReturn(student);
		StudentVO studentVO=studentService.addStudent(arrange);
		Assertions.assertEquals("vinay",studentVO.getName());
		Assertions.assertEquals(100,studentVO.getMarks());
		verify(studentRepository,times(1)).save(any(Student.class));
	}

	@Test
	void testForUpdateStudent(){
		Long requestedId = 1L;
		StudentVO newdata = new StudentVO("ramu",99);

		Student existingdata = new Student();
		existingdata.setName("vinay");
		existingdata.setMarks(100);

		when(studentRepository.findById(requestedId)).thenReturn(Optional.of(existingdata));
		when(studentRepository.save(any(Student.class))).thenReturn(existingdata);

		studentService.updateStudent(newdata,requestedId);

		assertEquals("ramu",existingdata.getName());
		assertEquals(99,existingdata.getMarks());
		verify(studentRepository,times(1)).save(existingdata);
	}

	@Test
	void testForGetStudents(){

		List<Student> students = new ArrayList<>();
		Student student1 = new Student();
		student1.setName("vinay");
		student1.setMarks(100);

		Student student2 = new Student();
		student2.setName("ramu");
		student2.setMarks(99);

		students.add(student1);
		students.add(student2);

		when(studentRepository.findAll()).thenReturn(students);

		List<StudentDTO> result = studentService.getStudents();

		assertEquals(2,result.size());
//		assertEquals("vinay",result.get(0).getName());
//		assertEquals(100,result.get(0).getMarks());
		verify(studentRepository,times(1)).findAll();
	}

	@Test
	void testForGetStudentByIdWhenExist(){
		Long id=1L;
		Student expectedstudent = new Student();
		expectedstudent.setName("vinay");
		expectedstudent.setMarks(100);

		when(studentRepository.findById(id)).thenReturn(Optional.of(expectedstudent));
		Optional<Student> student = studentService.getStudentById(id);
		assertTrue(student.isPresent());
		assertEquals("vinay",student.get().getName());
		verify(studentRepository,times(1)).findById(id);
	}

	@Test
	void testForGetStudentByIdWhenNotExist(){
		Long id = 1L;

		when(studentRepository.findById(id)).thenReturn(Optional.empty());
		Optional<Student> result = studentService.getStudentById(id);
		assertFalse(result.isPresent());
	}

	@Test
	void testForRemoveStudents(){
		studentService.removeStudents();
		verify(studentRepository,times(1)).deleteAll();
	}

	@Test
	void testForRemoveStudentById(){
		Long id = 1L;
		studentService.removeStudent(id);
		verify(studentRepository,times(1)).deleteById(id);
	}
}


