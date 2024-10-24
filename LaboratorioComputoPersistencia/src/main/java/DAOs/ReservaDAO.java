/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import ENUM.Estado;
import Entidades.ComputadoraEntidad;
import Entidades.EstudianteEntidad;
import Entidades.ReservaEntidad;
import InterfacesDAO.IReservaDAO;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 *
 * @author rramirez
 */
//NO LISTA
public class ReservaDAO implements IReservaDAO {

    private static final Logger logger = Logger.getLogger(ReservaDAO.class.getName());
    private EntityManagerFactory entityManagerFactory;

    public ReservaDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    @Override
    public void insertarReserva(ReservaEntidad reserva) throws PersistenceException {
        EntityManager entityManager = null;
        try
        {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            entityManager.persist(reserva);
            entityManager.getTransaction().commit(); // Confirmar transacción

            logger.info("Reserva insertada exitosamente: " + reserva);
        } catch (PersistenceException e)
        {
            if (entityManager != null && entityManager.getTransaction().isActive())
            {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error al insertar la reserva: " + e.getMessage());
            throw new PersistenceException("Error al insertar la reserva", e);
        } finally
        {
            if (entityManager != null)
            {
                entityManager.close(); // Cerrar el EntityManager
            }
        }
    }

    // Método para actualizar una reserva existente
    public void actualizarReserva(ReservaEntidad reserva) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try
        {
            transaction.begin();
            entityManager.merge(reserva); // Actualiza la reserva
            transaction.commit(); // Confirmar la transacción
            logger.log(Level.INFO, "Reserva actualizada con éxito: " + reserva);
        } catch (Exception e)
        {
            if (transaction.isActive())
            {
                transaction.rollback(); // Revertir la transacción en caso de error
            }
            logger.log(Level.SEVERE, "Error al actualizar la reserva: " + e.getMessage(), e);
            throw e; // Lanza la excepción para manejarla en el nivel superior
        } finally
        {
            entityManager.close(); // Asegúrate de cerrar el EntityManager
        }
    }

    // Método para eliminar una reserva
    public void eliminarReserva(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try
        {
            transaction.begin();
            ReservaEntidad reserva = obtenerReservaPorId(id); // Busca la reserva por ID
            if (reserva != null)
            {
                entityManager.remove(reserva); // Elimina la reserva
                logger.log(Level.INFO, "Reserva eliminada con éxito: " + reserva);
            }
            transaction.commit(); // Confirmar la transacción
        } catch (Exception e)
        {
            if (transaction.isActive())
            {
                transaction.rollback(); // Revertir la transacción en caso de error
            }
            logger.log(Level.SEVERE, "Error al eliminar la reserva: " + e.getMessage(), e);
            throw e; // Lanza la excepción para manejarla en el nivel superior
        } finally
        {
            entityManager.close(); // Asegúrate de cerrar el EntityManager
        }
    }

    public List<ReservaEntidad> obtenerReservas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            TypedQuery<ReservaEntidad> query = entityManager.createQuery("SELECT r FROM ReservaEntidad r", ReservaEntidad.class);
            return query.getResultList(); // Retorna la lista de reservas
        } finally
        {
            entityManager.close(); // Asegúrate de cerrar el EntityManager
        }
    }

    public ReservaEntidad obtenerReservaPorId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            return entityManager.find(ReservaEntidad.class, id); // Busca la reserva por ID
        } finally
        {
            entityManager.close(); // Asegúrate de cerrar el EntityManager
        }
    }

}
