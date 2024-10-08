package com.example.school.repositories;

import com.example.school.models.SectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<SectionModel, Long> {

}
