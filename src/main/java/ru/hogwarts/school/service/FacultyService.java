package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final Map<Long, Faculty> faculties = new HashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(counter.getAndIncrement());
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Optional<Faculty> getFaculty(Long id) {
        return Optional.ofNullable(faculties.get(id));
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public void deleteFaculty(Long id) {
        faculties.remove(id);
    }

    public Collection<Faculty> getAllFaculties() {
        return faculties.values();
    }

    // Фильтрация по цвету
    public List<Faculty> getFacultiesByColor(String color) {
        return faculties.values().stream()
                .filter(f -> f.getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
    }
}