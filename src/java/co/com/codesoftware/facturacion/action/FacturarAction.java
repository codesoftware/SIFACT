/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.action;

import co.com.codesoftware.facturacion.entity.ClienteEntity;
import co.com.codesoftware.facturacion.logica.FacturacionLogica;
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
        FacturacionLogica logica = new FacturacionLogica();
        try {
            String idTrans = logica.obtieneValorSecuenciaTemp();
            if(idTrans != null){
                String rtaTemp = logica.insertarTemporalProductos(prodFact, idTrans);
                if("Ok".equalsIgnoreCase(rtaTemp)){
                    //Aqui hago la facturacion
                }else{
                    addActionError("Error al generar la factura");
                }
                logica.borrarTemporalXidTransaccion(idTrans);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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
