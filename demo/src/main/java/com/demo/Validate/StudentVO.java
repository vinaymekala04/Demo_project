package com.demo.Validate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;



public class StudentVO {

    private Long studentId;

//    @Size(min = 2, max = 20)
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Positive
    private int marks;

    public StudentVO(String name, int marks) {
        this.studentId = studentId;
        this.name = name;
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "StudentVO{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
