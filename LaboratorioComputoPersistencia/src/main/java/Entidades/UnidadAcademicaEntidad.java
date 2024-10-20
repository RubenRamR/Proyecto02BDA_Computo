/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author rramirez
 */
@Entity
@Table(name = "UnidadAcademica")
public class UnidadAcademicaEntidad implements Serializable {

    @Id
    @Column(name = "idUnidadAcademica", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 60, nullable = false)
    private String nombre;
//
//    @OneToMany(mappedBy = "unidadAcademica", cascade = CascadeType.PERSIST)
//    private List<CentroComputoEntidad> centrosComputo = new ArrayList<>();
    
    @ManyToOne // Indica que este es el lado "many" de la relación
    @JoinColumn(name = "unidad_academica_id", nullable = false) // Columna que relaciona con UnidadAcademica
    private UnidadAcademicaEntidad unidadAcademica; // Debe coincidir con el mappedBy en UnidadAcademicaEntidad


    public UnidadAcademicaEntidad() {
    }

    public UnidadAcademicaEntidad(String nombre) {
        this.nombre = nombre;
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

    public UnidadAcademicaEntidad getUnidadAcademica() {
        return unidadAcademica;
    }

    public void setUnidadAcademica(UnidadAcademicaEntidad unidadAcademica) {
        this.unidadAcademica = unidadAcademica;
    }


}
