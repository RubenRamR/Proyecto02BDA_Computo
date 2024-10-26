/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.Main;

import DAOs.CentroComputoDAO;
import DAOs.ComputadoraDAO;
import ENUM_P.Estado;
import ENUM_P.TipoCompu;
import Entidades.CarreraEntidad;
import Entidades.CentroComputoEntidad;
import Entidades.ComputadoraEntidad;
import Entidades.SoftwareEntidad;
import Entidades.UnidadAcademicaEntidad;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class PreubasCentroComputo {

    /**
     * @param args the command line arguments
     */
    /*
    LISTO
    */
    public static void main(String[] args) {

        /*
         * INSERTAR Computadora con relaciones
         */
//        // Instancia del DAO
//        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
//
//        // Crear la entidad UnidadAcademicaEntidad
//        UnidadAcademicaEntidad unidadAcademica = new UnidadAcademicaEntidad();
//        unidadAcademica.setNombre("Facultad de Ingeniería");
//
//        // Crear la entidad CentroComputoEntidad
//        CentroComputoEntidad centroComputo = new CentroComputoEntidad();
//        centroComputo.setNombre("Centro de Cómputo Principal");
//        centroComputo.setContrasenaMaestra("password123");
//        centroComputo.setHoraInicio(LocalTime.of(8, 0));  // Hora de inicio: 08:00 AM
//        centroComputo.setHoraFin(LocalTime.of(20, 0));    // Hora de fin: 08:00 PM
//        centroComputo.setUnidadAcademica(unidadAcademica); // Asignar la unidad académica
//
//        // Crear algunas Computadoras para este Centro de Cómputo
//        ComputadoraEntidad computadora1 = new ComputadoraEntidad();
//        computadora1.setNombreAlumno("Juan Pérez");
//        computadora1.setEstado(Estado.DISPONIBLE);  // Usar una enumeración "DISPONIBLE" o "OCUPADA"
//        computadora1.setNumeroMaquina(1);
//        computadora1.setDireccionIP("192.168.122.1");
//        computadora1.setTipoCompu(TipoCompu.ADMIN);  // Usar la enumeración correspondiente
//
//        ComputadoraEntidad computadora2 = new ComputadoraEntidad();
//        computadora2.setNombreAlumno("Ana López");
//        computadora2.setEstado(Estado.DISPONIBLE);  // Usar una enumeración "OCUPADA"
//        computadora2.setNumeroMaquina(2);
//        computadora2.setDireccionIP("192.168.1.123");
//        computadora2.setTipoCompu(TipoCompu.MAESTRO);  // Usar la enumeración correspondiente
//
//        // Asignar el centro de cómputo a las computadoras
//        computadora1.setCentroComputo(centroComputo);
//        computadora2.setCentroComputo(centroComputo);
//
//        // Añadir las computadoras a la lista de computadoras del Centro de Cómputo
//        centroComputo.getComputadoras().add(computadora1);
//        centroComputo.getComputadoras().add(computadora2);
//
//        // Crear algunos software instalados
//        SoftwareEntidad software1 = new SoftwareEntidad();
//        software1.setNombre("Microsoft Office");
//
//        SoftwareEntidad software2 = new SoftwareEntidad();
//        software2.setNombre("Eclipse IDE");
//
//        // Asignar software a las computadoras
//        computadora1.getSoftwareList().add(software1);
//        computadora1.getSoftwareList().add(software2);
//
//        computadora2.getSoftwareList().add(software1);
//
//        // Ahora, prueba a insertar el centro de cómputo junto con las computadoras y software
//        try
//        {
//            centroComputoDAO.insertarCentroComputo(centroComputo);
//            System.out.println("Centro de cómputo insertado exitosamente.");
//        } catch (PersistenceException e)
//        {
//            System.err.println("Error durante la inserción: " + e.getMessage());
//        }
//
        /*
        EDITAR CENTROCOMPUTO
        */
//         // Crear una instancia del DAO
//        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
//
//        // Supongamos que ya tenemos un centro de cómputo existente con id = 1
//        Long centroComputoId = 1L;
//
//        // Primero, debes obtener el Centro de Cómputo existente para editarlo
//        CentroComputoEntidad centroComputoExistente = centroComputoDAO.obtenerCentroComputoPorID(centroComputoId);
//        
//        if (centroComputoExistente != null) {
//            // Realiza cambios en los atributos que deseas editar
//            centroComputoExistente.setNombre("Centro de Cómputo Actualizado");
//            centroComputoExistente.setContrasenaMaestra("nuevaContrasena123");
//            centroComputoExistente.setHoraInicio(LocalTime.of(9, 0));  // Cambiar hora de inicio
//            centroComputoExistente.setHoraFin(LocalTime.of(21, 0));    // Cambiar hora de fin
//            
//            // Editar el centro de cómputo
//            try {
//                centroComputoDAO.editarCentroComputo(centroComputoExistente);
//                System.out.println("Centro de cómputo editado exitosamente.");
//            } catch (PersistenceException e) {
//                System.err.println("Error durante la edición: " + e.getMessage());
//            }
//        } else {
//            System.err.println("No se encontró el centro de cómputo con ID: " + centroComputoId);
//        }
        /*
        ELIMINAR CENTROCOMPUTO    
        */
//        // Crear una instancia del DAO
//        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
//
//        // Especificar el ID del centro de cómputo que deseas eliminar
//        Long centroComputoId = 1L; // Cambia este ID por el que deseas eliminar
//
//        // Intentar eliminar el centro de cómputo
//        try {
//            centroComputoDAO.eliminarCentroComputoPorID(centroComputoId);
//            System.out.println("Centro de cómputo eliminado exitosamente.");
//        } catch (PersistenceException e) {
//            System.err.println("Error durante la eliminación: " + e.getMessage());
//        }
        /*
        METODOS OBTENER    
        */
//        // Crear una instancia del DAO
//        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();
//
//        // Probar el método obtenerCentroComputoPorID
//        Long centroComputoId = 4L; // Cambia este ID por uno existente en la base de datos
//        try {
//            CentroComputoEntidad centroComputo = centroComputoDAO.obtenerCentroComputoPorID(centroComputoId);
//            if (centroComputo != null) {
//                System.out.println("Centro de cómputo encontrado: " + centroComputo);
//            } else {
//                System.out.println("No se encontró un centro de cómputo con ID: " + centroComputoId);
//            }
//        } catch (PersistenceException e) {
//            System.err.println("Error durante la obtención del centro de cómputo: " + e.getMessage());
//        }
//
//        // Probar el método obtenerTodosLosCentroComputo
//        try {
//            List<CentroComputoEntidad> centrosComputo = centroComputoDAO.obtenerTodosLosCentroComputo();
//            System.out.println("Centros de cómputo encontrados: ");
//            for (CentroComputoEntidad cc : centrosComputo) {
//                System.out.println(cc);
//            }
//        } catch (PersistenceException e) {
//            System.err.println("Error durante la obtención de todos los centros de cómputo: " + e.getMessage());
//        }
    }

}
