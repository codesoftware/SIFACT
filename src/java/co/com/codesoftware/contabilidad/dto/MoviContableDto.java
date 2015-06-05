/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.contabilidad.dto;

/**
 *
 * @author Nicolas
 */
public class MoviContableDto {

    private String mvco_mvco;
    private String mvco_trans;
    private String mvco_sbcu;
    private String mvco_naturaleza;
    private String mvco_tido;
    private String mvco_valor;
    private String mvco_lladetalle;
    private String mvco_id_llave;
    private String mvco_tercero;
    private String mvco_tipo;
    private String mvco_fecha;
    //Variables necesarias para crear los asientos contables
    private String debitos;
    private String creditos;
    private String sbcu_nombre;
    private String sbcu_codigo;
    private String clas_clas;
    private String grup_grup;
    private String cuen_cuen;
    //Variables para la consulta
    private String clas_nombre;
    private String grup_nombre;
    private String cuen_nombre;
    private String fechaIni;
    private String fechaFin;

    public String getMvco_mvco() {
        return mvco_mvco;
    }

    public String getClas_clas() {
        return clas_clas;
    }

    public void setClas_clas(String clas_clas) {
        this.clas_clas = clas_clas;
    }

    public String getGrup_grup() {
        return grup_grup;
    }

    public void setGrup_grup(String grup_grup) {
        this.grup_grup = grup_grup;
    }

    public String getCuen_cuen() {
        return cuen_cuen;
    }

    public void setCuen_cuen(String cuen_cuen) {
        this.cuen_cuen = cuen_cuen;
    }

    public void setMvco_mvco(String mvco_mvco) {
        this.mvco_mvco = mvco_mvco;
    }

    public String getMvco_trans() {
        return mvco_trans;
    }

    public void setMvco_trans(String mvco_trans) {
        this.mvco_trans = mvco_trans;
    }

    public String getMvco_sbcu() {
        return mvco_sbcu;
    }

    public void setMvco_sbcu(String mvco_sbcu) {
        this.mvco_sbcu = mvco_sbcu;
    }

    public String getMvco_naturaleza() {
        return mvco_naturaleza;
    }

    public void setMvco_naturaleza(String mvco_naturaleza) {
        this.mvco_naturaleza = mvco_naturaleza;
    }

    public String getMvco_tido() {
        return mvco_tido;
    }

    public void setMvco_tido(String mvco_tido) {
        this.mvco_tido = mvco_tido;
    }

    public String getMvco_valor() {
        return mvco_valor;
    }

    public void setMvco_valor(String mvco_valor) {
        this.mvco_valor = mvco_valor;
    }

    public String getMvco_lladetalle() {
        return mvco_lladetalle;
    }

    public void setMvco_lladetalle(String mvco_lladetalle) {
        this.mvco_lladetalle = mvco_lladetalle;
    }

    public String getMvco_id_llave() {
        return mvco_id_llave;
    }

    public void setMvco_id_llave(String mvco_id_llave) {
        this.mvco_id_llave = mvco_id_llave;
    }

    public String getMvco_tercero() {
        return mvco_tercero;
    }

    public void setMvco_tercero(String mvco_tercero) {
        this.mvco_tercero = mvco_tercero;
    }

    public String getMvco_tipo() {
        return mvco_tipo;
    }

    public void setMvco_tipo(String mvco_tipo) {
        this.mvco_tipo = mvco_tipo;
    }

    public String getDebitos() {
        return debitos;
    }

    public void setDebitos(String debitos) {
        this.debitos = debitos;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public String getSbcu_nombre() {
        return sbcu_nombre;
    }

    public void setSbcu_nombre(String sbcu_nombre) {
        this.sbcu_nombre = sbcu_nombre;
    }

    public String getSbcu_codigo() {
        return sbcu_codigo;
    }

    public void setSbcu_codigo(String sbcu_codigo) {
        this.sbcu_codigo = sbcu_codigo;
    }

    public String getClas_nombre() {
        return clas_nombre;
    }

    public void setClas_nombre(String clas_nombre) {
        this.clas_nombre = clas_nombre;
    }

    public String getGrup_nombre() {
        return grup_nombre;
    }

    public void setGrup_nombre(String grup_nombre) {
        this.grup_nombre = grup_nombre;
    }

    public String getCuen_nombre() {
        return cuen_nombre;
    }

    public void setCuen_nombre(String cuen_nombre) {
        this.cuen_nombre = cuen_nombre;
    }

    public String getMvco_fecha() {
        return mvco_fecha;
    }

    public void setMvco_fecha(String mvco_fecha) {
        this.mvco_fecha = mvco_fecha;
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

}
