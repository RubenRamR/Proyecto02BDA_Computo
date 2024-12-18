/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.Main;

import DAOs.CarreraDAO;
import DAOs.EstudianteDAO;
import ENUM_P.Estatus;
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
/*
LISTO
 */
public class PruebasEstudiante {

    public static void main(String[] args) {

        /*
        INSERTAR
         */
//// Instancia del DAO de Estudiante SI
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//
//        // Crear una entidad de Carrera (ejemplo)
//        CarreraEntidad carrera = new CarreraEntidad("Ingeniería en Computación", 20);
//
//        // Crear una lista de bloqueos (si es necesario)
//        List<BloqueoEntidad> bloqueos = new ArrayList<>();
//
//        // Crear un estudiante con datos ficticios
//        EstudianteEntidad estudiante = new EstudianteEntidad();
//        estudiante.setNombre("ruben");
//        estudiante.setApPaterno("Pérez");
//        estudiante.setApMaterno("García");
//        estudiante.setEstatus(Estatus.INSCRITO);  // Enum con estatus de estudiante
//        estudiante.setContrasena("password123");
//        estudiante.setCarrera(carrera);
//        estudiante.setBloqueos(bloqueos);  // Por ahora no hay bloqueos, lista vacía
//
//        try
//        {
//            // Insertar el estudiante en la base de datos
//            estudianteDAO.insertarEstudiante(estudiante);
//            System.out.println("Estudiante insertado correctamente: " + estudiante);
//        } catch (PersistenceException e)
//        {
//            System.err.println("Error al insertar el estudiante: " + e.getMessage());
//        }
//        /*
//        Actualizar
//         */
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//
//// Crear un objeto EstudianteEntidad con los datos actualizados
//        EstudianteEntidad estudianteActualizado = new EstudianteEntidad();
//        estudianteActualizado.setId(1L); // ID del estudiante que quieres actualizar
//        estudianteActualizado.setNombre("NuevoNombre");
//        estudianteActualizado.setApPaterno("NuevoApPaterno");
//        estudianteActualizado.setApMaterno("NuevoApMaterno");
//        estudianteActualizado.setEstatus(Estatus.DESINSCRICTO);
//        estudianteActualizado.setContrasena("nuevaContrasena123");
//        CarreraEntidad nuevaCarrera = new CarreraEntidad();
//        nuevaCarrera.setId(1L); // Asumiendo que la carrera con ID 2 existe
//        estudianteActualizado.setCarrera(nuevaCarrera);
//
//// Llamar al método para actualizar el estudiante
//        try
//        {
//            estudianteDAO.editarEstudiante(estudianteActualizado);
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//
//
//        /*
//        Eliminar
//         */
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//
//        try {
//            Long idd = 1L;
//            estudianteDAO.eliminarEstudiantePorID(idd);
//        } catch (PersistenceException e) {
//            e.printStackTrace();
//        }
//
//        /*
//        Obtener por id
//         */
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//
//        // Obtener el estudiante por su ID
//        Long estudianteId = 2L; // Obtener el ID del estudiante guardado
//        EstudianteEntidad estudiante = estudianteDAO.obtenerEstudiantePorID(estudianteId);        

        /*
        Obtener Todos
         */
//        EstudianteDAO estudianteDAO = new EstudianteDAO();
//        System.out.println(estudianteDAO.obtenerTodosLosEstudiantes());
        /*
*
*       CARRERA
*
         */
//        // Crear una instancia de CarreraDAO
//        CarreraDAO carreraDAO = new CarreraDAO();
//
//        // Nombre de la carrera a buscar
//        String nombreCarrera = "Ingeniería en Computación"; // Cambia este valor según lo que tengas en la base de datos
//
//        // Llamar al método para obtener la carrera por nombre
//        CarreraEntidad carreraEncontrada = carreraDAO.obtenerCarreraPorNombre(nombreCarrera);
//
//        if (carreraEncontrada != null)
//        {
//            System.out.println("Carrera encontrada: " + carreraEncontrada);
//            // Acceder a la lista de estudiantes
//            for (EstudianteEntidad estudiante : carreraEncontrada.getEstudiantes())
//            {
//                System.out.println("Estudiante: " + estudiante);
//            }
//        } else
//        {
//            System.out.println("No se encontró la carrera con el nombre: " + nombreCarrera);
//        }

    }
}
