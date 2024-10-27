/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.CarreraEntidad;
import InterfacesDAO.ICarreraDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 *
 * @author rramirez
 */
public class CarreraDAO implements ICarreraDAO {

    private static final Logger logger = Logger.getLogger(CarreraDAO.class.getName());

    private EntityManagerFactory emf;

    public CarreraDAO() {
        emf = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    @Override
    public CarreraEntidad obtenerIdCarreraPorNombre(String nombre) throws Exception {
        EntityManager em = emf.createEntityManager();
        CarreraEntidad carrera = null;

        try
        {
            // Inicia la transacción si es necesario
            em.getTransaction().begin();

            // Consulta JPQL para obtener la carrera por nombre
            Query query = em.createQuery("SELECT c FROM CarreraEntidad c WHERE c.nombre = :nombre");
            query.setParameter("nombre", nombre);

            // Obtiene el resultado de la consulta
            carrera = (CarreraEntidad) query.getSingleResult();

            // Commit de la transacción
            em.getTransaction().commit();
        } catch (NoResultException e)
        {
            logger.warning("No se encontró ninguna carrera con el nombre: " + nombre);
            throw new Exception("La carrera con el nombre '" + nombre + "' no fue encontrada.");
        } catch (Exception e)
        {
            // Si ocurre un error, hacer rollback de la transacción
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            logger.severe("Error al obtener la carrera: " + e.getMessage());
            throw e;
        } finally
        {
            // Cerrar el EntityManager
            if (em != null && em.isOpen())
            {
                em.close();
            }
        }

        return carrera;
    }

    public List<CarreraEntidad> obtenerTodasLasCarreras() {
        EntityManager em = null;
        List<CarreraEntidad> carreras = null;

        try
        {
            em = emf.createEntityManager();
            // Inicia una transacción
            em.getTransaction().begin();

            // Realiza la consulta
            TypedQuery<CarreraEntidad> query = em.createQuery("SELECT c FROM CarreraEntidad c", CarreraEntidad.class);
            carreras = query.getResultList();

            // Confirma la transacción
            em.getTransaction().commit();
        } catch (Exception e)
        {
            if (em != null && em.getTransaction().isActive())
            {
                em.getTransaction().rollback(); // Deshacer cambios en caso de error
            }
            logger.severe("Error al obtener las carreras: " + e.getMessage());
        } finally
        {
            if (em != null)
            {
                em.close(); // Asegurarse de cerrar el EntityManager
            }
        }
        return carreras;
    }

    public CarreraEntidad obtenerCarreraPorId(Long idCarrera) {
        if (idCarrera == null)
        {
            logger.warning("El ID de carrera no puede ser nulo.");
            return null; // Retorna null si el ID es nulo
        }

        EntityManager em = null;
        CarreraEntidad carrera = null;

        try
        {
            // Crear una instancia del EntityManager
            em = emf.createEntityManager();

            // Iniciar una transacción
            em.getTransaction().begin();

            // Buscar la carrera por ID
            carrera = em.find(CarreraEntidad.class, idCarrera);

            // Confirmar la transacción
            em.getTransaction().commit();
        } catch (Exception e)
        {
            if (em != null && em.getTransaction().isActive())
            {
                em.getTransaction().rollback(); // Hacer rollback en caso de error
            }
            logger.log(Level.SEVERE, "Error al obtener la carrera por ID: " + idCarrera, e);
        } finally
        {
            if (em != null)
            {
                em.close(); // Cerrar el EntityManager
            }
        }

        return carrera;  // Retornar la carrera encontrada o null si no se encontró
    }

}
