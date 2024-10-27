/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.CentroComputoDAO;
import DTOs.CentroComputoDTO;
import DTOs.UnidadAcademicaDTO;
import Entidades.UnidadAcademicaEntidad;
import InterfacesNegocio.ICentroComputoNegocio;
import Negocio.Convertidores.Convertidores;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class CentroComputoNegocio implements ICentroComputoNegocio {

    CentroComputoDAO centroComputoDAO;
    Convertidores convertidor;

    public CentroComputoNegocio() {
    }

    public CentroComputoNegocio(CentroComputoDAO centroComputoDAO) {
        this.centroComputoDAO = centroComputoDAO;
    }

    @Override
    public void insertarCentroComputo(CentroComputoDTO centroComputo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editarCentroComputo(CentroComputoDTO centroComputo) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarCentroComputoPorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CentroComputoDTO obtenerCentroComputoPorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CentroComputoDTO> obtenerTodosLosCentroComputo() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public UnidadAcademicaDTO obtenerIdUnidadAcademicaPorNombre(String nombre) throws Exception {
        try
        {
            // Llama al método en el DAO para obtener la Unidad Académica
            UnidadAcademicaEntidad unidadAcademica = centroComputoDAO.obtenerIdUnidadAcademicaPorNombre(nombre);
            UnidadAcademicaDTO unidadAcademicadto = convertidor.convertirUnidadAcademicaEntidadADTO(unidadAcademica);

            return unidadAcademicadto;
        } catch (Exception e)
        {
            // Manejo de excepciones, log o cualquier otra lógica de negocio que quieras aplicar
            throw new Exception("Error al obtener la unidad académica en el negocio: " + e.getMessage(), e);
        }
    }

    public List<UnidadAcademicaDTO> obtenerUnidadesAcademicas() {
        List<UnidadAcademicaDTO> unidadAcademicaDTOs = new ArrayList<>();
        try
        {
            List<UnidadAcademicaEntidad> unidades = centroComputoDAO.obtenerUnidadesAcademicas();
            for (UnidadAcademicaEntidad unidad : unidades)
            {
                UnidadAcademicaDTO dto = new UnidadAcademicaDTO();
                dto.setId(unidad.getId());
                dto.setNombre(unidad.getNombre());
                // Agregar otros campos según los atributos de la entidad
                unidadAcademicaDTOs.add(dto);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return unidadAcademicaDTOs;
    }
}
