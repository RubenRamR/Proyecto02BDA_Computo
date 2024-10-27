/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesDAO;

import Entidades.CarreraEntidad;

/**
 *
 * @author rramirez
 */
public interface ICarreraDAO {
    
    public CarreraEntidad obtenerIdCarreraPorNombre(String nombre) throws Exception;
}
