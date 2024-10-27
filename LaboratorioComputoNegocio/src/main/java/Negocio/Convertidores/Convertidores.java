/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.Convertidores;

import DAOs.CarreraDAO;
import DAOs.EstudianteDAO;
import DTOs.BloqueoDTO;
import DTOs.CarreraDTO;
import DTOs.CentroComputoDTO;
import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import DTOs.UnidadAcademicaDTO;
import ENUM_P.Estatus;
import Entidades.BloqueoEntidad;
import Entidades.CarreraEntidad;
import Entidades.CentroComputoEntidad;
import Entidades.ComputadoraEntidad;
import Entidades.EstudianteEntidad;
import Entidades.UnidadAcademicaEntidad;
import static com.mysql.cj.conf.PropertyKey.logger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author rramirez
 */
public class Convertidores {

    private static final Logger logger = Logger.getLogger(EstudianteDAO.class.getName());

    private EstudianteDAO estudianteDAO = new EstudianteDAO();
    private CarreraDAO carreraDAO = new CarreraDAO();

    public Convertidores() {
    }

    public EstudianteEntidad convertirEstudianteAEntidad(EstudianteDTO estudiantedto) {
        Long id = estudiantedto.getId();
        String nombre = estudiantedto.getNombre();
        String apPaterno = estudiantedto.getApPaterno();
        String apMaterno = estudiantedto.getApMaterno();
        Estatus estatus = estudiantedto.getEstatus();
        String contrasena = estudiantedto.getContrasena();
        Long idCarrera = estudiantedto.getCarrera().getId(); // Obtener el ID de la carrera

        // Buscar la CarreraEntidad usando el ID
        CarreraEntidad carreraEntidad = carreraDAO.obtenerCarreraPorId(idCarrera);

        EstudianteEntidad estudianteEntidad = new EstudianteEntidad();
        estudianteEntidad.setId(id);
        estudianteEntidad.setNombre(nombre);
        estudianteEntidad.setApPaterno(apPaterno);
        estudianteEntidad.setApMaterno(apMaterno);
        estudianteEntidad.setEstatus(estatus);
        estudianteEntidad.setContrasena(contrasena);
        estudianteEntidad.setCarrera(carreraEntidad); // Asignar la carrera

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
        Long idCarrera = estudianteEntidad.getCarrera().getId(); // Obtener el ID de la carrera

        // Buscar la CarreraEntidad usando el ID
        CarreraEntidad carreraEntidad2 = carreraDAO.obtenerCarreraPorId(idCarrera);
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
        Long id = carreradto.getId();
        String nombre = carreradto.getNombre();
        int minutosMaxUsoDiario = carreradto.getMinutosMaxUsoDiario();

        return new CarreraEntidad(id, nombre, minutosMaxUsoDiario);
    }

    public CarreraDTO convertirCarreraADTO(CarreraEntidad carreraEntidad) {
        Long id = carreraEntidad.getId();
        String nombre = carreraEntidad.getNombre();
        int minutosMaxUsoDiario = carreraEntidad.getminutosMaxUsoDiario();

        return new CarreraDTO(id, nombre, minutosMaxUsoDiario);
    }

    public List<CarreraDTO> convertirCarrerasADTO(List<CarreraEntidad> carrerasEntidad) {
        List<CarreraDTO> carrerasDTO = new ArrayList<>(); // Crear una lista para almacenar los DTOs

        for (CarreraEntidad carreraEntidad : carrerasEntidad)
        { // Iterar sobre cada CarreraEntidad
            Long id = carreraEntidad.getId();
            String nombre = carreraEntidad.getNombre();
            int minutosMaxUsoDiario = carreraEntidad.getminutosMaxUsoDiario(); // Asegúrate de usar el método correcto

            // Crear un CarreraDTO y agregarlo a la lista
            CarreraDTO carreraDTO = new CarreraDTO(id, nombre, minutosMaxUsoDiario);
            carrerasDTO.add(carreraDTO);
        }

        return carrerasDTO; // Devolver la lista de CarreraDTO
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

    public UnidadAcademicaDTO convertirUnidadAcademicaEntidadADTO(UnidadAcademicaEntidad entidad) {
        // Crear el DTO vacío
        UnidadAcademicaDTO dto = new UnidadAcademicaDTO();

        // Mapear los atributos simples
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());

        // Mapear la lista de centros de cómputo si existe
        List<CentroComputoDTO> centrosComputoDTO = entidad.getCentrosComputo().stream()
                .map(this::convertirCentroComputoEntidadADTO) // Método para convertir cada CentroComputoEntidad a DTO
                .collect(Collectors.toList());

        // Asignar la lista convertida al DTO
        dto.setCentrosComputo(centrosComputoDTO);

        return dto;
    }

    public CentroComputoDTO convertirCentroComputoEntidadADTO(CentroComputoEntidad entidad) {
        CentroComputoDTO dto = new CentroComputoDTO();

        // Mapeo de atributos básicos
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setContrasenaMaestra(entidad.getContrasenaMaestra());
        dto.setHoraInicio(entidad.getHoraInicio());
        dto.setHoraFin(entidad.getHoraFin());

        // Mapeo de la relación con UnidadAcademica
        if (entidad.getUnidadAcademica() != null)
        {
            UnidadAcademicaDTO unidadAcademicaDTO = convertirUnidadAcademicaEntidadADTO(entidad.getUnidadAcademica());
            dto.setUnidadAcademica(unidadAcademicaDTO);
        }

        return dto;
    }

    public CentroComputoEntidad convertirCentroComputoDTOAEntidad(CentroComputoDTO centroComputoDTO) {
        if (centroComputoDTO == null)
        {
            return null; // Manejo de caso nulo
        }

        // Crear la entidad
        CentroComputoEntidad centroComputoEntidad = new CentroComputoEntidad();

        // Asignar los valores del DTO a la entidad
        centroComputoEntidad.setId(centroComputoDTO.getId());
        centroComputoEntidad.setNombre(centroComputoDTO.getNombre());
        centroComputoEntidad.setContrasenaMaestra(centroComputoDTO.getContrasenaMaestra());
        centroComputoEntidad.setHoraInicio(centroComputoDTO.getHoraInicio());
        centroComputoEntidad.setHoraFin(centroComputoDTO.getHoraFin());

        // Convertir UnidadAcademicaDTO a UnidadAcademicaEntidad
        if (centroComputoDTO.getUnidadAcademica() != null)
        {
            UnidadAcademicaEntidad unidadAcademicaEntidad = new UnidadAcademicaEntidad();
            unidadAcademicaEntidad.setId(centroComputoDTO.getUnidadAcademica().getId());
            // Asigna otros campos de UnidadAcademicaDTO según sea necesario
            centroComputoEntidad.setUnidadAcademica(unidadAcademicaEntidad);
        }

        // Convertir la lista de ComputadoraDTO a ComputadoraEntidad
        List<ComputadoraEntidad> computadorasEntidad = new ArrayList<>();
        for (ComputadoraDTO computadoraDTO : centroComputoDTO.getComputadoras())
        {
            ComputadoraEntidad computadoraEntidad = new ComputadoraEntidad();
            computadoraEntidad.setId(computadoraDTO.getId());
            // Asigna otros campos de ComputadoraDTO según sea necesario
            computadorasEntidad.add(computadoraEntidad);
        }
        centroComputoEntidad.setComputadoras(computadorasEntidad);

        return centroComputoEntidad;
    }

}
