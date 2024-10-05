package com.citasmedicas.citasmedicas.controller;


import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.citasmedicas.citasmedicas.model.Citas;
import com.citasmedicas.citasmedicas.service.CitasService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestMapping;

import org.slf4j.Logger;


@RestController
@RequestMapping("/citasmedicas")
public class CitasController {

    private static final Logger log = LoggerFactory.getLogger(CitasController.class);

    @Autowired
    private CitasService citasService;

    @GetMapping
    public List<Citas> getAllCitas(){
        return citasService.getAllCitas();
    }

    @GetMapping("/{id}")
public ResponseEntity<Object> getCitasById(@PathVariable Long id) {
    Optional<Citas> citas = citasService.getCitasById(id);
    if (citas.isPresent()) {
        EntityModel<Citas> resource = EntityModel.of(citas.get());
        // enlaces HATEOAS
        resource.add(linkTo(methodOn(CitasController.class).getCitasById(id)).withSelfRel());
        resource.add(linkTo(methodOn(CitasController.class).getAllCitas()).withRel("all-citas"));
        return ResponseEntity.ok(resource);
    } else {
        log.error("No se encontró la cita con ID {}", id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("No se encontró la cita con ID " + id));
    }
    }

    @PostMapping
    public ResponseEntity<Object> createCitas(@Validated  @RequestBody Citas citas) {
        Citas createCitas = citasService.createCitas(citas);
        if (createCitas == null ) {
            log.error("Error al crear la Cita {}", citas);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear la Cita"));
        }
        return ResponseEntity.ok("Cita creada exitosamente");
    }    


    @PutMapping ("/{id}")
    public Citas updateCitas(@PathVariable Long id, @RequestBody Citas citas){
        return citasService.updateCitas(id, citas);
    }
    @DeleteMapping ("/{id}")
    public void deleteCitas(@PathVariable Long id){
        citasService.deleteCitas(id);
    }

    static class ErrorResponse {
        private final String message;
    
        public ErrorResponse(String message) {
            this.message = message;
        }
    
        public String getMessage() {
            return message;
        }
    }
}
