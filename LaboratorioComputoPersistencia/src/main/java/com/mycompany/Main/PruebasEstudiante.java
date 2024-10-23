/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.Main;

import DAOs.EstudianteDAO;
import ENUM.Estatus;
import Entidades.BloqueoEntidad;
import Entidades.CarreraEntidad;
import Entidades.EstudianteEntidad;
import static com.mysql.cj.conf.PropertyKey.logger;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import static javax.persistence.Persistence.createEntityManagerFactory;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class PruebasEstudiante {

    public static void main(String[] args) {

//        /*
//        INSERTAR
//         */
// Instancia del DAO de Estudiante
        EstudianteDAO estudianteDAO = new EstudianteDAO();

        // Crear una entidad de Carrera (ejemplo)
        CarreraEntidad carrera = new CarreraEntidad("Ingeniería en Computación", 20);

        // Crear una lista de bloqueos (si es necesario)
        List<BloqueoEntidad> bloqueos = new ArrayList<>();

        // Crear un estudiante con datos ficticios
        EstudianteEntidad estudiante = new EstudianteEntidad();
        estudiante.setNombre("ruben");
        estudiante.setApPaterno("Pérez");
        estudiante.setApMaterno("García");
        estudiante.setEstatus(Estatus.INSCRITO);  // Enum con estatus de estudiante
        estudiante.setContrasena("password123");
        estudiante.setCarrera(carrera);
        estudiante.setBloqueos(bloqueos);  // Por ahora no hay bloqueos, lista vacía

        try
        {
            // Insertar el estudiante en la base de datos
            estudianteDAO.insertarEstudiante(estudiante);
            System.out.println("Estudiante insertado correctamente: " + estudiante);
        } catch (PersistenceException e)
        {
            System.err.println("Error al insertar el estudiante: " + e.getMessage());
        }
        /*
        Actualizar
         */
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//        // Crear una nueva carrera
//        CarreraEntidad carrera = new CarreraEntidad("Ingeniería de Software", 4);
//
//        // Crear un nuevo estudiante
//        EstudianteEntidad nuevoEstudiante = new EstudianteEntidad();
//        nuevoEstudiante.setNombre("Ruben");
//        nuevoEstudiante.setApPaterno("Pérez");
//        nuevoEstudiante.setApMaterno("García");
//        nuevoEstudiante.setEstatus(Estatus.INSCRITO);
//        nuevoEstudiante.setContrasena("123");
//        nuevoEstudiante.setCarrera(carrera);
//
//        // Guardar el nuevo estudiante
//        try
//        {
//            estudianteDAO.insertarEstudiante(nuevoEstudiante);
//            System.out.println("Estudiante guardado: " + nuevoEstudiante.getNombre());
//        } catch (PersistenceException e)
//        {
//            e.printStackTrace();
//        }
//
//        // Ahora vamos a editar el estudiante
//        nuevoEstudiante.setNombre("Juan Carlos");
//        nuevoEstudiante.setEstatus(Estatus.DESINSCRICTO);
//
//        try
//        {
//            estudianteDAO.editarEstudiante(nuevoEstudiante);
//            System.out.println("Estudiante editado: " + nuevoEstudiante.getNombre());
//        } catch (PersistenceException e)
//        {
//            e.printStackTrace();
//        }
//
        /*
        Eliminar
         */
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//
//        // Eliminar el estudiante por su ID
//        try {
//            Long idd = 2L;
//            estudianteDAO.eliminarEstudiantePorID(idd);
//        } catch (PersistenceException e) {
//            e.printStackTrace();
//        }
//
        /*
        Obtener por id
         */
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//
//        // Obtener el estudiante por su ID
//        Long estudianteId = 3L; // Obtener el ID del estudiante guardado
//        EstudianteEntidad estudiante = estudianteDAO.obtenerEstudiantePorID(estudianteId);        
//
        /*
        Obtener Todos
         */
    }
}
