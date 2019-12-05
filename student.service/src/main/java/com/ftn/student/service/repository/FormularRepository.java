package com.ftn.student.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Student;

@Repository
public interface FormularRepository extends JpaRepository<Formular, String> {
	
	@Query("select f from formulari f where f.student = :student")
	List<Formular> findByStudent(@Param("student") Student student);

}
