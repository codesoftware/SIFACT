/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.action;

import co.com.codesoftware.facturacion.entity.FacturaEntity;
import co.com.codesoftware.facturacion.logica.FacturacionLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Personal
 */
public class GenerarFacturaAction extends ActionSupport implements SessionAware {

    private Map session;
    private String nombreJasper;
    private InputStream fileInputStream;
    private long contentLength;
    private String contentName;
    private FacturaEntity factura;

    public String generarFactura() {
        HttpServletRequest request = ServletActionContext.getRequest();
        File reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/REPORTES/FUENTES/" + nombreJasper));
        File reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/REPORTES/factura_" + factura.getFact_fact() + ".pdf"));
        try {
            String path = reporte.getPath();
            FacturacionLogica logica = new FacturacionLogica();
             String rta = logica.generarFactura(factura.getFact_fact(), path,reporteDestino.getPath());
             if(rta.equalsIgnoreCase("Ok")){
                fileInputStream = new FileInputStream(reporteDestino);
                this.contentLength = reporteDestino.length();
                this.contentName = "factura_"+factura.getFact_fact()+".pdf";
            }else{
                addActionError("Error al generar factura " + factura.getFact_fact() +" \n" + rta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return SUCCESS;
    }

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getNombreJasper() {
        return nombreJasper;
    }

    public void setNombreJasper(String nombreJasper) {
        this.nombreJasper = nombreJasper;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

}
