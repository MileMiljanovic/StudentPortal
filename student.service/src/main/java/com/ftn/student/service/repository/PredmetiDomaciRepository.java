package com.ftn.student.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.StudijskiProgramDomaci;

@Repository
public interface PredmetiDomaciRepository extends JpaRepository<PredmetDomaci, String> {
	
	@Query("select p from predmeti_domaci p where p.program = :studijski_program")
	List<PredmetDomaci> findByProgram(@Param("studijski_program") StudijskiProgramDomaci studijskiProgram);

}
