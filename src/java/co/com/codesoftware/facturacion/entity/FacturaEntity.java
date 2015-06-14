/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.entity;

/**
 *
 * @author Personal
 */
public class FacturaEntity {

    private String fact_fact;
    private String fact_tius;
    private String fact_fec_ini;
    private String fact_fec_cierre;
    private String fact_clien;
    private String fact_vlr_total;
    private String fact_vlr_iva;
    private String fact_tipo_pago;
    private String fact_id_voucher;
    private String fact_cometarios;
    private String fact_estado;
    private String fact_naturaleza;
    private String fact_devolucion;
    private String fact_original;
    private String pagoTotal;
    private String descuento;
    //Datos del cliente de la factura
    private String clien_nombres;
    private String clien_cedula;
    //Fechas para realizar el filtro
    private String fechaIni;
    private String fechaFin;

    public String getFact_fact() {
        return fact_fact;
    }

    public void setFact_fact(String fact_fact) {
        this.fact_fact = fact_fact;
    }

    public String getFact_tius() {
        return fact_tius;
    }

    public void setFact_tius(String fact_tius) {
        this.fact_tius = fact_tius;
    }

    public String getFact_fec_ini() {
        return fact_fec_ini;
    }

    public void setFact_fec_ini(String fact_fec_ini) {
        this.fact_fec_ini = fact_fec_ini;
    }

    public String getFact_fec_cierre() {
        return fact_fec_cierre;
    }

    public void setFact_fec_cierre(String fact_fec_cierre) {
        this.fact_fec_cierre = fact_fec_cierre;
    }

    public String getFact_clien() {
        return fact_clien;
    }

    public void setFact_clien(String fact_clien) {
        this.fact_clien = fact_clien;
    }

    public String getFact_vlr_total() {
        return fact_vlr_total;
    }

    public void setFact_vlr_total(String fact_vlr_total) {
        this.fact_vlr_total = fact_vlr_total;
    }

    public String getFact_vlr_iva() {
        return fact_vlr_iva;
    }

    public void setFact_vlr_iva(String fact_vlr_iva) {
        this.fact_vlr_iva = fact_vlr_iva;
    }

    public String getFact_tipo_pago() {
        return fact_tipo_pago;
    }

    public void setFact_tipo_pago(String fact_tipo_pago) {
        this.fact_tipo_pago = fact_tipo_pago;
    }

    public String getFact_id_voucher() {
        return fact_id_voucher;
    }

    public void setFact_id_voucher(String fact_id_voucher) {
        this.fact_id_voucher = fact_id_voucher;
    }

    public String getFact_cometarios() {
        return fact_cometarios;
    }

    public void setFact_cometarios(String fact_cometarios) {
        this.fact_cometarios = fact_cometarios;
    }

    public String getFact_estado() {
        return fact_estado;
    }

    public void setFact_estado(String fact_estado) {
        this.fact_estado = fact_estado;
    }

    public String getFact_naturaleza() {
        return fact_naturaleza;
    }

    public void setFact_naturaleza(String fact_naturaleza) {
        this.fact_naturaleza = fact_naturaleza;
    }

    public String getFact_devolucion() {
        return fact_devolucion;
    }

    public void setFact_devolucion(String fact_devolucion) {
        this.fact_devolucion = fact_devolucion;
    }

    public String getFact_original() {
        return fact_original;
    }

    public void setFact_original(String fact_original) {
        this.fact_original = fact_original;
    }

    public String getClien_nombres() {
        return clien_nombres;
    }

    public void setClien_nombres(String clien_nombres) {
        this.clien_nombres = clien_nombres;
    }

    public String getClien_cedula() {
        return clien_cedula;
    }

    public void setClien_cedula(String clien_cedula) {
        this.clien_cedula = clien_cedula;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getPagoTotal() {
        return pagoTotal;
    }

    public void setPagoTotal(String pagoTotal) {
        this.pagoTotal = pagoTotal;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

}
