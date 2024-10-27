/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio;

import DAOs.ComputadoraDAO;
import DTOs.ComputadoraDTO;
import DTOs.SoftwareDTO;
import Entidades.ComputadoraEntidad;
import Entidades.SoftwareEntidad;
import InterfacesNegocio.IComputadoraNegocio;
import Negocio.Convertidores.Convertidores;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class ComputadoraNegocio implements IComputadoraNegocio {

      private ComputadoraDAO computadoraDAO;
    private Convertidores convertidor;

    public ComputadoraNegocio() {
        this.computadoraDAO = new ComputadoraDAO();
        this.convertidor = new Convertidores(); // Solo una inicialización necesaria
    }

    public ComputadoraNegocio(ComputadoraDAO computadoraDAO) {
        this.computadoraDAO = computadoraDAO;
        this.convertidor = new Convertidores(); // Inicializa convertidor también aquí
    }

    @Override
    public void insertarComputadora(ComputadoraDTO computadoraDTO) throws NegocioException {
        try {
            computadoraDAO.insertarComputadora(convertidor.convertirComputadoraDTOAEntidad(computadoraDTO));
        } catch (PersistenceException e) {
            throw new NegocioException("Error en la capa de negocio al insertar la computadora", e);
        }
    }

    @Override
    public void editarComputadora(ComputadoraDTO computadora) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Método no implementado
    }

    @Override
    public void eliminarComputadoraPorID(Long id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Método no implementado
    }

    @Override
    public ComputadoraDTO obtenerComputadoraPorID(Long id) throws NegocioException {
        try {
            ComputadoraEntidad computadoraEntidad = computadoraDAO.obtenerComputadoraPorID(id);
            if (computadoraEntidad == null) {
                throw new NegocioException("Computadora no encontrada");
            }
            return convertidor.convertirComputadoraEntidadADTO(computadoraEntidad);
        } catch (PersistenceException e) {
            throw new NegocioException("Error en la capa de negocio al obtener la computadora por id", e);
        }
    }

    @Override
    public List<ComputadoraDTO> obtenerTodasLasComputadora() throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<ComputadoraDTO> obtenerTodasLasComputadoras() throws NegocioException {
    try {
        List<ComputadoraEntidad> computadorasEntidad = computadoraDAO.obtenerTodasLasComputadoras();
        List<ComputadoraDTO> computadorasDTO = new ArrayList<>();

        for (ComputadoraEntidad computadora : computadorasEntidad) {
            ComputadoraDTO computadoraDTO = convertidor.convertirComputadoraEntidadADTO(computadora);
            computadorasDTO.add(computadoraDTO);
        }

        return computadorasDTO;
    } catch (PersistenceException e) {
        throw new NegocioException("Error al obtener las computadoras: " + e.getMessage(), e);
    }
}


    private Set<Long> processedIds = new HashSet<>();

    public ComputadoraDTO convertirComputadoraEntidadADTO(ComputadoraEntidad computadoraEntidad) {
        processedIds.clear();  // Limpia la lista al inicio de cada conversión
        return convertirComputadoraEntidadADTOInterno(computadoraEntidad);
    }

    private ComputadoraDTO convertirComputadoraEntidadADTOInterno(ComputadoraEntidad computadoraEntidad) {
        if (computadoraEntidad == null || processedIds.contains(computadoraEntidad.getId()))
        {
            return null;
        }

        // Marca este ID como procesado
        processedIds.add(computadoraEntidad.getId());

        ComputadoraDTO computadoraDTO = new ComputadoraDTO();
        computadoraDTO.setId(computadoraEntidad.getId());
        computadoraDTO.setNombreAlumno(computadoraEntidad.getNombreAlumno());
        computadoraDTO.setEstado(computadoraEntidad.getEstado());
        computadoraDTO.setNumeroMaquina(computadoraEntidad.getNumeroMaquina());
        computadoraDTO.setDireccionIP(computadoraEntidad.getDireccionIP());
        computadoraDTO.setTipoCompu(computadoraEntidad.getTipoCompu());

        // Convierte software asociado evitando referencia circular
        List<SoftwareDTO> softwareDTOList = new ArrayList<>();
        for (SoftwareEntidad software : computadoraEntidad.getSoftwareList())
        {
            SoftwareDTO softwareDTO = convertirSoftwareEntidadADTOInterno(software);
            softwareDTOList.add(softwareDTO);
        }
        computadoraDTO.setSoftwareList(softwareDTOList);

        return computadoraDTO;
    }

    public SoftwareDTO convertirSoftwareEntidadADTO(SoftwareEntidad softwareEntidad) {
        processedIds.clear();  // Limpia la lista al inicio de cada conversión
        return convertirSoftwareEntidadADTOInterno(softwareEntidad);
    }

    private SoftwareDTO convertirSoftwareEntidadADTOInterno(SoftwareEntidad softwareEntidad) {
        if (softwareEntidad == null || processedIds.contains(softwareEntidad.getId()))
        {
            return null;
        }

        // Marca este ID como procesado
        processedIds.add(softwareEntidad.getId());

        SoftwareDTO softwareDTO = new SoftwareDTO();
        softwareDTO.setId(softwareEntidad.getId());
        softwareDTO.setNombre(softwareEntidad.getNombre());

        List<ComputadoraDTO> computadorasDTO = new ArrayList<>();
        for (ComputadoraEntidad computadora : softwareEntidad.getComputadoras())
        {
            ComputadoraDTO computadoraDTO = convertirComputadoraEntidadADTOInterno(computadora);
            computadorasDTO.add(computadoraDTO);
        }
        softwareDTO.setComputadoras(computadorasDTO);

        return softwareDTO;
    }

}
