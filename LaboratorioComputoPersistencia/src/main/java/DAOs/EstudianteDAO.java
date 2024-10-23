/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.EstudianteEntidad;
import InterfacesDAO.IEstudianteDAO;
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
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try
        {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(estudiante);

            transaction.commit();

            logger.info("Estudiante insertado correctamente: " + estudiante.getNombre());
        } catch (PersistenceException e)
        {
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }

            logger.severe("Error al insertar el estudiante: " + e.getMessage());

            throw e;
        } finally
        {
            if (entityManager != null)
            {
                entityManager.close();
            }
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

            EstudianteEntidad estudianteExistente = entityManager.find(EstudianteEntidad.class, estudiante.getId());

            if (estudianteExistente != null)
            {
                estudianteExistente.setNombre(estudiante.getNombre());
                estudianteExistente.setApPaterno(estudiante.getApPaterno());
                estudianteExistente.setApMaterno(estudiante.getApMaterno());
                estudianteExistente.setEstatus(estudiante.getEstatus());
                estudianteExistente.setContrasena(estudiante.getContrasena());
                estudianteExistente.setCarrera(estudiante.getCarrera());

                entityManager.merge(estudianteExistente);

                transaction.commit();
                System.out.println("Estudiante actualizado con éxito: " + estudiante.getNombre());
            } else
            {
                System.err.println("Estudiante no encontrado con ID: " + estudiante.getId());
                throw new PersistenceException("Estudiante no encontrado con ID: " + estudiante.getId());
            }

        } catch (PersistenceException e)
        {
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
            System.err.println("Error al actualizar el Estudiante: " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e)
        {
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();

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
                System.out.println("Estudiante eliminado con éxito: " + estudiante.getNombre());
            } else
            {
                System.err.println("Estudiante no encontrado con ID: " + id);
                throw new PersistenceException("Estudiante no encontrado con ID: " + id);
            }
        } catch (PersistenceException e)
        {
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
            System.err.println("Error al eliminar el Estudiante: " + e.getMessage());
            e.printStackTrace();

        } catch (Exception e)
        {
            if (transaction != null && transaction.isActive())
            {
                transaction.rollback();
            }
            System.err.println("Error inesperado al eliminar el Estudiante: " + e.getMessage());
            e.printStackTrace();

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
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EstudianteEntidad estudiante = null;

        try
        {
            estudiante = entityManager.find(EstudianteEntidad.class, id);
            if (estudiante == null)
            {
                throw new PersistenceException("No se encontró ningún estudiante con el ID: " + id);
            }
            System.out.println("Estudiante encontrado: " + estudiante);
        } catch (PersistenceException e)
        {
            System.err.println("Error al obtener el estudiante: " + e.getMessage());
            throw e;
        } catch (Exception e)
        {
            System.err.println("Error inesperado al obtener el estudiante: " + e.getMessage());
            throw new PersistenceException("Error inesperado al obtener estudiante con ID: " + id, e);
        } finally
        {
            entityManager.close();
        }

        return estudiante;
    }

    @Override
    public List<EstudianteEntidad> obtenerTodosLosEstudiantes() throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<EstudianteEntidad> estudiantes = null;

        try
        {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<EstudianteEntidad> cq = cb.createQuery(EstudianteEntidad.class);
            Root<EstudianteEntidad> root = cq.from(EstudianteEntidad.class);
            cq.select(root);
            estudiantes = entityManager.createQuery(cq).getResultList();
            System.out.println("Estudiantes obtenidos: " + estudiantes);
        } catch (Exception e)
        {
            System.err.println("Error al obtener todos los estudiantes: " + e.getMessage());
            throw new PersistenceException("Error al obtener estudiantes", e);
        } finally
        {
            entityManager.close();
        }

        return estudiantes;
    }
}
