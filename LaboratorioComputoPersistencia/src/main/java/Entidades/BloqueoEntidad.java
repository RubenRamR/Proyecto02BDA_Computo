/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

/**
 *
 * @author rramirez
 */
@Entity
@Table(name = "Bloqueo")
public class BloqueoEntidad implements Serializable {

    @Id
    @Column(name = "idBloqueo", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaLiberacion", nullable = false)
    private LocalDateTime fechaLiberacion;

    @Column(name = "fechaBloqueo", nullable = false)
    private LocalDateTime fechaBloqueo;

    @Column(name = "motivo", length = 256, nullable = false)
    private String motivo;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idEstudiante", referencedColumnName = "idEstudiante")
    private EstudianteEntidad estudiante;

    public BloqueoEntidad() {
    }

    public BloqueoEntidad(LocalDateTime fechaLiberacion, LocalDateTime fechaBloqueo, String motivo, EstudianteEntidad estudiante) {
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

    public EstudianteEntidad getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntidad estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "BloqueoEntidad{" + "id=" + id + ", fechaLiberacion=" + fechaLiberacion + ", fechaBloqueo=" + fechaBloqueo + ", motivo=" + motivo + ", estudiante=" + estudiante + '}';
    }

}
