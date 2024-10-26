/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Entidades.ComputadoraEntidad;
import InterfacesDAO.IComputadoraDAO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 *
 * @author rramirez
 */
public class ComputadoraDAO {

    // Suponiendo que tengas un EntityManagerFactory configurado
    private EntityManagerFactory entityManagerFactory;

    public ComputadoraDAO() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("LaboratorioComputoJPA");
    }

    public void insertarComputadora(ComputadoraEntidad computadora) throws PersistenceException {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();

            // Inserta la computadora (JPA se encargará de persistir las relaciones si están en cascade)
            em.persist(computadora);

            em.getTransaction().commit();
            System.out.println("Computadora insertada exitosamente.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error al insertar la computadora: " + e.getMessage());
            throw new PersistenceException("Error al insertar la computadora: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
