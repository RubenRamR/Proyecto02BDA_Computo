/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.CentroComputoDAO;
import DAOs.ComputadoraDAO;
import DAOs.ReservaDAO;
import DTOs.CentroComputoDTO;
import DTOs.ComputadoraDTO;
import DTOs.UnidadAcademicaDTO;
import ENUM_P.Estado;
import Entidades.CentroComputoEntidad;
import Entidades.ComputadoraEntidad;
import Entidades.EstudianteEntidad;
import Entidades.ReservaEntidad;
import Entidades.UnidadAcademicaEntidad;
import InterfacesNegocio.ICentroComputoNegocio;
import Negocio.Convertidores.Convertidores;
import static com.mysql.cj.conf.PropertyKey.logger;
import excepciones.NegocioException;
import java.time.LocalDateTime;
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

    ComputadoraDAO computadoraDAO = new ComputadoraDAO();
    CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
    private ReservaDAO reservaDAO; // DAO para manejar reservas

    Convertidores convertidor;

    public CentroComputoNegocio() {
        this.centroComputoDAO = new CentroComputoDAO();
        this.convertidor = new Convertidores();
        this.reservaDAO = new ReservaDAO(); 
        this.computadoraDAO = new ComputadoraDAO();

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
    
   public void reservarComputadora(ComputadoraDTO computadoraDTO, EstudianteEntidad estudiante, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) throws NegocioException {
    try {
        // Convertir el DTO de computadora a entidad
        ComputadoraEntidad computadoraEntidad = convertidor.convertirComputadoraDTOAEntidad(computadoraDTO);
        
        // Crear una nueva reserva
        ReservaEntidad reserva = new ReservaEntidad();
        reserva.setComputadora(computadoraEntidad); // Establece la computadora
        reserva.setEstudiante(estudiante); // Establece el estudiante que reserva
        reserva.setFechaHoraInicio(fechaHoraInicio); // Establece la fecha y hora de inicio
        reserva.setFechaHoraFin(fechaHoraFin); // Establece la fecha y hora de fin

        // Llamar al DAO para insertar la reserva
        reservaDAO.insertarReserva(reserva); // Usa el ReservaDAO para insertar la reserva

        // Actualizar el estado de la computadora a OCUPADO
        computadoraEntidad.setEstado(Estado.OCUPADO);
        computadoraDAO.actualizarComputadora(computadoraEntidad); // Actualiza la computadora

    } catch (PersistenceException e) {
        throw new NegocioException("Error al reservar la computadora: " + e.getMessage(), e);
    } catch (Exception e) {
        throw new NegocioException("Error inesperado al reservar la computadora: " + e.getMessage(), e);
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
