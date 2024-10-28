/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Entidades.ComputadoraEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public interface IComputadoraDAO {

    public void insertarComputadora(ComputadoraEntidad computadora) throws PersistenceException;
    
    public void editarComputadora(ComputadoraEntidad computadora) throws PersistenceException;

    public void eliminarComputadoraPorID(Long id) throws PersistenceException;

    public ComputadoraEntidad obtenerComputadoraPorID(Long id) throws PersistenceException;

    public ComputadoraEntidad obtenerComputadoraPorEstudiante(long idEstudiante) throws PersistenceException ;

    
    public List<ComputadoraEntidad> obtenerTodasLasComputadoras() throws PersistenceException;
}
