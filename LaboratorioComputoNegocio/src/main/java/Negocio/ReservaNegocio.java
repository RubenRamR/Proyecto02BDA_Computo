/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.ComputadoraDAO;
import DAOs.ReservaDAO;
import DTOs.ReservaDTO;
import Entidades.ComputadoraEntidad;
import Entidades.EstudianteEntidad;
import Entidades.ReservaEntidad;
import InterfacesNegocio.IReservaNegocio;
import Negocio.Convertidores.Convertidores;
import excepciones.NegocioException;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class ReservaNegocio implements IReservaNegocio {

    ReservaDAO reservaDAO;
    private Convertidores convertidor;
    ComputadoraDAO computadoraDAO;

    public ReservaNegocio() {
    }

    public ReservaNegocio(ReservaDAO reservaDAO, ComputadoraDAO computadoraDAO, Convertidores convertidor) {
        this.reservaDAO = reservaDAO;
        this.computadoraDAO = computadoraDAO;
        this.convertidor = convertidor;
    }

    @Override
    public void insertarReserva(ReservaDTO reservaDTO) throws NegocioException {
        try {
            // Crear la entidad de reserva
            ReservaEntidad reservaEntidad = new ReservaEntidad();
            reservaEntidad.setFechaHoraInicio(reservaDTO.getFechaHoraInicio());
            reservaEntidad.setFechaHoraFin(reservaDTO.getFechaHoraFin());

            // Convertir EstudianteDTO a EstudianteEntidad
            EstudianteEntidad estudianteEntidad = convertidor.convertirEstudianteAEntidad(reservaDTO.getEstudiante());
            reservaEntidad.setEstudiante(estudianteEntidad);

            // Obtener la ComputadoraEntidad existente usando el ID
            Long idComputadora = reservaDTO.getComputadora().getId(); // Asegúrate de que esto sea correcto
            ComputadoraEntidad computadoraEntidad = computadoraDAO.obtenerComputadoraPorID(idComputadora);

            if (computadoraEntidad == null) {
                throw new NegocioException("Computadora no encontrada con ID: " + idComputadora);
            }

            reservaEntidad.setComputadora(computadoraEntidad);

            // Insertar la reserva en el DAO
            reservaDAO.insertarReserva(reservaEntidad);
        } catch (PersistenceException e) {
            throw new NegocioException("Error al insertar la reserva: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new NegocioException("Error inesperado al insertar la reserva: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ReservaDTO> obtenerReservas() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReservaDTO obtenerReservaPorId(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
