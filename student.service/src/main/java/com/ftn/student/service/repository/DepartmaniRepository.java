package com.ftn.student.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.student.service.models.Departman;

@Repository
public interface DepartmaniRepository extends JpaRepository<Departman, String> {

}
