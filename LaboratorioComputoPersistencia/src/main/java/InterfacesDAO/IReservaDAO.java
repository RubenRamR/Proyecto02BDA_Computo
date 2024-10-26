/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Entidades.ReservaEntidad;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public interface IReservaDAO {

    public void insertarReserva(ReservaEntidad reserva) throws PersistenceException;

    public List<ReservaEntidad> obtenerReservas() throws PersistenceException;
    
    public ReservaEntidad obtenerReservaPorId(Long id) throws PersistenceException;

    }
