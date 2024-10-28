/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.CentroComputoEntidad;
import Entidades.UnidadAcademicaEntidad;
import InterfacesDAO.ICentroComputoDAO;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

            // Verifica si la unidad académica no es nula y si su nombre es nulo
            if (centroComputo.getUnidadAcademica() != null)
            {
                // Usar Criteria API para verificar el nombre de la unidad académica
                CriteriaBuilder cb = em.getCriteriaBuilder();
                CriteriaQuery<Long> query = cb.createQuery(Long.class);
                Root<UnidadAcademicaEntidad> root = query.from(UnidadAcademicaEntidad.class);
                query.select(cb.count(root))
                        .where(cb.equal(root.get("id"), centroComputo.getUnidadAcademica().getId()));

                Long count = em.createQuery(query).getSingleResult();
                if (count == 0)
                {
                    throw new PersistenceException("La unidad académica no existe");
                }

                if (centroComputo.getUnidadAcademica().getNombre() == null)
                {
                    throw new PersistenceException("El nombre de la unidad académica no puede ser nulo");
                }
            } else
            {
                throw new PersistenceException("La unidad académica no puede ser nula");
            }

            // Persistir el nuevo Centro de Cómputo
            em.persist(centroComputo);
            em.getTransaction().commit();
            logger.info("Centro de cómputo insertado: " + centroComputo);
        } catch (Exception e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            // logger.severe("Error al insertar el centro de cómputo: " + e.getMessage());
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

    public UnidadAcademicaEntidad obtenerIdUnidadAcademicaPorNombre(String nombre) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        UnidadAcademicaEntidad unidadAcademica = null;

        try
        {
            em.getTransaction().begin();

            // Usa el nombre correcto de la entidad, que es "UnidadAcademicaEntidad"
            Query query = em.createQuery("SELECT u FROM unidadAcademica u WHERE u.nombre = :nombre;");
            query.setParameter("nombre", nombre);

            // Obtiene el resultado de la consulta
            unidadAcademica = (UnidadAcademicaEntidad) query.getSingleResult();

            // Commit de la transacción
            em.getTransaction().commit();
        } catch (NoResultException e)
        {
//            logger.warning("No se encontró ninguna unidad académica con el nombre: " + nombre);
//            throw new Exception("La unidad académica con el nombre '" + nombre + "' no fue encontrada.");
        } catch (Exception e)
        {
            // Si ocurre un error, hacer rollback de la transacción
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
//            logger.severe("Error al obtener la unidad académica: " + e.getMessage());
            throw e;
        } finally
        {
            // Cerrar el EntityManager
            if (em != null && em.isOpen())
            {
                em.close();
            }
        }

        return unidadAcademica;
    }

    public List<UnidadAcademicaEntidad> obtenerUnidadesAcademicas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<UnidadAcademicaEntidad> unidadesAcademicas = null;

        try
        {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UnidadAcademicaEntidad> criteriaQuery = criteriaBuilder.createQuery(UnidadAcademicaEntidad.class);
            Root<UnidadAcademicaEntidad> root = criteriaQuery.from(UnidadAcademicaEntidad.class);
            criteriaQuery.select(root);
            unidadesAcademicas = entityManager.createQuery(criteriaQuery).getResultList();
        } catch (Exception e)
        {
            logger.severe("Error al obtener unidades académicas: " + e.getMessage());
        } finally
        {
            if (entityManager != null && entityManager.isOpen())
            {
                entityManager.close();
            }
        }

        return unidadesAcademicas;
    }

}
