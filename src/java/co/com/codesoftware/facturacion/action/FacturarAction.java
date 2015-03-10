/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.action;

import co.com.codesoftware.facturacion.entity.ClienteEntity;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 *
 * @author ACER
 */
public class FacturarAction extends ActionSupport{
    
    private List remisionFact;
    private List prodFact;
    private ClienteEntity cliente;
    
    public String facturar(){
        return SUCCESS;        
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
}
