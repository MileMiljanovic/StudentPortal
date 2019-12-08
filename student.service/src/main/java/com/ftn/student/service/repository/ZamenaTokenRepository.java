package com.ftn.student.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.student.service.models.ZamenaToken;

public interface ZamenaTokenRepository extends JpaRepository<ZamenaToken, String> {

}
