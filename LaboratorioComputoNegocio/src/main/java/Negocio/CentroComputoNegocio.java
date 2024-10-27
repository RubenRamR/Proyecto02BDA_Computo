/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.CentroComputoDAO;
import DTOs.CentroComputoDTO;
import DTOs.UnidadAcademicaDTO;
import Entidades.CentroComputoEntidad;
import Entidades.UnidadAcademicaEntidad;
import InterfacesNegocio.ICentroComputoNegocio;
import Negocio.Convertidores.Convertidores;
import static com.mysql.cj.conf.PropertyKey.logger;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class CentroComputoNegocio implements ICentroComputoNegocio {

    CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
    ;
    Convertidores convertidor;

    public CentroComputoNegocio() {
        this.centroComputoDAO = new CentroComputoDAO();
        this.convertidor = new Convertidores();

    }

     @Override
    public void insertarCentroComputo(CentroComputoDTO centroComputoDTO) throws NegocioException {
        try {
            // Convertir el DTO a la entidad
            CentroComputoEntidad centroComputoEntidad = convertidor.convertirCentroComputoDTOAEntidad(centroComputoDTO);
            // Llamar al DAO para insertar la entidad en la base de datos
            centroComputoDAO.insertarCentroComputo(centroComputoEntidad);
        } catch (PersistenceException e) {
            throw new NegocioException("Error al insertar el centro de cómputo: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new NegocioException("Error inesperado: " + e.getMessage(), e);
        }
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
                unidadAcademicaDTOs.add(dto);
            }
        } catch (Exception e)
        {
            try
            {
                throw new Exception("Error al obtener unidades académicas", e);
            } catch (Exception ex)
            {
                Logger.getLogger(CentroComputoNegocio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return unidadAcademicaDTOs;
    }

}
