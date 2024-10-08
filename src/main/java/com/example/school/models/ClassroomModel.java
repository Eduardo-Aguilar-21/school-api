package com.example.school.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "classrooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassroomModel {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The classroom name cannot be blank")
    @Size(max = 100, message = "The classroom name cannot exceed 100 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name = "grade", referencedColumnName = "id", nullable = false)
    private GradeModel gradeModel;

    @ManyToOne
    @JoinColumn(name = "section", referencedColumnName = "id", nullable = false)
    private SectionModel sectionModel;

    @ManyToMany
    @JoinTable(name = "classroom_courses", joinColumns = @JoinColumn(name = "classroom_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<CourseModel> courses;

    @OneToMany(mappedBy = "classroom")
    private List<StudentModel> students;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}