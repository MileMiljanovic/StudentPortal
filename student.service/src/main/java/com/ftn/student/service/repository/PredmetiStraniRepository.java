package com.ftn.student.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ftn.student.service.models.PredmetID;
import com.ftn.student.service.models.PredmetStrani;
import com.ftn.student.service.models.StudijskiProgramStrani;

@Repository
public interface PredmetiStraniRepository extends JpaRepository<PredmetStrani, PredmetID> {
	
	@Query("select p from predmeti_strani p where p.program = :studijski_program")
	List<PredmetStrani> findByProgramStrani(@Param("studijski_program") StudijskiProgramStrani studijskiProgram);

}
