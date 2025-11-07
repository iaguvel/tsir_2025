package com.mycompany.sportstrack;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "facturaBean")
@SessionScoped
public class FacturaBean implements Serializable {

    private String nombre;
    private String apellidos;
    private String tarjeta;
    private String fechaCaducidad;
    private double importe;
    private String moneda;
    
    public FacturaBean() {
        this.moneda = "€";
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getTarjeta() { return tarjeta; }
    public void setTarjeta(String tarjeta) { this.tarjeta = tarjeta; }

    public String getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(String fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }

    public double getImporte() { return importe; }
    public void setImporte(double importe) { this.importe = importe; }

    public String getMoneda() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        return locale.getLanguage().equals("en") ? "$" : "€";
    }

    public String getImporteFormateado() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(FacesContext.getCurrentInstance().getViewRoot().getLocale());
        return nf.format(importe);
    }

    public String confirmarCompra() {
        // Validación básica de tarjeta y caducidad
        if (tarjeta == null || tarjeta.length() != 16) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Número de tarjeta inválido", ""));
            return null;
        }

        if (fechaCaducidad == null || !fechaCaducidad.matches("(0[1-9]|1[0-2])/\\d{2}")) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fecha de caducidad inválida (MM/AA)", ""));
            return null;
        }

        // Compra simulada
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra realizada correctamente ✅", ""));
        
        // Logout después de comprar
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "bienvenida.xhtml?faces-redirect=true";
    }
}