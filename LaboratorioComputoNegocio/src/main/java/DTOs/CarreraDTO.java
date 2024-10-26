/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class CarreraDTO {

    private Long id;

    private String nombre;

    private int minutosMaxUsoDiario;

    private List<EstudianteDTO> estudiantes = new ArrayList<>();

    public CarreraDTO() {
    }

    public CarreraDTO(String nombre, int minutosMaxUsoDiario) {
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

    public int getMinutosMaxUsoDiario() {
        return minutosMaxUsoDiario;
    }

    public void setMinutosMaxUsoDiario(int minutosMaxUsoDiario) {
        this.minutosMaxUsoDiario = minutosMaxUsoDiario;
    }

    public List<EstudianteDTO> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<EstudianteDTO> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return "CarreraDTO{" + "id=" + id + ", nombre=" + nombre + ", minutosMaxUsoDiario=" + minutosMaxUsoDiario + ", estudiantes=" + estudiantes + '}';
    }

}
