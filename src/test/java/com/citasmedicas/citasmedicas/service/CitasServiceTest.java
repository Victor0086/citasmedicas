package com.citasmedicas.citasmedicas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.citasmedicas.citasmedicas.model.Citas;
import com.citasmedicas.citasmedicas.repository.CitasRepository;


@ExtendWith(MockitoExtension.class)
public class CitasServiceTest {
    
    @InjectMocks
    private CitasServiceImpl citasServicio;

    @Mock
    private CitasRepository citasRepositoryMock;

    @Test
    public void guardarCitasTest() {
        Citas citas = new Citas();
        citas.setNombre("Juan");

        when(citasRepositoryMock.save(any())).thenReturn(citas);

        Citas resultado = citasServicio.createCitas(citas);

        assertEquals("Juan", resultado.getNombre());
    }


    @Test
    public void actualizarCitasTest() {
        Citas citas = new Citas();
        citas.setNombre("Roberto");

        when(citasRepositoryMock.existsById(anyLong())).thenReturn(true);
        when(citasRepositoryMock.save(any())).thenReturn(citas);

        Citas resultado = citasServicio.updateCitas(1L, citas);

        assertNotNull(resultado);
        assertEquals("Roberto", resultado.getNombre());
    }

    @Test
    public void actualizarCitasNoExistenteTest() {
        Citas citas = new Citas();
        when(citasRepositoryMock.existsById(anyLong())).thenReturn(false);

        Citas resultado = citasServicio.updateCitas(1L, citas);

        assertNull(resultado);
    }

    @Test
    public void eliminarCitasTest() {
        citasServicio.deleteCitas(1L);
        verify(citasRepositoryMock, times(1)).deleteById(1L);
    }

}
