/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import DTOs.ComputadoraDTO;
import Entidades.ComputadoraEntidad;
import excepciones.NegocioException;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public interface IComputadoraNegocio {

    public void insertarComputadora(ComputadoraDTO computadora) throws NegocioException;

    public void editarComputadora(ComputadoraDTO computadora) throws NegocioException;

    public void eliminarComputadoraPorID(Long id) throws NegocioException;

    public ComputadoraDTO obtenerComputadoraPorID(Long id) throws NegocioException;

    public List<ComputadoraDTO> obtenerTodasLasComputadora() throws NegocioException;
}
