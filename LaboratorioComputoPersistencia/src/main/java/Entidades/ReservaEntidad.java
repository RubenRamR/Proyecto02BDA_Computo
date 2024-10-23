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
@Table(name = "Reserva")
public class ReservaEntidad implements Serializable {

    @Id
    @Column(name = "idReserva", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fechaHoraInicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fechaHoraFin", nullable = false)
    private LocalDateTime fechaHoraFin;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntidad estudiante;

    @ManyToOne
    @JoinColumn(name = "computadora_id")
    private ComputadoraEntidad computadora;

    public ReservaEntidad() {
    }

    public ReservaEntidad(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, EstudianteEntidad estudiante, ComputadoraEntidad computadora) {
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

    public EstudianteEntidad getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(EstudianteEntidad estudiante) {
        this.estudiante = estudiante;
    }

    public ComputadoraEntidad getComputadora() {
        return computadora;
    }

    public void setComputadora(ComputadoraEntidad computadora) {
        this.computadora = computadora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaEntidad))
        {
            return false;
        }
        ReservaEntidad other = (ReservaEntidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ReservaEntidad{" + "id=" + id + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", estudiante=" + estudiante + ", computadora=" + computadora + '}';
    }

}
