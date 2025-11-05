package com.mycompany.sportstrack;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "logoutBean")
@RequestScoped
public class LogoutBean {

    public String logout() throws IOException {
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();
        return "/bienvenida.xhtml?faces-redirect=true";
    }
}
