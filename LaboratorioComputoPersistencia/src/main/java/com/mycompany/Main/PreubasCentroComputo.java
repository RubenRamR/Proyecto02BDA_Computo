/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.Main;

import DAOs.CentroComputoDAO;
import ENUM.Estado;
import ENUM.TipoCompu;
import Entidades.CarreraEntidad;
import Entidades.CentroComputoEntidad;
import Entidades.ComputadoraEntidad;
import Entidades.UnidadAcademicaEntidad;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
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
        INSERTAR CentroComputo
         */
//
        // Crea una instancia del DAO donde se encuentra el método insertarCentroComputo
        CentroComputoDAO centroComputoDAO = new CentroComputoDAO();

        // Crea una nueva entidad CentroComputo
        CentroComputoEntidad centroComputo = new CentroComputoEntidad();
        centroComputo.setNombre("Centro de Computo Principal");
        centroComputo.setContrasenaMaestra("tuContraseñaSegura");
        centroComputo.setHoraInicio(LocalTime.of(8, 2));
        centroComputo.setHoraFin(LocalTime.of(20, 0));
        
        // Establece la unidad académica (asegúrate de que exista en la base de datos)
        UnidadAcademicaEntidad unidadAcademica = new UnidadAcademicaEntidad();
        unidadAcademica.setId(1L);
        unidadAcademica.setNombre("ISW");
        centroComputo.setUnidadAcademica(unidadAcademica);

        try {
            // Inserta el centro de cómputo
            centroComputoDAO.insertarCentroComputo(centroComputo);
            System.out.println("Centro de cómputo insertado exitosamente.");
        } catch (PersistenceException e) {
            System.err.println("Error al insertar el centro de cómputo: " + e.getMessage());
        }

    }

}
