/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

/**
 *
 * @author user
 */
public class DatosReporteCarrerasDTO {
    
    private String nombreCarrera;
    private int minutosUsoPorDia;
    private int cantidadAlumnos;

    // Constructor por omisión
    public DatosReporteCarrerasDTO() {
  
    }

    // Constructor
    public DatosReporteCarrerasDTO(String nombreCarrera, int minutosUsoPorDia, int cantidadAlumnos) {
        this.nombreCarrera = nombreCarrera;
        this.minutosUsoPorDia = minutosUsoPorDia;
        this.cantidadAlumnos = cantidadAlumnos;
    }

    // Getters y Setters
    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public int getMinutosUsoPorDia() {
        return minutosUsoPorDia;
    }

    public void setMinutosUsoPorDia(int minutosUsoPorDia) {
        this.minutosUsoPorDia = minutosUsoPorDia;
    }

    public int getCantidadAlumnos() {
        return cantidadAlumnos;
    }

    public void setCantidadAlumnos(int cantidadAlumnos) {
        this.cantidadAlumnos = cantidadAlumnos;
    }

    // Método toString
    @Override
    public String toString() {
        return "DatosReporteCarrerasDTO{" +
                "nombreCarrera='" + nombreCarrera + '\'' +
                ", minutosUsoPorDia=" + minutosUsoPorDia +
                ", cantidadAlumnos=" + cantidadAlumnos +
                '}';
    }
}

