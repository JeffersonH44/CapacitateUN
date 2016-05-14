/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.WebService;

import java.util.Date;

/**
 *
 * @author arqsoft
 */
public class EventInfo {
    private String Name;
    private String trainer;
    private int participants;
    private Date date;

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the trainer
     */
    public String getTrainer() {
        return trainer;
    }

    /**
     * @param trainer the trainer to set
     */
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    /**
     * @return the participants
     */
    public int getParticipants() {
        return participants;
    }

    /**
     * @param participants the participants to set
     */
    public void setParticipants(int participants) {
        this.participants = participants;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

   
    
}
