/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.fon.ai.zajednicki.communication;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Response implements Serializable {
    
    private Object result;

    public Response() {
    }

    public Response(Object response) {
        this.result = response;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
    
    
    
}
