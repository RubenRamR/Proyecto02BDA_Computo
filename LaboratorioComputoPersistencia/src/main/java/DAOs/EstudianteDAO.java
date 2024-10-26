/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.EstudianteEntidad;
import InterfacesDAO.IEstudianteDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 *
 * @author rramirez
 */
public class EstudianteDAO implements IEstudianteDAO {

    private static final Logger logger = Logger.getLogger(EstudianteDAO.class.getName());

    private EntityManagerFactory entityManagerFactory;

    public EstudianteDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    @Override
    public void insertarEstudiante(EstudianteEntidad estudiante) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            entityManager.getTransaction().begin();
            entityManager.persist(estudiante);
            entityManager.getTransaction().commit();
            logger.info("Estudiante creado exitosamente: " + estudiante);
        } catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            logger.severe("Error creando estudiante: " + e.getMessage());
        } finally
        {
            entityManager.close();
        }

    }

    @Override
    public void editarEstudiante(EstudianteEntidad estudiante) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try
        {
            transaction = entityManager.getTransaction();
            transaction.begin();

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<EstudianteEntidad> cq = cb.createQuery(EstudianteEntidad.class);
            Root<EstudianteEntidad> estudianteRoot = cq.from(EstudianteEntidad.class);

            cq.select(estudianteRoot).where(cb.equal(estudianteRoot.get("id"), estudiante.getId()));

            EstudianteEntidad estudianteExistente = entityManager.createQuery(cq).getSingleResult();

            if (estudianteExistente != null)
            {
                estudianteExistente.setNombre(estudiante.getNombre());
                estudianteExistente.setApPaterno(estudiante.getApPaterno());
                estudianteExistente.setApMaterno(estudiante.getApMaterno());
                estudianteExistente.setEstatus(estudiante.getEstatus());
                estudianteExistente.setContrasena(estudiante.getContrasena());
                estudianteExistente.setCarrera(estudiante.getCarrera());
                estudianteExistente.setReservas(estudiante.getReservas());
                estudianteExistente.setBloqueos(estudiante.getBloqueos());

                entityManager.merge(estudianteExistente);
                transaction.commit();

                logger.log(Level.INFO, "Estudiante actualizado con éxito: {0}", estudianteExistente);
            } else
            {
                logger.log(Level.WARNING, "No se encontró el estudiante con ID: {0}", estudiante.getId());
            }

        } catch (NoResultException e)
        {
            logger.log(Level.WARNING, "No se encontró ningún estudiante con el ID: {0}", estudiante.getId());
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
        } catch (PersistenceException e)
        {
            logger.log(Level.SEVERE, "Error al actualizar el estudiante: {0}", e.getMessage());
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error inesperado al actualizar el estudiante: {0}", e.getMessage());
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
        } finally
        {
            if (entityManager != null)
            {
                entityManager.close();
            }
        }
    }

    @Override
    public void eliminarEstudiantePorID(Long id) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = null;

        try
        {
            transaction = entityManager.getTransaction();
            transaction.begin();

            EstudianteEntidad estudiante = entityManager.find(EstudianteEntidad.class, id);

            if (estudiante != null)
            {
                entityManager.remove(estudiante);
                transaction.commit();
                logger.log(Level.INFO, "Estudiante eliminado con éxito: ID {0}", id);
            } else
            {
                logger.log(Level.WARNING, "No se encontró un estudiante con ID {0}", id);
                transaction.rollback();
            }

        } catch (PersistenceException e)
        {
            logger.log(Level.SEVERE, "Error al eliminar el estudiante: {0}", e.getMessage());
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e)
        {
            logger.log(Level.SEVERE, "Error inesperado al eliminar el estudiante: {0}", e.getMessage());
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
        } finally
        {
            if (entityManager != null)
            {
                entityManager.close();
            }
        }
    }

    @Override
    public EstudianteEntidad obtenerEstudiantePorID(Long id) throws PersistenceException {
        EntityManager em = null;
        EstudianteEntidad estudiante = null;

        try
        {
            em = entityManagerFactory.createEntityManager();
            estudiante = em.find(EstudianteEntidad.class, id);

            if (estudiante != null)
            {
                System.out.println("Estudiante encontrado: " + estudiante);
            } else
            {
                System.out.println("Estudiante no encontrado con ID: " + id);
            }
        } catch (Exception e)
        {
            throw new PersistenceException("Error al obtener el estudiante con ID: " + id, e);
        } finally
        {
            if (em != null && em.isOpen())
            {
                em.close();
            }
        }

        return estudiante;
    }

    @Override
    public List<EstudianteEntidad> obtenerTodosLosEstudiantes() throws PersistenceException {
        EntityManager em = null;
        List<EstudianteEntidad> estudiantes = new ArrayList<>();

        try
        {
            em = entityManagerFactory.createEntityManager();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<EstudianteEntidad> cq = cb.createQuery(EstudianteEntidad.class);
            Root<EstudianteEntidad> root = cq.from(EstudianteEntidad.class);
            cq.select(root);

            estudiantes = em.createQuery(cq).getResultList();

            if (estudiantes.isEmpty())
            {
                System.out.println("No se encontraron estudiantes.");
            } else
            {
                System.out.println("Total de estudiantes encontrados: " + estudiantes.size());
            }
        } catch (Exception e)
        {
            throw new PersistenceException("Error al obtener la lista de estudiantes", e);
        } finally
        {
            if (em != null && em.isOpen())
            {
                em.close();
            }
        }

        return estudiantes;
    }
}
