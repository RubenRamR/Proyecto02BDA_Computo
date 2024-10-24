/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author rramirez
 */
@Entity
@Table(name = "CentroComputo")
public class CentroComputoEntidad implements Serializable {

    @Id
    @Column(name = "idCentroComputo", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 60, nullable = false)
    private String nombre;

    @Column(name = "contrasenaMaestra", length = 25, nullable = false)
    private String contrasenaMaestra;

    @Column(name = "horaInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "horaFin", nullable = false)
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "idUnidadAcademica", nullable = false)
    private UnidadAcademicaEntidad unidadAcademica;

    @OneToMany(mappedBy = "centroComputo", cascade = CascadeType.ALL)
    private List<ComputadoraEntidad> computadoras;

    @ManyToMany(cascade =
    {
        CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(
            name = "CentroComputo_Regla",
            joinColumns = @JoinColumn(name = "idCentroComputo"),
            inverseJoinColumns = @JoinColumn(name = "idRegla")
    )
    private List<ReglaEntidad> reglas = new ArrayList<>();

    public CentroComputoEntidad() {
    }

    public CentroComputoEntidad(String nombre, String contrasenaMaestra, LocalTime horaInicio, LocalTime horaFin, UnidadAcademicaEntidad unidadAcademica, List<ComputadoraEntidad> computadoras) {
        this.nombre = nombre;
        this.contrasenaMaestra = contrasenaMaestra;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.unidadAcademica = unidadAcademica;
        this.computadoras = computadoras;
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

    public UnidadAcademicaEntidad getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademicaEntidad unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }

    public List<ComputadoraEntidad> getComputadoras() {
        return computadoras;
    }

    public void setComputadoras(List<ComputadoraEntidad> computadoras) {
        this.computadoras = computadoras;
    }

    public List<ReglaEntidad> getReglas() {
        return reglas;
    }

    public void setReglas(List<ReglaEntidad> reglas) {
        this.reglas = reglas;
    }

    @Override
    public String toString() {
        return "CentroComputoEntidad{" + "id=" + id + ", nombre=" + nombre + ", contrasenaMaestra=" + contrasenaMaestra + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", unidadAcademica=" + unidadAcademica + ", computadoras=" + computadoras + ", reglas=" + reglas + '}';
    }

}
