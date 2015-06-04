/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.ajax.controlador;

import co.com.codesoftware.contabilidad.logica.ContabilidadLogica;
import co.com.codesoftware.facturacion.entity.ProductosFactEntitiy;
import co.com.codesoftware.facturacion.logica.RemisionLogica;
import co.com.codesoftware.parametros.Parametro;
import co.com.codesoftware.producto.logica.ProductoLogica;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.opensymphony.xwork2.ActionSupport;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Personal
 */
public class AjaxControllerFacturacion extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = 1L;
    private String codigoBarras;
    private String dska_dska;
    private String refe_refe;
    private String rmce_rmce;
    private String cantidad;
    private String rmce_imei;
    private Map session;
    private Parametro parametros;
    private String descuento;
    private String productosArray;
    private String tipoPago;
    private String vlrTarjeta;

    /**
     * Funcion encargada de obtener los datos de un producto
     */
    public void traeProducto() {
        descuento = descuento.replaceAll("\\.", "");
        obtieneObjParametros();
        String respuesta;
        ProductoLogica logica = new ProductoLogica();
        RemisionLogica logicaR = new RemisionLogica();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String[] elementos = codigoBarras.split("-");
            if (elementos[0].equalsIgnoreCase("1")) {
                respuesta = logica.buscaProductoXCodigoBarras(codigoBarras, parametros.getSede(), descuento);
            } else {
                respuesta = logicaR.consultaRemisionXId(codigoBarras);
            }
            out.print(respuesta);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void traeProductoXid() {
        String respuesta = "";
        ProductoLogica logica = new ProductoLogica();
        obtieneObjParametros();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            respuesta = logica.buscaProdXIdProducto(dska_dska, parametros.getSede());
            out.print(respuesta);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obtieneDatosFact() {
        ProductoLogica logica = new ProductoLogica();
        obtieneObjParametros();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = logica.adicionProdFactura(dska_dska, cantidad, parametros.getSede(), descuento);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obtieneDatosFactRem() {
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

    /**
     * Funcion encargada de realizar la acciond buscar una remision por
     */
    public void buscaRemisionXImei() {
        RemisionLogica logica = new RemisionLogica();
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            PrintWriter out = response.getWriter();
            String objJson = logica.consultaRemisionXImei(rmce_imei);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obtieneObjParametros() {
        try {
            parametros = (Parametro) session.get("parametros");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void SimulaMoviContables() {
        try {
            this.obtieneObjParametros();
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/plain;charset=utf-8");
            HttpServletRequest request = ServletActionContext.getRequest();
            PrintWriter out = response.getWriter();
            String objJson = "";
            Map<String, Object> rta = new HashMap<String, Object>();
            if (this.parametros == null) {
                rta.put("respuesta", "error");
                rta.put("traza", "sede nula");
            } else {
                ArrayList<ProductosFactEntitiy> productos = generaListaProductos();
                if (productos.size() > 0) {
                    ContabilidadLogica logica = new ContabilidadLogica();
                    String valida = logica.generaSimulacionAsientoContable(productos, parametros.getSede(),tipoPago,vlrTarjeta);
                    if (!"Ok".equalsIgnoreCase(valida)) {
                        rta.put("respuesta", "error");
                        rta.put("traza", valida);
                    }
                }
            }
            Gson gson = new Gson();
            objJson = gson.toJson(rta);
            out.print(objJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ProductosFactEntitiy> generaListaProductos() {
        JsonElement json = new JsonParser().parse(this.productosArray);
        JsonArray array = json.getAsJsonArray();
        Iterator iterator = array.iterator();
        ArrayList<ProductosFactEntitiy> productosList = null;
        while (iterator.hasNext()) {
            if (productosList == null) {
                productosList = new ArrayList<>();
            }
            JsonElement json2 = (JsonElement) iterator.next();
            Gson gson = new Gson();
            ProductosFactEntitiy prod = gson.fromJson(json2, ProductosFactEntitiy.class);
            productosList.add(prod);
        }
        return productosList;
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

    public String getRmce_imei() {
        return rmce_imei;
    }

    public void setRmce_imei(String rmce_imei) {
        this.rmce_imei = rmce_imei;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public Parametro getParametros() {
        return parametros;
    }

    public void setParametros(Parametro parametros) {
        this.parametros = parametros;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getProductosArray() {
        return productosArray;
    }

    public void setProductosArray(String productosArray) {
        this.productosArray = productosArray;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getVlrTarjeta() {
        return vlrTarjeta;
    }

    public void setVlrTarjeta(String vlrTarjeta) {
        this.vlrTarjeta = vlrTarjeta;
    }

}
