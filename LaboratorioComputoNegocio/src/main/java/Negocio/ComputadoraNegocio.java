/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.ComputadoraDAO;
import DTOs.ComputadoraDTO;
import InterfacesNegocio.IComputadoraNegocio;
import Negocio.Convertidores.Convertidores;
import excepciones.NegocioException;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class ComputadoraNegocio implements IComputadoraNegocio {

    ComputadoraDAO computadoraDAO = new ComputadoraDAO();
    Convertidores convertidor = new Convertidores();

    public ComputadoraNegocio() {
        this.computadoraDAO = new ComputadoraDAO();
        convertidor = new Convertidores();
    }

    public ComputadoraNegocio(ComputadoraDAO computadoraDAO) {
        this.computadoraDAO = computadoraDAO;
    }

    @Override
    public void insertarComputadora(ComputadoraDTO computadoraDTO) throws NegocioException {
        try
        {
            // Llama al método del DAO para insertar la computadora
            computadoraDAO.insertarComputadora(convertidor.convertirComputadoraDTOAEntidad(computadoraDTO));
        } catch (PersistenceException e)
        {
            throw new NegocioException("Error en la capa de negocio al insertar la computadora", e);
        }
    }

    @Override
    public void editarComputadora(ComputadoraDTO computadora) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarComputadoraPorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ComputadoraDTO obtenerComputadoraPorID(Long id) throws NegocioException {
        try{
        ComputadoraDTO computadoraDTO = convertidor.convertirComputadoraEntidadADTO(computadoraDAO.obtenerComputadoraPorID(id));
        return computadoraDTO;
        }catch(PersistenceException e){
            throw new NegocioException("Error en la capa de negocio al obtener la computadora por id", e);
        }
    }

    @Override
    public List<ComputadoraDTO> obtenerTodasLasComputadora() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
