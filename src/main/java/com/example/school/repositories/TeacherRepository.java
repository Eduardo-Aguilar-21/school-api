package com.example.school.repositories;

import com.example.school.models.TeacherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherModel, Long> {
}
