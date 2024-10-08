package com.example.school.services;

import com.example.school.models.ClassroomModel;
import com.example.school.models.CourseModel;
import com.example.school.repositories.ClassroomRepository;
import com.example.school.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository, CourseRepository courseRepository) {
        this.classroomRepository = classroomRepository;
        this.courseRepository = courseRepository;
    }

    public ClassroomModel findById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Record with id " + id + " not found"));
    }

    public List<ClassroomModel> findAll() {
        return classroomRepository.findAll();
    }

    public Page<ClassroomModel> findAll(Pageable pageable){
        return classroomRepository.findAll(pageable);
    }

    public ClassroomModel save(ClassroomModel classroomModel){
        return classroomRepository.save(classroomModel);
    }

    public ClassroomModel update(Long id, ClassroomModel classroomModel) {
        ClassroomModel existing = classroomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Record with id " + id + " not found"));

        existing.setName(classroomModel.getName());
        existing.setGradeModel(classroomModel.getGradeModel());
        existing.setSectionModel(classroomModel.getSectionModel());

        return classroomRepository.save(existing);
    }
    public void deleteById(Long id){
        classroomRepository.deleteById(id);
    }

    public void addCourse(Long classroomId, Long courseId) {
        ClassroomModel classroom = findById(classroomId);
        CourseModel course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        classroom.getCourses().add(course);
        classroomRepository.save(classroom);
    }

    public void removeCourse(Long classroomId, Long courseId){
        ClassroomModel classroom = findById(classroomId);
        CourseModel course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course with id " + courseId + " not found"));

        if (classroom.getCourses().remove(course)) {
            classroomRepository.save(classroom);
        } else {
            throw new EntityNotFoundException("Course with id " + courseId + " not found in classroom with id " + classroomId);
        }

    }


}
