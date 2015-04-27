/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.action;

import co.com.codesoftware.facturacion.entity.FacturaEntity;
import co.com.codesoftware.facturacion.entity.RemisionEntity;
import co.com.codesoftware.facturacion.logica.FacturacionLogica;
import co.com.codesoftware.parametros.Parametro;
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
    private RemisionEntity remision;
    private Parametro parametros;
    /***
     * Funcion la cual genera la Factura (Pdf)
     * @return 
     */
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
    /**
     * Funcion encargada de realizar la accion de generar el pdf de Remision
     * @return 
     */
    public String generarRemision() {
        HttpServletRequest request = ServletActionContext.getRequest();
        File reporte = new File(request.getSession().getServletContext().getRealPath("/WEB-INF/REPORTES/FUENTES/" + nombreJasper));
        File reporteDestino = new File(request.getSession().getServletContext().getRealPath("/IMAGENES/REPORTES/remision_" + remision.getRmce_trans() + ".pdf"));
        try {
            String path = reporte.getPath();
            FacturacionLogica logica = new FacturacionLogica();
             String rta = logica.generarRemision(remision.getRmce_trans(), path,reporteDestino.getPath());
             if(rta.equalsIgnoreCase("Ok")){
                fileInputStream = new FileInputStream(reporteDestino);
                this.contentLength = reporteDestino.length();
                this.contentName = "remision_"+remision.getRmce_trans()+".pdf";
            }else{
                addActionError("Error al generar factura " + remision.getRmce_trans() +" \n" + rta);
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

    public RemisionEntity getRemision() {
        return remision;
    }

    public void setRemision(RemisionEntity remision) {
        this.remision = remision;
    }

    public Parametro getParametros() {
        return parametros;
    }

    public void setParametros(Parametro parametros) {
        this.parametros = parametros;
    }
}
