package com.ftn.student.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.student.service.models.PredmetID;
import com.ftn.student.service.models.PredmetStrani;

@Repository
public interface PredmetiStraniRepository extends JpaRepository<PredmetStrani, PredmetID> {

}
