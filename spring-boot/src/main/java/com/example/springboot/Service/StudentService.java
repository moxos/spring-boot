package com.example.springboot.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.springboot.Model.Course;
import com.example.springboot.Model.Student;
import org.springframework.stereotype.Component;



@Component
public class StudentService {

    private static List<Student> students = new ArrayList<>();

    static {

        Course course1 = new Course("Course1", "Spring", "test", Arrays
                .asList("1", "2", "3",
                        "4"));
        Course course2 = new Course("Course2", "Spring1", "test2",
                Arrays.asList("1a", "2a", "3a",
                        "4a"));
        Course course3 = new Course("Course3", "Spring2", "test3",
                Arrays.asList("1b", "2b",
                        "3b", "4b", "5b"));
        Course course4 = new Course("Course4", "Spring3",
                "test4!", Arrays.asList(
                "1c", "2c", "3c",
                "4c","5c","6c"));

        Student Bartek = new Student("Student1", "Bartek",
                "TEST", new ArrayList<>(Arrays
                .asList(course1, course2, course3, course4)));

        Student Artek = new Student("Student2", "Artek",
                "TEST1", new ArrayList<>(Arrays
                .asList(course1, course2, course3, course4)));

        students.add(Bartek);
        students.add(Artek);
    }

    public List<Student> retrieveAllStudents() {
        return students;
    }

    public Student retrieveStudent(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public List<Course> retrieveCourses(String studentId) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        return student.getCourses();
    }

    public Course retrieveCourse(String studentId, String courseId) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        for (Course course : student.getCourses()) {
            if (course.getId().equals(courseId)) {
                return course;
            }
        }

        return null;
    }

    private SecureRandom random = new SecureRandom();

    public Course addCourse(String studentId, Course course) {
        Student student = retrieveStudent(studentId);

        if (student == null) {
            return null;
        }

        String randomId = new BigInteger(130, random).toString(32);
        course.setId(randomId);

        student.getCourses().add(course);

        return course;
    }
}
