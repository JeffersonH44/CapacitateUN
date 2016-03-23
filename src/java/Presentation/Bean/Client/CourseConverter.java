/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation.Bean.Client;

import DataAccess.DAO.CourseDAO.CoursesDAO;
import DataAccess.Entity.Courses;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 * Clase encargada de mostrar los elementos de la lista de cursos en una lista
 * desplegable
 * @author Jefferson
 */
@ManagedBean
@RequestScoped
public class CourseConverter implements Converter{

    @EJB
    CoursesDAO courseDAO;

    /**
     * Método usado cuando se selecciona un elemento de la lista desplegable,
     * para de esta manera obtener el objeto seleccionado.
     * @param context
     * @param component
     * @param value
     * @return El objeto seleccionado
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.isEmpty()) {
            return null;
        }
        
        try {
            return courseDAO.getById(Integer.valueOf(value));
        } catch(NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s no es un identificador válido", value)), e);
        }
    }

    /**
     * Convierte los elementos de la lista en Cadenas para mostrar en la lista
     * desplegable
     * @param context
     * @param component
     * @param value
     * @return Cadena que representa el objeto.
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof Courses) {
            return String.valueOf(((Courses) value).getId());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s no es un curso válido", value)));
        }
    }
    
}
