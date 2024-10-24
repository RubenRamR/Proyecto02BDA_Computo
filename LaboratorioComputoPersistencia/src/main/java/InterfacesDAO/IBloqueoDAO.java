/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Entidades.BloqueoEntidad;
import Entidades.EstudianteEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public interface IBloqueoDAO {

    public void insertarBloqueo(BloqueoEntidad bloqueo, Long idEstudiante) throws PersistenceException;

    public void editarBloqueo(BloqueoEntidad bloqueo) throws PersistenceException;

    public void eliminarBloqueoPorID(Long id) throws PersistenceException;

    public BloqueoEntidad obtenerBloqueoPorID(Long id) throws PersistenceException;

    public List<BloqueoEntidad> obtenerTodosLosBloqueos() throws PersistenceException;
}
