/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class CentroComputoDTO {

    private Long id;

    private String nombre;

    private String contrasenaMaestra;

    private LocalTime horaInicio;

    private LocalTime horaFin;

    private UnidadAcademicaDTO unidadAcademica;

    private List<ComputadoraDTO> computadoras = new ArrayList<>();

    public CentroComputoDTO() {
    }

    public CentroComputoDTO(String nombre, String contrasenaMaestra, LocalTime horaInicio, LocalTime horaFin, UnidadAcademicaDTO unidadAcademica) {
        this.nombre = nombre;
        this.contrasenaMaestra = contrasenaMaestra;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.unidadAcademica = unidadAcademica;
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

    public String getContrasenaMaestra() {
        return contrasenaMaestra;
    }

    public void setContrasenaMaestra(String contrasenaMaestra) {
        this.contrasenaMaestra = contrasenaMaestra;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public UnidadAcademicaDTO getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademicaDTO unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public List<ComputadoraDTO> getComputadoras() {
        return computadoras;
    }

    public void setComputadoras(List<ComputadoraDTO> computadoras) {
        this.computadoras = computadoras;
    }

    @Override
    public String toString() {
        return "CentroComputoDTO{" + "id=" + id + ", nombre=" + nombre + ", contrasenaMaestra=" + contrasenaMaestra + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", unidadAcademica=" + unidadAcademica + ", computadoras=" + computadoras + '}';
    }

}
