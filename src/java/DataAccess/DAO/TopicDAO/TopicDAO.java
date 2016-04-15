/*
<<<<<<< HEAD
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
=======
 * To change this template, choose Tools | Templates
>>>>>>> ExperienceManagement
 * and open the template in the editor.
 */
package DataAccess.DAO.TopicDAO;


import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import DataAccess.Entity.Topic;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase encargada del acceso a datos de los tópicos de las capacitaciones
 * @author Manu
 */
@Stateless
public class TopicDAO implements Serializable {
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    //public EntityManagerFactory emf = Persistence.createEntityManagerFactory("BancoPersistenceU");
    
    /**
     * Guarda un nuevo tópico dentro de la base de datos.
     * @param topic - tópico a guardar
     * @return verdadero en caso de que se guarde exitosamente.
     */
    public boolean persist(Topic topic) {
        //em = emf.createEntityManager();
        em.persist(topic);
        return true;
    }
    
    /**
     * Actualiza un tópico de acuerdo a un id, se puede modificar el área y el nombre del tópico.
     * @param topic - tópico a actualizar.
     * @return verdadero en caso de que se actualice correctamente.
     */
    public boolean update(Topic topic) {
        Topic dbTopic = this.getById(topic.getId());
        
        dbTopic.setArea(topic.getArea());
        dbTopic.setName(topic.getName());
        
        return true;
    }
    
    /**
     * Obtiene el tópico de un curso.
     * @param course - curso a obtener el tópico
     * @return Tópico asociado al curso o null en caso de no encontrarlo.
     */
    public Topic searchByCourse(Courses course) {
        //EntityManager em = emf.createEntityManager();
        
        Topic topic;
        Query q = em.createNamedQuery("Topic.findById");
        q.setParameter("id", course.getTopicID());
        
        topic = (Topic) q.getSingleResult();
                
        return topic;
    }
    
    /**
     * Obtiene la lista de tópicos que maneja el capacitador.
     * @param trainer - capacitador
     * @return lista con los tópicos encontrados.
     */
    public List<Topic> getTopicsByTrainer(User trainer) {
        //EntityManager em = emf.createEntityManager();
        TypedQuery<Topic> q = em.createNamedQuery("Topic.findByTrainer", Topic.class);
        q.setParameter("trainer_id", trainer);
        
        List<Topic> result = q.getResultList();
        return result;
    }
    
    /**
     * Obtiene un tópico a partir del identificador.
     * @param id - identificador del tópico.
     * @return Tópico encontrado o null en caso contrario.
     */
    public Topic getById(int id) {
        TypedQuery<Topic> q = em.createNamedQuery("Topic.findById", Topic.class);
        q.setParameter("id", id);
        
        Topic topic = q.getSingleResult();
        return topic;
    }
}
