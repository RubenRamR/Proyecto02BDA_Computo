/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Entidades.CarreraEntidad;
import Entidades.ReservaEntidad;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author user
 */
public interface IReporteCarreraDAO {

/**
     * Obtiene una lista de reservas entre las fechas especificadas.
     * 
     * @param fechaInicio la fecha y hora de inicio
     * @param fechaFin la fecha y hora de fin
     * @return lista de reservas que cumplen con los criterios
     * @throws PersistenciaException en caso de error de persistencia
     */
    List<ReservaEntidad> obtenerReservasPorCarrera(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException;

    /**
     * Obtiene una carrera por su ID.
     * 
     * @param idCarrera el ID de la carrera a obtener
     * @return la carrera correspondiente al ID
     * @throws PersistenciaException en caso de error de persistencia
     */
    CarreraEntidad obtenerCarreraPorId(Long idCarrera) throws PersistenciaException;
}
