package com.citasmedicas.citasmedicas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.citasmedicas.citasmedicas.model.Citas;
import com.citasmedicas.citasmedicas.repository.CitasRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CitasServiceImpl implements CitasService {

    @Autowired
    private CitasRepository citasRepository;

    @Override
    public List<Citas> getAllCitas() {
        return citasRepository.findAll();
    }

    @Override
    public Optional<Citas> getCitasById(Long id) {
        return citasRepository.findById(id);
    }

    @Override
    public Citas createCitas(Citas citas) {
        return citasRepository.save(citas);
    }

    @Override
    public Citas updateCitas(Long id, Citas citas) {
        if (citasRepository.existsById(id)) {
            citas.setId(id);
            return citasRepository.save(citas);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCitas(Long id) {
        citasRepository.deleteById(id);
    }
}
