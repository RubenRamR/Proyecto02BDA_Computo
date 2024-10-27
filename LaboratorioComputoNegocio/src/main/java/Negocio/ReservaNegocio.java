/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

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

    public ReservaNegocio() {
    }

    public ReservaNegocio(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
        this.convertidor = new Convertidores();
    }

 
    
    @Override
    public void insertarReserva(ReservaDTO reservaDTO) throws NegocioException {
        try {
            // Convertir el DTO a la entidad
            ReservaEntidad reservaEntidad = new ReservaEntidad();
            reservaEntidad.setFechaHoraInicio(reservaDTO.getFechaHoraInicio());
            reservaEntidad.setFechaHoraFin(reservaDTO.getFechaHoraFin());

            // Convertir EstudianteDTO a EstudianteEntidad
            EstudianteEntidad estudianteEntidad = convertidor.convertirEstudianteAEntidad(reservaDTO.getEstudiante());
            reservaEntidad.setEstudiante(estudianteEntidad);

            // Convertir ComputadoraDTO a ComputadoraEntidad
            ComputadoraEntidad computadoraEntidad = convertidor.convertirComputadoraDTOAEntidad(reservaDTO.getComputadora());
            reservaEntidad.setComputadora(computadoraEntidad);

            // Llamar al DAO para insertar la reserva
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
