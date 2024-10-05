package com.citasmedicas.citasmedicas.service;

import com.citasmedicas.citasmedicas.model.Citas;
import java.util.List;
import java.util.Optional;



public interface CitasService {
    List<Citas> getAllCitas();
    Optional<Citas> getCitasById(Long id);
    Citas createCitas(Citas citas);
    Citas updateCitas(Long id, Citas citas);
    void deleteCitas(Long id);


    
}
