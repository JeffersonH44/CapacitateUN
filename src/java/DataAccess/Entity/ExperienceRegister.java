/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Manu
 */
@Entity
@Table(name = "experience_register")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExperienceRegister.findAll", query = "SELECT e FROM ExperienceRegister e"),
    @NamedQuery(name = "ExperienceRegister.findByRegisterID", query = "SELECT e FROM ExperienceRegister e WHERE e.registerID = :registerID"),
    @NamedQuery(name = "ExperienceRegister.findByTrainerID", query = "SELECT e FROM ExperienceRegister e WHERE e.userID = :TrainerID")})
public class ExperienceRegister implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "register_ID")
    private Integer registerID;
    @JoinColumn(name = "topic_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Topic topicID;
    @JoinColumn(name = "user_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User userID;

    public ExperienceRegister() {
    }

    public ExperienceRegister(Integer registerID) {
        this.registerID = registerID;
    }

    public Integer getRegisterID() {
        return registerID;
    }

    public void setRegisterID(Integer registerID) {
        this.registerID = registerID;
    }

    public Topic getTopicID() {
        return topicID;
    }

    public void setTopicID(Topic topicID) {
        this.topicID = topicID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (registerID != null ? registerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExperienceRegister)) {
            return false;
        }
        ExperienceRegister other = (ExperienceRegister) object;
        if ((this.registerID == null && other.registerID != null) || (this.registerID != null && !this.registerID.equals(other.registerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.ExperienceRegister[ registerID=" + registerID + " ]";
    }
    
}
