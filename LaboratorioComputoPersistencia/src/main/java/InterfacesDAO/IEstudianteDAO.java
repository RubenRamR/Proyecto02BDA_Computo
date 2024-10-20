/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Entidades.EstudianteEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public interface IEstudianteDAO {
    
    public void insertarEstudiante(EstudianteEntidad estudiante) throws PersistenceException;
    
    public void editarEstudiante(EstudianteEntidad estudiante) throws PersistenceException;
    
    public void eliminarEstudiantePorID(Long id) throws PersistenceException;
    
    public EstudianteEntidad obtenerEstudiantePorID(Long id) throws PersistenceException;
    
    public List<EstudianteEntidad> obtenerTodosLosEstudiantes() throws PersistenceException;
    
}
