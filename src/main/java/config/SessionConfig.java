/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author a.podgoreanu
 */
public class SessionConfig implements HttpSessionListener {
 
    private int totalSession = 0;
    
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        totalSession++;
        System.out.format("==== Session %s was created. %s active session ====",event.getSession().getId(),totalSession);
        //Durata della sessione
        event.getSession().setMaxInactiveInterval(60*60);
    }
 
    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        totalSession--;
        System.out.format("==== Session %s was destroyed. %s active session ====",event.getSession().getId(), totalSession);
    }

    public int getTotalSession() {
        return totalSession;
    }

    public void setTotalSession(int totalSession) {
        this.totalSession = totalSession;
    }
    
}
