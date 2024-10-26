/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import DTOs.BloqueoDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface IBloqueoNegocio {

    public void insertarBloqueo(BloqueoDTO bloqueo, Long idEstudiante) throws NegocioException;

    public void editarBloqueo(BloqueoDTO bloqueo) throws NegocioException;

    public void eliminarBloqueoPorID(Long id) throws NegocioException;

    public BloqueoDTO obtenerBloqueoPorID(Long id) throws NegocioException;

    public List<BloqueoDTO> obtenerTodosLosBloqueos() throws NegocioException;
}
