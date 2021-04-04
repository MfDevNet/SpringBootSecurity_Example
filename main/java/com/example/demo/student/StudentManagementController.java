package com.example.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    private static final List<Student> STUDENTS = new ArrayList<>();

    static {
        new Student(1, "James Bond");
        new Student(2, "Maria Jones");
        new Student(3, "Anna Smith");
    }


    public static List<Student> getStudentList() {
        return STUDENT_LIST;
    }





    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents() {
        System.out.println("1");
        return getStudentList();
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void registerNewStudents(@RequestBody Student student) {
//        System.out.println(String.format("%s %s", student, "is registry"));
        STUDENTS.add(student);
        System.out.println(STUDENTS);


    }

    @DeleteMapping(value = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
        System.out.println("delete student with id : " + studentId);
    }

    @PutMapping(value = "{studentId}")
    @PreAuthorize("hasAnyAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
        System.out.println(String.format("%s %s", studentId, student));

    }


}
