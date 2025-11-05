/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.sportstrack;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author iaguvel
 */
@Named
@RequestScoped
public class LogoutBean {
    public String logout() throws IOException {
        // Cierra la sesión del usuario
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();

        // Redirige a la página de bienvenida
        return "/bienvenida.xhtml?faces-redirect=true";
    }
}
