/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.sede.action;

import co.com.codesoftware.parametros.Parametro;
import co.com.codesoftware.sede.logica.SedeLogica;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author nicolas
 */
public class SedeAction extends ActionSupport implements SessionAware{

    private Map sedes;
    private String sede;
    private String accion;
    private String bandera;
    private Map session;
    private Parametro parametro;

    /**
     * Funcion encargada de realizar
     *
     * @return
     */
    public String obtieneSedeFacturacion() {
        String sedeNombre = "";
        try {
            sedeNombre = (String) sedes.get(sede);
            if(sedeNombre == null){
                addActionError("Sede inexistente o inactiva por favor intente de nuevo");
                return INPUT;
            }else{
                this.parametro = new Parametro();
                parametro.setSede(sede);
                parametro.setSedeNombre(sedeNombre);
                Map session = ActionContext.getContext().getSession();
                session.put("parametros", parametro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion para obtener las sedes activas
     * del sistema
     *
     * @return
     */
    public String obtieneSedes() {
        SedeLogica logica = null;
        try {
            logica = new SedeLogica();
            this.sedes = logica.buscaSedesActivasMapa();
            if(sedes == null){
                bandera = "N";
                addActionError("No existen sedes activas en el sistema o ha sido imposible la conexion con la base de datos por favor contactese con el administrador");
            }else{
                bandera = "S";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public void validate() {
        if ("obtieneSedes".equalsIgnoreCase(accion)) {
        }
        if("escojeSede".equalsIgnoreCase(accion)){
            SedeLogica logica = new SedeLogica();
            this.sedes = logica.buscaSedesActivasMapa();
            if("-1".equalsIgnoreCase(sede)){
                addActionError("Por Favor Seleccione una sede");
            }
        }
    }

    public Map getSedes() {
        return sedes;
    }

    public void setSedes(Map sedes) {
        this.sedes = sedes;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public Parametro getParametro() {
        return parametro;
    }

    public void setParametro(Parametro parametro) {
        this.parametro = parametro;
    }
    
}
