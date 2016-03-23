/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entidad que representa una abstracción una fila en la tabla courses de la base de datos.
 * @author Jefferson
 */
@Entity
@Table(name = "courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.findById", query = "SELECT c FROM Courses c WHERE c.id = :id"),
    @NamedQuery(name = "Courses.findByName", query = "SELECT c FROM Courses c WHERE c.name = :name"),
    @NamedQuery(name = "Courses.findByDate", query = "SELECT c FROM Courses c WHERE c.date = :date"),
    @NamedQuery(name = "Courses.findByUser", query = "SELECT c FROM Courses c WHERE c.trainerID = :trainer_id"),
    @NamedQuery(name = "Courses.findRegisteredCourses", query = "SELECT c FROM UserRegister ur JOIN ur.coursesID c WHERE ur.userID = :user_id"),
    @NamedQuery(name = "Courses.findUnregisteredCourses", query = "SELECT c FROM Courses AS c WHERE NOT EXISTS (SELECT ur FROM UserRegister AS ur WHERE ur.coursesID = c AND ur.userID = :user_id)"),
    @NamedQuery(name = "Courses.findRegisteredCoursesByDate", query = "SELECT c FROM UserRegister ur JOIN ur.coursesID c WHERE ur.userID = :user_id and ur.coursesID.date > :date ORDER BY ur.coursesID.date")
})
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "topic_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Topic topicID;
    @JoinColumn(name = "trainer_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User trainerID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coursesID")
    private Collection<UserRegister> userRegisterCollection;

    public Courses() {
    }

    public Courses(Integer id) {
        this.id = id;
    }

    public Courses(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    /**
     * Setter del identificador del curso.
     * 
     * @param id 
     * @throws NullPointerException - en caso de ser nulo el Id
     */
    public void setId(Integer id) {
        if(id == null) {
            throw new NullPointerException("El id del curos no puede ser nulo");
        }
        
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    /**
     * setter del nombre del curso
     * 
     * @param name - nombre del curso
     * @throws NullPointerException - en caso de ser nulo el nombre.
     * @throws IllegalArgumentException - en caso de que el nombre sea vacío.
     */
    public void setName(String name) {
        if(name == null) throw new NullPointerException("El nombre del curso no puede ser nulo");
        if(name.equals("")) throw new IllegalArgumentException("El nombre no puede ser vacío");
        
        this.name = name;
    }

    public Date getDate() {
        return date;
    }
    
    /**
     * Setter de fecha del curso.
     * 
     * @param date - fecha del curso a dar.
     * @throws NullPointerException - en caso de que la fecha sea nula.
     * 
     */
    public void setDate(Date date) {
        if(date == null) throw new NullPointerException("La fecha del curso no puede ser nula");
        
        this.date = date;
    }

    public Topic getTopicID() {
        return topicID;
    }

    
    /**
     * Setter del tópico del curso.
     * 
     * @param topicID 
     * @throws NullPointerException - En caso de que el tópico del curso sea nulo.
     */
    public void setTopicID(Topic topicID) {
        if(topicID == null) throw new NullPointerException("El tópico del curso no puede ser nulo");
        
        this.topicID = topicID;
    }
    
    public User getTrainerID() {
        return trainerID;
    }

    /**
     * Setter del capacitador del curso.
     * 
     * @param trainerID 
     * @throws NullPointerException - En caso de que el capacitador del curso sea nulo.
     */
    public void setTrainerID(User trainerID) {
        if(trainerID == null) throw new NullPointerException("El capacitador no puede ser nulo");
        
        this.trainerID = trainerID;
    }
    
    @XmlTransient
    public Collection<UserRegister> getUserRegisterCollection() {
        return userRegisterCollection;
    }
    
    /**
     * Setter del registro del curso.
     * 
     * @param userRegisterCollection 
     */
    public void setUserRegisterCollection(Collection<UserRegister> userRegisterCollection) {
        this.userRegisterCollection = userRegisterCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "id: " + id + " Nombre: " + name;
    }
}
