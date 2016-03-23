/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Trainer;

import BusinessLogic.CourseManagement.CoursesRegister;
import DataAccess.DAO.CourseDAO.CoursesDAO;
import DataAccess.DAO.TopicDAO.TopicDAO;
import DataAccess.Entity.Courses;
import DataAccess.Entity.Topic;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Bean encargado de la página principal 
 * @author Jefferson
 */
@Named
@ConversationScoped
public class TrainerBean implements Serializable {
    
    @EJB
    private CoursesDAO coursesDAO;
    @EJB
    private TopicDAO topicDAO;
    @Inject
    private Conversation conversation;
    
    private List<Courses> myCourses;
    private List<Topic> myExperience;
    private Courses currentCourse;
    private Topic currentTopic;
    
    /**
     * Obtiene la lista de cursos y temas relacionados con el capacitador de la
     * sesión.
     */
    @PostConstruct
    public void update() {
        CoursesRegister cr = new CoursesRegister();
        myCourses = cr.getCoursesByTrainer(coursesDAO);
        setMyExperience(cr.getAvailableTopics(topicDAO));
    }
    
    /**
     * al ser ConversationScoped, esto se usa para editar los tópicos y cursos,
     * con esto evitar un SessionScoped más difícil de manejar
     */
    public void initConversation() {
        conversation.begin();
    }
    
    /**
     * Cuando cualquier conversación del scope termina, regresa a la página 
     * príncipal.
     * @return Página principal de los capacitadores.
     */
    private String endConversation() {
        conversation.end();
        return "trainerIndex.xhtml";
    }

    /**
     * @return the myCourses
     */
    public List<Courses> getMyCourses() {
        return myCourses;
    }

    /**
     * @param myCourses the myCourses to set
     */
    public void setMyCourses(List<Courses> myCourses) {
        this.myCourses = myCourses;
    }

    /**
     * @return the myExperience
     */
    public List<Topic> getMyExperience() {
        return myExperience;
    }

    /**
     * @param myExperience the myExperience to set
     */
    public void setMyExperience(List<Topic> myExperience) {
        this.myExperience = myExperience;
    }
    
    /**
     * @return the currentCourse
     */
    public Courses getCurrentCourse() {
        return currentCourse;
    }

    /**
     * @param currentCourse the currentCourse to set
     */
    public void setCurrentCourse(Courses currentCourse) {
        this.currentCourse = currentCourse;
    }

    /**
     * @return the currentTopic
     */
    public Topic getCurrentTopic() {
        return currentTopic;
    }

    /**
     * @param currentTopic the currentTopic to set
     */
    public void setCurrentTopic(Topic currentTopic) {
        this.currentTopic = currentTopic;
    }
    
    /**
     * Redirecciona para editar un curso seleccionado.
     * @param selectedCourse
     * @return Página de edición del curso (se usa "?faces-redirect=true" para
     * pasar el id de la conversación para mantener el bean)
     */
    public String editCourse(Courses selectedCourse) {
        this.setCurrentCourse(selectedCourse);
        this.initConversation();
        return "editCourse.xhtml?faces-redirect=true";
    }
    
    /**
     * Redirecciona para editar un tema seleccionado.
     * @param selectedTopic
     * @return Página de edición del tema (se usa "?faces-redirect=true" para
     * pasar el id de la conversación para mantener el bean)
     */
    public String editTopic(Topic selectedTopic) {
        this.setCurrentTopic(selectedTopic);
        this.initConversation();
        return "editTopic.xhtml?faces-redirect=true";
    }
    
    /**
     * Guarda los cambios del curso editado.
     * @return Página príncipal de los capacitadores.
     */
    public String saveCourseChanges() {
        this.endConversation();
        coursesDAO.update(currentCourse);
        return "trainerIndex.xhtml";
    }
    
    /**
     * Elimina un curso del capacitador de la sesión
     * @param course
     * @return La página principal para refrescar.
     */
    public String removeCourse(Courses course) {
        coursesDAO.remove(course);
        return "trainerIndex.xhtml?faces-redirect=true";
    }
    
    /**
     * Guarda los cambios en el tema seleccionado.
     * @return Página príncipal de los capacitadores.
     */
    public String saveTopicChanges() {
        this.endConversation();
        topicDAO.update(currentTopic);
        return "trainerIndex.xhtml";
    }
    
}
