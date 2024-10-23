/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.naming.TimeLimitExceededException;
import javax.persistence.*;

/**
 *
 * @author rramirez
 */
@Entity
@Table(name = "Carrera")
public class CarreraEntidad implements Serializable {

    @Id
    @Column(name = "idCarrera", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 35, nullable = false)
    private String nombre;

    @Column(name = "minutosMaxUsoDiario", nullable = false)
    private int minutosMaxUsoDiario;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
    private List<EstudianteEntidad> estudiantes = new ArrayList<>();

    public CarreraEntidad() {
    }

    public CarreraEntidad(String nombre, int minutosMaxUsoDiario) {
        this.nombre = nombre;
        this.minutosMaxUsoDiario = minutosMaxUsoDiario;
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

    public int getminutosMaxUsoDiario() {
        return minutosMaxUsoDiario;
    }

    public void setminMaxUsoDiario(int minutosMaxUsoDiario) {
        this.minutosMaxUsoDiario = minutosMaxUsoDiario;
    }

    public List<EstudianteEntidad> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<EstudianteEntidad> estudiantes) {
        this.estudiantes = estudiantes;
    }

}
