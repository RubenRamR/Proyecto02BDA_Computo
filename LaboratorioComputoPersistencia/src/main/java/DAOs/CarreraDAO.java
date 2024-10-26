/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.CarreraEntidad;
import InterfacesDAO.ICarreraDAO;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

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
            TypedQuery<CarreraEntidad> query = entityManager.createQuery(
                    "SELECT c FROM CarreraEntidad c WHERE c.nombre = :nombre", CarreraEntidad.class);
            query.setParameter("nombre", nombre);
            return query.getSingleResult(); // Devuelve la carrera existente
        } catch (NoResultException e)
        {
            throw new Exception("No se encontró la carrera con nombre: " + nombre, e);
        } finally
        {
            entityManager.close();
        }
    }

    public CarreraEntidad obtenerCarreraPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try
        {
            return em.find(CarreraEntidad.class, id);
        } catch (Exception e)
        {
            // Manejo de errores, si es necesario
            e.printStackTrace();
            return null; // Retorna null si ocurre un error
        } finally
        {
            em.close(); // Asegúrate de cerrar el EntityManager para liberar recursos
        }
    }

}
