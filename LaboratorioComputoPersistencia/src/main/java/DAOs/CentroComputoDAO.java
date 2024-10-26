/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.CentroComputoEntidad;
import InterfacesDAO.ICentroComputoDAO;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author rramirez
 */
public class CentroComputoDAO implements ICentroComputoDAO {

    private static final Logger logger = Logger.getLogger(EstudianteDAO.class.getName());

    private EntityManagerFactory entityManagerFactory;

    public CentroComputoDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    @Override
    public void insertarCentroComputo(CentroComputoEntidad centroComputo) throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.persist(centroComputo);
            em.getTransaction().commit();
            logger.info("Centro de cómputo insertado: " + centroComputo);
        } catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            logger.severe("Error al insertar el centro de cómputo: " + e.getMessage());
            throw new PersistenceException("Error al insertar el centro de cómputo: " + e.getMessage(), e);
        } finally
        {
            em.close();
        }
    }

    @Override
    public void editarCentroComputo(CentroComputoEntidad centroComputo) throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();
        try
        {
            em.getTransaction().begin();
            em.merge(centroComputo);
            em.getTransaction().commit();
            logger.info("Centro de cómputo editado: " + centroComputo);
        } catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            logger.severe("Error al editar el centro de cómputo: " + e.getMessage());
            throw new PersistenceException("Error al editar el centro de cómputo: " + e.getMessage(), e);
        } finally
        {
            em.close();
        }
    }

    @Override
    public void eliminarCentroComputoPorID(Long id) throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();
        try
        {
            em.getTransaction().begin();
            CentroComputoEntidad centroComputo = em.find(CentroComputoEntidad.class, id);
            if (centroComputo != null)
            {
                em.remove(centroComputo);
                em.getTransaction().commit();
                logger.info("Centro de cómputo eliminado con ID: " + id);
            } else
            {
                logger.warning("Centro de cómputo no encontrado con ID: " + id);
            }
        } catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            logger.severe("Error al eliminar el centro de cómputo: " + e.getMessage());
            throw new PersistenceException("Error al eliminar el centro de cómputo: " + e.getMessage(), e);
        } finally
        {
            em.close();
        }
    }

    @Override
    public CentroComputoEntidad obtenerCentroComputoPorID(Long id) throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();
        try
        {
            return em.find(CentroComputoEntidad.class, id);
        } catch (Exception e)
        {
            logger.severe("Error al obtener el centro de cómputo por ID: " + e.getMessage());
            throw new PersistenceException("Error al obtener el centro de cómputo por ID: " + e.getMessage(), e);
        } finally
        {
            em.close();
        }
    }

    @Override
    public List<CentroComputoEntidad> obtenerTodosLosCentroComputo() throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();
        try
        {
            TypedQuery<CentroComputoEntidad> query = em.createQuery("SELECT c FROM CentroComputoEntidad c", CentroComputoEntidad.class);
            return query.getResultList();
        } catch (Exception e)
        {
            logger.severe("Error al obtener todos los centros de cómputo: " + e.getMessage());
            throw new PersistenceException("Error al obtener todos los centros de cómputo: " + e.getMessage(), e);
        } finally
        {
            em.close();
        }
    }

}
