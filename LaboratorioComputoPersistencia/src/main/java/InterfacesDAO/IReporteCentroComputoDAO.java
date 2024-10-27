/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Entidades.CarreraEntidad;
import Entidades.CentroComputoEntidad;
import Entidades.ReservaEntidad;
import excepciones.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IReporteCentroComputoDAO {

    /**
     * Método para obtener un reporte de computadoras en un centro de cómputo
     * específico dentro de un rango de fechas determinado.
     *
     * @param centroComputo Nombre o ID del centro de cómputo.
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin Fecha de fin del rango.
     * @return Lista de objetos ReporteDTO que contienen la información
     * filtrada.
     */
    List<CentroComputoEntidad> obtenerReportesPorCentroYFechas(String centroComputo, LocalDate fechaInicio, LocalDate fechaFin);

    /**
     * Método para obtener todos los centros de cómputo disponibles.
     *
     * @return Lista de nombres o IDs de centros de cómputo.
     */
    List<String> obtenerCentrosComputo();

}
