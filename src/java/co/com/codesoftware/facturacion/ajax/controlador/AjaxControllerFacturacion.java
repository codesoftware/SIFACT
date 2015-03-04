/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.ajax.controlador;

import co.com.codesoftware.producto.entity.Producto;
import co.com.codesoftware.producto.logica.ProductoLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Personal
 */
public class AjaxControllerFacturacion extends ActionSupport {

    private static final long serialVersionUID = 1L;
    private String codigoBarras;

    public void traeProducto() {
        String respuesta= "";
        ProductoLogica logica = new ProductoLogica();
        try {
            
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            respuesta=logica.buscaProductoXCodigoBarras(codigoBarras);
            out.print(respuesta);
           
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

}
