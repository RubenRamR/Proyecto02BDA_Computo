/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import ENUM_P.Estado;
import Entidades.ComputadoraEntidad;
import InterfacesDAO.IComputadoraDAO;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
        try {
            em.getTransaction().begin();

            // Verificar si ya existe una computadora con la misma dirección IP
            if (obtenerComputadoraPorDireccionIP(computadora.getDireccionIP()) != null) {
                throw new PersistenceException("Ya existe una computadora con la dirección IP: " + computadora.getDireccionIP());
            }

            em.persist(computadora);
            em.getTransaction().commit();
            logger.info("Computadora insertada exitosamente: " + computadora);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.severe("Error al insertar la computadora: " + e.getMessage());
            throw new PersistenceException("Error al insertar la computadora: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    //funciona
//    public void insertarComputadora(ComputadoraEntidad computadora) throws PersistenceException {
//        EntityManager em = entityManagerFactory.createEntityManager();
//        try {
//            em.getTransaction().begin();
//            logger.info("Insertando computadora: " + computadora);
//
//            em.persist(computadora);
//
//            em.getTransaction().commit();
//            System.out.println("Computadora insertada exitosamente.");
//        } catch (Exception e) {
//            if (em.getTransaction().isActive()) {
//                em.getTransaction().rollback();
//            }
//            System.err.println("Error al insertar la computadora: " + e.getMessage());
//            throw new PersistenceException("Error al insertar la computadora: " + e.getMessage(), e);
//        } finally {
//            em.close();
//        }
//    }
    @Override
    public void editarComputadora(ComputadoraEntidad computadora) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(computadora); // Actualiza la computadora existente
            entityManager.getTransaction().commit();
            logger.info("Computadora actualizada: " + computadora.getId());
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Deshacer cambios en caso de error
            }
            logger.severe("Error al actualizar la computadora: " + e.getMessage());
            throw new PersistenceException("Error al actualizar la computadora: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void eliminarComputadoraPorID(Long id) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            ComputadoraEntidad computadora = entityManager.find(ComputadoraEntidad.class, id);
            if (computadora != null) {
                entityManager.remove(computadora); // Elimina la computadora
                logger.info("Computadora eliminada con ID: " + id);
            } else {
                logger.warning("Computadora no encontrada con ID: " + id);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback(); // Deshacer cambios en caso de error
            }
            logger.severe("Error al eliminar la computadora: " + e.getMessage());
            throw new PersistenceException("Error al eliminar la computadora: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public ComputadoraEntidad obtenerComputadoraPorEstudiante(long idEstudiante) throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<ComputadoraEntidad> query = cb.createQuery(ComputadoraEntidad.class);
            Root<ComputadoraEntidad> root = query.from(ComputadoraEntidad.class);
            query.select(root).where(cb.equal(root.get("estudiante").get("id"), idEstudiante)); // Acceso correcto a la relación

            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            logger.warning("No se encontró computadora para el estudiante con ID: " + idEstudiante);
            return null;
        } catch (Exception e) {
            logger.severe("Error al obtener la computadora por estudiante: " + e.getMessage());
            throw new PersistenceException("Error al obtener la computadora por estudiante", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ComputadoraEntidad> obtenerComputadoraPorIdEstudiante(long idEstudiante) throws PersistenceException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ComputadoraEntidad> query = cb.createQuery(ComputadoraEntidad.class);
        Root<ComputadoraEntidad> root = query.from(ComputadoraEntidad.class);
        query.select(root).where(cb.equal(root.get("estudiante").get("id"), idEstudiante));

        return entityManager.createQuery(query).getResultList();
    } catch (Exception e) {
        logger.severe("Error al obtener la computadora por estudiante con ID: " + idEstudiante + ". Mensaje: " + e.getMessage());
        throw new PersistenceException("Error al obtener la computadora por estudiante", e);
    } finally {
        entityManager.close();
    }
}


    
    @Override
    public ComputadoraEntidad obtenerComputadoraPorID(Long id) throws PersistenceException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ComputadoraEntidad> query = cb.createQuery(ComputadoraEntidad.class);
        Root<ComputadoraEntidad> root = query.from(ComputadoraEntidad.class);
        root.fetch("centroComputo", JoinType.LEFT); // Carga el centro de cómputo asociado

        // Cambiar "idComputadora" por "id"
        query.select(root).where(cb.equal(root.get("id"), id));

        try {
            return entityManager.createQuery(query).getSingleResult();
        } catch (NoResultException e) {
            logger.warning("No se encontró ninguna computadora con ID: " + id);
            return null; // Manejo adecuado si no se encuentra
        }
    } catch (Exception e) {
        logger.severe("Error al obtener la computadora por ID: " + e.getMessage());
        throw new PersistenceException("Error al obtener la computadora por ID: " + e.getMessage(), e);
    } finally {
        entityManager.close(); // Asegúrate de cerrar el EntityManager
    }
}



    @Override
public ComputadoraEntidad obtenerComputadoraPorDireccionIP(String direccionIP) throws PersistenceException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ComputadoraEntidad> query = cb.createQuery(ComputadoraEntidad.class);
        Root<ComputadoraEntidad> root = query.from(ComputadoraEntidad.class);
        query.select(root).where(cb.equal(root.get("direccionIP"), direccionIP));

        // Este método devolverá solo un resultado, pero en caso de duplicados
        // se lanzará una excepción.
        return entityManager.createQuery(query).getSingleResult();
    } catch (NoResultException e) {
        return null; // Retorna null si no se encuentra
    } catch (NonUniqueResultException e) {
        logger.warning("Se encontraron múltiples computadoras con la dirección IP: " + direccionIP);
        return null; // O manejarlo de otra manera
    } catch (Exception e) {
        logger.severe("Error al obtener la computadora por dirección IP: " + e.getMessage());
        throw new PersistenceException("Error al obtener la computadora por dirección IP", e);
    } finally {
        entityManager.close(); // Cerrar el EntityManager
    }
}


    @Override
    public void actualizarComputadora(ComputadoraEntidad computadora) throws PersistenceException {
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();

            // Buscar la computadora existente
            ComputadoraEntidad existente = entityManager.find(ComputadoraEntidad.class, computadora.getId());
            if (existente == null) {
                throw new PersistenceException("La computadora con ID " + computadora.getId() + " no existe.");
            }

            // Verificar si ya existe otra computadora con la misma dirección IP
            ComputadoraEntidad otraComputadora = obtenerComputadoraPorDireccionIP(computadora.getDireccionIP());
            if (otraComputadora != null && !otraComputadora.getId().equals(computadora.getId())) {
                throw new PersistenceException("Ya existe una computadora con la dirección IP: " + computadora.getDireccionIP());
            }

            // Actualizar solo los campos necesarios
            existente.setEstado(computadora.getEstado());
            existente.setNombreAlumno(computadora.getNombreAlumno());
            // ... otros campos según sea necesario

            entityManager.getTransaction().commit(); // Confirmar transacción

            logger.info("Computadora actualizada exitosamente: " + existente);
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

//    public void actualizarComputadora(ComputadoraEntidad computadora) throws PersistenceException {
//    EntityManager entityManager = null;
//    try {
//        entityManager = entityManagerFactory.createEntityManager();
//        entityManager.getTransaction().begin();
//
//        // Verificar si ya existe una computadora con la misma dirección IP
//        ComputadoraEntidad existente = obtenerComputadoraPorDireccionIP(computadora.getDireccionIP());
//        if (existente != null && !existente.getId().equals(computadora.getId())) {
//            // Si ya existe una computadora con la misma IP y es diferente de la actual, lanza una excepción
//            throw new PersistenceException("Ya existe una computadora con la dirección IP: " + computadora.getDireccionIP());
//        }
//
//        // Si no hay conflictos, proceder a actualizar
//        entityManager.merge(computadora);
//        entityManager.getTransaction().commit(); // Confirmar transacción
//
//        logger.info("Computadora actualizada exitosamente: " + computadora);
//    } catch (PersistenceException e) {
//        if (entityManager != null && entityManager.getTransaction().isActive()) {
//            entityManager.getTransaction().rollback(); // Hacer rollback en caso de error
//        }
//        logger.severe("Error al actualizar la computadora: " + e.getMessage());
//        throw new PersistenceException("Error al actualizar la computadora", e);
//    } finally {
//        if (entityManager != null) {
//            entityManager.close(); // Cerrar el EntityManager
//        }
//    }
//}
    @Override
    public List<ComputadoraEntidad> obtenerTodasLasComputadoras() throws PersistenceException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<ComputadoraEntidad> query = entityManager.createQuery("SELECT c FROM ComputadoraEntidad c", ComputadoraEntidad.class);
            return query.getResultList(); // Retorna la lista de computadoras
        } catch (Exception e) {
            logger.severe("Error al obtener todas las computadoras: " + e.getMessage());
            throw new PersistenceException("Error al obtener todas las computadoras: " + e.getMessage(), e);
        } finally {
            entityManager.close();
        }
    }
}
