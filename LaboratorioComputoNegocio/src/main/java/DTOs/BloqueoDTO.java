/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalDateTime;

/**
 *
 * @author rramirez
 */
public class BloqueoDTO {

    private Long id;

    private LocalDateTime fechaLiberacion;

    private LocalDateTime fechaBloqueo;

    private String motivo;

    private EstudianteDTO estudiante;

    public BloqueoDTO() {
    }

    public BloqueoDTO(LocalDateTime fechaLiberacion, LocalDateTime fechaBloqueo, String motivo, EstudianteDTO estudiante) {
        this.fechaLiberacion = fechaLiberacion;
        this.fechaBloqueo = fechaBloqueo;
        this.motivo = motivo;
        this.estudiante = estudiante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaLiberacion() {
        return fechaLiberacion;
    }

    public void setFechaLiberacion(LocalDateTime fechaLiberacion) {
        this.fechaLiberacion = fechaLiberacion;
    }

    public LocalDateTime getFechaBloqueo() {
        return fechaBloqueo;
    }

    public void setFechaBloqueo(LocalDateTime fechaBloqueo) {
        this.fechaBloqueo = fechaBloqueo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "BloqueoDTO{" + "id=" + id + ", fechaLiberacion=" + fechaLiberacion + ", fechaBloqueo=" + fechaBloqueo + ", motivo=" + motivo + ", estudiante=" + estudiante + '}';
    }

}
