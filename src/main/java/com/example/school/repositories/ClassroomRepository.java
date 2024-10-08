package com.example.school.repositories;

import com.example.school.models.ClassroomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomModel, Long> {
}
