/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import DTOs.ReservaDTO;
import Entidades.ReservaEntidad;
import excepciones.NegocioException;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public interface IReservaNegocio {

    public void insertarReserva(ReservaDTO reserva) throws NegocioException;

    public List<ReservaDTO> obtenerReservas() throws NegocioException;

    public ReservaDTO obtenerReservaPorId(Long id) throws NegocioException;

}
