package com.mycompany.sportstrack;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.IOException;
import java.io.OutputStream;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.bean.ManagedProperty; // ¡Importante!

@ManagedBean(name = "pdfBean")
@RequestScoped
public class PdfBean {

    // Inyecta el bean de sesión (DeporteBean) para acceder a los datos
    @ManagedProperty(value = "#{deporteBean}")
    private DeporteBean deporteBean;

    // Método 'setter' obligatorio para la inyección
    public void setDeporteBean(DeporteBean deporteBean) {
        this.deporteBean = deporteBean;
    }

    public void generarResumenPDF() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();

        ec.responseReset(); 
        ec.setResponseContentType("application/pdf");
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"resumen_semanal.pdf\"");

        try (OutputStream output = ec.getResponseOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, output);
            document.open();
            
            // --- Contenido del PDF ---
            document.add(new Paragraph("Resumen Semanal - SportsTrack"));
            document.add(new Paragraph(" ")); // Espacio

            // Añadir las actividades del resumen
            for (java.util.Map<String, String> act : deporteBean.getResumen()) {
                document.add(new Paragraph(
                    "Actividad: " + act.get("nombre") + 
                    " | Distancia: " + act.get("distancia") + " km" +
                    " | Duracion: " + act.get("duracion") + " min" +
                    " | Calorias: " + act.get("calorias") + " kcal"
                ));
            }
            
            document.add(new Paragraph(" ")); // Espacio
            document.add(new Paragraph("--- Totales ---"));
            document.add(new Paragraph("Distancia Total: " + deporteBean.getTotalDistancia() + " km"));
            document.add(new Paragraph("Duracion Total: " + deporteBean.getTotalDuracion() + " min"));
            document.add(new Paragraph("Calorias Totales: " + deporteBean.getTotalCalorias() + " kcal"));
            // --- Fin del Contenido ---
            
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        fc.responseComplete(); // Indica a JSF que ya terminamos la respuesta
    }
}
