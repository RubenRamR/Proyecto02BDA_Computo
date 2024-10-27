/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.CentroComputoDTO;
import Entidades.CentroComputoEntidad;
import InterfacesDAO.IReporteCentroComputoDAO;
import InterfacesNegocio.IReporteCentroComputoBO;
import Negocio.Convertidores.Convertidores;
import excepciones.NegocioException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rramirez
 */
public class ReporteCentroComputoBO implements IReporteCentroComputoBO {

    IReporteCentroComputoDAO reporteCentroComputoDAO;
    Convertidores convertidor = new Convertidores();

    public ReporteCentroComputoBO() {
    }

    public ReporteCentroComputoBO(IReporteCentroComputoDAO reporteCentroComputoDAO) {
        this.reporteCentroComputoDAO = reporteCentroComputoDAO;
    }

    @Override
    public List<CentroComputoDTO> generarReportePorCentroYFechas(String centroComputo, LocalDate fechaInicio, LocalDate fechaFin) {
        List<CentroComputoDTO> reportesDTO = new ArrayList<>();

        try
        {
            // Llama al DAO para obtener los reportes de entidades
            List<CentroComputoEntidad> reportesEntidad = reporteCentroComputoDAO.obtenerReportesPorCentroYFechas(centroComputo, fechaInicio, fechaFin);

            // Convertir las entidades a DTOs
            for (CentroComputoEntidad centro : reportesEntidad)
            {
                CentroComputoDTO centroDTO = convertidor.convertirCentroComputoEntidadADTO(centro);
                reportesDTO.add(centroDTO);
            }
        } catch (Exception e)
        {
            // Manejo de excepciones
            Logger.getLogger(ReporteCentroComputoBO.class.getName()).log(Level.SEVERE, "Error al generar el reporte de centro de cómputo", e);
            try
            {
                throw new NegocioException("Error al generar el reporte de centro de cómputo", e);
            } catch (NegocioException ex)
            {
                Logger.getLogger(ReporteCentroComputoBO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return reportesDTO; // Asegúrate de retornar la lista de DTOs
    }

}
