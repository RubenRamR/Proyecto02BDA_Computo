/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.ComputadoraEntidad;
import InterfacesDAO.IComputadoraDAO;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
public class ComputadoraDAO implements IComputadoraDAO {

    private EntityManagerFactory entityManagerFactory;
    private static final Logger logger = Logger.getLogger(ComputadoraDAO.class.getName());

    public ComputadoraDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    @Override
    public void insertarComputadora(ComputadoraEntidad computadora) throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();
        try
        {
            em.getTransaction().begin();

            em.persist(computadora);

            em.getTransaction().commit();
            System.out.println("Computadora insertada exitosamente.");
        } catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            System.err.println("Error al insertar la computadora: " + e.getMessage());
            throw new PersistenceException("Error al insertar la computadora: " + e.getMessage(), e);
        } finally
        {
            em.close();
        }
    }

    @Override
    public void editarComputadora(ComputadoraEntidad computadora) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            entityManager.getTransaction().begin();
            entityManager.merge(computadora); // Actualiza la computadora existente
            entityManager.getTransaction().commit();
            logger.info("Computadora actualizada: " + computadora.getId());
        } catch (Exception e)
        {
            if (entityManager.getTransaction().isActive())
            {
                entityManager.getTransaction().rollback(); // Deshacer cambios en caso de error
            }
            logger.severe("Error al actualizar la computadora: " + e.getMessage());
            throw new PersistenceException("Error al actualizar la computadora: " + e.getMessage(), e);
        } finally
        {
            entityManager.close();
        }
    }

    @Override
    public void eliminarComputadoraPorID(Long id) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            entityManager.getTransaction().begin();
            ComputadoraEntidad computadora = entityManager.find(ComputadoraEntidad.class, id);
            if (computadora != null)
            {
                entityManager.remove(computadora); // Elimina la computadora
                logger.info("Computadora eliminada con ID: " + id);
            } else
            {
                logger.warning("Computadora no encontrada con ID: " + id);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e)
        {
            if (entityManager.getTransaction().isActive())
            {
                entityManager.getTransaction().rollback(); // Deshacer cambios en caso de error
            }
            logger.severe("Error al eliminar la computadora: " + e.getMessage());
            throw new PersistenceException("Error al eliminar la computadora: " + e.getMessage(), e);
        } finally
        {
            entityManager.close();
        }
    }
    
    public void actualizarComputadora(ComputadoraEntidad computadora) throws PersistenceException {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            // Usar merge para actualizar la computadora
            entityManager.merge(computadora);
            entityManager.getTransaction().commit(); // Confirmar transacción

            logger.info("Computadora actualizada exitosamente: " + computadora);
        } catch (PersistenceException e) {
            if (entityManager != null && entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Hacer rollback en caso de error
            }
            logger.severe("Error al actualizar la computadora: " + e.getMessage());
            throw new PersistenceException("Error al actualizar la computadora", e);
        } finally {
            if (entityManager != null) {
                entityManager.close(); // Cerrar el EntityManager
            }
        }
    }

    @Override
    public ComputadoraEntidad obtenerComputadoraPorID(Long id) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ComputadoraEntidad> query = cb.createQuery(ComputadoraEntidad.class);
            Root<ComputadoraEntidad> root = query.from(ComputadoraEntidad.class);

            // Realizar la unión con CentroComputoEntidad y establecerla como fetch
            root.fetch("centroComputo", JoinType.LEFT); // Cambia "centroComputo" por el nombre del atributo en ComputadoraEntidad

            // Condición de búsqueda por ID
            query.select(root).where(cb.equal(root.get("id"), id));

            // Ejecutar la consulta
            return entityManager.createQuery(query).getSingleResult();
        } catch (Exception e)
        {
            logger.severe("Error al obtener la computadora por ID: " + e.getMessage());
            throw new PersistenceException("Error al obtener la computadora por ID: " + e.getMessage(), e);
        } finally
        {
            entityManager.close();
        }
    }

    @Override
    public List<ComputadoraEntidad> obtenerTodasLasComputadoras() throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            TypedQuery<ComputadoraEntidad> query = entityManager.createQuery("SELECT c FROM ComputadoraEntidad c", ComputadoraEntidad.class);
            return query.getResultList(); // Retorna la lista de computadoras
        } catch (Exception e)
        {
            logger.severe("Error al obtener todas las computadoras: " + e.getMessage());
            throw new PersistenceException("Error al obtener todas las computadoras: " + e.getMessage(), e);
        } finally
        {
            entityManager.close();
        }
    }
}
