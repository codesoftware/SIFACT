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
public class RemisionEntity {
    
    private String rmce_rmce;
    private String rmce_refe;
    private String rmce_imei;
    private String rmce_iccid;
    private String rmce_valor;
    private String rmce_comision;
    private String rmce_tppl;
    private String rmce_fcve;
    private String rmce_fcsl;
    private String rmce_fcen;
    private String rmce_tius_ent;
    private String rmce_tius_sal;
    private String rmce_codigo;
    private String rmce_sede;
    private String rmce_estado;
    private String rmce_pagado;
    private String rmce_comdev;
    //Fechas necesarias para crear las sentencias con los beteewen
    private String valorBeteween;
    private String fechaBeteween;
    //
    private String filtros;  // Variable la cual me indicara si el dto es para mostrar al usuario o es para la base de datos
    //
    private String valorSf;

    public String getRmce_rmce() {
        return rmce_rmce;
    }

    public void setRmce_rmce(String rmce_rmce) {
        this.rmce_rmce = rmce_rmce;
    }

    public String getRmce_refe() {
        return rmce_refe;
    }

    public void setRmce_refe(String rmce_refe) {
        this.rmce_refe = rmce_refe;
    }

    public String getRmce_imei() {
        return rmce_imei;
    }

    public void setRmce_imei(String rmce_imei) {
        this.rmce_imei = rmce_imei;
    }

    public String getRmce_iccid() {
        return rmce_iccid;
    }

    public void setRmce_iccid(String rmce_iccid) {
        this.rmce_iccid = rmce_iccid;
    }

    public String getRmce_valor() {

        return rmce_valor;
    }

    public void setRmce_valor(String rmce_valor) {
        this.rmce_valor = rmce_valor;
    }

    public String getRmce_comision() {
        return rmce_comision;
    }

    public void setRmce_comision(String rmce_comision) {
        this.rmce_comision = rmce_comision;
    }

    public String getRmce_tppl() {
        if (this.filtros.equalsIgnoreCase("S")) {
            if (rmce_tppl.equalsIgnoreCase("ps")) {
                return "POSTPAGO";
            } else if (rmce_tppl.equalsIgnoreCase("pr")) {
                return "PREPAGO";
            }
        }
        return rmce_tppl;
    }

    public void setRmce_tppl(String rmce_tppl) {
        this.rmce_tppl = rmce_tppl;
    }

    public String getRmce_fcve() {
        return rmce_fcve;
    }

    public void setRmce_fcve(String rmce_fcve) {
        this.rmce_fcve = rmce_fcve;
    }

    public String getRmce_fcsl() {
        return rmce_fcsl;
    }

    public void setRmce_fcsl(String rmce_fcsl) {
        this.rmce_fcsl = rmce_fcsl;
    }

    public String getRmce_fcen() {
        return rmce_fcen;
    }

    public void setRmce_fcen(String rmce_fcen) {
        this.rmce_fcen = rmce_fcen;
    }

    public String getRmce_tius_ent() {
        return rmce_tius_ent;
    }

    public void setRmce_tius_ent(String rmce_tius_ent) {
        this.rmce_tius_ent = rmce_tius_ent;
    }

    public String getRmce_tius_sal() {
        return rmce_tius_sal;
    }

    public void setRmce_tius_sal(String rmce_tius_sal) {
        this.rmce_tius_sal = rmce_tius_sal;
    }

    public String getRmce_codigo() {
        return rmce_codigo;
    }

    public void setRmce_codigo(String rmce_codigo) {
        this.rmce_codigo = rmce_codigo;
    }

    public String getRmce_sede() {
        return rmce_sede;
    }

    public void setRmce_sede(String rmce_sede) {
        this.rmce_sede = rmce_sede;
    }

    public String getRmce_estado() {
        if (this.filtros.equalsIgnoreCase("S")) {
            if (rmce_estado.equalsIgnoreCase("E")) {
                return "STAND";
            } else if (rmce_estado.equalsIgnoreCase("V")) {
                return "VENDIDO";
            } else if (rmce_estado.equalsIgnoreCase("D")) {
                return "DEVUELTO";
            }
        }
        return rmce_estado;
    }

    public void setRmce_estado(String rmce_estado) {
        this.rmce_estado = rmce_estado;
    }

    public String getRmce_pagado() {
        return rmce_pagado;
    }

    public void setRmce_pagado(String rmce_pagado) {
        this.rmce_pagado = rmce_pagado;
    }

    public String getRmce_comdev() {
        return rmce_comdev;
    }

    public void setRmce_comdev(String rmce_comdev) {
        this.rmce_comdev = rmce_comdev;
    }

    public String getValorBeteween() {
        return valorBeteween;
    }

    public void setValorBeteween(String valorBeteween) {
        this.valorBeteween = valorBeteween;
    }

    public String getFechaBeteween() {
        return fechaBeteween;
    }

    public void setFechaBeteween(String fechaBeteween) {
        this.fechaBeteween = fechaBeteween;
    }

    public String getFiltros() {
        return filtros;
    }

    public void setFiltros(String filtros) {
        this.filtros = filtros;
    }

    public String getValorSf() {
        return valorSf;
    }

    public void setValorSf(String valorSf) {
        this.valorSf = valorSf;
    }
    
}
