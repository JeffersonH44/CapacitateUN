/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Client;

import BusinessLogic.CourseManagement.CertificateGenerator;
import DataAccess.DAO.UserDAO;
import DataAccess.DAO.CoursesDAO;
import DataAccess.Entity.Courses;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author manu
 */
@ManagedBean
@SessionScoped
public class CertificateBean implements Serializable {

    private Courses course;
    private String message;
    @EJB
    private UserDAO userDAO;

    /**
     * @return the course
     */
    public Courses getCourse() {
        return course;
    }

    /**
     * @param course the password to set
     */
    public void setCourse(Courses course) {
        this.course = course;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
    
    /**
     * @return the id
     */

    
    public String generateCertification(Courses course) {
        CertificateGenerator cg = new CertificateGenerator();
        
        boolean saved = cg.createPDF(course);
        if(saved) {
            message = "El certificado se ha creado correctamente";
        } else {
            message = "El certificado no se ha podido crear";
        }
        
        return "getCertificate.xhtml?faces-redirect=true";
    }
    /*
    public String createUser() {
        HandleUser hu = new HandleUser();
        message = hu.createUser(firstname, lastname, username, password, id);
        return "index.xhtml";
    }*/
}
