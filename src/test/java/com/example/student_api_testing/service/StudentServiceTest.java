package com.example.student_api_testing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.student_api_testing.client.ExternalPostClient;
import com.example.student_api_testing.client.model.ExternalPost;
import com.example.student_api_testing.client.model.Student;
import com.example.student_api_testing.client.service.StudentService;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private ExternalPostClient externalPostClient;

    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentService(externalPostClient);
    }

    @Test
    void shouldCreateStudent() {
        Student student = studentService.addStudent(
                "Rahul", "rahul@example.com");

        assertEquals(1L, student.id());
        assertEquals("Rahul", student.name());
        assertEquals("rahul@example.com", student.email());
    }

    @Test
    void shouldReturnAllStudents() {
        studentService.addStudent(
                "Rahul", "rahul@example.com");

        studentService.addStudent(
                "Priya", "priya@example.com");

        List<Student> students =
                studentService.getAllStudents();

        assertEquals(2, students.size());
        assertEquals("Rahul", students.get(0).name());
        assertEquals("Priya", students.get(1).name());
    }

    @Test
    void shouldThrowWhenStudentDoesNotExist() {
        assertThrows(
                NoSuchElementException.class,
                () -> studentService.getStudentById(99L)
        );
    }

    @Test
    void shouldReturnExternalPosts() {
        List<ExternalPost> mockPosts = List.of(
                new ExternalPost(
                        1L, 1L, "Test title", "Test body")
        );

        when(externalPostClient.fetchPosts())
                .thenReturn(mockPosts);

        List<ExternalPost> result =
                studentService.getExternalPosts();

        assertEquals(1, result.size());
        assertEquals("Test title", result.get(0).title());

        verify(externalPostClient, times(1)).fetchPosts();
    }
}