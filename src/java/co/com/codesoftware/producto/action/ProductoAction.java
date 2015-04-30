/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.producto.action;

import co.com.codesoftware.inventario.logica.CategoriaLogica;
import co.com.codesoftware.inventario.logica.ReferenciaLogica;
import co.com.codesoftware.parametros.Parametro;
import co.com.codesoftware.usuario.entity.Usuario;
import com.opensymphony.xwork2.ActionSupport;
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

    public void validate() {
        obtieneObjParametros();
        if ("consultaGeneralProductos".equalsIgnoreCase(accion)) {
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
}
