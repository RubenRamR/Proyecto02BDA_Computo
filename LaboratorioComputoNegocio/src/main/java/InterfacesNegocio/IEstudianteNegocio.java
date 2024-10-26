/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import DTOs.EstudianteDTO;
import Entidades.EstudianteEntidad;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IEstudianteNegocio {

    public void insertarEstudiante(EstudianteDTO estudiante) throws NegocioException;

    public void editarEstudiante(EstudianteDTO estudiante) throws NegocioException;

    public void eliminarEstudiantePorID(Long id) throws NegocioException;

    public EstudianteDTO obtenerEstudiantePorID(Long id) throws NegocioException;

    public List<EstudianteDTO> obtenerTodosLosEstudiantes() throws NegocioException;

}
