/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.Main;

import DAOs.ComputadoraDAO;
import DTOs.ComputadoraDTO;
import DTOs.SoftwareDTO;
import ENUM_P.Estado;
import ENUM_P.TipoCompu;
import Entidades.CentroComputoEntidad;
import Entidades.ComputadoraEntidad;
import Entidades.SoftwareEntidad;
import Negocio.ComputadoraNegocio;
import Negocio.Convertidores.Convertidores;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class PruebaEstudiante {

    public static void main(String[] args) {
        try
        {
            ComputadoraDAO computadoraDAO = new ComputadoraDAO();

            // Crear y configurar una instancia de ComputadoraEntidad
            ComputadoraEntidad computadoraEntidad = new ComputadoraEntidad();
            computadoraEntidad.setNombreAlumno("Juan Pérez");
            computadoraEntidad.setEstado(Estado.DISPONIBLE); // Enum Estado debe incluir DISPONIBLE
            computadoraEntidad.setNumeroMaquina(101);
            computadoraEntidad.setDireccionIP("192.16768.0.112.220"); // Dirección IP corregida
            computadoraEntidad.setTipoCompu(TipoCompu.ADMIN); // Enum TipoCompu debe incluir ADMIN

            // Crear y configurar CentroComputoEntidad antes de asignarlo a ComputadoraEntidad
            CentroComputoEntidad centroComputo = new CentroComputoEntidad();
            centroComputo.setId(1L); // Usa un ID existente en la base de datos
            computadoraEntidad.setCentroComputo(centroComputo); // Asigna CentroComputo

            // Inserta la computadora en la base de datos
            computadoraDAO.insertarComputadora(computadoraEntidad);

            ComputadoraNegocio cn = new ComputadoraNegocio();

            // Prueba obtenerComputadoraPorID usando el ID de la computadora recién insertada
            ComputadoraDTO computadoraDTO = cn.obtenerComputadoraPorID(computadoraEntidad.getId());

            if (computadoraDTO != null)
            {
                System.out.println("Computadora obtenida:");
                System.out.println("ID: " + computadoraDTO.getId());
                System.out.println("Nombre del Alumno: " + computadoraDTO.getNombreAlumno());
                System.out.println("Estado: " + computadoraDTO.getEstado());
                System.out.println("Número de Máquina: " + computadoraDTO.getNumeroMaquina());
                System.out.println("Dirección IP: " + computadoraDTO.getDireccionIP());
                System.out.println("Tipo de Computadora: " + computadoraDTO.getTipoCompu());
                // Verifica que CentroComputo no sea null antes de acceder a sus métodos
                if (computadoraDTO.getCentroComputo() != null)
                {
                    System.out.println("Centro de Cómputo: " + computadoraDTO.getCentroComputo().getId());
                } else
                {
                    System.out.println("Centro de Cómputo no asignado.");
                }
            } else
            {
                System.out.println("La computadora con el ID especificado no fue encontrada.");
            }

        } catch (NegocioException e)
        {
            System.err.println("Error al obtener la computadora: " + e.getMessage());
            e.printStackTrace();
        } catch (PersistenceException e)
        {
            System.err.println("Error de persistencia: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e)
        {
            System.err.println("Se produjo un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
