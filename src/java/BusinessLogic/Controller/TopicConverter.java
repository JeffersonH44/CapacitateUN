/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Controller;

import DataAccess.DAO.CoursesDAO;
import DataAccess.DAO.TopicDAO;
import DataAccess.Entity.Courses;
import DataAccess.Entity.Topic;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author Jefferson
 */
@ManagedBean
@RequestScoped
public class TopicConverter implements Converter{
   
    @EJB
    TopicDAO topicDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value == null || value.isEmpty()) {
            return null;
        }
        
        try {
            return topicDAO.getById(Integer.valueOf(value));
        } catch(NumberFormatException e) {
            throw new ConverterException(new FacesMessage(String.format("%s no es un identificador válido", value)), e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }

        if (value instanceof Topic) {
            return String.valueOf(((Topic) value).getId());
        } else {
            throw new ConverterException(new FacesMessage(String.format("%s no es un curso válido", value)));
        }
    }
    
}
