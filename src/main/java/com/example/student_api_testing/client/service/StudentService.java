package com.example.student_api_testing.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.student_api_testing.client.ExternalPostClient;
import com.example.student_api_testing.client.model.ExternalPost;
import com.example.student_api_testing.client.model.Student;

@Service
public class StudentService {

    private final ExternalPostClient externalPostClient;

    private final List<Student> students = new ArrayList<>();
    private long nextId = 1;

    public StudentService(ExternalPostClient externalPostClient) {
        this.externalPostClient = externalPostClient;
    }

    public Student addStudent(String name, String email) {
        Student student = new Student(nextId++, name, email);
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return List.copyOf(students);
    }

    public Student getStudentById(Long id) {
        return students.stream()
                .filter(student -> student.id().equals(id))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Student not found: " + id));
    }

    public List<ExternalPost> getExternalPosts() {
        return externalPostClient.fetchPosts();
    }
}