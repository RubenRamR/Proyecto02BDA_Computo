/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import ENUM_P.Estado;
import Entidades.ComputadoraEntidad;
import Entidades.EstudianteEntidad;
import Entidades.ReservaEntidad;
import InterfacesDAO.IReservaDAO;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
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
//NO LISTA
public class ReservaDAO implements IReservaDAO {

    private ComputadoraDAO computadoraDAO;
    private static final Logger logger = Logger.getLogger(ReservaDAO.class.getName());
    private EntityManagerFactory entityManagerFactory;

    public ReservaDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }
    
    public ReservaDAO(ComputadoraDAO computadoraDAO) {
        this.computadoraDAO = computadoraDAO;
    }

    @Override
   public void insertarReserva(ReservaEntidad reserva) throws PersistenceException {
    EntityManager entityManager = null;

    try {
        // Verificar que la reserva y la computadora no sean nulos
        if (reserva == null || reserva.getComputadora() == null) {
            throw new PersistenceException("Reserva o computadora no pueden ser nulos");
        }

        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        // Obtener la dirección IP de la computadora de la reserva
        String direccionIP = reserva.getComputadora().getDireccionIP();
        
        // Buscar la computadora por dirección IP usando ComputadoraDAO
        ComputadoraDAO computadoraDAO = new ComputadoraDAO(); // Asegúrate de que esté inicializado
        ComputadoraEntidad computadora = computadoraDAO.obtenerComputadoraPorDireccionIP(direccionIP);
        
        if (computadora != null) {
            // La computadora ya existe, modificar su estado
            computadora.setEstado(Estado.OCUPADO);
            entityManager.merge(computadora); // Actualizar la computadora en la base de datos
            
            // Asociar la reserva a la computadora
            reserva.setComputadora(computadora);
            entityManager.persist(reserva); // Persistir solo la reserva
        } else {
            logger.severe("No se encontró la computadora con dirección IP: " + direccionIP);
            throw new PersistenceException("No se encontró la computadora con dirección IP: " + direccionIP);
        }

        entityManager.getTransaction().commit(); // Confirmar transacción

        logger.info("Reserva insertada exitosamente: " + reserva);
    } catch (PersistenceException e) {
        if (entityManager != null && entityManager.getTransaction().isActive()) {
            entityManager.getTransaction().rollback(); // Hacer rollback en caso de error
        }
        logger.severe("Error al insertar la reserva: " + e.getMessage());
        throw new PersistenceException("Error al insertar la reserva", e);
    } finally {
        if (entityManager != null) {
            entityManager.close(); // Cerrar el EntityManager
        }
    }
}





    
    
    //funciona
//    public void insertarReserva(ReservaEntidad reserva) throws PersistenceException {
//    EntityManager entityManager = null;
//    ComputadoraDAO computadoraDAO = new ComputadoraDAO();
//
//    try {
//        entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        ComputadoraEntidad computadora = reserva.getComputadora(); // Obtén la computadora de la reserva
//        if (computadora != null) {
//            // Verificar si la computadora ya existe en la base de datos
//            ComputadoraEntidad existente = computadoraDAO.obtenerComputadoraPorDireccionIP(computadora.getDireccionIP());
//            if (existente == null) {
//                // Persistir nueva computadora si no existe
//                entityManager.persist(computadora);
//            } else {
//                // Asociar la reserva a la computadora existente usando merge
//                reserva.setComputadora(entityManager.merge(existente));
//            }
//        }
//
//        entityManager.persist(reserva); // Persistir la reserva
//        entityManager.getTransaction().commit(); // Confirmar transacción
//        logger.info("Reserva insertada exitosamente: " + reserva);
//    } catch (PersistenceException e) {
//        if (entityManager != null && entityManager.getTransaction().isActive()) {
//            entityManager.getTransaction().rollback(); // Revertir si hay error
//        }
//        throw e;
//    } finally {
//        if (entityManager != null) {
//            entityManager.close(); // Cerrar el EntityManager
//        }
//    }
//}





    
//    public void insertarReserva(ReservaEntidad reserva) throws PersistenceException {
//        EntityManager entityManager = null;
//        try
//        {
//            entityManager = entityManagerFactory.createEntityManager();
//            entityManager.getTransaction().begin();
//
//            entityManager.persist(reserva);
//            entityManager.getTransaction().commit(); // Confirmar transacción
//
//            logger.info("Reserva insertada exitosamente: " + reserva);
//        } catch (PersistenceException e)
//        {
//            if (entityManager != null && entityManager.getTransaction().isActive())
//            {
//                entityManager.getTransaction().rollback();
//            }
//            logger.severe("Error al insertar la reserva: " + e.getMessage());
//            throw new PersistenceException("Error al insertar la reserva", e);
//        } finally
//        {
//            if (entityManager != null)
//            {
//                entityManager.close(); // Cerrar el EntityManager
//            }
//        }
//    }
//    
    

    @Override
    public List<ReservaEntidad> obtenerReservas() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            TypedQuery<ReservaEntidad> query = entityManager.createQuery("SELECT r FROM ReservaEntidad r", ReservaEntidad.class);
            return query.getResultList(); // Retorna la lista de reservas
        } finally
        {
            entityManager.close(); // Asegúrate de cerrar el EntityManager
        }
    }

    @Override
    public ReservaEntidad obtenerReservaPorId(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try
        {
            return entityManager.find(ReservaEntidad.class, id); // Busca la reserva por ID
        } finally
        {
            entityManager.close(); // Asegúrate de cerrar el EntityManager
        }
    }

}
