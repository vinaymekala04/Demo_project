package com.demo.service;

import com.demo.DTO.StudentDTO;
import com.demo.Validate.StudentVO;
import com.demo.model.Student;

import com.demo.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    public StudentRepository studentRepository;

    public StudentVO addStudent(StudentVO studentVO) {
        Student student=new Student();
        student.setName(studentVO.getName());
        student.setMarks(studentVO.getMarks());
        studentRepository.save(student);
        return studentVO;
    }


    public void updateStudent(StudentVO studentVO, Long studentId) {
        Student student=new Student();
        student.setName(studentVO.getName());
        student.setMarks(studentVO.getMarks());

        Optional<Student> std=studentRepository.findById(studentId);
        if(std.isPresent())
        {
            Student existindStudent= std.get();
            existindStudent.setName(student.getName());
            existindStudent.setMarks(student.getMarks());
            studentRepository.save(existindStudent);
        }

    }


    public List<StudentDTO> getStudents() {
        List<Student> student=studentRepository.findAll();
        List<StudentDTO> studentDTOS=new ArrayList<>();
        for(Student std: student)
        {
            StudentDTO data=new StudentDTO();
            data.setStudentId(std.getStudentId());
            data.setName(std.getName());
            data.setMarks(std.getMarks());
            studentDTOS.add(data);
        }
        return studentDTOS;
    }

    public Optional<Student> getStudentById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public void removeStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public void removeStudents() {
        studentRepository.deleteAll();
    }

}
