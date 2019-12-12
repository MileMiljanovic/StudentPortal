package com.ftn.student.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ftn.student.service.models.Korisnik;
import com.ftn.student.service.models.Uloga;

@Repository
public interface KorisniciRepository extends JpaRepository<Korisnik, String> {
	
	@Query("select k from korisnici k where k.uloga = :uloga")
	List<Korisnik> findByRole(@Param("uloga") Uloga uloga);

}
