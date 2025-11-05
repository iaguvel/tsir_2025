package com.mycompany.sportstrack;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "deporteBean")
@SessionScoped
public class DeporteBean implements Serializable {

    private String deporteSeleccionado;
    private List<Map<String, String>> resumen = new ArrayList<>();

    public List<Map<String, String>> getDeportes() {
        List<Map<String, String>> lista = new ArrayList<>();

        lista.add(crearDeporte("deporte.running", "running"));
        lista.add(crearDeporte("deporte.ciclismo", "ciclismo"));
        lista.add(crearDeporte("deporte.natacion", "natacion"));
        lista.add(crearDeporte("deporte.senderismo", "senderismo"));

        return lista;
    }

    private Map<String, String> crearDeporte(String clave, String imagen) {
        Map<String, String> dep = new HashMap<>();
        dep.put("clave", clave);
        dep.put("imagen", imagen);
        return dep;
    }

    public String getDeporteSeleccionado() {
        return deporteSeleccionado;
    }

    public void setDeporteSeleccionado(String deporteSeleccionado) {
        this.deporteSeleccionado = deporteSeleccionado;
    }

    public String irADetalle() {
        return "detalle.xhtml?faces-redirect=true";
    }

    public String irAResumen() {
        return "resumen.xhtml?faces-redirect=true";
    }

    public String volver() {
        return "deportes.xhtml?faces-redirect=true";
    }

    public List<Map<String, String>> getActividades() {
        List<Map<String, String>> actividades = new ArrayList<>();

        if ("deporte.running".equals(deporteSeleccionado)) {
            actividades.add(crearActividad("5 km tempo run", 5, 25, 300));
            actividades.add(crearActividad("Intervalos 10x400m", 4, 20, 250));
        } else if ("deporte.ciclismo".equals(deporteSeleccionado)) {
            actividades.add(crearActividad("Ruta montaña", 30, 90, 800));
            actividades.add(crearActividad("Rodillo indoor", 20, 60, 500));
        } else if ("deporte.natacion".equals(deporteSeleccionado)) {
            actividades.add(crearActividad("Técnica 1000 m", 1, 30, 250));
            actividades.add(crearActividad("Aguas abiertas", 1.5, 40, 320));
        } else if ("deporte.senderismo".equals(deporteSeleccionado)) {
            actividades.add(crearActividad("Ruta corta", 5, 60, 400));
            actividades.add(crearActividad("Ruta larga", 10, 120, 800));
        }

        return actividades;
    }

    private Map<String, String> crearActividad(String nombre, double distancia, int duracion, int calorias) {
        Map<String, String> act = new HashMap<>();
        act.put("nombre", nombre);
        act.put("distancia", String.valueOf(distancia));
        act.put("duracion", String.valueOf(duracion));
        act.put("calorias", String.valueOf(calorias));
        return act;
    }

    public void agregarActividad(Map<String, String> act) {
        resumen.add(act);
    }

    public List<Map<String, String>> getResumen() {
        return resumen;
    }

    public double getTotalDistancia() {
        return resumen.stream().mapToDouble(a -> Double.parseDouble(a.get("distancia"))).sum();
    }

    public int getTotalDuracion() {
        return resumen.stream().mapToInt(a -> Integer.parseInt(a.get("duracion"))).sum();
    }

    public int getTotalCalorias() {
        return resumen.stream().mapToInt(a -> Integer.parseInt(a.get("calorias"))).sum();
    }
}
