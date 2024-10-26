/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.Convertidores;

import DAOs.EstudianteDAO;
import DTOs.BloqueoDTO;
import DTOs.CarreraDTO;
import DTOs.EstudianteDTO;
import ENUM_P.Estatus;
import Entidades.BloqueoEntidad;
import Entidades.CarreraEntidad;
import Entidades.EstudianteEntidad;
import static com.mysql.cj.conf.PropertyKey.logger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author rramirez
 */
public class Convertidores {

    private static final Logger logger = Logger.getLogger(EstudianteDAO.class.getName());

    private EstudianteDAO estudianteDAO = new EstudianteDAO();

    public Convertidores() {
    }

    public EstudianteEntidad convertirEstudianteAEntidad(EstudianteDTO estudiantedto) {
        Long id = estudiantedto.getId();
        String nombre = estudiantedto.getNombre();
        String apPaterno = estudiantedto.getApPaterno();
        String apMaterno = estudiantedto.getApMaterno();
        Estatus estatus = estudiantedto.getEstatus();
        String contrasena = estudiantedto.getContrasena();
        CarreraDTO carreradto = estudiantedto.getCarrera();

        CarreraEntidad carreraEntidad = this.convertirCarreraAEntidad(carreradto);

        EstudianteEntidad estudianteEntidad = new EstudianteEntidad();
        estudianteEntidad.setId(id);
        estudianteEntidad.setNombre(nombre);
        estudianteEntidad.setApPaterno(apPaterno);
        estudianteEntidad.setApMaterno(apMaterno);
        estudianteEntidad.setEstatus(estatus);
        estudianteEntidad.setContrasena(contrasena);
        estudianteEntidad.setCarrera(carreraEntidad);
        List<BloqueoDTO> listaBloqueosdto = estudiantedto.getBloqueos();

        List<BloqueoEntidad> listaBloqueosEntidad = this.convertirBloqueosAEntidad(listaBloqueosdto);
        estudianteEntidad.setBloqueos(listaBloqueosEntidad);

        return estudianteEntidad;

    }

    public EstudianteDTO convertirEstudianteADTO(EstudianteEntidad estudianteEntidad) {
        String nombre = estudianteEntidad.getNombre();
        String apPaterno = estudianteEntidad.getApPaterno();
        String apMaterno = estudianteEntidad.getApMaterno();
        Estatus estatus = estudianteEntidad.getEstatus();
        String contrasena = estudianteEntidad.getContrasena();
        CarreraEntidad carreraEntidad = estudianteEntidad.getCarrera();

        // Convertir CarreraEntidad a CarreraDTO
        CarreraDTO carreraDTO = this.convertirCarreraADTO(carreraEntidad);

        // Convertir bloqueos (sin incluir al estudiante dentro de los bloqueos para evitar recursión)
        List<BloqueoEntidad> bloqueosEntidad = estudianteEntidad.getBloqueos();
        List<BloqueoDTO> bloqueosDTO = this.convertirBloqueosADTO(bloqueosEntidad);

        // Crear el DTO del estudiante
        EstudianteDTO estudianteDTO = new EstudianteDTO();
        estudianteDTO.setNombre(nombre);
        estudianteDTO.setApPaterno(apPaterno);
        estudianteDTO.setApMaterno(apMaterno);
        estudianteDTO.setEstatus(estatus);
        estudianteDTO.setContrasena(contrasena);
        estudianteDTO.setCarrera(carreraDTO);
        estudianteDTO.setBloqueos(bloqueosDTO);

        return estudianteDTO;
    }

    public List<EstudianteDTO> convertirListaEstudiantesEntidadADTO(List<EstudianteEntidad> listaEstudiantesEntidad) {
        List<EstudianteDTO> listaEstudiantesDTO = new ArrayList<>();

        for (EstudianteEntidad estudianteEntidad : listaEstudiantesEntidad)
        {
            EstudianteDTO estudianteDTO = this.convertirEstudianteADTO(estudianteEntidad);
            listaEstudiantesDTO.add(estudianteDTO);
        }

        return listaEstudiantesDTO;
    }

    public CarreraEntidad convertirCarreraAEntidad(CarreraDTO carreradto) {
        String nombre = carreradto.getNombre();
        int minutosMaxUsoDiario = carreradto.getMinutosMaxUsoDiario();

        return new CarreraEntidad(nombre, minutosMaxUsoDiario);
    }

    public CarreraDTO convertirCarreraADTO(CarreraEntidad carreraEntidad) {
        String nombre = carreraEntidad.getNombre();
        int minutosMaxUsoDiario = carreraEntidad.getminutosMaxUsoDiario();

        return new CarreraDTO(nombre, minutosMaxUsoDiario);
    }

    public List<BloqueoEntidad> convertirBloqueosAEntidad(List<BloqueoDTO> bloqueosdto) {
        List<BloqueoEntidad> bloqueosEntidad = new ArrayList<>();

        for (BloqueoDTO bloqueoDTO : bloqueosdto)
        {
            LocalDateTime fechaLiberacion = bloqueoDTO.getFechaLiberacion();
            LocalDateTime fechaBloqueo = bloqueoDTO.getFechaBloqueo();
            String motivo = bloqueoDTO.getMotivo();

            EstudianteDTO estudiante = bloqueoDTO.getEstudiante();
            Long estudianteId = estudiante.getId();

            EstudianteEntidad estudianteEntidad = estudianteDAO.obtenerEstudiantePorID(estudianteId);

            if (estudianteEntidad == null)
            {
                logger.severe("No se encontró el estudiante con ID: " + estudianteId);
                continue;
            }

            BloqueoEntidad bloqueoEntidad = new BloqueoEntidad(fechaLiberacion, fechaBloqueo, motivo, estudianteEntidad);
            bloqueosEntidad.add(bloqueoEntidad);
        }

        return bloqueosEntidad;

    }

    public List<BloqueoDTO> convertirBloqueosADTO(List<BloqueoEntidad> bloqueosEntidad) {
        List<BloqueoDTO> bloqueosDTO = new ArrayList<>();

        for (BloqueoEntidad bloqueo : bloqueosEntidad)
        {
            LocalDateTime fechaLiberacion = bloqueo.getFechaLiberacion();
            LocalDateTime fechaBloqueo = bloqueo.getFechaBloqueo();
            String motivo = bloqueo.getMotivo();

            // No incluimos al estudiante aquí para evitar el ciclo de recursión
            BloqueoDTO bloqueoDTO = new BloqueoDTO(fechaLiberacion, fechaBloqueo, motivo, null);
            bloqueosDTO.add(bloqueoDTO);
        }

        return bloqueosDTO;
    }

    public BloqueoDTO convertirBloqueoADTO(BloqueoEntidad bloqueo) {
        if (bloqueo == null)
        {
            return null;
        }

        LocalDateTime fechaLiberacion = bloqueo.getFechaLiberacion();
        LocalDateTime fechaBloqueo = bloqueo.getFechaBloqueo();
        String motivo = bloqueo.getMotivo();

        EstudianteEntidad estudiante = bloqueo.getEstudiante();
        EstudianteDTO estudianteDTO = this.convertirEstudianteADTO(estudiante);

        BloqueoDTO bloqueoDTO = new BloqueoDTO(fechaLiberacion, fechaBloqueo, motivo, estudianteDTO);
        return bloqueoDTO;
    }

}
