/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.Main;

import DAOs.BloqueoDAO;
import DAOs.EstudianteDAO;
import ENUM.Estatus;
import Entidades.BloqueoEntidad;
import Entidades.CarreraEntidad;
import Entidades.EstudianteEntidad;
import java.time.LocalDateTime;
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
    public static void main(String[] args) {

        /*
        INSERTAR BLOQUEO
         */
//                // Crear un objeto DAO para estudiantes
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//
//        // Crear una carrera para el estudiante
//        CarreraEntidad carrera = new CarreraEntidad("Ingeniería en Sistemas", 2);
//
//        // Crear un estudiante
//        EstudianteEntidad estudiante = new EstudianteEntidad();
//        estudiante.setNombre("Boooooooooooooooooooob");
//        estudiante.setApPaterno("Pérez");
//        estudiante.setApMaterno("Gómez");
//        estudiante.setEstatus(Estatus.INSCRITO);
//        estudiante.setContrasena("banban");
//        estudiante.setCarrera(carrera);
//
//        // Persistir el estudiante en la base de datos
//        try
//        {
//            estudianteDAO.insertarEstudiante(estudiante);
//        } catch (PersistenceException e)
//        {
//            System.err.println("Error al agregar el estudiante: " + e.getMessage());
//            e.printStackTrace(); // Imprimir la traza de la pila para más detalles
//        } catch (Exception e)
//        {
//            System.err.println("Error inesperado: " + e.getMessage());
//            e.printStackTrace(); // Imprimir la traza de la pila para más detalles
//        } finally
//        {
//            estudianteDAO.cerrar(); // Asegúrate de tener un método para cerrar la conexión
//        }
//
//
//        // Crear una instancia del DAO para Bloqueo
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//        
//        // Crear un nuevo bloqueo
//        BloqueoEntidad nuevoBloqueo = new BloqueoEntidad(
//            LocalDateTime.now().plusDays(7), // fechaLiberacion
//            LocalDateTime.now(),              // fechaBloqueo
//            "Incumplimiento de normas",       // motivo
//            estudiante                         // estudiante relacionado
//        );
//
//        try {
//            // Insertar el nuevo bloqueo en la base de datos
//            bloqueoDAO.insertarBloqueo(nuevoBloqueo);
//            System.out.println("Bloqueo insertado exitosamente.");
//        } catch (PersistenceException e) {
//            System.err.println("Error al insertar el bloqueo: " + e.getMessage());
//        }

        /*
        EDITAR BLOQUEO
         */
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//
//        // Crear o recuperar un bloqueo que deseas editar
//        BloqueoEntidad bloqueo = new BloqueoEntidad();
//        bloqueo.setId(3L); // ID del bloqueo existente
//        bloqueo.setFechaLiberacion(LocalDateTime.now().plusDays(1)); // Nueva fecha de liberación
//        bloqueo.setFechaBloqueo(LocalDateTime.now()); // Nueva fecha de bloqueo
//        bloqueo.setMotivo("Incumplimiento de normas actualizadaaaa");
//
//        try
//        {
//            bloqueoDAO.editarBloqueo(bloqueo);
//            System.out.println("Bloqueo editado exitosamente.");
//        } catch (PersistenceException e)
//        {
//            System.err.println("Error al editar el bloqueo: " + e.getMessage());
//        }
//
        /*
        ELIMINAR BLOQUEO
         */
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//
//        try
//        {
//            // ID del bloqueo que deseas eliminar
//            Long bloqueoId = 2L;
//
//            // Llamar al método para eliminar el bloqueo por ID
//            bloqueoDAO.eliminarBloqueoPorID(bloqueoId);
//            System.out.println("Bloqueo con ID " + bloqueoId + " eliminado correctamente.");
//        } catch (PersistenceException e)
//        {
//            System.out.println("Error al eliminar el bloqueo: " + e.getMessage());
//        }
        /*
        OBTENER POR ID
         */
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//
//        try
//        {
//            Long bloqueoId = 3L;
//
//            BloqueoEntidad bloqueo = bloqueoDAO.obtenerBloqueoPorID(bloqueoId);
//            System.out.println("Bloqueo encontrado: " + bloqueo);
//
//        } catch (PersistenceException e)
//        {
//            System.out.println("Error al obtener el bloqueo: " + e.getMessage());
//        }

        /*
        OBTENER TODOS
         */
//        BloqueoDAO bloqueoDAO = new BloqueoDAO();
//
//        try
//        {
//            // Llamar al método para obtener todos los bloqueos
//            List<BloqueoEntidad> bloqueos = bloqueoDAO.obtenerTodosLosBloqueos();
//
//            // Imprimir los bloqueos obtenidos
//            for (BloqueoEntidad bloqueo : bloqueos)
//            {
//                System.out.println(bloqueo);
//            }
//
//        } catch (PersistenceException e)
//        {
//            System.out.println("Error al obtener los bloqueos: " + e.getMessage());
//        }
    }

}
