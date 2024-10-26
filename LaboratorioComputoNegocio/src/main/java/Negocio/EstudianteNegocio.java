/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.EstudianteDAO;
import DTOs.EstudianteDTO;
import InterfacesNegocio.IEstudianteNegocio;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class EstudianteNegocio implements IEstudianteNegocio {

    EstudianteDAO estudianteDAO;

    public EstudianteNegocio() {
    }

    public EstudianteNegocio(EstudianteDAO estudianteDAO) {
        this.estudianteDAO = estudianteDAO;
    }

    @Override
    public void insertarEstudiante(EstudianteDTO estudiante) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editarEstudiante(EstudianteDTO estudiante) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarEstudiantePorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
