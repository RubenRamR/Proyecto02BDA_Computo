/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package InterfacesNegocio;

import DTOs.CentroComputoDTO;
import DTOs.ComputadoraDTO;
import Entidades.EstudianteEntidad;
import excepciones.NegocioException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author rramirez
 */
public interface ICentroComputoNegocio {

    public void insertarCentroComputo(CentroComputoDTO centroComputo) throws NegocioException;

    public void editarCentroComputo(CentroComputoDTO centroComputo) throws NegocioException;

    public void eliminarCentroComputoPorID(Long id) throws NegocioException;

    public CentroComputoDTO obtenerCentroComputoPorID(Long id) throws NegocioException;

    public CentroComputoDTO obtenerCentroComputoPorComputadora(Long idComputadora);

    public void reservarComputadora(ComputadoraDTO computadoraDTO, EstudianteEntidad estudiante, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) throws NegocioException;

    public List<CentroComputoDTO> obtenerTodosLosCentroComputo() throws NegocioException;

    public CentroComputoDTO obtenerCentroComputoPorNombre(String nombre) throws NegocioException;
}
