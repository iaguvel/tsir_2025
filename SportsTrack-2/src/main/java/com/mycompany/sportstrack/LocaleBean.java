package com.mycompany.sportstrack;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "localeBean")
@SessionScoped
public class LocaleBean implements Serializable {

    // NO inicializar aquí. 'locale' será null al principio.
    private Locale locale; 

    public Locale getLocale() {
        // --- ESTE ES EL CAMBIO ---
        // Si 'locale' es null, lo inicializamos de forma segura.
        if (locale == null) {
            // Esta es la forma segura de coger el idioma por defecto (del navegador)
            locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        }
        // --- FIN DEL CAMBIO ---
        
        return locale;
    }

    public String getLanguage() {
        // Llamamos a getLocale() (que ahora es seguro) en lugar de a la variable
        return getLocale().getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        // Esto está perfecto, actualiza la vista actual
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
