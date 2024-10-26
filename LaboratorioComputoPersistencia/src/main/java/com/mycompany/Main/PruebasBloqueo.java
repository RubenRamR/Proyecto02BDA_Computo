/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.Main;

import DAOs.BloqueoDAO;
import DAOs.EstudianteDAO;
import ENUM_P.Estatus;
import Entidades.BloqueoEntidad;
import Entidades.CarreraEntidad;
import Entidades.EstudianteEntidad;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class PruebasBloqueo {

    /**
     * @param args the command line arguments
     */
    /*
    LISTO
     */
    public static void main(String[] args) {

        /*
        INSERTAR BLOQUEO
         */
//        // Crear instancias de las entidades
//        EstudianteEntidad estudiante = new EstudianteEntidad(
//                "Juan", // nombre
//                "Pérez", // apPaterno
//                "García", // apMaterno
//                Estatus.INSCRITO, // estatus
//                "contrasena123", // contrasena
//                new CarreraEntidad("ISW", 250), // carrera (puedes inicializarla más adelante si es necesario)
//                new ArrayList<>() // bloqueos (inicializado como lista vacía)
//        );
//
//        // Insertar el estudiante en la base de datos para obtener su ID
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//        try
//        {
//            estudianteDAO.insertarEstudiante(estudiante);  // Asegúrate de tener este método en EstudianteDAO
//        } catch (PersistenceException e)
//        {
//            System.err.println("Error al insertar el estudiante: " + e.getMessage());
//            return; // Salir si hay un error al insertar el estudiante
//        }
//
//        // Crear un BloqueoEntidad
//        BloqueoEntidad bloqueo = new BloqueoEntidad();
//        bloqueo.setFechaBloqueo(LocalDateTime.now()); // Asegúrate de que tengas un setter para la fecha
//        bloqueo.setFechaLiberacion(LocalDateTime.now().plusDays(8)); // 8 días después
//        bloqueo.setMotivo("Falta de asistencia");
//
//        // Insertar el bloqueo en la base de datos
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//        try
//        {
//            // Usa el ID del estudiante recién insertado
//            bloqueoDAO.insertarBloqueo(bloqueo, 3L);
//            System.out.println("Bloqueo insertado correctamente para el estudiante con ID: " + estudiante.getId());
//        } catch (PersistenceException e)
//        {
//            System.err.println("Error al insertar el bloqueo: " + e.getMessage());
//        }
//
        //
        /*
        EDITAR BLOQUEO
         */
//// Crear una instancia de BloqueoEntidad con los nuevos valores
//        BloqueoEntidad bloqueoEditar = new BloqueoEntidad();
//        bloqueoEditar.setId(1L); // ID del bloqueo que deseas editar
//        bloqueoEditar.setFechaBloqueo(LocalDateTime.now());
//        bloqueoEditar.setFechaLiberacion(LocalDateTime.now().plusDays(10));
//        bloqueoEditar.setMotivo("Asistencia mejorada");
//
//// Llamar al método editarBloqueo
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//        bloqueoDAO.editarBloqueo(bloqueoEditar);
//
        /*
        ELIMINAR BLOQUEO
         */
//        // ID del bloqueo que deseas eliminar
//        Long idBloqueo = 2L;
//
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//        bloqueoDAO.eliminarBloqueoPorID(idBloqueo);
//
        /*
        OBTENER POR ID
         */
//        // ID del bloqueo que deseas obtener
//        Long idBloqueo = 3L;
//
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//        try
//        {
//            BloqueoEntidad bloqueo = bloqueoDAO.obtenerBloqueoPorID(idBloqueo);
//            System.out.println("Bloqueo encontrado: " + bloqueo);
//        } catch (PersistenceException e)
//        {
//            System.err.println(e.getMessage());
//        }
        /*
        OBTENER TODOS
         */
        //
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//        try
//        {
//            List<BloqueoEntidad> bloqueos = bloqueoDAO.obtenerTodosLosBloqueos();
//            for (BloqueoEntidad bloqueo : bloqueos)
//            {
//                System.out.println("Bloqueo: " + bloqueo);
//            }
//        } catch (PersistenceException e)
//        {
//            System.err.println(e.getMessage());
//        }
        /*
        OBTENER BLOQUEO POR ID ESTUDIANTE
         */
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//        System.out.println(bloqueoDAO.obtenerBloqueoPorIdEstudiante(3L));
    }

}
