/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.producto.action;

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
    /**
     * Funcion encargada de realizar el reenvio y recargar las listas necesarias
     * para la consulta de productos antes de facturar
     *
     * @return
     */
    public String redireccionConsultaProductos() {
        try {
            
        }catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public void validate() {

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
}
