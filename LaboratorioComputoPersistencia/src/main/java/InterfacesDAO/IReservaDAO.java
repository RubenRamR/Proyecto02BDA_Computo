/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Entidades.ReservaEntidad;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public interface IReservaDAO {
    
        public void insertarReserva(ReservaEntidad reserva) throws PersistenceException;
}
