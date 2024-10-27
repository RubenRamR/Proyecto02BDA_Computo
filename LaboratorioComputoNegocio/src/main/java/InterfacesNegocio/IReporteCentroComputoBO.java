/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import DTOs.CentroComputoDTO;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IReporteCentroComputoBO {
    
        public List<CentroComputoDTO> generarReportePorCentroYFechas(String centroComputo, LocalDate fechaInicio, LocalDate fechaFin);
}
