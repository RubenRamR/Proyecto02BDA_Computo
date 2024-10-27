/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.Main;

import DAOs.CentroComputoDAO;
import DAOs.ComputadoraDAO;
import ENUM_P.Estado;
import ENUM_P.TipoCompu;
import Entidades.CentroComputoEntidad;
import Entidades.ComputadoraEntidad;
import Entidades.SoftwareEntidad;
import Entidades.UnidadAcademicaEntidad;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class PruebaComputadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /* 1
        INSERTAR
         */
        ComputadoraDAO computadoraDAO = new ComputadoraDAO();
        
        /*
        Insert centro para computadora
        */
        // Instancia del DAO
        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();

        // Crear la entidad UnidadAcademicaEntidad
        UnidadAcademicaEntidad unidadAcademica = new UnidadAcademicaEntidad();
        unidadAcademica.setNombre("ITSON CENTRO");

        // Crear la entidad CentroComputoEntidad
        CentroComputoEntidad centroComputo = new CentroComputoEntidad();
        centroComputo.setNombre("Centro de Cómputo Principal");
        centroComputo.setContrasenaMaestra("password123");
        centroComputo.setHoraInicio(LocalTime.of(8, 0));  // Hora de inicio: 08:00 AM
        centroComputo.setHoraFin(LocalTime.of(20, 0));    // Hora de fin: 08:00 PM
        centroComputo.setUnidadAcademica(unidadAcademica); // Asignar la unidad académica

        // Crear algunas Computadoras para este Centro de Cómputo
        ComputadoraEntidad computadora1 = new ComputadoraEntidad();
        computadora1.setNombreAlumno("Juan Pérez");
        computadora1.setEstado(Estado.DISPONIBLE);  // Usar una enumeración "DISPONIBLE" o "OCUPADA"
        computadora1.setNumeroMaquina(1);
        computadora1.setDireccionIP("192.168.12342.1");
        computadora1.setTipoCompu(TipoCompu.ADMIN);  // Usar la enumeración correspondiente

        ComputadoraEntidad computadora2 = new ComputadoraEntidad();
        computadora2.setNombreAlumno("Ana López");
        computadora2.setEstado(Estado.DISPONIBLE);  // Usar una enumeración "OCUPADA"
        computadora2.setNumeroMaquina(2);
        computadora2.setDireccionIP("192.162318.1.123");
        computadora2.setTipoCompu(TipoCompu.MAESTRO);  // Usar la enumeración correspondiente

        // Asignar el centro de cómputo a las computadoras
        computadora1.setCentroComputo(centroComputo);
        computadora2.setCentroComputo(centroComputo);

        // Añadir las computadoras a la lista de computadoras del Centro de Cómputo
        centroComputo.getComputadoras().add(computadora1);
        centroComputo.getComputadoras().add(computadora2);

        // Crear algunos software instalados
        SoftwareEntidad software1 = new SoftwareEntidad();
        software1.setNombre("Microsoft Office");

        SoftwareEntidad software2 = new SoftwareEntidad();
        software2.setNombre("Eclipse IDE");

        // Asignar software a las computadoras
        computadora1.getSoftwareList().add(software1);
        computadora1.getSoftwareList().add(software2);

        computadora2.getSoftwareList().add(software1);

        // Ahora, prueba a insertar el centro de cómputo junto con las computadoras y software
        try
        {
            centroComputoDAO.insertarCentroComputo(centroComputo);
            System.out.println("Centro de cómputo insertado exitosamente.");
        } catch (PersistenceException e)
        {
            System.err.println("Error durante la inserción: " + e.getMessage());
        }

        
        try {
            // Persistir el centro de cómputo antes de usarlo (esto es un ejemplo, ajusta según tu flujo)
            // entityManager.persist(centroComputo); // Esto debe hacerse en el DAO o en otro método

            // Crear una nueva computadora para insertar
            ComputadoraEntidad nuevaComputadora = new ComputadoraEntidad();
            nuevaComputadora.setNombreAlumno("Juan Perez");
            nuevaComputadora.setEstado(Estado.DISPONIBLE);
            nuevaComputadora.setNumeroMaquina(101);
            nuevaComputadora.setDireccionIP("192.168.1.111510");
            nuevaComputadora.setTipoCompu(TipoCompu.ESTUDIANTE);
            nuevaComputadora.setCentroComputo(centroComputo); // Establecer la relación con CentroComputo

            // Insertar nueva computadora
            computadoraDAO.insertarComputadora(nuevaComputadora);
        } catch (PersistenceException e) {
            System.err.println("Error en la operación de la base de datos: " + e.getMessage());
        }

        /*
        ACTUALIZAR COMPUTADORA    
         */
//        ComputadoraDAO computadoraDAO = new ComputadoraDAO();
//
//        try
//        {
//            // Obtener todas las computadoras
//            List<ComputadoraEntidad> computadoras = computadoraDAO.obtenerTodasLasComputadora();
//            if (computadoras.isEmpty())
//            {
//                System.out.println("No hay computadoras para editar.");
//                return;
//            }
//
//            // Seleccionar la primera computadora para editar
//            ComputadoraEntidad computadoraAEditar = computadoras.get(0);
//            System.out.println("Computadora a editar: ID " + computadoraAEditar.getId() + ", Alumno: " + computadoraAEditar.getNombreAlumno());
//
//            // Modificar los atributos de la computadora
//            computadoraAEditar.setNombreAlumno("Nuevo Nombre"); // Cambia el nombre del alumno
//            computadoraAEditar.setEstado(Estado.DISPONIBLE); // Cambia el estado de la computadora
//            computadoraAEditar.setNumeroMaquina(202); // Cambia el número de máquina
//            computadoraAEditar.setDireccionIP("192.168.1.30"); // Cambia la dirección IP
//
//            // Llamar al método para editar
//            computadoraDAO.editarComputadora(computadoraAEditar);
//            System.out.println("Computadora editada: ID " + computadoraAEditar.getId());
//
//            // Mostrar la computadora editada
//            ComputadoraEntidad computadoraEditada = computadoraDAO.obtenerComputadoraPorID(computadoraAEditar.getId());
//            System.out.println("Computadora después de editar: ID " + computadoraEditada.getId() + ", Alumno: " + computadoraEditada.getNombreAlumno());
//
//        } catch (PersistenceException e)
//        {
//            System.err.println("Error en la operación de la base de datos: " + e.getMessage());
//        } catch (Exception e)
//        {
//            System.err.println("Error inesperado: " + e.getMessage());
//        }
    }

}
