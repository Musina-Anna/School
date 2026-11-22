package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final Map<Long, Student> students = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Student createStudent(Student student) {
        student.setId(counter.getAndIncrement());
        students.put(student.getId(), student);
        return student;
    }

    // Используем Optional, чтобы избежать возврата null (по критериям)
    public Optional<Student> getStudent(Long id) {
        return Optional.ofNullable(students.get(id));
    }

    public Student updateStudent(Student student) {
        if (students.containsKey(student.getId())) {
            students.put(student.getId(), student);
            return student;
        }
        return null; // По-хорошему тут нужна обработка ошибки, но пока оставим так
    }

    public void deleteStudent(Long id) {
        students.remove(id);
    }

    public Collection<Student> getAllStudents() {
        return students.values();
    }

    // Фильтрация по возрасту
    public List<Student> getStudentsByAge(int age) {
        return students.values().stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }
}