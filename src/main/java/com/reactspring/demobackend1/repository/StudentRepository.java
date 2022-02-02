package com.reactspring.demobackend1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reactspring.demobackend1.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
