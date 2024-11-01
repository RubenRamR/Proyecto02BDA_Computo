/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.ComputadoraDAO;
import DAOs.EstudianteDAO;
import DAOs.ReservaDAO;
import DTOs.ReservaDTO;
import ENUM_P.Estado;
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
    EstudianteDAO estudianteDAO;

    public ReservaNegocio() {
    }

    public ReservaNegocio(ReservaDAO reservaDAO, ComputadoraDAO computadoraDAO, EstudianteDAO estudianteDAO,Convertidores convertidor) {
        this.reservaDAO = reservaDAO;
        this.computadoraDAO = computadoraDAO;
        this.convertidor = convertidor;
        this.estudianteDAO = estudianteDAO;
        
    }

   
    @Override
    public void insertarReserva(ReservaDTO reservaDTO) throws NegocioException {
    try {
        ReservaEntidad reservaEntidad = new ReservaEntidad();
        reservaEntidad.setFechaHoraInicio(reservaDTO.getFechaHoraInicio());
        reservaEntidad.setFechaHoraFin(reservaDTO.getFechaHoraFin());

        // Convertir EstudianteDTO a EstudianteEntidad
        EstudianteEntidad estudianteEntidad = convertidor.convertirEstudianteAEntidad(reservaDTO.getEstudiante());
        reservaEntidad.setEstudiante(estudianteEntidad);

        // Obtener la computadora por dirección IP
        String direccionIP = reservaDTO.getComputadora().getDireccionIP(); // Asegúrate de que este método exista
        ComputadoraEntidad computadoraEntidad = computadoraDAO.obtenerComputadoraPorDireccionIP(direccionIP);
        
        if (computadoraEntidad != null) {
            // Si la computadora existe, actualizar su estado
            computadoraEntidad.setEstado(Estado.OCUPADO); // O el estado que necesites
            computadoraDAO.actualizarComputadora(computadoraEntidad); // Actualizar la computadora
            reservaEntidad.setComputadora(computadoraEntidad);
        } else {
            throw new NegocioException("No se encontró computadora con la dirección IP: " + direccionIP);
        }

        // Llamar al DAO para insertar la reserva
        reservaDAO.insertarReserva(reservaEntidad);
    } catch (PersistenceException e) {
        throw new NegocioException("Error al insertar la reserva: " + e.getMessage(), e);
    } catch (Exception e) {
        throw new NegocioException("Error inesperado al insertar la reserva: " + e.getMessage(), e);
    }
}

    
    //funciona +
//public void insertarReserva(ReservaDTO reservaDTO) throws NegocioException {
//    try {
//        ReservaEntidad reservaEntidad = new ReservaEntidad();
//        reservaEntidad.setFechaHoraInicio(reservaDTO.getFechaHoraInicio());
//        reservaEntidad.setFechaHoraFin(reservaDTO.getFechaHoraFin());
//
//        // Convertir EstudianteDTO a EstudianteEntidad
//        EstudianteEntidad estudianteEntidad = convertidor.convertirEstudianteAEntidad(reservaDTO.getEstudiante());
//        reservaEntidad.setEstudiante(estudianteEntidad);
//
//        // Verificar si la computadora ya existe
//        ComputadoraEntidad computadoraEntidad = computadoraDAO.obtenerComputadoraPorID(reservaDTO.getComputadora().getId());
//        if (computadoraEntidad == null) {
//            throw new NegocioException("La computadora con ID " + reservaDTO.getComputadora().getId() + " no existe.");
//        }
//        reservaEntidad.setComputadora(computadoraEntidad);
//
//        // Llamar al DAO para insertar la reserva
//        reservaDAO.insertarReserva(reservaEntidad);
//    } catch (PersistenceException e) {
//        throw new NegocioException("Error al insertar la reserva: " + e.getMessage(), e);
//    } catch (Exception e) {
//        throw new NegocioException("Error inesperado al insertar la reserva: " + e.getMessage(), e);
//    }
//}





    @Override
    public List<ReservaDTO> obtenerReservas() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ReservaDTO obtenerReservaPorId(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
