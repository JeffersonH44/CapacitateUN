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
    @NamedQuery(name = "UserRegister.findByStatus", query = "SELECT u FROM UserRegister u WHERE u.status = :status"),
    @NamedQuery(name = "UserRegister.findRegister", query = "SELECT u FROM UserRegister u WHERE u.coursesID = :course_id and u.userID = :user_id")
})
public class UserRegister implements Serializable {
    
    public static String ACTIVE = "activo";
    public static String RETIRED = "retirado";

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "register_ID")
    private Integer registerID;
    @JoinColumn(name = "courses_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Courses coursesID;
    @JoinColumn(name = "user_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User userID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;

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

    /**
     * 
     * @param registerID 
     */
    public void setRegisterID(Integer registerID) {
        if(registerID == null) throw new NullPointerException("El id de registro de usuarios no puede ser nulo.");
        
        this.registerID = registerID;
    }


    public Courses getCoursesID() {
        return coursesID;
    }
    
    /**
     * 
     * @param coursesID 
     */
    public void setCoursesID(Courses coursesID) {
        if(coursesID == null) throw new NullPointerException("El curso del registro de usuarios no puede ser nulo.");
        
        this.coursesID = coursesID;
    }

    public User getUserID() {
        return userID;
    }

    /**
     * 
     * @param userID 
     */
    public void setUserID(User userID) {
        if(userID == null) throw new NullPointerException("El usuario del registro de usuarios no puede ser nulo");
        
        this.userID = userID;
    }
    
    public String getStatus() {
        return status;
    }

    /**
     * Setter del estatus del registro de usuarios.
     * 
     * @param status
     * @throws NullPointerException - En caso de que el estatus del registro sea nulo.
     * @throws IllegalArgumentException - En caso de que el estado no sea activo o retirado.
     */
    public void setStatus(String status) {
        if(status == null) throw new NullPointerException("El estatus del registro de usuarios no puede ser nulo.");
        if(!status.equals(ACTIVE) && !status.equals(RETIRED)) throw new IllegalArgumentException("el estatus no es válido.");
        
        this.status = status;
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
