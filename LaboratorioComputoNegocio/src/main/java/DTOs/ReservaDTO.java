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
public class ReservaDTO {

    private Long id;

    private LocalDateTime fechaHoraInicio;

    private LocalDateTime fechaHoraFin;

    private EstudianteDTO estudiante;

    private ComputadoraDTO computadora;

    public ReservaDTO() {
    }

    public ReservaDTO(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, EstudianteDTO estudiante, ComputadoraDTO computadora) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estudiante = estudiante;
        this.computadora = computadora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public EstudianteDTO getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteDTO estudiante) {
        this.estudiante = estudiante;
    }

    public ComputadoraDTO getComputadora() {
        return computadora;
    }

    public void setComputadora(ComputadoraDTO computadora) {
        this.computadora = computadora;
    }

    @Override
    public String toString() {
        return "ReservaDTO{" + "id=" + id + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", estudiante=" + estudiante + ", computadora=" + computadora + '}';
    }

}
