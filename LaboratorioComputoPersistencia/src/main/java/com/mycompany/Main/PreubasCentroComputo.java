/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.Main;

import DAOs.CentroComputoDAO;
import DAOs.ComputadoraDAO;
import ENUM.Estado;
import ENUM.TipoCompu;
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
    public static void main(String[] args) {

        /*
         * INSERTAR Computadora con Software
         */
        // Instancia del DAO
        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();

        // Crear la entidad UnidadAcademicaEntidad
        UnidadAcademicaEntidad unidadAcademica = new UnidadAcademicaEntidad();
        unidadAcademica.setNombre("Facultad de Ingeniería");

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
        computadora1.setDireccionIP("192.168.1.10");
        computadora1.setTipoCompu(TipoCompu.ADMIN);  // Usar la enumeración correspondiente

        ComputadoraEntidad computadora2 = new ComputadoraEntidad();
        computadora2.setNombreAlumno("Ana López");
        computadora2.setEstado(Estado.DISPONIBLE);  // Usar una enumeración "OCUPADA"
        computadora2.setNumeroMaquina(2);
        computadora2.setDireccionIP("192.168.1.11");
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
    }

}
