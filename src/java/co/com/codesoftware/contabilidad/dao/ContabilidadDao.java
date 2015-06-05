/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.codesoftware.contabilidad.dao;

import co.com.codesoftware.facturacion.entity.ProductosFactEntitiy;

/**
 *
 * @author nicolas
 */
public class ContabilidadDao {
    /**
     * Funcion encargada de obtener el valor que 
     * @return 
     */
    public String obtieneDatoSecuenciaTempoSim(){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT nextval('co_temp_sim_movi_contables') secuencia ");
        return sql.toString();
    }
    /**
     * Funcion encargada de crear el insert para la tabla temporal de simulacion de facturacion 
     * @return 
     */
    public String insertTemoralProductos(ProductosFactEntitiy producto, String idTrans) {
        String sql = "";
        sql += "INSERT INTO co_ttem_sifc(                                  \n";
        sql += "            tem_sifc_trans, tem_sifc_dska, tem_sifc_cant, tem_sifc_dcto )  \n";
        sql += "    VALUES (" + idTrans + ", " + producto.getDska_dska()+ "," + producto.getDska_cant() + "," + producto.getDescuento() + ")\n";
        return sql;
    }
    
    /**
     * Funcion encargada de realizar el query para obtene el asiento contable de
     * una transaccion
     *
     * @param idTrans
     * @return
     */
    public String generaAsientoContable(String idTrans) {
        String sql = "";
        sql += "SELECT sbcu_nombre,sbcu_codigo,to_char(simc_valor,'9,999,999,999.00') debitos, 'N/A' creditos      \n";
        sql += "  FROM co_tsimc,co_tsbcu                                                                       \n";
        sql += " WHERE simc_trans = " + idTrans + "                                                            \n";
        sql += "   AND simc_naturaleza = 'D'                                                                   \n";
        sql += "   AND simc_sbcu = sbcu_sbcu                                                                   \n";
        sql += " UNION all                                                                                     \n";
        sql += "SELECT sbcu_nombre,sbcu_codigo, 'N/A' debitos, to_char(simc_valor,'9,999,999,999.00') creditos\n";
        sql += "  FROM co_tsimc,co_tsbcu                                                                       \n";
        sql += " WHERE simc_trans = " + idTrans+ "                                                   \n";
        sql += "   AND simc_naturaleza = 'C'                                                                   \n";
        sql += "   AND simc_sbcu = sbcu_sbcu                                                                   \n";
        return sql;
    }
    
}
