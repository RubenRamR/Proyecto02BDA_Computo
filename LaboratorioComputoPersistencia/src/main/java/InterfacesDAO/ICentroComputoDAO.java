/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Entidades.CentroComputoEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public interface ICentroComputoDAO {

    public void insertarCentroComputo(CentroComputoEntidad centroComputo) throws PersistenceException;

    public void editarCentroComputo(CentroComputoEntidad centroComputo) throws PersistenceException;

    public void eliminarCentroComputoPorID(Long id) throws PersistenceException;

    public CentroComputoEntidad obtenerCentroComputoPorID(Long id) throws PersistenceException;

    public List<CentroComputoEntidad> obtenerTodosLosCentroComputo() throws PersistenceException;
}
