/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.BloqueoEntidad;
import Entidades.EstudianteEntidad;
import InterfacesDAO.IBloqueoDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rramirez
 */
public class BloqueoDAO implements IBloqueoDAO {

    private static final Logger logger = Logger.getLogger(EstudianteDAO.class.getName());

    private EntityManagerFactory entityManagerFactory;

    public BloqueoDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    @Override
    public void insertarBloqueo(BloqueoEntidad bloqueo, Long idEstudiante) throws PersistenceException {
        EntityManager entityManager = null;
        try
        {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            EstudianteEntidad estudiante = entityManager.find(EstudianteEntidad.class, idEstudiante);
            if (estudiante == null)
            {
                throw new PersistenceException("Estudiante no encontrado con ID: " + idEstudiante);
            }

            bloqueo.setEstudiante(estudiante);

            entityManager.persist(bloqueo);
            entityManager.getTransaction().commit();
            logger.info("Bloqueo insertado exitosamente: " + bloqueo);
        } catch (PersistenceException e)
        {
            if (entityManager != null && entityManager.getTransaction().isActive())
            {
                entityManager.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error al insertar el bloqueo: " + e.getMessage(), e);
            throw new PersistenceException("Error al insertar el bloqueo: " + e.getMessage(), e);
        } finally
        {
            if (entityManager != null)
            {
                entityManager.close();
            }
        }
    }

    @Override
    public void editarBloqueo(BloqueoEntidad bloqueo) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try
        {
            transaction = em.getTransaction();
            transaction.begin();

            BloqueoEntidad bloqueoExistente = em.find(BloqueoEntidad.class, bloqueo.getId());

            if (bloqueoExistente != null)
            {
                bloqueoExistente.setFechaBloqueo(bloqueo.getFechaBloqueo());
                bloqueoExistente.setFechaLiberacion(bloqueo.getFechaLiberacion());
                bloqueoExistente.setMotivo(bloqueo.getMotivo());

                em.merge(bloqueoExistente);
            } else
            {
                System.out.println("El bloqueo con ID " + bloqueo.getId() + " no existe.");
            }

            transaction.commit();
        } catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            System.err.println("Error al editar el bloqueo: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }

    @Override
    public void eliminarBloqueoPorID(Long idBloqueo) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try
        {
            transaction = em.getTransaction();
            transaction.begin();

            BloqueoEntidad bloqueo = em.find(BloqueoEntidad.class, idBloqueo);

            if (bloqueo != null)
            {
                em.remove(bloqueo);
                System.out.println("Bloqueo eliminado con éxito.");
            } else
            {
                System.out.println("El bloqueo con ID " + idBloqueo + " no existe.");
            }

            transaction.commit();
        } catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            System.err.println("Error al eliminar el bloqueo: " + e.getMessage());
        } finally
        {
            em.close();
        }
    }

    @Override
    public BloqueoEntidad obtenerBloqueoPorID(Long id) throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();

        try
        {
            BloqueoEntidad bloqueo = em.find(BloqueoEntidad.class, id);

            if (bloqueo == null)
            {
                throw new PersistenceException("El bloqueo con ID " + id + " no existe.");
            }

            return bloqueo;
        } catch (Exception e)
        {
            throw new PersistenceException("Error al obtener el bloqueo: " + e.getMessage(), e);
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<BloqueoEntidad> obtenerTodosLosBloqueos() throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();
        List<BloqueoEntidad> bloqueos = new ArrayList<>();

        try
        {
            TypedQuery<BloqueoEntidad> query = em.createQuery("SELECT b FROM BloqueoEntidad b", BloqueoEntidad.class);
            bloqueos = query.getResultList();
        } catch (Exception e)
        {
            throw new PersistenceException("Error al obtener todos los bloqueos: " + e.getMessage(), e);
        } finally
        {
            em.close();
        }

        return bloqueos;
    }

}
