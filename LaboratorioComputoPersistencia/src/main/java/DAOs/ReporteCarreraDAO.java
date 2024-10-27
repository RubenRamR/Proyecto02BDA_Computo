/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.CarreraEntidad;
import Entidades.EstudianteEntidad;
import Entidades.ReservaEntidad;
import InterfacesDAO.IReporteCarreraDAO;
import excepciones.PersistenciaException;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 *
 * @author user
 */
public class ReporteCarreraDAO  implements IReporteCarreraDAO{

    private EntityManagerFactory entityManagerFactory;

    public ReporteCarreraDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    @Override
    public List<ReservaEntidad> obtenerReservasPorCarrera(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaException {
        EntityManager entityManager = null;
        List<ReservaEntidad> reservas = new ArrayList<>();

        try {
            entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<ReservaEntidad> query = entityManager.createQuery(
                "SELECT r FROM ReservaEntidad r WHERE r.fechaHoraInicio BETWEEN :fechaInicio AND :fechaFin", ReservaEntidad.class);
            query.setParameter("fechaInicio", fechaInicio);
            query.setParameter("fechaFin", fechaFin);
            reservas = query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener las reservas", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return reservas;
    }

    public CarreraEntidad obtenerCarreraPorId(Long idCarrera) throws PersistenciaException {
        EntityManager entityManager = null;
        CarreraEntidad carrera = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            carrera = entityManager.find(CarreraEntidad.class, idCarrera);
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener la carrera", e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        return carrera;
    }
}
