/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import ENUM_P.Estatus;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class EstudianteDTO {

    private Long id;

    private String nombre;

    private String apPaterno;

    private String apMaterno;

    private Estatus estatus;

    private String contrasena;

    private List<ReservaDTO> reservas = new ArrayList<>();

    private CarreraDTO Carrera;

    private List<BloqueoDTO> bloqueos = new ArrayList<>();

    public EstudianteDTO() {
    }

    public EstudianteDTO(String nombre, String apPaterno, String apMaterno, Estatus estatus, String contrasena) {
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.estatus = estatus;
        this.contrasena = contrasena;
    }

    public CarreraDTO getCarrera() {
        return Carrera;
    }

    public void setCarrera(CarreraDTO Carrera) {
        this.Carrera = Carrera;
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

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    public List<BloqueoDTO> getBloqueos() {
        return bloqueos;
    }

    public void setBloqueos(List<BloqueoDTO> bloqueos) {
        this.bloqueos = bloqueos;
    }

    @Override
    public String toString() {
        return "EstudianteDTO{" + "id=" + id + ", nombre=" + nombre + ", apPaterno=" + apPaterno + ", apMaterno=" + apMaterno + ", estatus=" + estatus + ", contrasena=" + contrasena + ", reservas=" + reservas + ", Carrera=" + Carrera + ", bloqueos=" + bloqueos + '}';
    }

}
