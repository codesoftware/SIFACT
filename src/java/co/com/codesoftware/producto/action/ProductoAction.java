/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.producto.action;

import co.com.codesoftware.inventario.logica.CategoriaLogica;
import co.com.codesoftware.inventario.logica.ReferenciaLogica;
import co.com.codesoftware.parametros.Parametro;
import co.com.codesoftware.producto.entity.Producto;
import co.com.codesoftware.producto.logica.ProductoLogica;
import co.com.codesoftware.usuario.entity.Usuario;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class ProductoAction extends ActionSupport implements SessionAware {

    private Usuario usuario;
    private Map session;
    private Map sedes;
    private Map referecias;
    private Map categorias;
    private Parametro parametros;
    private String accion;
    private List productos;
    private Producto producto;

    /**
     * Funcion encargada de realizar el reenvio y recargar las listas necesarias
     * para la consulta de productos antes de facturar
     *
     * @return
     */
    public String redireccionConsultaProductos() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de consultar los productos por
     * medio de determinados filtros
     *
     * @return
     */
    public String ejecutaConsultaProd() {
        ProductoLogica logica = new ProductoLogica();
        try {
            productos = logica.obtieneProductosXFiltros(producto, parametros.getSede());
            if(productos == null){
                addActionError("La consulta no arrojo ningun resultado");
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public void validate() {
        obtieneObjParametros();
        if ("consultaGeneralProductos".equalsIgnoreCase(accion)) {
            ReferenciaLogica logicaRef = new ReferenciaLogica();
            CategoriaLogica logicaCat = new CategoriaLogica();

            this.referecias = logicaRef.obtieneIdDescrReferenciaActivos();
            this.categorias = logicaCat.obtieneCategoriasActivasMap();
        }else if("ejecutaConsulta".equalsIgnoreCase(accion)){
            ReferenciaLogica logicaRef = new ReferenciaLogica();
            CategoriaLogica logicaCat = new CategoriaLogica();

            this.referecias = logicaRef.obtieneIdDescrReferenciaActivos();
            this.categorias = logicaCat.obtieneCategoriasActivasMap();
        }
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSedes() {
        return sedes;
    }

    public void setSedes(Map sedes) {
        this.sedes = sedes;
    }

    public Map getReferecias() {
        return referecias;
    }

    public void setReferecias(Map referecias) {
        this.referecias = referecias;
    }

    public Map getCategorias() {
        return categorias;
    }

    public void setCategorias(Map categorias) {
        this.categorias = categorias;
    }

    public Parametro getParametros() {
        return parametros;
    }

    public void setParametros(Parametro parametros) {
        this.parametros = parametros;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public void obtieneObjParametros() {
        try {
            parametros = (Parametro) session.get("parametros");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getProductos() {
        return productos;
    }

    public void setProductos(List productos) {
        this.productos = productos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
