/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import DTOs.DatosReporteCarrerasDTO;
import Entidades.CarreraEntidad;
import Entidades.ReservaEntidad;
import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author user
 */
public interface IReporteCarreraBO {
    
     DatosReporteCarrerasDTO obtenerReporteCarreras(Long idCarrera, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException;

     DatosReporteCarrerasDTO procesarReservas(List<ReservaEntidad> reservas, CarreraEntidad carrera);

}