/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.facturacion.dao;

import co.com.codesoftware.facturacion.entity.FacturaEntity;

/**
 *
 * @author nicolas
 */
public class FacturaDao {
    
    public String consultaFacturasXFiltro(FacturaEntity objEntity){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT fact_fact, fact_tius, fact_fec_ini, fact_fec_cierre, fact_clien, ");
        sql.append("to_char(fact_vlr_total,'9,999,999,999.00') fact_vlr_total, to_char(fact_vlr_iva,'9,999,999,999.00') fact_vlr_iva, fact_tipo_pago, fact_id_voucher, ");
        sql.append("fact_cometarios, fact_estado, fact_naturaleza, fact_devolucion, ");
        sql.append("fact_original, clien_cedula, clien_apellidos || ' ' || clien_nombres nombres, ");
        sql.append("to_char(((fact_vlr_total + fact_vlr_iva)-fact_vlr_dcto ),'9,999,999,999.00') pagoTotal, ");
        sql.append(" to_char(fact_vlr_dcto,'9,999,999,999.00') descuento ");
        sql.append(" FROM fa_tfact, us_tclien ");
        sql.append(" WHERE fact_clien = clien_clien ");
        return sql.toString();
    }
}
