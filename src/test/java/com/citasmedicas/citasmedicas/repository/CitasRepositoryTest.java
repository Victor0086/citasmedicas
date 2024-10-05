package com.citasmedicas.citasmedicas.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.citasmedicas.citasmedicas.model.Citas;

import java.time.LocalDateTime;
import java.util.Optional;


@SpringBootTest
@Transactional
public class CitasRepositoryTest {

    @Autowired
    private CitasRepository citasRepository;
    
    @Test
    public void guardarCitasTest() {
        Citas citas = new Citas();
        citas.setNombre("Juan");
        citas.setApellido("Gonzalez");
        citas.setRut("14.524.632-9");
        citas.setCorreo("uan.unique.email@gmail.com");
        citas.setTelefono("956423212");
        citas.setDireccion("Calle Falsa 123");
        citas.setEstado("PROGRAMADA");
        citas.setFechaHora(LocalDateTime.now().plusDays(1));
        citas.setEstado("PROGRAMADA"); 
    
        Citas resultado = citasRepository.save(citas);
    
        assertNotNull(resultado.getId());
        assertEquals("Juan", resultado.getNombre());
    }

    
    @Test
    public void encontrarPorIdTest() {
        Citas citas = new Citas();
        citas.setNombre("Juan");
        citas.setApellido("Gonzalez");
        citas.setRut("14.524.632-9");
        citas.setCorreo("uan.unique.email@gmail.com");
        citas.setTelefono("956423212");
        citas.setDireccion("Calle Falsa 123");
        citas.setEstado("PROGRAMADA");
        citas.setFechaHora(LocalDateTime.now().plusDays(1));
        citas.setEstado("PROGRAMADA"); 
    
        Citas savedCitas = citasRepository.save(citas);
    
        Optional<Citas> resultado = citasRepository.findById(savedCitas.getId());
    
        assertTrue(resultado.isPresent());
        assertEquals("Juan", resultado.get().getNombre());
    }

    @Test
    public void eliminarCitasTest() {
        Citas citas = new Citas();
        citas.setNombre("Juan");
        citas.setApellido("Gonzalez");
        citas.setRut("14.524.632-9");
        citas.setCorreo("uan.unique.email@gmail.com");
        citas.setTelefono("956423212");
        citas.setDireccion("Calle Falsa 123");
        citas.setEstado("PROGRAMADA");
        citas.setFechaHora(LocalDateTime.now().plusDays(1));
        citas.setEstado("PROGRAMADA"); 

        Citas savedCitas = citasRepository.save(citas);

        citasRepository.deleteById(savedCitas.getId());

        Optional<Citas> resultado = citasRepository.findById(savedCitas.getId());
        assertFalse(resultado.isPresent());
    }

}
