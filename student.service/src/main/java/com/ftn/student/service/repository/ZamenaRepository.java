package com.ftn.student.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.student.service.models.Formular;
import com.ftn.student.service.models.Zamena;

@Repository
public interface ZamenaRepository extends JpaRepository<Zamena, String> {
	
	@Query("select z from zamene z where z.formular = :formular")
	List<Zamena> findByFormular(@Param("formular") Formular formular);

}
