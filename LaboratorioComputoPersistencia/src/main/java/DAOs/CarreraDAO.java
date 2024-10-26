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
    public CarreraEntidad obtenerCarreraPorNombre(String nombre) throws Exception {
        EntityManager entityManager = emf.createEntityManager();
        try
        {
            // Crear un CriteriaBuilder
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

            // Crear una CriteriaQuery para CarreraEntidad
            CriteriaQuery<CarreraEntidad> criteriaQuery = criteriaBuilder.createQuery(CarreraEntidad.class);

            // Definir la raíz (FROM)
            Root<CarreraEntidad> carreraRoot = criteriaQuery.from(CarreraEntidad.class);

            // Realizar el JOIN FETCH para estudiantes
            carreraRoot.fetch("estudiantes", JoinType.LEFT);

            // Establecer la condición WHERE
            criteriaQuery.select(carreraRoot)
                    .where(criteriaBuilder.equal(carreraRoot.get("nombre"), nombre));

            // Ejecutar la consulta
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e)
        {
            throw new Exception("No se encontró la carrera con nombre: " + nombre, e);
        } catch (Exception e)
        {
            throw new Exception("Error al obtener la carrera: " + e.getMessage(), e);
        } finally
        {
            entityManager.close(); // Cerrar el EntityManager en el bloque finally
        }
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
