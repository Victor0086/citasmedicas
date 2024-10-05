package com.citasmedicas.citasmedicas.model;


import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;


@Entity
@Table(name = "pacientes")
public class Citas {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre no debe estar vacío")
    private String nombre;

    @Column(name = "apellido", nullable = false)
    @NotBlank(message = "El apellido no debe estar vacío")
    private String apellido;

    @Column(name = "rut", nullable = false)
    @NotBlank(message = "El rut no debe estar vacío")
    private String rut;

    @Column(name = "correo", nullable = false, unique = true)
    @Email(message = "Debe ser un correo electrónico válido")
    private String correo;

    @Column(name = "telefono", nullable = false)
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "Debe ser un número de teléfono válido")
    private String telefono;

    @Column(name = "direccion", nullable = false)
    @NotBlank(message = "La direccion no puede estar vacia")
    private String direccion;

    @Column(name = "fecha_hora", nullable = false)
    @FutureOrPresent(message = "La fecha y hora deben ser futuras o actuales")
    private LocalDateTime fechaHora;

    @Column(name = "estado", nullable = false)
    @NotBlank(message = "El estado no debe estar vacío")
    private String estado; // Estados posibles: "PROGRAMADA", "CANCELADA", "COMPLETADA"




       // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
