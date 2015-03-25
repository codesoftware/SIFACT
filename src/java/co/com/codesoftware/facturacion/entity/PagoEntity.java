/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.entity;

/**
 *
 * @author ACER
 */
public class PagoEntity {
    
    private String tipoPago;
    private String idVucher;

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getIdVucher() {
        return idVucher;
    }

    public void setIdVucher(String idVucher) {
        this.idVucher = idVucher;
    }
    
}
