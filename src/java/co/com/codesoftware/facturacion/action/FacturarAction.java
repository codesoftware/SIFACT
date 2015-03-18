/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.action;

import co.com.codesoftware.facturacion.entity.ClienteEntity;
import co.com.codesoftware.facturacion.logica.FacturacionLogica;
import co.com.codesoftware.usuario.logica.UsuarioLogica;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author ACER
 */
public class FacturarAction extends ActionSupport {

    private List remisionFact;
    private List prodFact;
    private ClienteEntity cliente;
    private String accion;
    private String usuario;
    private String fact_fact;

    public String facturar() {
        FacturacionLogica logica = new FacturacionLogica();
        try {
            String idTrans = logica.obtieneValorSecuenciaTemp();
            if (idTrans != null) {
                String rtaTemp = logica.insertarTemporalProductos(prodFact, idTrans);
                if ("Ok".equalsIgnoreCase(rtaTemp)) {
                    //Aqui hago la facturacion
                    String valida = logica.creaFacturacion(idTrans, usuario, cliente.getClien_clien());
                    String []facturo = valida.split("-");
                    if(!"Ok".equalsIgnoreCase(facturo[0])){
                        addActionError(valida);
                        return ERROR;
                    }else{
                        fact_fact = facturo[1];
                    }
                } else {
                    addActionError("Error al generar la factura");
                }
                logica.borrarTemporalXidTransaccion(idTrans);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    public void validate() {
        if ("facturar".equalsIgnoreCase(accion)) {
            UsuarioLogica usuarioLogica = new UsuarioLogica();
            String valida = usuarioLogica.validaSedeUsuaSedeFactura(usuario);
            if(!"Ok".equalsIgnoreCase(valida)){
                addActionError(valida);
            }
            usuarioLogica = null;
        }
    }

    public List getRemisionFact() {
        return remisionFact;
    }

    public void setRemisionFact(List remisionFact) {
        this.remisionFact = remisionFact;
    }

    public List getProdFact() {
        return prodFact;
    }

    public void setProdFact(List prodFact) {
        this.prodFact = prodFact;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFact_fact() {
        return fact_fact;
    }

    public void setFact_fact(String fact_fact) {
        this.fact_fact = fact_fact;
    }
}
