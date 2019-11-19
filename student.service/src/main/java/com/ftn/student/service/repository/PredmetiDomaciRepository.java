package com.ftn.student.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.student.service.models.PredmetDomaci;
import com.ftn.student.service.models.PredmetDomaciID;

@Repository
public interface PredmetiDomaciRepository extends JpaRepository<PredmetDomaci, PredmetDomaciID> {

}
