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

    private EntityManagerFactory entityManagerFactory;

    public CarreraDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    @Override
    public CarreraEntidad obtenerCarreraPorNombre(String nombreCarrera) throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        try
        {
            TypedQuery<CarreraEntidad> query = em.createQuery(
                    "SELECT c FROM CarreraEntidad c WHERE c.nombre = :nombre",
                    CarreraEntidad.class
            );
            query.setParameter("nombre", nombreCarrera);

            List<CarreraEntidad> carreras = query.getResultList();
            if (carreras.isEmpty())
            {
                return null; // O maneja el caso donde no se encuentra
            } else if (carreras.size() > 1)
            {
                throw new IllegalStateException("More than one result was returned.");
            }
            return carreras.get(0);

        } catch (Exception e)
        {
            throw new Exception("Error al obtener la carrera por el nombre: " + e.getMessage(), e);
        } finally
        {
            em.close();
        }
        
    }

}
