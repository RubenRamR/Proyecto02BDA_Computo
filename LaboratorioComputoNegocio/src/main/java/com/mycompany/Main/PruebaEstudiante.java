/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.Main;

import DAOs.ComputadoraDAO;
import DTOs.ComputadoraDTO;
import Negocio.ComputadoraNegocio;
import Negocio.Convertidores.Convertidores;
import excepciones.NegocioException;

/**
 *
 * @author rramirez
 */
public class PruebaEstudiante {

    public static void main(String[] args) {
       // Crear instancias de los DAOs y Convertidores necesarios
        ComputadoraDAO computadoraDAO = new ComputadoraDAO(); // Asegúrate de que este objeto esté correctamente configurado
        Convertidores convertidor = new Convertidores(); // Inicializa convertidor

        // Crear una instancia de ComputadoraNegocio, pasando los DAOs y convertidor
        ComputadoraNegocio computadoraNegocio = new ComputadoraNegocio();

        // ID de prueba para la computadora
        Long idPrueba = 1L;

        try {
            // Llamar al método obtenerComputadoraPorID
            ComputadoraDTO computadoraDTO = computadoraNegocio.obtenerComputadoraPorID(idPrueba);

            // Imprimir los resultados
            if (computadoraDTO != null) {
                System.out.println("Computadora encontrada:");
                System.out.println("ID: " + computadoraDTO.getId());
                System.out.println("Nombre del Alumno: " + computadoraDTO.getNombreAlumno());
                System.out.println("Estado: " + computadoraDTO.getEstado());
                System.out.println("Número de Máquina: " + computadoraDTO.getNumeroMaquina());
                System.out.println("Dirección IP: " + computadoraDTO.getDireccionIP());
                System.out.println("Tipo de Computadora: " + computadoraDTO.getTipoCompu());
                System.out.println("Centro de Cómputo: " + (computadoraDTO.getCentroComputo() != null ? computadoraDTO.getCentroComputo().getNombre() : "N/A"));
            } else {
                System.out.println("No se encontró una computadora con el ID especificado.");
            }
        } catch (NegocioException e) {
            System.err.println("Ocurrió un error al obtener la computadora: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
