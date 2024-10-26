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
public class SoftwareDTO {

    private Long id;

    private String nombre;

    private List<ComputadoraDTO> computadoras = new ArrayList<>();

    public SoftwareDTO() {
    }

    public SoftwareDTO(String nombre) {
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

    public List<ComputadoraDTO> getComputadoras() {
        return computadoras;
    }

    public void setComputadoras(List<ComputadoraDTO> computadoras) {
        this.computadoras = computadoras;
    }

    @Override
    public String toString() {
        return "SoftwareDTO{" + "id=" + id + ", nombre=" + nombre + ", computadoras=" + computadoras + '}';
    }

}
