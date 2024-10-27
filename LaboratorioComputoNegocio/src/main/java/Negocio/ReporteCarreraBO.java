/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DTOs.DatosReporteCarrerasDTO;
import Entidades.CarreraEntidad;
import Entidades.ReservaEntidad;
import InterfacesDAO.IReporteCarreraDAO;
import InterfacesNegocio.IReporteCarreraBO;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author user
 */
public class ReporteCarreraBO implements IReporteCarreraBO {
    private IReporteCarreraDAO reporteCarreraDAO;

    public ReporteCarreraBO(IReporteCarreraDAO reporteCarreraDAO) {
        this.reporteCarreraDAO = reporteCarreraDAO;
    }

    @Override
    public DatosReporteCarrerasDTO obtenerReporteCarreras(Long idCarrera, LocalDateTime fechaInicio, LocalDateTime fechaFin) throws NegocioException {
        try {
            List<ReservaEntidad> reservas = reporteCarreraDAO.obtenerReservasPorCarrera(fechaInicio, fechaFin);
            CarreraEntidad carrera = reporteCarreraDAO.obtenerCarreraPorId(idCarrera);
            return procesarReservas(reservas, carrera);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al obtener el reporte de carreras.", e);
        }
    }

    public DatosReporteCarrerasDTO procesarReservas(List<ReservaEntidad> reservas, CarreraEntidad carrera) {
        int minutosUso = 0;
        Set<Long> alumnosSet = new HashSet<>();

        // Procesar cada reserva para sumar minutos de uso y contar alumnos
        for (ReservaEntidad reserva : reservas) {
            // Calcular minutos de uso
            long minutos = ChronoUnit.MINUTES.between(reserva.getFechaHoraInicio(), reserva.getFechaHoraFin());
            minutosUso += minutos;

            // Agregar estudiantes únicos a un conjunto para evitar duplicados
            if (reserva.getEstudiante() != null) {
                alumnosSet.add(reserva.getEstudiante().getId());
            }
        }

        int cantidadAlumnos = alumnosSet.size();

        // Obtener el nombre de la carrera
        String nombreCarrera = carrera != null ? carrera.getNombre() : "Desconocida";

        return new DatosReporteCarrerasDTO(nombreCarrera, minutosUso, cantidadAlumnos);
    }

}
