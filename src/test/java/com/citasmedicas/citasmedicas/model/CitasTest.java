package com.citasmedicas.citasmedicas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.time.LocalDateTime;
import java.util.Set;

public class CitasTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCrearCitasConExito() {
        Citas citas = new Citas();
        citas.setNombre("Juan");
        citas.setApellido("Pérez");
        citas.setRut("12345678-9");
        citas.setCorreo("juan.perez@example.com");
        citas.setTelefono("+56912345678");
        citas.setDireccion("Calle Falsa 123");
        citas.setFechaHora(LocalDateTime.now().plusDays(1));
        citas.setEstado("PROGRAMADA");

        assertNotNull(citas);
        assertEquals("Juan", citas.getNombre());
        assertEquals("Pérez", citas.getApellido());
    }

    @Test
    public void testValidarCamposObligatorios() {
        Citas citas = new Citas(); 

        Set<ConstraintViolation<Citas>> violations = validator.validate(citas);
        assertFalse(violations.isEmpty()); 

        // Verificar que la violación se debe a que el nombre no está presente
        assertTrue(violations.stream().anyMatch(violation -> 
            violation.getPropertyPath().toString().equals("nombre") && 
            violation.getMessage().equals("El nombre no debe estar vacío")));

        // Verificar que la violación se debe a que el apellido no está presente
        assertTrue(violations.stream().anyMatch(violation -> 
            violation.getPropertyPath().toString().equals("apellido") && 
            violation.getMessage().equals("El apellido no debe estar vacío")));
    }

    @Test
    public void testValidarFormatoCorreoIncorrecto() {
        Citas citas = new Citas();
        citas.setNombre("Juan");
        citas.setApellido("Pérez");
        citas.setRut("12345678-9");
        citas.setCorreo("correo_incorrecto"); 
        citas.setTelefono("+56912345678");
        citas.setDireccion("Calle Falsa 123");
        citas.setFechaHora(LocalDateTime.now().plusDays(1));
        citas.setEstado("PROGRAMADA");

        Set<ConstraintViolation<Citas>> violations = validator.validate(citas);
        assertFalse(violations.isEmpty()); // Asegurar que existen violaciones

        // Verificar que la violación se debe a un formato incorrecto del correo
        assertTrue(violations.stream().anyMatch(violation -> 
            violation.getPropertyPath().toString().equals("correo") && 
            violation.getMessage().equals("Debe ser un correo electrónico válido")));
    }

    @Test
    public void testValidarFechaHoraEnFuturo() {
        Citas citas = new Citas();
        citas.setNombre("Juan");
        citas.setApellido("Pérez");
        citas.setRut("12345678-9");
        citas.setCorreo("juan.perez@example.com");
        citas.setTelefono("+56912345678");
        citas.setDireccion("Calle Falsa 123");
        citas.setFechaHora(LocalDateTime.now().minusDays(1));
        citas.setEstado("PROGRAMADA");

        Set<ConstraintViolation<Citas>> violations = validator.validate(citas);
        assertFalse(violations.isEmpty()); // Asegurar que existen violaciones

        // Verificar que la violación se debe a la fecha y hora en el pasado
        assertTrue(violations.stream().anyMatch(violation -> 
            violation.getPropertyPath().toString().equals("fechaHora") && 
            violation.getMessage().equals("La fecha y hora deben ser futuras o actuales")));
    }
}