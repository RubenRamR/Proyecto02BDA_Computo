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
        CarreraEntidad carrera = carreraDAO.obtenerCarreraPorId(estudianteDTO.getCarrera().getId());
        if (carrera != null)
        {
            EstudianteEntidad estudiante = new EstudianteEntidad();
            estudiante.setNombre(estudianteDTO.getNombre());
            estudiante.setApPaterno(estudianteDTO.getApPaterno());
            estudiante.setApMaterno(estudianteDTO.getApMaterno());
            estudiante.setEstatus(estudianteDTO.getEstatus());
            estudiante.setCarrera(carrera); // Asocia la carrera existente
            estudianteDAO.insertarEstudiante(estudiante);
        } else
        {
            System.out.println("La carrera no existe");
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

    public CarreraDTO obtenerCarreraPorNombre(String nombre) throws NegocioException {
        CarreraDTO carreraDTO = null;
        try
        {
            CarreraEntidad carreraEn = carreraDAO.obtenerCarreraPorNombre(nombre);
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

}
