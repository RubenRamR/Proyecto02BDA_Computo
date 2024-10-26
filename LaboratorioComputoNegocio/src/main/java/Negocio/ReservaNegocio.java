/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.ReservaDAO;
import DTOs.ReservaDTO;
import InterfacesNegocio.IReservaNegocio;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class ReservaNegocio implements IReservaNegocio {

    ReservaDAO reservaDAO;

    public ReservaNegocio() {
    }

    public ReservaNegocio(ReservaDAO reservaDAO) {
        this.reservaDAO = reservaDAO;
    }

    @Override
    public void insertarReserva(ReservaDTO reserva) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
