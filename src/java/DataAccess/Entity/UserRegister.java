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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jefferson
 */
@Entity
@Table(name = "user_register")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserRegister.findAll", query = "SELECT u FROM UserRegister u"),
    @NamedQuery(name = "UserRegister.findByRegisterID", query = "SELECT u FROM UserRegister u WHERE u.registerID = :registerID"),
    @NamedQuery(name = "UserRegister.findByStatus", query = "SELECT u FROM UserRegister u WHERE u.status = :status")})
public class UserRegister implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "register_ID")
    private Integer registerID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "courses_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Courses coursesID;
    @JoinColumn(name = "user_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User userID;

    public UserRegister() {
    }

    public UserRegister(Integer registerID) {
        this.registerID = registerID;
    }

    public UserRegister(Integer registerID, String status) {
        this.registerID = registerID;
        this.status = status;
    }

    public Integer getRegisterID() {
        return registerID;
    }

    public void setRegisterID(Integer registerID) {
        this.registerID = registerID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Courses getCoursesID() {
        return coursesID;
    }

    public void setCoursesID(Courses coursesID) {
        this.coursesID = coursesID;
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
        if (!(object instanceof UserRegister)) {
            return false;
        }
        UserRegister other = (UserRegister) object;
        if ((this.registerID == null && other.registerID != null) || (this.registerID != null && !this.registerID.equals(other.registerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DataAccess.Entity.UserRegister[ registerID=" + registerID + " ]";
    }
    
}
