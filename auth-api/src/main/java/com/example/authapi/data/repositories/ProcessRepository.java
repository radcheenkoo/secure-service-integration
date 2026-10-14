package com.example.authapi.data.repositories;

import com.example.authapi.data.entities.ProcessingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessingLog, UUID> {

}
