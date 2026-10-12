package com.demo.controller;

import com.demo.DTO.StudentDTO;
import com.demo.Validate.StudentVO;
import com.demo.exception.CustomeException;
import com.demo.model.ErrorResponce;
import com.demo.model.Student;
import com.demo.service.StudentService;
//import com.demo.util.EmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("demo")
public class StudentController {

    @Autowired
    public StudentService studentService;

//    @Autowired(required=true)
//    EmailUtil emailUtil;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @PostMapping()
    public ErrorResponce addStudent(@Valid @RequestBody StudentVO studentVO) throws CustomeException {
        LOGGER.info("addStudent() invoked with student body..");
        studentService.addStudent(studentVO);
        LOGGER.info("student data is "+studentVO);
        ErrorResponce errorResponce=new ErrorResponce();
        errorResponce.setMessage("Success...");
        errorResponce.setHttpCode(HttpStatus.OK.value());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LOGGER.info("Timestamp is "+ timestamp.toString());
//        emailUtil.sendEmail("vinaymekala@gmail.com","Student Added","Student added successfully in the database");
        return errorResponce;
    }

    @GetMapping
    public List<StudentDTO> getStudents() throws CustomeException {
        LOGGER.info("getStudents() invoked ...");
        List<StudentDTO> students = studentService.getStudents();
        LOGGER.info("Students list are "+ students);
        return students;
    }

    @GetMapping("/{studentId}")
    public Optional<Student> getStudentById(@PathVariable Long studentId) throws CustomeException{
        LOGGER.info("getStudentById() invoked with studentId..");
        Optional<Student> student = studentService.getStudentById(studentId);
        LOGGER.info("student data is "+ student);
        return student;
    }

    @DeleteMapping("/{studentId}")
    public void removeStudent(@PathVariable Long studentId) throws CustomeException{
        LOGGER.info("removeStudent() invoked with studentId");
        studentService.removeStudent(studentId);
    }

    @DeleteMapping
    public void removeStudents() throws CustomeException {
        LOGGER.info("removeStudents() invoked, all the students are deleted");
        studentService.removeStudents();
    }

    @PutMapping("/{studentId}")
    public ErrorResponce updateStudent(@RequestBody StudentVO studentVO, @PathVariable Long studentId) throws CustomeException {
        LOGGER.info("updateStudent() invoked with StudentVO and studentId");
        studentService.updateStudent(studentVO,studentId);
        ErrorResponce errorResponce=new ErrorResponce();
        errorResponce.setHttpCode(HttpStatus.OK.value());
        errorResponce.setMessage("Success...");
        return errorResponce;
    }

}
