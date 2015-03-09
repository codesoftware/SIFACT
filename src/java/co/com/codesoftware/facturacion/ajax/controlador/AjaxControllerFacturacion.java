/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.ajax.controlador;

import co.com.codesoftware.facturacion.logica.RemisionLogica;
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
    private String dska_dska;
    private String refe_refe;
    private String rmce_rmce;
    private String cantidad;
    /**
     * Funcion encargada de obtener los datos de un producto
     */
    public void traeProducto() {
        String respuesta= "";
        ProductoLogica logica = new ProductoLogica();
        RemisionLogica logicaR = new RemisionLogica();
        try {            
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String []elementos = codigoBarras.split("-");
            if(elementos[0].equalsIgnoreCase("1")){
                respuesta=logica.buscaProductoXCodigoBarras(codigoBarras);
            }else{
                respuesta = logicaR.consultaRemisionXId(codigoBarras);
            }
            out.print(respuesta);           
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void obtieneDatosFact(){
        ProductoLogica logica = new ProductoLogica();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = logica.adicionProdFactura(dska_dska, cantidad);
            out.print(objJson);
                        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void obtieneDatosFactRem(){
        RemisionLogica logica = new RemisionLogica();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = logica.insertaRemision(rmce_rmce);
            out.print(objJson);
                        
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

    public String getDska_dska() {
        return dska_dska;
    }

    public void setDska_dska(String dska_dska) {
        this.dska_dska = dska_dska;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getRefe_refe() {
        return refe_refe;
    }

    public void setRefe_refe(String refe_refe) {
        this.refe_refe = refe_refe;
    }

    public String getRmce_rmce() {
        return rmce_rmce;
    }

    public void setRmce_rmce(String rmce_rmce) {
        this.rmce_rmce = rmce_rmce;
    }
}
