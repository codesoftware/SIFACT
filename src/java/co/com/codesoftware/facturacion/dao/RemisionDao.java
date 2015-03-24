/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.dao;

/**
 *
 * @author Nicolas
 */
public class RemisionDao {

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
    private String rmce_fact;

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

    public String getRmce_fact() {
        return rmce_fact;
    }

    public void setRmce_fact(String rmce_fact) {
        this.rmce_fact = rmce_fact;
    }

    /**
     * Funcion encargada de Realizar el Query para la cosulta de una remision en
     * especifico
     *
     * @return
     */
    public String consultaEspecificaXCodigo() {
        String select = "SELECT rmce_rmce, rmce_refe, rmce_imei, rmce_iccid, to_char(rmce_valor,'LFM9,999,999,999,999.00') rmce_valor , rmce_comision, ";
        select += "case when rmce_tppl='pr' then 'Prepago' else 'postpago' end rmce_tppl, rmce_fcve, rmce_fcsl, rmce_fcen, rmce_tius_ent, rmce_tius_sal,rmce_codigo, rmce_sede, rmce_estado, rmce_pagado, rmce_comdev FROM in_trmce where rmce_codigo='"
                .concat(this.getRmce_codigo())
                .concat("'");
        return select;
    }

    /**
     *
     * @return
     */
    public String consultaRemision() {
        String select = "";
        select += "SELECT  rmce_rmce,rmce_codigo,                                                                       \n";
        select += "        to_char(rmce_valor,'LFM9,999,999,999,999.00') rmce_valor ,                                   \n";
        select += "        cast(rmce_valor as int)    rmce_valorSf ,                                                    \n";
        select += "        case when rmce_tppl='pr' then 'Prepago' else 'postpago' end plan,                            \n";
        select += "        rmce_fcve,                                                                                   \n";
        select += "        to_char(cast(case                                                                            \n";
        select += "        when rmce_tppl='pr' then (select para_valor from em_tpara where para_clave = 'COMISIONPRE')  \n";
        select += "        when rmce_tppl='pr' then (select para_valor from em_tpara where para_clave = 'COMISIONPOST') \n";
        select += "        else (select para_valor from em_tpara where para_clave = 'COMISIONREP')                      \n";
        select += "        end as numeric),'LFM9,999,999,999,999.00') comision                                          \n";
        select += "  FROM in_trmce,in_trefe                                                                             \n";
        select += " WHERE rmce_estado = 'E'                                                                             \n";
        select += "   AND rmce_refe = refe_refe                                                                         \n";
        select += "   AND rmce_rmce = " + this.getRmce_rmce() + "";
        return select;
    }

    /**
     * Funcion encargada de realizar el query de busqueda para las remisiones
     * teniendo en cuenta el codigo de la remision
     *
     * @return
     */
    public String buscaRemisionXImei() {
        String sql = "";
        sql += "SELECT rmce_rmce     , rmce_refe     , rmce_imei     , rmce_iccid    , \n";
        sql += "to_char(rmce_valor,'LFM9,999,999,999,999.00') rmce_valor    , rmce_comision , rmce_tppl     , rmce_fcve     ,        \n";
        sql += "rmce_fcsl     , rmce_fcen     , rmce_tius_ent , rmce_tius_sal ,        \n";
        sql += "rmce_codigo   , rmce_sede     , rmce_estado   , rmce_pagado   ,        \n";
        sql += "rmce_comdev   , rmce_fact                                              \n";
        sql += "  FROM in_trmce                                                        \n";
        sql += " WHERE rmce_imei LIKE '%"+this.getRmce_imei()+"%'                      \n";
        return sql;
    }
}
