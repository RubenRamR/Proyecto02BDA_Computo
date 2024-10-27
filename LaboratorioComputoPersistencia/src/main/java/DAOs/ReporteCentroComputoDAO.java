/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.CentroComputoEntidad;
import Entidades.ComputadoraEntidad;
import Entidades.ReservaEntidad;
import InterfacesDAO.IReporteCentroComputoDAO;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author rramirez
 */
public class ReporteCentroComputoDAO implements IReporteCentroComputoDAO {

    private EntityManagerFactory entityManagerFactory;

    public ReporteCentroComputoDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    @Override
    public List<CentroComputoEntidad> obtenerReportesPorCentroYFechas(String centroComputo, LocalDate fechaInicio, LocalDate fechaFin) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<CentroComputoEntidad> reportes = new ArrayList<>();

        try
        {
            String jpql = "SELECT c FROM CentroComputoEntidad c "
                    + "JOIN c.computadoras comp "
                    + "JOIN comp.reservas r "
                    + "WHERE c.nombre = :centroComputo "
                    + "AND r.fechaHoraInicio BETWEEN :fechaInicio AND :fechaFin "
                    + "GROUP BY c.idCentroComputo, comp.idComputadora, comp.direccionIP, comp.estado, "
                    + "comp.nombreAlumno, comp.numeroMaquina, comp.tipoCompu";

            TypedQuery<CentroComputoEntidad> query = entityManager.createQuery(jpql, CentroComputoEntidad.class);
            query.setParameter("centroComputo", centroComputo);
            query.setParameter("fechaInicio", fechaInicio.atStartOfDay()); // Convierte LocalDate a LocalDateTime
            query.setParameter("fechaFin", fechaFin.atTime(23, 59, 59)); // Incluye todo el día

            reportes = query.getResultList();

            // Procesar los minutos de uso y cantidad de alumnos
            for (CentroComputoEntidad centro : reportes)
            {
                for (ComputadoraEntidad computadora : centro.getComputadoras())
                {
                    int cantidadAlumnos = 0; // Puedes calcular la cantidad de alumnos según las reservas
                    int minutosUso = 0;

                    for (ReservaEntidad reserva : computadora.getReservas())
                    {
                        if (reserva.getFechaHoraInicio().isAfter(fechaInicio.atStartOfDay())
                                && reserva.getFechaHoraInicio().isBefore(fechaFin.atTime(23, 59, 59)))
                        {
                            minutosUso += (int) Duration.between(reserva.getFechaHoraInicio(), reserva.getFechaHoraFin()).toMinutes();
                            cantidadAlumnos++;
                        }
                    }

                    System.out.println("Computadora: " + computadora.getNumeroMaquina()
                            + ", Alumnos: " + cantidadAlumnos
                            + ", Minutos Uso: " + minutosUso);
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace(); // Manejo de excepciones
        } finally
        {
            entityManager.close();
        }

        return reportes;
    }

    @Override
    public List<String> obtenerCentrosComputo() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<String> centrosComputo = null;

        try
        {
            String jpql = "SELECT DISTINCT c.nombre FROM centroComputo c";
            TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
            centrosComputo = query.getResultList();
        } catch (Exception e)
        {
            System.err.println("Error al obtener centros de cómputo: " + e.getMessage());
            // Manejo de excepciones
        } finally
        {
            entityManager.close();
        }
        return centrosComputo;
    }

}
