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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Jefferson
 */
@Entity
@Table(name = "courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.findById", query = "SELECT c FROM Courses c WHERE c.id = :id"),
    @NamedQuery(name = "Courses.findByName", query = "SELECT c FROM Courses c WHERE c.name = :name")})
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

    public Courses(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Topic getTopicID() {
        return topicID;
    }

    public void setTopicID(Topic topicID) {
        this.topicID = topicID;
    }

    public User getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(User trainerID) {
        this.trainerID = trainerID;
    }

    @XmlTransient
    public Collection<UserRegister> getUserRegisterCollection() {
        return userRegisterCollection;
    }

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
        return "DataAccess.Entity.Courses[ id=" + id + " ]";
    }
    
}
