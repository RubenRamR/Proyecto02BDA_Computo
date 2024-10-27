/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.CarreraDAO;
import DAOs.EstudianteDAO;
import DTOs.CarreraDTO;
import DTOs.EstudianteDTO;
import Entidades.CarreraEntidad;
import Entidades.EstudianteEntidad;
import InterfacesNegocio.IEstudianteNegocio;
import Negocio.Convertidores.Convertidores;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rramirez
 */
public class EstudianteNegocio implements IEstudianteNegocio {

    Convertidores convertidor = new Convertidores();
    EstudianteDAO estudianteDAO = new EstudianteDAO();
    CarreraDAO carreraDAO = new CarreraDAO();

    public EstudianteNegocio() {
    }

    @Override
    public void insertarEstudiante(EstudianteDTO estudianteDTO) throws NegocioException {

        try
        {

            Long idCarrera = estudianteDTO.getCarrera().getId();
            if (idCarrera == null)
            {
                throw new NegocioException("El ID de la carrera no puede ser nulo.");
            }
            CarreraEntidad carreraEntidad = carreraDAO.obtenerCarreraPorId(idCarrera);

            EstudianteEntidad estudianteEntidad = convertidor.convertirEstudianteAEntidad(estudianteDTO);

            carreraEntidad = carreraDAO.obtenerCarreraPorId(estudianteDTO.getCarrera().getId());
            if (carreraEntidad == null)
            {
                throw new NegocioException("Carrera no encontrada para el ID: " + estudianteDTO.getCarrera().getId());
            }

            estudianteEntidad.setCarrera(carreraEntidad);

            estudianteDAO.insertarEstudiante(estudianteEntidad);
        } catch (Exception e)
        {
            throw new NegocioException("Error al insertar el estudiante: " + e.getMessage(), e);
        }
    }

    @Override
    public void editarEstudiante(EstudianteDTO estudiante) throws NegocioException {
        try
        {
            estudianteDAO.editarEstudiante(convertidor.convertirEstudianteAEntidad(estudiante));
        } catch (Exception ex)
        {
            throw new NegocioException("Error al editar el estudiante: " + ex.getMessage(), ex);
        }
    }

    @Override
    public void eliminarEstudiantePorID(Long id) throws NegocioException {
        try
        {
            estudianteDAO.eliminarEstudiantePorID(id);
        } catch (Exception ex)
        {
            throw new NegocioException("Error al eliminar el estudiante con ID " + id + ": " + ex.getMessage(), ex);
        }
    }

    @Override
    public EstudianteDTO obtenerEstudiantePorID(Long id) throws NegocioException {
        try
        {
            EstudianteEntidad estudianteE = estudianteDAO.obtenerEstudiantePorID(id);
            return convertidor.convertirEstudianteADTO(estudianteE);
        } catch (Exception ex)
        {
            throw new NegocioException("Error al obtener el estudiante con ID " + id + ": " + ex.getMessage(), ex);
        }
    }

    @Override
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() throws NegocioException {
        try
        {
            List<EstudianteEntidad> listaEstudiantesE = estudianteDAO.obtenerTodosLosEstudiantes();
            return convertidor.convertirListaEstudiantesEntidadADTO(listaEstudiantesE);
        } catch (Exception ex)
        {
            throw new NegocioException("Error al obtener la lista de estudiantes: " + ex.getMessage(), ex);
        }
    }

    public CarreraDTO obtenerIdCarreraPorNombre(String nombre) throws NegocioException {
        CarreraDTO carreraDTO = null;
        try
        {
            CarreraEntidad carreraEn = carreraDAO.obtenerIdCarreraPorNombre(nombre);
            carreraDTO = convertidor.convertirCarreraADTO(carreraEn);
            if (carreraDTO == null)
            {
                throw new NegocioException("No se encontró la carrera con nombre: " + nombre);
            }
        } catch (Exception ex)
        {
            throw new NegocioException("Error al obtener la carrera: " + ex.getMessage(), ex);
        }
        return carreraDTO;
    }

    public List<CarreraDTO> obtenerCarreras() {
        List<CarreraDTO> listaCarrerasDTO = new ArrayList<>();
        try
        {
            // Aquí asumo que tu DAO tiene un método que retorna las carreras desde la base de datos
            List<CarreraEntidad> listaCarrerase = carreraDAO.obtenerTodasLasCarreras();
            listaCarrerasDTO = convertidor.convertirCarrerasADTO(listaCarrerase);
        } catch (Exception e)
        {
            System.out.println("Error");
        }
        return listaCarrerasDTO;

    }

}
