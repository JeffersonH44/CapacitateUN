/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Client;

import BusinessLogic.CourseManagement.CertificateGenerator;
import DataAccess.DAO.UserDAO.UserDAO;
import DataAccess.DAO.CourseDAO.CoursesDAO;
import DataAccess.Entity.Courses;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Bean encargado de la generación de certificados.
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
     * Función que genera los certificados a partir del un curso dado
     * @param course
     * @return Página donde se puede ver el certificado.
     * @throws DocumentException - Ver CertificateGenerator
     * @throws FileNotFoundException - Ver CertificateGenerator
     */
    public String generateCertification(Courses course) throws DocumentException, FileNotFoundException {
        CertificateGenerator cg = new CertificateGenerator();
        
        boolean saved = cg.createPDF(course);
        if(saved) {
            message = "El certificado se ha creado correctamente";
        } else {
            message = "El certificado no se ha podido crear";
        }
        
        return "getCertificate.xhtml?faces-redirect=true";
    }
}
