/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import ENUM_P.Estatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author rramirez
 */
@Entity
@Table(name = "Estudiante")
public class EstudianteEntidad implements Serializable {

    @Id
    @Column(name = "idEstudiante", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 35, nullable = false)
    private String nombre;

    @Column(name = "apPaterno", length = 35, nullable = false)
    private String apPaterno;

    @Column(name = "apMaterno", length = 35, nullable = false)
    private String apMaterno;

    @Enumerated(EnumType.STRING)
    @Column(name = "Estatus")
    private Estatus estatus;

    @Column(name = "contrasena", nullable = false)
    private String contrasena;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<ReservaEntidad> reservas = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idCarrera", nullable = false)
    private CarreraEntidad carrera;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<BloqueoEntidad> bloqueos = new ArrayList<>();

    public EstudianteEntidad() {
    }

    public EstudianteEntidad(String nombre, String apPaterno, String apMaterno, Estatus estatus, String contrasena, CarreraEntidad carrera) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.estatus = estatus;
        this.contrasena = contrasena;
        this.carrera = carrera;
    }

    
    public EstudianteEntidad(String nombre, String apPaterno, String apMaterno, Estatus estatus, String contrasena, CarreraEntidad carrera, List<BloqueoEntidad> bloqueos) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.estatus = estatus;
        this.contrasena = contrasena;
        this.carrera = carrera;
        this.bloqueos = bloqueos;
    }

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

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public Estatus getEstatus() {
        return estatus;
    }

    public void setEstatus(Estatus estatus) {
        this.estatus = estatus;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<ReservaEntidad> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntidad> reservas) {
        this.reservas = reservas;
    }

    public CarreraEntidad getCarrera() {
        return carrera;
    }

    public void setCarrera(CarreraEntidad carrera) {
        this.carrera = carrera;
    }

    public List<BloqueoEntidad> getBloqueos() {
        return bloqueos;
    }

    public void setBloqueos(List<BloqueoEntidad> bloqueos) {
        this.bloqueos = bloqueos;
    }

    @Override
    public String toString() {
        return "EstudianteEntidad{" + "id=" + id + ", nombre=" + nombre + ", apPaterno=" + apPaterno + ", apMaterno=" + apMaterno + ", estatus=" + estatus + ", contrasena=" + contrasena + ", reservas=" + reservas + ", carrera=" + carrera + ", bloqueos=" + bloqueos + '}';
    }

}
