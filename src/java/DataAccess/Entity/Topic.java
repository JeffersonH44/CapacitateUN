/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Abstracción de la tabla topic de la base de datos
 * @author Jefferson
 */
@Entity
@Table(name = "topic")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t"),
    @NamedQuery(name = "Topic.findById", query = "SELECT t FROM Topic t WHERE t.id = :id"),
    @NamedQuery(name = "Topic.findByArea", query = "SELECT t FROM Topic t WHERE t.area = :area"),
    @NamedQuery(name = "Topic.findByName", query = "SELECT t FROM Topic t WHERE t.name = :name"),
    @NamedQuery(name = "Topic.findByTrainer", query = "SELECT DISTINCT t FROM Topic AS t JOIN ExperienceRegister AS er WHERE er.userID = :trainer_id")
})
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Area")
    private String area;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topicID")
    private Collection<Courses> coursesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "topicID")
    private Collection<ExperienceRegister> experienceRegisterCollection;

    public Topic() {
    }

    public Topic(Integer id) {
        this.id = id;
    }

    public Topic(Integer id, String area) {
        this.id = id;
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    /**
     * Setter del identificador del tópico
     * 
     * @param id 
     * @throws NullPointerException - En caso de que el tópico sea nulo.
     */
    public void setId(Integer id) {
        if(id == null) throw new NullPointerException("El id del tópico no puede ser nulo");
        
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    /**
     * Setter del área del tópico.
     * 
     * @param area 
     * @throws NullPointerException - En caso de que el área sea nula.
     * @throws IllegalArgumentException - En caso de que el área sea vacía.
     */
    public void setArea(String area) {
        if(area == null) throw new NullPointerException("El área del tópico no puede ser nula");
        if(area.equals("")) throw new IllegalArgumentException("El área del tópico no puede ser vacía.");
        
        this.area = area;
    }

    public String getName() {
        return name;
    }

    /**
     * Setter del nombre del tópico.
     * 
     * @param name 
     * @throws NullPointerException - En caso de que el nombre del tópico sea nulo.
     * @throws IllegalArgumentException - En caso de que el nombre de tópico sea vacío.
     */
    public void setName(String name) {
        if(name == null) throw new NullPointerException("El nombre del tópico no puede ser nulo");
        if(name.equals(name)) throw new IllegalArgumentException("El nombre del área no puede estar vacía");
        
        this.name = name;
    }

    @XmlTransient
    public Collection<Courses> getCoursesCollection() {
        return coursesCollection;
    }

    public void setCoursesCollection(Collection<Courses> coursesCollection) {
        this.coursesCollection = coursesCollection;
    }

    @XmlTransient
    public Collection<ExperienceRegister> getExperienceRegisterCollection() {
        return experienceRegisterCollection;
    }

    public void setExperienceRegisterCollection(Collection<ExperienceRegister> experienceRegisterCollection) {
        this.experienceRegisterCollection = experienceRegisterCollection;
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
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Área: " + area + ", Tema: " + name;
    }
}


