/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.action;

import co.com.codesoftware.facturacion.entity.ClienteEntity;
import co.com.codesoftware.usuario.entity.Usuario;
import co.com.codesoftware.facturacion.logica.ClienteLogica;
import co.com.codesoftware.parametros.Parametro;
import co.com.codesoftware.utilidades.ParametrosActivos;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 * Clase encargada de realizar las acciones relacionadas con el cliente
 *
 * @author Personal
 */
public class ClienteAction extends ActionSupport implements SessionAware {

    private Usuario usuario;
    private Map session;
    private ClienteEntity cliente;
    private String accion;
    private String existeCliente;
    private Parametro parametros;

    public String execute() {
        return "facturador";
    }

    /**
     * Funcion encargada de realizar la accion de consultar si un cliente existe
     * en la base de datos
     *
     * @return
     */
    public String consultaClienteIniFactura() {
        ClienteLogica logicaCliente = null;
        try {
            logicaCliente = new ClienteLogica();
            String cedulaAux = cliente.getClien_cedula();
            cliente = logicaCliente.obtenerclienteXCeduala(cliente);
            if (cliente == null) {
                addActionError("Cliente inexistente en el sistema. Por favor ingrese los siguientes datos basicos del cliente.");
                cliente = new ClienteEntity();
                cliente.setClien_cedula(cedulaAux);
                existeCliente = "N";
                return "inexistente";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * Funcion encargada de realizar la accion de insertar un cliente
     *
     * @return
     */
    public String insertarCliente() {
        ClienteLogica logicaCliente = null;
        try{
            logicaCliente = new ClienteLogica();
            String rta = logicaCliente.insertaCliente(cliente);
            if (!rta.equalsIgnoreCase("Ok")) {
                addActionError("Error al insertar el Cliente intente de nuevo");
                return "error";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }
    
    public void obtieneObjParametros() {
        try {
            parametros = (Parametro) session.get("parametros");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void validate(){
        obtieneObjParametros();
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

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getExisteCliente() {
        return existeCliente;
    }

    public void setExisteCliente(String existeCliente) {
        this.existeCliente = existeCliente;
    }

    public Parametro getParametros() {
        return parametros;
    }

    public void setParametros(Parametro parametros) {
        this.parametros = parametros;
    }
}
