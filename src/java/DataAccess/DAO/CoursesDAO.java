/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.DAO;

import DataAccess.Entity.Courses;
import DataAccess.Entity.User;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 * DAO de cursos.
 * @author Jefferson
 */
@Stateless
public class CoursesDAO {
    @PersistenceContext(unitName = "BancoPersistenceU")
    private EntityManager em;
    
    /**
     * Guarda un curso en la base de datos.
     * @param course - curso a guardar.
     * @return verdadero en caso de persistir el curso.
     */
    public boolean persist(Courses course) {
        em.persist(course);
        return true;
    }
    
    /**
     * Actualiza los atributos del curso (fecha, nombre y tópico) dado el identificador.
     * @param course - curso a actualizar.
     * @return verdadero en caso de actualizar el curso correctamente.
     */
    public boolean update(Courses course) {
        Courses dbCourse = this.getById(course.getId());
        
        dbCourse.setDate(course.getDate());
        dbCourse.setName(course.getName());
        dbCourse.setTopicID(course.getTopicID());
        
        return true;
    }
    
    /**
     * Elimina el curso que recibe como parámetro.
     * @param course
     * @return verdadero en caso de eliminar correctamente.
     */
    public boolean remove(Courses course) {
        Courses dbCourse = this.getById(course.getId());
        
        em.remove(dbCourse);
        
        return true;
    }
    
    /**
     * Busca un curso de acuerdo a su identificador
     * @param id
     * @return el curso buscado o nulo en caso de no estar en la base de datos.
     */
    public Courses getById(int id) {
        TypedQuery<Courses> q = em.createNamedQuery("Courses.findById", Courses.class);
        q.setParameter("id", id);
        
        Courses course = q.getSingleResult();
        return course;
    }
    
    /**
     * Función que devuelve la lista de cursos de acuerdo a un capacitador
     * @param trainer - capacitador.
     * @return lista de cursos que dicta el capacitador.
     */
    public List<Courses> getByTrainer(User trainer) {
        TypedQuery<Courses> q = em.createNamedQuery("Courses.findByUser", Courses.class);
        q.setParameter("trainer_id", trainer);
        
        List<Courses> myCourses = q.getResultList();
        return myCourses;
    }
    
    /**
     * Obtiene los cursos registrados por el usuario.
     * @param user
     * @return lista con los cursos del usuario.
     */
    public List<Courses> getRegisteredCoursesByUser(User user) { 
        TypedQuery<Courses> q = em.createNamedQuery("Courses.findRegisteredCourses", Courses.class);
        q.setParameter("user_id", user);
        
        List<Courses> myRegisteredCourses = q.getResultList();
        return myRegisteredCourses;
    }
    
    /**
     * Obtiene los cursos en lo que no se ha registrado el usuario.
     * @param user
     * @return lista de cursos no registrados.
     */
    public List<Courses> getUnregisteredCoursesByUser(User user) { 
        TypedQuery<Courses> q = em.createNamedQuery("Courses.findUnregisteredCourses", Courses.class);
        q.setParameter("user_id", user);
        
        List<Courses> myRegisteredCourses = q.getResultList();
        return myRegisteredCourses;
    }
    
    /**
     * Obtiene los cursos registrados después de una fecha dada.
     * @param user
     * @param date
     * @return lista de cursos registrados.
     */
    public List<Courses> getRegisteredCoursesByDate(User user, Date date) { 
        TypedQuery<Courses> q = em.createNamedQuery("Courses.findRegisteredCoursesByDate", Courses.class);
        q.setParameter("user_id", user);
        q.setParameter("date", date);
        
        List<Courses> myRegisteredCourses = q.getResultList();
        return myRegisteredCourses;
    }
}
