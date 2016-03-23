/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.ExperienceRegister;
import DataAccess.Entity.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Clase encargada del acceso a datos del registro de experiencias.
 * @author Manu
 */
@Stateless
public class ExperienceDAO {
    
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    
    /**
     * Guarda una nueva experiencia en la base de datos.
     * @param experience - experiencia a guardar.
     * @return verdadero en caso de persistir la nueva experiencia.
     */
    public boolean persist(ExperienceRegister experience) {
        em.persist(experience);
        return true;
    }
    
    /**
     * Obtiene un registro de experiencia de acuerdo con el capacitador.
     * @param Trainer - capacitador.
     * @return registro de experiencia del capacitador.
     */
    public ExperienceRegister searchByTrainerID(User Trainer) {
        
        //EntityManager em = emf1.createEntityManager();
        ExperienceRegister experienceRegister = null;
        Query query = em.createNamedQuery("ExperienceRegister.findByTrainerID");
        query.setParameter("TrainerID", Trainer.getId());
        
        experienceRegister = (ExperienceRegister) query.getSingleResult();
        return experienceRegister;
    }
}
