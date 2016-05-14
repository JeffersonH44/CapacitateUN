/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.WebService;

/**
 *
 * @author arqsoft2016
 */
public class ROB {
    private boolean success;
    private String err_message;
    private EventInfo data;
    
    public ROB(boolean success, String err_message, EventInfo data) {
        this.success = success;
        this.err_message = err_message;
        this.data = data;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the err_message
     */
    public String getErr_message() {
        return err_message;
    }

    /**
     * @param err_message the err_message to set
     */
    public void setErr_message(String err_message) {
        this.err_message = err_message;
    }

    /**
     * @return the data
     */
    public EventInfo getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(EventInfo data) {
        this.data = data;
    }
    
    
}
