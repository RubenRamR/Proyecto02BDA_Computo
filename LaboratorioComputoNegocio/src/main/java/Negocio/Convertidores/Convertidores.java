/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.Convertidores;

import DAOs.CarreraDAO;
import DAOs.ComputadoraDAO;
import DAOs.EstudianteDAO;
import DTOs.BloqueoDTO;
import DTOs.CarreraDTO;
import DTOs.CentroComputoDTO;
import DTOs.ComputadoraDTO;
import DTOs.EstudianteDTO;
import DTOs.SoftwareDTO;
import DTOs.UnidadAcademicaDTO;
import ENUM_P.Estatus;
import Entidades.BloqueoEntidad;
import Entidades.CarreraEntidad;
import Entidades.CentroComputoEntidad;
import Entidades.ComputadoraEntidad;
import Entidades.EstudianteEntidad;
import Entidades.SoftwareEntidad;
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
    private ComputadoraDAO computadoraDAO = new ComputadoraDAO();

    public Convertidores() {
    }

    public EstudianteEntidad convertirEstudianteAEntidad(EstudianteDTO estudiantedto) {
        if (estudiantedto.getId() == null)
        {
            System.out.println("Advertencia: El ID del estudiante es nulo.");
        }

        Long id = estudiantedto.getId();
        String nombre = estudiantedto.getNombre();
        String apPaterno = estudiantedto.getApPaterno();
        String apMaterno = estudiantedto.getApMaterno();
        Estatus estatus = estudiantedto.getEstatus();
        String contrasena = estudiantedto.getContrasena();

        CarreraEntidad carreraEntidad = null;

        // Verificar si la carrera no es nula antes de obtener el ID
        if (estudiantedto.getCarrera() != null)
        {
            Long idCarrera = estudiantedto.getCarrera().getId();
            carreraEntidad = carreraDAO.obtenerCarreraPorId(idCarrera);
        } else
        {
            System.out.println("Advertencia: Carrera no especificada para el estudiante.");
        }

        EstudianteEntidad estudianteEntidad = new EstudianteEntidad();
        estudianteEntidad.setId(id);
        estudianteEntidad.setNombre(nombre);
        estudianteEntidad.setApPaterno(apPaterno);
        estudianteEntidad.setApMaterno(apMaterno);
        estudianteEntidad.setEstatus(estatus);
        estudianteEntidad.setContrasena(contrasena);
        estudianteEntidad.setCarrera(carreraEntidad); // Asignar la carrera (puede ser nula si no existe)

        List<BloqueoDTO> listaBloqueosdto = estudiantedto.getBloqueos();
        List<BloqueoEntidad> listaBloqueosEntidad = this.convertirBloqueosAEntidad(listaBloqueosdto);
        estudianteEntidad.setBloqueos(listaBloqueosEntidad);

        return estudianteEntidad;
    }

    //
    public EstudianteDTO convertirEstudianteADTO(EstudianteEntidad estudianteEntidad) {
        EstudianteDTO estudianteDTO = new EstudianteDTO();

        // Asegúrate de que estos valores no sean null
        estudianteDTO.setId(estudianteEntidad.getId());
        estudianteDTO.setNombre(estudianteEntidad.getNombre());
        estudianteDTO.setApPaterno(estudianteEntidad.getApPaterno());
        estudianteDTO.setApMaterno(estudianteEntidad.getApMaterno());
        estudianteDTO.setEstatus(estudianteEntidad.getEstatus());
        estudianteDTO.setContrasena(estudianteEntidad.getContrasena());

        // Convertir CarreraEntidad a CarreraDTO
        CarreraEntidad carreraEntidad = estudianteEntidad.getCarrera();
        if (carreraEntidad != null)
        {
            CarreraDTO carreraDTO = this.convertirCarreraADTO(carreraEntidad);
            estudianteDTO.setCarrera(carreraDTO);
        }

        // Convertir bloqueos
        List<BloqueoEntidad> bloqueosEntidad = estudianteEntidad.getBloqueos();
        List<BloqueoDTO> bloqueosDTO = this.convertirBloqueosADTO(bloqueosEntidad);
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

        // Mapeo de la relación con UnidadAcademica (evitando referencia circular)
        if (entidad.getUnidadAcademica() != null)
        {
            UnidadAcademicaDTO unidadAcademicaDTO = new UnidadAcademicaDTO();
            unidadAcademicaDTO.setId(entidad.getUnidadAcademica().getId());
            unidadAcademicaDTO.setNombre(entidad.getUnidadAcademica().getNombre());
            dto.setUnidadAcademica(unidadAcademicaDTO); // Asigna solo el DTO parcial o simplificado
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

    public ComputadoraEntidad convertirComputadoraDTOAEntidad(ComputadoraDTO computadoraDTO) {
        // Primero, verifica si la computadoraDTO es nula
        if (computadoraDTO == null)
        {
            return null; // Manejo de caso nulo
        }

        // Intentar obtener la entidad existente de la base de datos
        ComputadoraEntidad computadoraEntidad = null;
        if (computadoraDTO.getId() != null)
        {
            computadoraEntidad = computadoraDAO.obtenerComputadoraPorID(computadoraDTO.getId());
        }

        // Si no se encontró la entidad existente, crea una nueva instancia
        if (computadoraEntidad == null)
        {
            computadoraEntidad = new ComputadoraEntidad();
        }

        // Asigna los valores del DTO a la entidad
        computadoraEntidad.setId(computadoraDTO.getId());
        computadoraEntidad.setNombreAlumno(computadoraDTO.getNombreAlumno());
        computadoraEntidad.setEstado(computadoraDTO.getEstado());
        computadoraEntidad.setNumeroMaquina(computadoraDTO.getNumeroMaquina());
        computadoraEntidad.setDireccionIP(computadoraDTO.getDireccionIP());
        computadoraEntidad.setTipoCompu(computadoraDTO.getTipoCompu());

        // Manejo del CentroComputo
        CentroComputoEntidad centroComputoEntidad = convertirCentroComputoDTOAEntidad(computadoraDTO.getCentroComputo());
        computadoraEntidad.setCentroComputo(centroComputoEntidad);

        // Manejo de la lista de Software
        List<SoftwareEntidad> softwareList = convertirListaSoftwareDTOAEntidad(computadoraDTO.getSoftwareList());
        computadoraEntidad.setSoftwareList(softwareList);

        return computadoraEntidad;
    }

    // Método para convertir SoftwareEntidad a SoftwareDTO
    public SoftwareDTO convertirSoftwareEntidadADTO(SoftwareEntidad softwareEntidad) {
        if (softwareEntidad == null)
        {
            return null;
        }

        SoftwareDTO softwareDTO = new SoftwareDTO();
        softwareDTO.setId(softwareEntidad.getId());
        softwareDTO.setNombre(softwareEntidad.getNombre());

        // Si necesitas convertir la lista de ComputadoraEntidad a ComputadoraDTO
        List<ComputadoraDTO> computadorasDTO = new ArrayList<>();
        for (ComputadoraEntidad computadora : softwareEntidad.getComputadoras())
        {
            // Aquí puedes llamar al método correspondiente para convertir cada ComputadoraEntidad
            ComputadoraDTO computadoraDTO = convertirComputadoraEntidadADTO(computadora);
            computadorasDTO.add(computadoraDTO);
        }
        softwareDTO.setComputadoras(computadorasDTO);

        return softwareDTO;
    }

    // Método para convertir SoftwareDTO a SoftwareEntidad
    public SoftwareEntidad convertirSoftwareDTOAEntidad(SoftwareDTO softwareDTO) {
        if (softwareDTO == null)
        {
            return null;
        }

        SoftwareEntidad softwareEntidad = new SoftwareEntidad();
        softwareEntidad.setId(softwareDTO.getId());
        softwareEntidad.setNombre(softwareDTO.getNombre());

        // Si necesitas convertir la lista de ComputadoraDTO a ComputadoraEntidad
        List<ComputadoraEntidad> computadorasEntidad = new ArrayList<>();
        for (ComputadoraDTO computadora : softwareDTO.getComputadoras())
        {
            // Aquí puedes llamar al método correspondiente para convertir cada ComputadoraDTO
            ComputadoraEntidad computadoraEntidad = convertirComputadoraDTOAEntidad(computadora);
            computadorasEntidad.add(computadoraEntidad);
        }
        softwareEntidad.setComputadoras(computadorasEntidad);

        return softwareEntidad;
    }

    // Método para convertir una lista de SoftwareEntidad a una lista de SoftwareDTO
    public List<SoftwareDTO> convertirListaSoftwareEntidadADTO(List<SoftwareEntidad> softwares) {
        List<SoftwareDTO> softwaresDTO = new ArrayList<>();
        if (softwares != null)
        {
            for (SoftwareEntidad softwareEntidad : softwares)
            {
                softwaresDTO.add(convertirSoftwareEntidadADTO(softwareEntidad));
            }
        }
        return softwaresDTO;
    }

    // Método para convertir una lista de SoftwareDTO a una lista de SoftwareEntidad
    public List<SoftwareEntidad> convertirListaSoftwareDTOAEntidad(List<SoftwareDTO> softwaresDTO) {
        List<SoftwareEntidad> softwaresEntidad = new ArrayList<>();
        if (softwaresDTO != null)
        {
            for (SoftwareDTO softwareDTO : softwaresDTO)
            {
                softwaresEntidad.add(convertirSoftwareDTOAEntidad(softwareDTO));
            }
        }
        return softwaresEntidad;
    }

    // Método para convertir ComputadoraEntidad a ComputadoraDTO
    public ComputadoraDTO convertirComputadoraEntidadADTO(ComputadoraEntidad computadora) {
        if (computadora == null)
        {
            return null;
        }

        // Crear un nuevo objeto ComputadoraDTO y convertir los campos
        ComputadoraDTO computadoraDTO = new ComputadoraDTO();
        computadoraDTO.setId(computadora.getId());
        computadoraDTO.setNombreAlumno(computadora.getNombreAlumno());
        computadoraDTO.setEstado(computadora.getEstado());
        computadoraDTO.setNumeroMaquina(computadora.getNumeroMaquina());
        computadoraDTO.setDireccionIP(computadora.getDireccionIP());
        computadoraDTO.setTipoCompu(computadora.getTipoCompu());

        // Convertir el CentroComputoEntidad a CentroComputoDTO
        CentroComputoDTO centroComputoDTO = convertirCentroComputoEntidadADTO(computadora.getCentroComputo());
        computadoraDTO.setCentroComputo(centroComputoDTO);

        // Convertir la lista de SoftwareEntidad a SoftwareDTO
        List<SoftwareDTO> softwareListDTO = new ArrayList<>();
        for (SoftwareEntidad softwareEntidad : computadora.getSoftwareList())
        {
            SoftwareDTO softwareDTO = convertirSoftwareEntidadADTO(softwareEntidad);
            softwareListDTO.add(softwareDTO);
        }
        computadoraDTO.setSoftwareList(softwareListDTO);

        return computadoraDTO;
    }

}
