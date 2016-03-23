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
 * Abstracción de la tabla user de la base de datos.
 * @author Jefferson
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id"),
    @NamedQuery(name = "User.findByRole", query = "SELECT u FROM User u WHERE u.role = :role"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final int USER = 1;
    public static final int TRAINER = 2;
    public static final int ADMIN = 3;
    
    @Id
    @Basic(optional = false)
    @NotNull(message = "ID")
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull(message = "role")
    @Column(name = "role")
    private int role;
    @Basic(optional = false)
    @NotNull(message = "username")
    @Size(min = 8, max = 16)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull(message = "password")
    @Size(min = 1, max = 32)
    @Column(name = "password")
    private String password;
    @Size(max = 20)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 20)
    @Column(name = "lastname")
    private String lastname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<UserRegister> userRegisterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userID")
    private Collection<ExperienceRegister> experienceRegisterCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trainerID")
    private Collection<Courses> coursesCollection;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, int role, String username, String password) {
        this.id = id;
        this.role = role;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }
    
    /**
     * Setter del identificador del usuario.´
     * 
     * @param id 
     * @throws NullPointerException - En caso de que el id del usuario sea nulo.
     * @throws IllegalArgumentException - En caso de que el id de usuario sea negativo.
     */
    public void setId(Integer id) {
        if(id == null) throw new NullPointerException("el id de usuario no puede ser nulo.");
        if(id < 0) throw new IllegalArgumentException("el id de usuario no puede ser negativo.");
        
        this.id = id;
    }

    public int getRole() {
        return role;
    }
    
    /**
     * Devuelve el rol en forma de string para las vistas
     * @return rol
     */
    public String getStringRole() {
        switch(role) {
            case USER:
                return "Usuario";
            case TRAINER:
                return "Capacitador";
            case ADMIN:
                return "Administrador";
        }
        
        return "";
    }

    /**
     * Setter del rol de usuario.
     * 
     * @param role 
     * @throws IllegalArgumentException - En caso de que el rol no esté dentro de los roles válidos
     */
    public void setRole(int role) {
        if(role != USER && role != TRAINER && role != ADMIN) throw new IllegalArgumentException("El rol del usuario no es válido.");
        
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    /**
     * Setter del usuario.
     * 
     * @param username 
     * @throws NullPointerException - En caso del que el usuario sea nulo.
     * @throws IllegalArgumentException - En caso de que el usuario esté vacío o no tenga entre 8 y 20 caracteres.
     */
    public void setUsername(String username) {
        if(username == null) throw new NullPointerException("El usuario no puede ser nulo.");
        if(username.equals("")) throw new IllegalArgumentException("El usuario no puede ser vacío");
        if(username.length() < 8 || username.length() > 20) throw new IllegalArgumentException("El usuario debe estar entre 8 y 20 caracteres.");
        
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * Setter de la contraseña del usuario.
     * @param password 
     * @throws NullPointerException - En caso de que la contraseña sea nula.
     * @throws IllegalArgumentException - En caso de que la contraseña sea vacía o no cumpla las restricciones.
     */
    public void setPassword(String password) {
        if(password == null) throw new NullPointerException("La contraseña no puede ser nula.");
        if(password.equals("")) throw new IllegalArgumentException("La contraseña no puede ser vacía");
        if(password.length() < 8 || password.length() > 20) throw new IllegalArgumentException("La contraseña debe estar entre 8 y 20 caracteres.");
        
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }
    
    /**
     * Setter del los nombres del usuario.
     * @param firstname
     * @throws NullPointerException - en caso de que nombre sea nulo.
     * @throws IllegalArgumentException - en caso de que el nombre sea vacío.
     */
    public void setFirstname(String firstname) {
        if(firstname == null) throw new NullPointerException("Los nombres del usuario no puede ser nulo.");
        if(firstname.equals("")) throw new IllegalArgumentException("Los nombres del usuario no pueden ser vacios");
        
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    /**
     * Setter del los apellidos del usuario.
     * @param lastname 
     * @throws NullPointerException - en caso de que los apellidos del usuario sean nulos.
     * @throws IllegalArgumentException - en caso de que los apellidos del usuario sea vacío.
     */
    public void setLastname(String lastname) {
        if(firstname == null) throw new NullPointerException("Los apellidos del usuario no puede ser nulo.");
        if(firstname.equals("")) throw new IllegalArgumentException("Los apellidos del usuario no pueden ser vacios");
        
        this.lastname = lastname;
    }
    
    @XmlTransient
    public Collection<Courses> getCoursesCollection() {
        return coursesCollection;
    }

    public void setCoursesCollection(Collection<Courses> coursesCollection) {
        this.coursesCollection = coursesCollection;
    }

    @XmlTransient
    public Collection<UserRegister> getUserRegisterCollection() {
        return userRegisterCollection;
    }

    public void setUserRegisterCollection(Collection<UserRegister> userRegisterCollection) {
        this.userRegisterCollection = userRegisterCollection;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }
}
