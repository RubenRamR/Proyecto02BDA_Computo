/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.Main;

import DAOs.ReservaDAO;
import ENUM.Estado;
import ENUM.Estatus;
import ENUM.TipoCompu;
import Entidades.CarreraEntidad;
import Entidades.CentroComputoEntidad;
import Entidades.ComputadoraEntidad;
import Entidades.EstudianteEntidad;
import Entidades.ReservaEntidad;
import Entidades.UnidadAcademicaEntidad;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rramirez
 */
public class PruebasReserva {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear instancia de ReservaDAO
        ReservaDAO reservaDAO = new ReservaDAO();

        // Crear una nueva reserva
        ReservaEntidad nuevaReserva = new ReservaEntidad();
        nuevaReserva.setFechaHoraInicio(LocalDateTime.now());
        nuevaReserva.setFechaHoraFin(LocalDateTime.now().plusHours(2)); // Reserva por 2 horas

        // Asumimos que se crean entidades de estudiante y computadora de ejemplo
        EstudianteEntidad estudiante = new EstudianteEntidad();
        estudiante.setId(1L); // Estudiante existente (suponemos que ya está en la base de datos)
        nuevaReserva.setEstudiante(estudiante);

        ComputadoraEntidad computadora = new ComputadoraEntidad();
        computadora.setId(1L); // Computadora existente (suponemos que ya está en la base de datos)
        nuevaReserva.setComputadora(computadora);

        // Intentar insertar la nueva reserva
        try
        {
            reservaDAO.insertarReserva(nuevaReserva);
            System.out.println("Reserva insertada con éxito: " + nuevaReserva);
        } catch (Exception e)
        {
            System.err.println("Error al insertar la reserva: " + e.getMessage());
        } 
    }
}

