package com.citasmedicas.citasmedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.citasmedicas.citasmedicas.model.Citas;

public interface CitasRepository extends JpaRepository<Citas, Long> {
    
}
