package com.citasmedicas.citasmedicas.controller;

import com.citasmedicas.citasmedicas.model.Citas;
import com.citasmedicas.citasmedicas.service.CitasService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CitasController.class)
public class CitasControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitasService citasService;

    @Test
    public void obtenerTodosTest() throws Exception {
        Citas citas1 = new Citas();
        citas1.setNombre("Juan");
        citas1.setId(1L);

        Citas citas2 = new Citas();
        citas2.setNombre("Roberto");
        citas2.setId(2L);

        List<Citas> citas = Arrays.asList(citas1, citas2);
        Mockito.when(citasService.getAllCitas()).thenReturn(citas);

        mockMvc.perform(MockMvcRequestBuilders.get("/citasmedicas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("Juan")))
                .andExpect(jsonPath("$[1].nombre", is("Roberto")));
    }
    
    @Test
    public void testGetCitasByIdHateoasLinks() throws Exception {
        Citas mockCitas = new Citas();
        mockCitas.setId(1L);
        mockCitas.setNombre("Mock Roberto");

        Mockito.when(citasService.getCitasById(anyLong())).thenReturn(Optional.of(mockCitas));

        mockMvc.perform(MockMvcRequestBuilders.get("/citasmedicas/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andExpect(jsonPath("$._links.all-citas.href").exists());
    }
}
