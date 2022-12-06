package com.example.micro.service.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.micro.service.model.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long>{

}

