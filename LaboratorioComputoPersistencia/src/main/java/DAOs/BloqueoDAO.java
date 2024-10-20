/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.BloqueoEntidad;
import Entidades.EstudianteEntidad;
import InterfacesDAO.IBloqueoDAO;
import java.util.List;
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
    public void insertarBloqueo(BloqueoEntidad bloqueo) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            entityManager.getTransaction().begin();
            entityManager.persist(bloqueo);
            entityManager.getTransaction().commit();
            logger.info("Bloqueo insertado: " + bloqueo);
        } catch (Exception e)
        {
            if (entityManager.getTransaction().isActive())
            {
                entityManager.getTransaction().rollback();
            }
            logger.severe("Error al insertar bloqueo: " + e.getMessage());
            throw new PersistenceException("No se pudo insertar el bloqueo", e);
        } finally
        {
            entityManager.close();
        }
    }

    @Override
    public void editarBloqueo(BloqueoEntidad bloqueo) throws PersistenceException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try
        {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();

            BloqueoEntidad bloqueoExistente = entityManager.find(BloqueoEntidad.class, bloqueo.getId());
            if (bloqueoExistente != null)
            {
                if (bloqueo.getEstudiante() != null)
                {
                    EstudianteEntidad estudiante = entityManager.find(EstudianteEntidad.class, bloqueo.getEstudiante().getId());
                    if (estudiante != null)
                    {
                        bloqueoExistente.setEstudiante(estudiante);
                    } else
                    {
                        throw new PersistenceException("Estudiante no encontrado con ID: " + bloqueo.getEstudiante().getId());
                    }
                }

                // Actualizar otros campos del bloqueo
                bloqueoExistente.setFechaLiberacion(bloqueo.getFechaLiberacion());
                bloqueoExistente.setFechaBloqueo(bloqueo.getFechaBloqueo());
                bloqueoExistente.setMotivo(bloqueo.getMotivo());

                // Guardar los cambios
                entityManager.merge(bloqueoExistente);
            } else
            {
                throw new PersistenceException("Bloqueo no encontrado con ID: " + bloqueo.getId());
            }

            transaction.commit();
        } catch (Exception e)
        {
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
            throw new PersistenceException("Error al editar el bloqueo: " + e.getMessage(), e);
        } finally
        {
            if (entityManager != null)
            {
                entityManager.close();
            }
        }
    }

    @Override
    public void eliminarBloqueoPorID(Long id) throws PersistenceException {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try
        {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();

            BloqueoEntidad bloqueo = entityManager.find(BloqueoEntidad.class, id);

            if (bloqueo != null)
            {
                entityManager.remove(bloqueo);
            } else
            {
                throw new PersistenceException("No se encontró el bloqueo con ID: " + id);
            }

            transaction.commit();
        } catch (Exception e)
        {
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
            throw new PersistenceException("Error al eliminar el bloqueo con ID: " + id, e);
        } finally
        {
            if (entityManager != null)
            {
                entityManager.close();
            }
        }
    }

    @Override
    public BloqueoEntidad obtenerBloqueoPorID(Long id) throws PersistenceException {
        EntityManager entityManager = null;

        try
        {
            entityManager = entityManagerFactory.createEntityManager();

            BloqueoEntidad bloqueo = entityManager.find(BloqueoEntidad.class, id);

            if (bloqueo == null)
            {
                throw new PersistenceException("No se encontró el bloqueo con ID: " + id);
            }

            return bloqueo;

        } catch (Exception e)
        {
            throw new PersistenceException("Error al obtener el bloqueo con ID: " + id, e);
        } finally
        {
            if (entityManager != null)
            {
                entityManager.close();
            }
        }
    }

    @Override
    public List<BloqueoEntidad> obtenerTodosLosBloqueos() throws PersistenceException {
        EntityManager entityManager = null;

        try
        {
            entityManager = entityManagerFactory.createEntityManager();

            TypedQuery<BloqueoEntidad> query = entityManager.createQuery("SELECT b FROM BloqueoEntidad b", BloqueoEntidad.class);

            return query.getResultList();

        } catch (Exception e)
        {
            throw new PersistenceException("Error al obtener todos los bloqueos: " + e.getMessage(), e);
        } finally
        {
            if (entityManager != null)
            {
                entityManager.close();
            }
        }
    }

}
