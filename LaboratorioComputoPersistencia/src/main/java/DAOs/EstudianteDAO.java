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
        entityManager.getTransaction().begin();

        try
        {
            entityManager.persist(estudiante);
            entityManager.getTransaction().commit();
            System.out.println("Estudiante agregado con éxito: " + estudiante.getNombre());
        } catch (PersistenceException e)
        {
            entityManager.getTransaction().rollback();
            System.err.println("Error al agregar el Estudiante: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally
        {
            entityManager.close();
        }

    }

    @Override
    public void editarEstudiante(EstudianteEntidad estudiante) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try
        {
            // Crear CriteriaBuilder y CriteriaQuery
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EstudianteEntidad> criteriaQuery = criteriaBuilder.createQuery(EstudianteEntidad.class);
            Root<EstudianteEntidad> root = criteriaQuery.from(EstudianteEntidad.class);

            // Buscar el estudiante por su ID
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), estudiante.getId()));
            EstudianteEntidad estudianteExistente = entityManager.createQuery(criteriaQuery).getSingleResult();

            // Actualizar los campos del estudiante existente
            estudianteExistente.setNombre(estudiante.getNombre());
            estudianteExistente.setApPaterno(estudiante.getApPaterno());
            estudianteExistente.setApMaterno(estudiante.getApMaterno());
            estudianteExistente.setEstatus(estudiante.getEstatus());
            estudianteExistente.setContrasena(estudiante.getContrasena());
            estudianteExistente.setCarrera(estudiante.getCarrera());

            // Persistir los cambios
            entityManager.getTransaction().commit();
            logger.log(Level.INFO, "Estudiante actualizado con éxito: {0}", estudiante.getNombre());
        } catch (PersistenceException e)
        {
            entityManager.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error al actualizar el Estudiante: {0}", e.getMessage());
            e.printStackTrace(); // Imprimir la traza de la pila para más detalles
        } catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            logger.log(Level.SEVERE, "Error inesperado: {0}", e.getMessage());
            e.printStackTrace(); // Imprimir la traza de la pila para más detalles
        } finally
        {
            entityManager.close();
        }
    }

    @Override
    public void eliminarEstudiantePorID(Long id) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        try
        {
            EstudianteEntidad estudiante = entityManager.find(EstudianteEntidad.class, id);
            if (estudiante != null)
            {
                entityManager.remove(estudiante);
                entityManager.getTransaction().commit();
                System.out.println("Estudiante eliminado con éxito: " + estudiante.getNombre());
            } else
            {
                System.out.println("Estudiante no encontrado con ID: " + id);
                entityManager.getTransaction().rollback();
            }
        } catch (PersistenceException e)
        {
            entityManager.getTransaction().rollback();
            System.err.println("Error al eliminar el Estudiante: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e)
        {
            entityManager.getTransaction().rollback();
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        } finally
        {
            entityManager.close();
        }
    }

    @Override
    public EstudianteEntidad obtenerEstudiantePorID(Long id) throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();
        EstudianteEntidad estudiante = null;

        try
        {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<EstudianteEntidad> cq = cb.createQuery(EstudianteEntidad.class);
            Root<EstudianteEntidad> estudianteRoot = cq.from(EstudianteEntidad.class);

            // Agregamos el fetch para cargar las colecciones relacionadas
            estudianteRoot.fetch("computadoras", JoinType.LEFT);
            estudianteRoot.fetch("bloqueos", JoinType.LEFT);

            cq.select(estudianteRoot).where(cb.equal(estudianteRoot.get("id"), id));

            estudiante = em.createQuery(cq).getSingleResult();

            logger.info("Estudiante encontrado: " + estudiante);

        } catch (Exception e)
        {
            System.out.println("");
            throw new PersistenceException("Error al obtener estudiante", e);
        } finally
        {
            em.close();
        }

        return estudiante;
    }

    @Override
    public List<EstudianteEntidad> obtenerTodosLosEstudiantes() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void cerrar() {
        entityManagerFactory.close();
    }

}
