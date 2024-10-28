/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.BloqueoDAO;
import DTOs.BloqueoDTO;
import Entidades.BloqueoEntidad;
import InterfacesNegocio.IBloqueoNegocio;
import Negocio.Convertidores.Convertidores;
import excepciones.NegocioException;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class BloqueoNegocio implements IBloqueoNegocio {

    Convertidores convertidores = new Convertidores();
    BloqueoDAO bloqueoDAO;

    public BloqueoNegocio() {
    }

    public BloqueoNegocio(BloqueoDAO bloqueoDAO) {
        this.bloqueoDAO = new BloqueoDAO();
    }

    @Override
    public void insertarBloqueo(BloqueoDTO bloqueo, Long idEstudiante) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editarBloqueo(BloqueoDTO bloqueo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarBloqueoPorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public BloqueoDTO obtenerBloqueoPorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<BloqueoDTO> obtenerTodosLosBloqueos() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<BloqueoDTO> obtenerBloqueosPorIdEstudiante(Long id) throws PersistenceException {
        List<BloqueoEntidad> bloqueoEn = bloqueoDAO.obtenerBloqueosPorIdEstudiante(id);
        return convertidores.convertirBloqueosADTO(bloqueoEn);
    }

}
